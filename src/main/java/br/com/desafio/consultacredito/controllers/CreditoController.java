package br.com.desafio.consultacredito.controllers;

import br.com.desafio.consultacredito.dto.CreditoDto;
import br.com.desafio.consultacredito.services.CreditoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Creditos", description = "Operações relacionadas a creditos")
@Slf4j
@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

    CreditoService creditoService;

    public CreditoController(CreditoService creditoService) {
        this.creditoService = creditoService;
    }

    @GetMapping("/{numeroNfse}")
    public ResponseEntity<List<CreditoDto>> buscarPorNumeroNfse(
            @PathVariable("numeroNfse") String numeroNfse
    ) {
        return ResponseEntity.ok(creditoService.buscarPorNumeroNfse(numeroNfse).stream()
                .map(CreditoDto::fromEntity)
                .toList());
    }

    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDto> buscaPorNumeroCredito(
            @PathVariable("numeroCredito") String numeroCredito
    ) {
        return ResponseEntity.ok(CreditoDto.fromEntity(creditoService.buscarPorNumeroCredito(numeroCredito)));
    }

}
