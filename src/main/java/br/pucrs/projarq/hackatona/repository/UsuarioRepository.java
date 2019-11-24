package br.pucrs.projarq.hackatona.repository;


import br.pucrs.projarq.hackatona.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByEmailAndSenha(String email, String senha);
}
