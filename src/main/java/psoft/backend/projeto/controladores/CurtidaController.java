package psoft.backend.projeto.controladores;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psoft.backend.projeto.entidades.Curtida;
import psoft.backend.projeto.excecoes.CurtidaInexistenteException;
import psoft.backend.projeto.excecoes.CurtidaJaExisteException;
import psoft.backend.projeto.servicos.CurtidasService;
import psoft.backend.projeto.servicos.JWTService;

import javax.servlet.ServletException;

@RestController
@Api(value = "API Campanhas")
@CrossOrigin(origins = "*")
public class CurtidaController {

    private CurtidasService curtidasService;
    private JWTService jwtService;

    public CurtidaController(CurtidasService curtidasService, JWTService jwtService) {
        this.curtidasService = curtidasService;
        this.jwtService = jwtService;
    }

    @PostMapping("/curtidas")
    @ApiOperation(value = "Salva um Like no banco de dados")
    public ResponseEntity<Curtida> adicionaLike(@RequestHeader("Authorization") String header,
                                                @RequestBody Curtida curtida) {
        try {
            if (jwtService.usuarioTemPermissao(header)) {
                return new ResponseEntity<>(this.curtidasService.adicionaLike(curtida), HttpStatus.OK);
            }
        } catch (ServletException se) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (CurtidaJaExisteException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    private boolean usuarioEhDonoDaCurtida (String header, Long idCurtida) throws ServletException {
        String emailUsuario = this.jwtService.getSujeitoDoToken(header);
        return emailUsuario.equals(this.curtidasService.getCurtida(idCurtida).get().getUsuarioDoLike());
    }

    @DeleteMapping("/curtidas/{id}")
    @ApiOperation(value = "Remove um Like do banco de dados a partir da url")
    public ResponseEntity<Curtida> removeLike(@PathVariable Long id, @RequestHeader("Authorization") String header) {
        if (!this.curtidasService.getCurtida(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            if (this.jwtService.usuarioTemPermissao(header) && usuarioEhDonoDaCurtida(header, id)) {
                return new ResponseEntity<>(this.curtidasService.removeLike(id), HttpStatus.OK);
            }
        } catch (ServletException se) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (CurtidaInexistenteException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
