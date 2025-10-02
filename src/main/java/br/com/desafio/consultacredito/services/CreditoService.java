package br.com.desafio.consultacredito.services;

import br.com.desafio.consultacredito.exceptions.NotFoundBusinessException;
import br.com.desafio.consultacredito.messaging.CreditoKafkaProducer;
import br.com.desafio.consultacredito.model.Credito;
import br.com.desafio.consultacredito.repositories.CreditoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final CreditoRepository creditoRepository;
    private final CreditoKafkaProducer creditoKafkaProducer;

    @Transactional
    public List<Credito> buscarPorNumeroNfse(String numeroNfse) {
        creditoKafkaProducer.enviarEventoConsultaNumeroNfse(numeroNfse);
        return  creditoRepository.findByNumeroNfse(numeroNfse);
    }

    @Transactional
    public Credito buscarPorNumeroCredito(String numeroCredito) {
        creditoKafkaProducer.enviarEventoConsultaNumeroCredito(numeroCredito);
        Optional<Credito> result = creditoRepository.findByNumeroCredito(numeroCredito);
        if(result.isEmpty()) {
            throw new NotFoundBusinessException("Nenhum crédito encontrado para o Número do Crédito: " + numeroCredito);
        }
        return result.orElse(null);
    }
}
