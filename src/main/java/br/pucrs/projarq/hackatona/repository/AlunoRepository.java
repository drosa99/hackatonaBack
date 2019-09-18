package br.pucrs.projarq.hackatona.repository;

import br.pucrs.projarq.hackatona.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
