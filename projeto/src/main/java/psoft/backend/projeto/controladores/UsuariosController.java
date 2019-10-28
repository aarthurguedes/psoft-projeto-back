package psoft.backend.projeto.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psoft.backend.projeto.entidades.Usuario;
import psoft.backend.projeto.servicos.UsuariosService;

import javax.servlet.ServletException;

@RestController
public class UsuariosController {

    private UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) throws ServletException {
        if (usuariosService.getUsuario(usuario.getEmail()).isPresent()) {
            throw new ServletException("Usuario ja cadastrado");
        }

        return new ResponseEntity<>(usuariosService.cadastraUsuario(usuario), HttpStatus.OK);
    }
}