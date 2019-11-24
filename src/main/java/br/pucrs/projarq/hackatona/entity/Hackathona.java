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
@Table(name = "HACKATHONA")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Hackathona.class)
public class Hackathona {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "Hacathona_SEQ", sequenceName = "Hacathona_SEQ")
    @GeneratedValue(generator = "Hacathona_SEQ", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private Boolean isEncerrado;
}

