package br.pucrs.projarq.hackatona.service;

import br.pucrs.projarq.hackatona.api.request.TimeRequest;
import br.pucrs.projarq.hackatona.entity.Aluno;
import br.pucrs.projarq.hackatona.entity.Time;
import br.pucrs.projarq.hackatona.exception.ExpectedException;
import br.pucrs.projarq.hackatona.repository.TimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {
    private TimeRepository timeRepository;
    private AlunoService alunoService;

    public TimeService(TimeRepository timeRepository, AlunoService alunoService) {
        this.timeRepository = timeRepository;
        this.alunoService = alunoService;
    }

    @Transactional
    public void cadastroTime(TimeRequest timeRequest) {
        List<String> cursos = new ArrayList<>();
        if (timeRequest.getIntegrantes().size() > 6) {
            throw new ExpectedException("Time nao pode ter mais de 6 integrantes.");
        }
        List<Aluno> integrantes = alunoService.buscarAlunosPorIds(timeRequest.getIntegrantes());
        for (Aluno aluno : integrantes) {
            if (aluno.getTimeId() != null) {
                if (timeRequest.getId() == null || !aluno.getTimeId().equals(timeRequest.getId())) {
                    throw new ExpectedException("Este aluno ja esta em um time");
                }
            }
            if (!cursos.contains(aluno.getCurso())) {
                cursos.add(aluno.getCurso());
            }
        }
        if (cursos.size() < 2) {
            throw new ExpectedException("E necessario ter integrantes de pelo menos 2 cursos diferentes");
        }

        //limpa alunos do time que esta sendo editado
        if (timeRequest.getId() != null) {
            List<Aluno> alunosTime = alunoService.buscarPorTime(timeRequest.getId());
            List<Long> integrantesRemovidos = alunosTime.stream().map(Aluno::getId).collect(Collectors.toList());
            alunoService.cadastrarAlunosEmTime(integrantesRemovidos, null);
        }


        Time time = Time.builder()
                .id(timeRequest.getId())
                .nomeTime(timeRequest.getNomeTime())
                .integrantes(integrantes)
                .build();
        Time timeSalvo = timeRepository.save(time);
        alunoService.cadastrarAlunosEmTime(timeRequest.getIntegrantes(), timeSalvo.getId());

    }

    public void excluirTime(Long id) {
        Time time = timeRepository.findById(id).orElseThrow(() -> new ExpectedException("Time nao encontrado"));
        if (time.getAvaliacoes() != null && time.getAvaliacoes().size() > 0) {
            throw new ExpectedException("Nao e possivel excluir times que possuem avaliacoes cadastradas.");
        }
        time.setIntegrantes(alunoService.buscarPorTime(time.getId()));
        List<Long> idsAlunos = time.getIntegrantes().stream().map(Aluno::getId).collect(Collectors.toList());
        alunoService.cadastrarAlunosEmTime(idsAlunos, null);
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
