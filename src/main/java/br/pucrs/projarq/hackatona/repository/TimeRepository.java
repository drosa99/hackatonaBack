package br.pucrs.projarq.hackatona.repository;

import br.pucrs.projarq.hackatona.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}
