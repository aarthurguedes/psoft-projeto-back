package psoft.backend.projeto.controladores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import psoft.backend.projeto.entidades.Usuario;
import psoft.backend.projeto.servicos.JWTService;
import psoft.backend.projeto.servicos.UsuariosService;

import javax.servlet.ServletException;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private UsuariosService usuariosService;
    private JWTService jwtService;

    public LoginController(UsuariosService usuariosService, JWTService jwtService) {
        this.usuariosService = usuariosService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse authenticate(@RequestBody Usuario usuario) throws ServletException {
        Optional<Usuario> authUsuario = usuariosService.getUsuario(usuario.getEmail());

        if (!authUsuario.isPresent()) {
            throw new ServletException("Usuario nao encontrado");
        }

        verificaSenha(usuario, authUsuario);

        String token = jwtService.geraToken(authUsuario.get().getEmail());

        return new LoginResponse(token);
    }

    private void verificaSenha(Usuario usuario, Optional<Usuario> authUsuario) throws ServletException {
        if (!authUsuario.get().getSenha().equals(usuario.getSenha())) {
            throw new ServletException("Senha invalida!");
        }
    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }
}
