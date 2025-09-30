package br.com.desafio.consultacredito.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreditoKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPICO_CONSULTA_NUMERO_NFSE = "consulta.numeronfse";
    private static final String TOPICO_CONSULTA_NUMERO_CREDITO = "consulta.numerocredito";

    public void enviarEventoConsultaNumeroNfse(String numeroNfse) {
        this.enviarEventoConsulta(numeroNfse, "numeroNfse", TOPICO_CONSULTA_NUMERO_NFSE);
    }

    public void enviarEventoConsultaNumeroCredito(String numeroCredito) {
        this.enviarEventoConsulta(numeroCredito, "numeroCredito", TOPICO_CONSULTA_NUMERO_CREDITO);
    }

    private void enviarEventoConsulta(String consulta, String chaveEvento, String topico) {
        try {
            String message = objectMapper.writeValueAsString(Map.of(chaveEvento, consulta));
            kafkaTemplate.send(topico, message);
            log.info("Evento '{}::{}' enviado para o topico kafka: {}", chaveEvento, consulta, topico);
        } catch (Exception e) {
            log.error("Erro ao enviar evento " + chaveEvento, e);
        }
    }

}
