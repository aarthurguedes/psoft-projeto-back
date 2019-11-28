package psoft.backend.projeto.servicos;

import org.springframework.stereotype.Service;
import psoft.backend.projeto.email.JavaMailApp;
import psoft.backend.projeto.entidades.Usuario;
import psoft.backend.projeto.excecoes.UsuarioInexistenteException;
import psoft.backend.projeto.excecoes.UsuarioJaExisteException;
import psoft.backend.projeto.repositorios.UsuariosRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    private UsuariosRepository<Usuario, String> usuariosRepository;

    public UsuariosService(UsuariosRepository<Usuario, String> usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public Usuario cadastraUsuario(Usuario usuario) throws UsuarioJaExisteException {
        if (usuariosRepository.existsById(usuario.getEmail())) {
            throw new UsuarioJaExisteException("Usuario com mesmo email ja cadastrado!");
        }

        JavaMailApp javaMailApp = new JavaMailApp();
        javaMailApp.enviaMensagem(usuario.getEmail());
        return this.usuariosRepository.save(usuario);
    }

    public Optional<Usuario> getUsuario(String email) {
        return this.usuariosRepository.findById(email);
    }

    public Usuario exibePerfilUsuario(String email) throws UsuarioInexistenteException {
        if (!this.usuariosRepository.existsById(email)) {
            throw new UsuarioInexistenteException("Usuario nao encontrado!");
        }

        return this.usuariosRepository.findById(email).get();
    }

    public List<Usuario> getUsuariosPeloEmail(String email) {
        return this.usuariosRepository.findByEmailContainingIgnoreCase(email);
    }
}
