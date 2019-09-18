package br.pucrs.projarq.hackatona.api.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvaliacaoRequest {
    private Long idTime;
    private int softwareFuncionando;
    private int processo;
    private int pitch;
    private int inovacao;
    private int formacaoTime;
}
