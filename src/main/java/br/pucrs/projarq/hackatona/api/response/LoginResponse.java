package br.pucrs.projarq.hackatona.api.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private Boolean isAvaliador;
    private Boolean isEncerrado;
    private String timeVencedor;
    private Long idTime;
}
