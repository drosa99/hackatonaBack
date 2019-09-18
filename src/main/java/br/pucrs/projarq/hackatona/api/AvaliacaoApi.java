package br.pucrs.projarq.hackatona.api;

import br.pucrs.projarq.hackatona.api.request.AvaliacaoRequest;
import br.pucrs.projarq.hackatona.service.AvaliacaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoApi {
    private AvaliacaoService avaliacaoService;

    public AvaliacaoApi(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @PostMapping("")
    @ApiOperation(value = "Cadastra ou edita uma avaliacao.")
    public ResponseEntity salvarTime(@Valid @RequestBody AvaliacaoRequest avaliacaoRequest) {
        avaliacaoService.cadastrarAvaliacao(avaliacaoRequest);
        return ResponseEntity.ok(Void.class);
    }
}
