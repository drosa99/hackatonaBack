package br.pucrs.projarq.hackatona.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ALUNO")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Aluno.class)
public class Aluno {
    @Id
    @SequenceGenerator(allocationSize = 1, name = "aluno_seq", sequenceName = "aluno_seq")
    @GeneratedValue(generator = "aluno_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String nome;
    @Column
    private String curso;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_time")
    private Time time;

    private Long timeId;
}
