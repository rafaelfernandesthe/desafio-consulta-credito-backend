package br.com.desafio.consultacredito.dto;

import br.com.desafio.consultacredito.model.Credito;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditoDto {
	private String numeroCredito;
	private String numeroNfse;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataConstituicao;
	private BigDecimal valorIssqn;
	private String tipoCredito;
	private String simplesNacional;
	private BigDecimal aliquota;
	private BigDecimal valorFaturado;
	private BigDecimal valorDeducao;
	private BigDecimal baseCalculo;

    public static CreditoDto fromEntity(Credito credito) {
        return CreditoDto.builder()
                .numeroCredito(credito.getNumeroCredito())
                .numeroNfse(credito.getNumeroNfse())
                .dataConstituicao(credito.getDataConstituicao())
                .valorIssqn(credito.getValorIssqn())
                .tipoCredito(credito.getTipoCredito())
                .simplesNacional(credito.isSimplesNacional() ? "Sim" : "Não")
                .aliquota(credito.getAliquota())
                .valorFaturado(credito.getValorFaturado())
                .valorDeducao(credito.getValorDeducao())
                .baseCalculo(credito.getBaseCalculo())
                .build();
    }
}
