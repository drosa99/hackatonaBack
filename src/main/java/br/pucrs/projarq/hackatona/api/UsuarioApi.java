package br.pucrs.projarq.hackatona.api;

import br.pucrs.projarq.hackatona.api.response.LoginResponse;
import br.pucrs.projarq.hackatona.entity.Usuario;
import br.pucrs.projarq.hackatona.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioApi {

    private UsuarioService usuarioService;

    public UsuarioApi(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<Usuario>> lstUsuarios() {
        return ResponseEntity.ok(usuarioService.buscarAll());
    }

    @PostMapping(value = "/novo")
    @ResponseBody
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> logar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.logar(usuario.getEmail(), usuario.getSenha()));
    }
}