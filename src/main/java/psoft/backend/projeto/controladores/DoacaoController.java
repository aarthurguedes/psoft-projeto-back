package psoft.backend.projeto.controladores;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psoft.backend.projeto.entidades.Doacao;
import psoft.backend.projeto.excecoes.DoacaoJaExisteException;
import psoft.backend.projeto.servicos.DoacoesService;
import psoft.backend.projeto.servicos.JWTService;

import javax.servlet.ServletException;

@RestController
@Api(value = "API Campanhas")
@CrossOrigin(origins = "*")
public class DoacaoController {

    private DoacoesService doacoesService;
    private JWTService jwtService;

    public DoacaoController(DoacoesService doacoesService, JWTService jwtService) {
        this.doacoesService = doacoesService;
        this.jwtService = jwtService;
    }

    @PostMapping("/doacoes")
    @ApiOperation(value = "Salva uma Doação no banco de dados")
    public ResponseEntity<Doacao> adicionaDoacao(@RequestHeader("Authorization") String header,
                                                 @RequestBody Doacao doacao) {
        try {
            if (jwtService.usuarioTemPermissao(header)) {
                return new ResponseEntity<>(doacoesService.adicionaDoacao(doacao), HttpStatus.OK);
            }
        } catch (ServletException se) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (DoacaoJaExisteException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
