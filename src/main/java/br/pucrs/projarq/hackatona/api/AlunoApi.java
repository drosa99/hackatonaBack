package br.pucrs.projarq.hackatona.api;

import br.pucrs.projarq.hackatona.api.request.AlunoRequest;
import br.pucrs.projarq.hackatona.entity.Aluno;
import br.pucrs.projarq.hackatona.service.AlunoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("")
    @ApiOperation(value = "Listar alunos que não estão em um time.")
    public ResponseEntity<List<Aluno>> listarAlunosDisponiveis() {
        return ResponseEntity.ok(alunoService.listarTodosAlunos());
    }
}
