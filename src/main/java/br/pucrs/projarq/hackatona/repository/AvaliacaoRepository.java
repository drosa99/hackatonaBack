package br.pucrs.projarq.hackatona.repository;

import br.pucrs.projarq.hackatona.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
