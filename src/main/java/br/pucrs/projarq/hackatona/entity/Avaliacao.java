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
@Table(name = "AVALIACAO")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Avaliacao.class)
public class Avaliacao {
    @Id
    @SequenceGenerator(allocationSize = 1, name = "avaliacao_seq", sequenceName = "avaliacao_seq")
    @GeneratedValue(generator = "avaliacao_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_time")
    private Time time;

    @Column
    private int softwareFuncionando;

    @Column
    private int processo;

    @Column
    private int pitch;

    @Column
    private int inovacao;

    @Column
    private int formacaoTime;

}
