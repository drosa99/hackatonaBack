package br.pucrs.projarq.hackatona.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TIME")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Time.class)
public class Time {
    @Id
    @SequenceGenerator(allocationSize = 1, name = "time_seq", sequenceName = "time_seq")
    @GeneratedValue(generator = "time_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nomeTime;

    @OneToMany(mappedBy = "time")
    private List<Aluno> integrantes = new ArrayList<>();

    @OneToMany(mappedBy = "time")
    private List<Avaliacao> avaliacoes = new ArrayList<>();
}
