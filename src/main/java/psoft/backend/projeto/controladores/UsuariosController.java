package psoft.backend.projeto.controladores;

import com.sun.mail.iap.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psoft.backend.projeto.entidades.Usuario;
import psoft.backend.projeto.excecoes.UsuarioInexistenteException;
import psoft.backend.projeto.excecoes.UsuarioJaExisteException;
import psoft.backend.projeto.servicos.UsuariosService;

import java.util.List;

@RestController
@Api(value = "API Campanhas")
@CrossOrigin(origins = "*")
public class UsuariosController {

    private UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/usuarios")
    @ApiOperation(value = "Salva um Usuário no banco de dados")
    public ResponseEntity<Usuario> cadastraUsuario(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(usuariosService.cadastraUsuario(usuario), HttpStatus.OK);
        } catch (UsuarioJaExisteException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/usuarios/{email}")
    @ApiOperation(value = "Retorna um Usuário (perfil) a partir da url")
    public ResponseEntity<Usuario> exibePerfilUsuario(@PathVariable("email") String email) {
        try {
            return new ResponseEntity<>(usuariosService.exibePerfilUsuario(email), HttpStatus.OK);
        } catch (UsuarioInexistenteException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/usuarios")
    @ApiOperation(value = "Retorna uma Lista de Usuários a partir do email contido na url")
    public ResponseEntity<List<Usuario>> getUsuariosPeloEmail(@RequestParam (value = "email") String email) {
        return new ResponseEntity<>(usuariosService.getUsuariosPeloEmail(email), HttpStatus.OK);
    }
}