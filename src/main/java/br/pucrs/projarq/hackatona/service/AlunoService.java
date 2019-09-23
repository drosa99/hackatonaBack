package br.pucrs.projarq.hackatona.service;

import br.pucrs.projarq.hackatona.api.request.AlunoRequest;
import br.pucrs.projarq.hackatona.entity.Aluno;
import br.pucrs.projarq.hackatona.exception.ExpectedException;
import br.pucrs.projarq.hackatona.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {
    private AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void cadastroAluno(AlunoRequest alunoRequest) {
        Aluno aluno = Aluno.builder().curso(alunoRequest.getCurso()).nome(alunoRequest.getNome()).build();
        alunoRepository.save(aluno);
    }

    public List<Aluno> buscarAlunosPorIds(List<Long> idsAlunos) {
        List<Aluno> alunos = new ArrayList<>();
        for (Long id : idsAlunos) {
            alunos.add(alunoRepository.findById(id).orElseThrow(() -> new ExpectedException("Aluno nao encontrado")));
        }
        return alunos;
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAllByTimeIsNull();
    }

    public void cadastrarTime(List<Long> idAlunos, Long idTime) {
        List<Aluno> alunos = new ArrayList<>();
        for (Long id : idAlunos) {
            alunos.add(alunoRepository.findById(id).get());
        }
        for (Aluno aluno : alunos) {
            aluno.setTimeId(idTime);
            alunoRepository.save(aluno);
        }
    }

    public List<Aluno> buscarPorTime(Long idTime) {
        return alunoRepository.findAllByTimeId(idTime);
    }
}
