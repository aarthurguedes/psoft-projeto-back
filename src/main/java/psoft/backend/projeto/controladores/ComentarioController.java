package psoft.backend.projeto.controladores;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psoft.backend.projeto.entidades.Comentario;
import psoft.backend.projeto.excecoes.ComentarioInexistenteException;
import psoft.backend.projeto.excecoes.ComentarioJaExisteException;
import psoft.backend.projeto.servicos.ComentariosService;
import psoft.backend.projeto.servicos.JWTService;

import javax.servlet.ServletException;

@RestController
@Api(value = "API Campanhas")
@CrossOrigin(origins = "*")
public class ComentarioController {

    private ComentariosService comentariosService;
    private JWTService jwtService;

    public ComentarioController(ComentariosService comentariosService, JWTService jwtService) {
        this.comentariosService = comentariosService;
        this.jwtService = jwtService;
    }

    @PostMapping("/comentarios")
    @ApiOperation(value = "Salva um Comentário no banco de dados")
    public ResponseEntity<Comentario> adicionaComentario(@RequestHeader("Authorization") String header,
                                                         @RequestBody Comentario comentario) {
        try {
            if (jwtService.usuarioTemPermissao(header)) {
                return new ResponseEntity<>(comentariosService.adicionaComentario(comentario), HttpStatus.OK);
            }
        } catch (ServletException se) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ComentarioJaExisteException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private boolean usuarioEhDonoDoComentario(String header, Long idComentario) throws ServletException {
        String emailUsuario = this.jwtService.getSujeitoDoToken(header);
        return emailUsuario.equals(this.comentariosService.getComentario(idComentario).get().getEmailUsuarioDono());
    }

    @DeleteMapping("/comentarios/{id}")
    @ApiOperation(value = "Remove um Comentário do banco de dados a partir da url")
    public ResponseEntity<Comentario> removeComentario(@PathVariable Long id, @RequestHeader("Authorization") String header) {
        if (!this.comentariosService.getComentario(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            if (jwtService.usuarioTemPermissao(header) && usuarioEhDonoDoComentario(header, id)) {
                return new ResponseEntity<>(comentariosService.removeComentario(id), HttpStatus.OK);
            }
        } catch (ServletException se) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (ComentarioInexistenteException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

}
