package br.pucrs.projarq.hackatona.repository;


import br.pucrs.projarq.hackatona.entity.Hackathona;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HackathonaRepository extends CrudRepository<Hackathona, Long> {
    List<Hackathona> findAll();
}
