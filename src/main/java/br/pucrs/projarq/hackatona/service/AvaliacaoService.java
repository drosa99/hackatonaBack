package br.pucrs.projarq.hackatona.service;

import br.pucrs.projarq.hackatona.api.request.AvaliacaoRequest;
import br.pucrs.projarq.hackatona.entity.Avaliacao;
import br.pucrs.projarq.hackatona.entity.Hackathona;
import br.pucrs.projarq.hackatona.entity.Time;
import br.pucrs.projarq.hackatona.exception.ExpectedException;
import br.pucrs.projarq.hackatona.repository.AvaliacaoRepository;
import br.pucrs.projarq.hackatona.repository.HackathonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvaliacaoService {

    private AvaliacaoRepository avaliacaoRepository;
    private TimeService timeService;
    private HackathonaRepository hackathonaRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, TimeService timeService, HackathonaRepository hackathonaRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.timeService = timeService;
        this.hackathonaRepository = hackathonaRepository;
    }

    @Transactional
    public void cadastrarAvaliacao(AvaliacaoRequest avaliacaoRequest) {
        Hackathona hacka = hackathonaRepository.findAll().get(0);
        if (hacka.getIsEncerrado()) {
            throw new ExpectedException("Não é mais possível avaliar um time, votações encerradas");
        }
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

    public String encerrarVotacao() {
        Hackathona hacka = hackathonaRepository.findAll().get(0);
        hacka.setIsEncerrado(true);
        hackathonaRepository.save(hacka);
        return contabilizarTimeVencedor();
    }

    public String contabilizarTimeVencedor() {
        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();
        Avaliacao maiorAvaliacao = avaliacoes.get(0);
        int maiorSoma = 0;
        for (Avaliacao ava : avaliacoes) {
            int somaAtual = ava.getFormacaoTime() + ava.getInovacao() + ava.getPitch() + ava.getProcesso() + ava.getSoftwareFuncionando();
            if (somaAtual > maiorSoma) {
                maiorSoma = somaAtual;
                maiorAvaliacao = ava;
            }
        }
        return maiorAvaliacao.getTime().getNomeTime();
    }


}
