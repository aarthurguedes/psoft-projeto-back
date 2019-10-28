package psoft.backend.projeto.servicos;

import org.springframework.stereotype.Service;
import psoft.backend.projeto.entidades.Usuario;
import psoft.backend.projeto.repositorios.UsuariosRepository;

import java.util.Optional;

@Service
public class UsuariosService {

    private UsuariosRepository<Usuario, String> usuariosRepository;

    public UsuariosService(UsuariosRepository<Usuario, String> usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public Usuario cadastraUsuario(Usuario usuario) {
        return this.usuariosRepository.save(usuario);
    }

    public Optional<Usuario> getUsuario(String email) {
        return this.usuariosRepository.findById(email);
    }
}
