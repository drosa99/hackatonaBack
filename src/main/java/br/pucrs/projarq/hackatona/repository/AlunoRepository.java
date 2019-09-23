package br.pucrs.projarq.hackatona.repository;

import br.pucrs.projarq.hackatona.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findAllByTimeIsNull();

    List<Aluno> findAllByTimeId(Long id);
}
