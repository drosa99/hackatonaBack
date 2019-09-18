package br.pucrs.projarq.hackatona.api.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeRequest {
    private Long id;
    private String nomeTime;
    private List<Long> integrantes;
}
