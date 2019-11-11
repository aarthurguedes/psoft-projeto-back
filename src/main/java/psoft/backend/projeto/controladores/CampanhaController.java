package psoft.backend.projeto.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psoft.backend.projeto.entidades.Campanha;
import psoft.backend.projeto.excecoes.CampanhaJaExisteException;
import psoft.backend.projeto.servicos.CampanhaService;
import psoft.backend.projeto.servicos.JWTService;

import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

@RestController
public class CampanhaController {

    private CampanhaService campanhaService;
    private JWTService jwtService;

    public CampanhaController(CampanhaService campanhaService, JWTService jwtService) {
        this.campanhaService = campanhaService;
        this.jwtService = jwtService;
    }

    @PostMapping("/campanhas")
    public ResponseEntity<Campanha> cadastraCampanha(@RequestHeader("Authorization") String header, @RequestBody Campanha campanha) {
        try {
            if (jwtService.usuarioTemPermissao(header, campanha.getUsuarioDono())) {
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
    public ResponseEntity<Optional<Campanha>> getCampanha(@PathVariable ("url") String url) {
        if (!campanhaService.getCampanha(url).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(campanhaService.getCampanha(url), HttpStatus.OK);
    }

    @GetMapping("/campanhas")
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
}
