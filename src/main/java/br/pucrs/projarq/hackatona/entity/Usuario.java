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
@Table(name = "USUARIO")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Usuario.class)
public class Usuario {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "USUARIO_SEQ", sequenceName = "USUARIO_SEQ")
    @GeneratedValue(generator = "USUARIO_SEQ", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Boolean isAvaliador;

    @Column
    private Long timeId;
}

