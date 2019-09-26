package br.pucrs.projarq.hackatona.api;

import br.pucrs.projarq.hackatona.api.request.TimeRequest;
import br.pucrs.projarq.hackatona.entity.Aluno;
import br.pucrs.projarq.hackatona.entity.Time;
import br.pucrs.projarq.hackatona.service.GeneratePDF;
import br.pucrs.projarq.hackatona.service.TimeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeApi {
    private TimeService timeService;

    public TimeApi(TimeService timeService) {
        this.timeService = timeService;
    }

    @PostMapping("")
    @ApiOperation(value = "Cadastra ou edita um time.")
    public ResponseEntity salvarTime(@Valid @RequestBody TimeRequest timeRequest) {
        timeService.cadastroTime(timeRequest);
        return ResponseEntity.ok(Void.class);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um time.")
    public ResponseEntity deletarTime(@PathVariable Long id) {
        timeService.excluirTime(id);
        return ResponseEntity.ok(Void.class);
    }

    @GetMapping("/todos")
    @ApiOperation(value = "Listar todos os times.")
    public ResponseEntity<List<Time>> listarTodosTimes() {
        List<Time> times = timeService.listarTimes();
        return ResponseEntity.ok(times);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar detalhe de um time.")
    public ResponseEntity<Time> detalharTime(@PathVariable Long id) {
        Time time = timeService.detalheTime(id);
        return ResponseEntity.ok(time);
    }

    @RequestMapping(value = "/certificado/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport(@PathVariable Long id) {

        List<Aluno> alunos = timeService.detalheTime(id).getIntegrantes();
        ByteArrayInputStream bis = GeneratePDF.certificado(alunos);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=certificado.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
