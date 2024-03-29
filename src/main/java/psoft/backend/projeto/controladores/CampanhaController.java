package psoft.backend.projeto.controladores;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psoft.backend.projeto.entidades.Campanha;
import psoft.backend.projeto.excecoes.CampanhaInexistenteException;
import psoft.backend.projeto.excecoes.CampanhaJaExisteException;
import psoft.backend.projeto.servicos.CampanhaService;
import psoft.backend.projeto.servicos.JWTService;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "API Campanhas")
@CrossOrigin(origins = "*")
public class CampanhaController {

    private CampanhaService campanhaService;
    private JWTService jwtService;

    public CampanhaController(CampanhaService campanhaService, JWTService jwtService) {
        this.campanhaService = campanhaService;
        this.jwtService = jwtService;
    }

    @PostMapping("/campanhas")
    @ApiOperation(value = "Salva uma Campanha no banco de dados")
    public ResponseEntity<Campanha> cadastraCampanha(@RequestHeader("Authorization") String header, @RequestBody Campanha campanha) {
        try {
            if (jwtService.usuarioTemPermissao(header, campanha.getUsuarioDono().getEmail())) {
                return new ResponseEntity<>(campanhaService.cadastraCampanha(campanha), HttpStatus.OK);
            }
        } catch (ServletException se) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (CampanhaJaExisteException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/campanhas/{url}")
    @ApiOperation(value = "Retorna uma Campanha a partir da url passada")
    public ResponseEntity<Optional<Campanha>> getCampanha(@RequestHeader("Authorization") String header,
                                                          @PathVariable ("url") String url) {
        try {
            if (jwtService.usuarioTemPermissao(header)) {
                return new ResponseEntity<>(campanhaService.getCampanha(url), HttpStatus.OK);
            }
        } catch (ServletException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } catch (CampanhaInexistenteException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/campanhas")
    @ApiOperation(value = "Retorna uma Lista de Campanhas a partir dos parâmetros passados na url")
    public ResponseEntity<List<Campanha>> getCampanhasPeloNome(@RequestHeader("Authorization") String header,
                                                               @RequestParam (value = "substring") String substring,
                                                               @RequestParam (value = "retornarTodas") String retornarTodas) {
        try {
            if (jwtService.usuarioTemPermissao(header)) {
                boolean retornarTodasCampanhas = Boolean.valueOf(retornarTodas);
                return new ResponseEntity<>(this.campanhaService.getCampanhasPeloNome(substring, retornarTodasCampanhas), HttpStatus.OK);
            }
        } catch (ServletException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/campanhas/ranking/{criterio}")
    @ApiOperation(value = "Retorna uma Lista de Campanhas a partir de um determinado critério")
    public ResponseEntity<List<Campanha>> listaCampanhasPorCriterio(@PathVariable ("criterio") String criterio) {
        return new ResponseEntity<>(this.campanhaService.listaCampanhasPorCriterio(criterio), HttpStatus.OK);
    }
}
