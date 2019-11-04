package psoft.backend.projeto.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psoft.backend.projeto.entidades.Campanha;
import psoft.backend.projeto.servicos.CampanhaService;
import psoft.backend.projeto.servicos.JWTService;

import javax.servlet.ServletException;
import java.util.List;

@RestController
public class CampanhaController {

    private CampanhaService campanhaService;
    private JWTService jwtService;

    public CampanhaController(CampanhaService campanhaService, JWTService jwtService) {
        this.campanhaService = campanhaService;
        this.jwtService = jwtService;
    }

    @GetMapping("/campanhas")
    public ResponseEntity<List<Campanha>> getCampanhas() {
        return new ResponseEntity<>(campanhaService.getCampanhas(), HttpStatus.OK);
    }

    @PostMapping("/campanhas")
    public ResponseEntity<Campanha> cadastraCampanha(@RequestHeader("Authorization") String header, @RequestBody Campanha campanha) throws ServletException {
        try {
            if (jwtService.usuarioTemPermissao(header, campanha.getUsuarioDono())) {
                if(campanhaService.getCampanha(campanha.getId()).isPresent()) {
                    throw new ServletException("Campanha já cadastrada!");
                }

                return new ResponseEntity<>(campanhaService.cadastraCampanha(campanha), HttpStatus.OK);
            }
        } catch (ServletException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
