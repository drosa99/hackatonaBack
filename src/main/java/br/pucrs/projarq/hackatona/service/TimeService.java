package br.pucrs.projarq.hackatona.service;

import br.pucrs.projarq.hackatona.api.request.TimeRequest;
import br.pucrs.projarq.hackatona.entity.Aluno;
import br.pucrs.projarq.hackatona.entity.Time;
import br.pucrs.projarq.hackatona.exception.ExpectedException;
import br.pucrs.projarq.hackatona.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeService {
    private TimeRepository timeRepository;
    private AlunoService alunoService;

    public TimeService(TimeRepository timeRepository, AlunoService alunoService) {
        this.timeRepository = timeRepository;
        this.alunoService = alunoService;
    }

    public void cadastroTime(TimeRequest timeRequest) {
        List<String> cursos = new ArrayList<>();
        List<Aluno> integrantes = alunoService.buscarAlunosPorIds(timeRequest.getIntegrantes());
        for (Aluno aluno : integrantes) {
            if (!cursos.contains(aluno.getCurso())) {
                cursos.add(aluno.getCurso());
            }
        }
        if (cursos.size() < 2) {
            throw new ExpectedException("E necessario ter integrantes de pelo menos 2 cursos diferentes");
        }
        Time time = Time.builder()
                .id(timeRequest.getId())
                .nomeTime(timeRequest.getNomeTime())
                .integrantes(integrantes)
                .build();
        timeRepository.save(time);
    }

    public void excluirTime(Long id) {
        Time time = timeRepository.findById(id).orElseThrow(() -> new ExpectedException("Time nao encontrado"));
        timeRepository.delete(time);
    }

    public List<Time> listarTimes() {
        return timeRepository.findAll();
    }

    public Time detalheTime(Long id) {
        return timeRepository.findById(id).orElseThrow(() -> new ExpectedException("Time nao encontrado"));
    }
}
