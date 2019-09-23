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
            if (aluno.getTimeId() != null) {
                throw new ExpectedException("Este aluno ja esta em um time");
            }
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
        Long id = timeRepository.save(time).getId();
        alunoService.cadastrarTime(timeRequest.getIntegrantes(), id);

    }

    public void excluirTime(Long id) {
        Time time = timeRepository.findById(id).orElseThrow(() -> new ExpectedException("Time nao encontrado"));
        timeRepository.delete(time);
    }

    public List<Time> listarTimes() {
        List<Time> times = timeRepository.findAll();
        for (Time time : times) {
            time.setIntegrantes(alunoService.buscarPorTime(time.getId()));
        }
        return times;
    }

    public Time detalheTime(Long id) {
        Time time = timeRepository.findById(id).orElseThrow(() -> new ExpectedException("Time nao encontrado"));
        time.setIntegrantes(alunoService.buscarPorTime(time.getId()));
        return time;
    }


}
