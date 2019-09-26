package br.pucrs.projarq.hackatona.service;

import br.pucrs.projarq.hackatona.api.request.AlunoRequest;
import br.pucrs.projarq.hackatona.api.request.AvaliacaoRequest;
import br.pucrs.projarq.hackatona.api.request.TimeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private AlunoService alunoService;
    private TimeService timeService;
    private AvaliacaoService avaliacaoService;

    public CommandLineAppStartupRunner(AlunoService alunoService, TimeService timeService, AvaliacaoService avaliacaoService) {
        this.alunoService = alunoService;
        this.timeService = timeService;
        this.avaliacaoService = avaliacaoService;
    }

    private static final Logger LOG =
            LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Override
    public void run(String... args) throws Exception {
        LOG.info("Fazendo inserts...");
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Daniela Amaral").curso("Engenharia de Software").build());
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Maria Silva").curso("Ciencia da Computacao").build());
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Vitor Tietbohl").curso("Engenharia da Computacao").build());
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Marcelo Chaves").curso("Sistemas de Informacao").build());
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Rafael Mattos").curso("Sistemas de Informacao").build());
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Eduardo Begossi").curso("Ciencia da Computacao").build());
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Francisco Arias").curso("Engenharia de Software").build());
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Andressa Loreto").curso("Engenharia de Software").build());
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Natalia Queiroz").curso("Ciencia da Computacao").build());
        alunoService.cadastroAluno(AlunoRequest.builder().nome("Mariana Miranda").curso("Engenharia da Computacao").build());
        timeService.cadastroTime(TimeRequest.builder().integrantes(Arrays.asList(1L, 4L, 3L)).nomeTime("Azul").build());
        timeService.cadastroTime(TimeRequest.builder().integrantes(Arrays.asList(2L, 5L)).nomeTime("Amarelo").build());
        avaliacaoService.cadastrarAvaliacao(AvaliacaoRequest.builder()
                .idTime(1L)
                .formacaoTime(5)
                .inovacao(5)
                .pitch(5)
                .processo(5)
                .softwareFuncionando(5)
                .build());
    }
}
