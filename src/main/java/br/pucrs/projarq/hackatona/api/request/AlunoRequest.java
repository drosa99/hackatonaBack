package br.pucrs.projarq.hackatona.api.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlunoRequest {
    private String nome;
    private String curso;
}
