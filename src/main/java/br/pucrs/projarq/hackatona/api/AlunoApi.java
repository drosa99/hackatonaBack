package br.pucrs.projarq.hackatona.api;

import br.pucrs.projarq.hackatona.api.request.AlunoRequest;
import br.pucrs.projarq.hackatona.service.AlunoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoApi {
    private AlunoService alunoService;

    public AlunoApi(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("")
    @ApiOperation(value = "Cadastra um aluno.")
    public ResponseEntity salvarAluno(@Valid @RequestBody AlunoRequest alunoRequest) {
        alunoService.cadastroAluno(alunoRequest);
        return ResponseEntity.ok(Void.class);
    }
}
