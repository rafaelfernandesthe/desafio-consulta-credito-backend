package br.com.desafio.consultacredito;

import br.com.desafio.consultacredito.exceptions.NotFoundBusinessException;
import br.com.desafio.consultacredito.model.Credito;
import br.com.desafio.consultacredito.repositories.CreditoRepository;
import br.com.desafio.consultacredito.services.CreditoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class ConsultaCreditoBackendIntegrationTest {

    @Autowired
    private CreditoRepository creditoRepository;

    @Autowired
    private CreditoService creditoService;

    private Credito credito;

    private Credito criarNovoCredito() {
        return Credito.builder()
                .numeroCredito("")
                .numeroCredito("")
                .numeroNfse("")
                .dataConstituicao(LocalDate.now())
                .valorIssqn(BigDecimal.valueOf(10))
                .tipoCredito("")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(10))
                .valorFaturado(BigDecimal.valueOf(10))
                .valorDeducao(BigDecimal.valueOf(10))
                .baseCalculo(BigDecimal.valueOf(10))
                .build();
    }

    @BeforeEach
    void setup() {
        credito = criarNovoCredito();
    }

    @Test
    void testBuscarPorNumeroNfse() {
        String numeroNfseBusca = "1234567890";

        credito.setNumeroNfse(numeroNfseBusca);
        creditoRepository.save(credito);

        List<Credito> resultado = creditoService.buscarPorNumeroNfse(numeroNfseBusca);

        Assertions.assertNotNull(resultado);
        assertThat(resultado.size() == 1);
        assertThat(resultado.get(0).getNumeroNfse()).isEqualTo(numeroNfseBusca);
    }

    @Test
    void testBuscarPorNumeroNfseInexistente() {
        String numeroNfseBusca = "1234567890";

        credito.setNumeroNfse(numeroNfseBusca);
        creditoRepository.save(credito);

        List<Credito> resultado = creditoService.buscarPorNumeroNfse(numeroNfseBusca + "0");

        Assertions.assertNotNull(resultado);
        assertThat(resultado.isEmpty());
    }

    @Test
    void testBuscarPorNumeroNfseVazio() {
        String numeroNfseBusca = "1234567890";

        credito.setNumeroNfse(numeroNfseBusca);
        creditoRepository.save(credito);

        List<Credito> resultado = creditoService.buscarPorNumeroNfse(null);

        Assertions.assertNotNull(resultado);
        assertThat(resultado.isEmpty());
    }

    @Test
    void testBuscarPorNumeroCredito() {
        String numeroCreditoBusca = "1234567890";

        credito.setNumeroCredito(numeroCreditoBusca);
        creditoRepository.save(credito);

        Credito resultado = creditoService.buscarPorNumeroCredito(numeroCreditoBusca);

        Assertions.assertNotNull(resultado);
        assertThat(resultado.getNumeroCredito().equals(numeroCreditoBusca));
    }

    @Test
    void testBuscarPorNumeroCreditoInexistente() {
        String numeroCreditoBusca = "1234567890";

        credito.setNumeroCredito(numeroCreditoBusca);
        creditoRepository.save(credito);

        NotFoundBusinessException exception = assertThrows(NotFoundBusinessException.class, () -> {
            creditoService.buscarPorNumeroCredito(numeroCreditoBusca + "0");
        });

        Assertions.assertNotNull(exception);
    }

    @Test
    void testBuscarPorNumeroCreditoVazio() {
        String numeroCreditoBusca = "1234567890";

        credito.setNumeroCredito(numeroCreditoBusca);
        creditoRepository.save(credito);

        NotFoundBusinessException exception = assertThrows(NotFoundBusinessException.class, () -> {
            creditoService.buscarPorNumeroCredito(null);
        });

        Assertions.assertNotNull(exception);
    }
}
