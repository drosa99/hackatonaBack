package br.pucrs.projarq.hackatona.service;

import br.pucrs.projarq.hackatona.api.request.AvaliacaoRequest;
import br.pucrs.projarq.hackatona.entity.Avaliacao;
import br.pucrs.projarq.hackatona.entity.Time;
import br.pucrs.projarq.hackatona.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;
    private TimeService timeService;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, TimeService timeService) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.timeService = timeService;
    }

    @Transactional
    public void cadastrarAvaliacao(AvaliacaoRequest avaliacaoRequest) {
        Time time = timeService.detalheTime(avaliacaoRequest.getIdTime());
        avaliacaoRepository.save(Avaliacao.builder()
                .time(time)
                .formacaoTime(avaliacaoRequest.getFormacaoTime())
                .inovacao(avaliacaoRequest.getInovacao())
                .pitch(avaliacaoRequest.getPitch())
                .processo(avaliacaoRequest.getProcesso())
                .softwareFuncionando(avaliacaoRequest.getSoftwareFuncionando())
                .build());

    }
}
