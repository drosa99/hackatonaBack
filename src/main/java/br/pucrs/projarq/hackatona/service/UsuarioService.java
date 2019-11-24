package br.pucrs.projarq.hackatona.service;

import br.pucrs.projarq.hackatona.api.response.LoginResponse;
import br.pucrs.projarq.hackatona.entity.Hackathona;
import br.pucrs.projarq.hackatona.entity.Usuario;
import br.pucrs.projarq.hackatona.repository.HackathonaRepository;
import br.pucrs.projarq.hackatona.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private HackathonaRepository hackathonaRepository;
    private AvaliacaoService avaliacaoService;

    public UsuarioService(UsuarioRepository usuarioRepository, HackathonaRepository hackathonaRepository, AvaliacaoService avaliacaoService) {
        this.usuarioRepository = usuarioRepository;
        this.hackathonaRepository = hackathonaRepository;
        this.avaliacaoService = avaliacaoService;
    }

    public List<Usuario> buscarAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Usuario salvar(Usuario usu) {
        return usuarioRepository.save(usu);

    }

    public LoginResponse logar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmailAndSenha(email, senha);
        List<Hackathona> hackas = hackathonaRepository.findAll();
        Boolean isEncerrado = hackathonaRepository.findById(1L).get().getIsEncerrado();
        String timeVencedor = null;
        if (isEncerrado != null && isEncerrado) {
            timeVencedor = avaliacaoService.contabilizarTimeVencedor();
        }
        return LoginResponse.builder()
                .isAvaliador(usuario.getIsAvaliador())
                .isEncerrado(isEncerrado)
                .idTime(usuario.getTimeId())
                .timeVencedor(timeVencedor)
                .build();
    }

}