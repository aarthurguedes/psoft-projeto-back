package psoft.backend.projeto.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import psoft.backend.projeto.entidades.Usuario;
import psoft.backend.projeto.excecoes.UsuarioJaExisteException;
import psoft.backend.projeto.servicos.UsuariosService;

import java.util.List;

@RestController
public class UsuariosController {

    private UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuariosService.cadastraUsuario(usuario), HttpStatus.OK);
        } catch (UsuarioJaExisteException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return new ResponseEntity<>(usuariosService.getUsuarios(), HttpStatus.OK);
    }
}