package psoft.backend.projeto.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import psoft.backend.projeto.entidades.Campanha;
import psoft.backend.projeto.servicos.CampanhaService;

import javax.servlet.ServletException;
import java.util.List;

@RestController
public class CampanhaController {

    private CampanhaService campanhaService;

    public CampanhaController(CampanhaService campanhaService) { this.campanhaService = campanhaService; }

    @GetMapping("/campanhas")
    public ResponseEntity<List<Campanha>> getCampanhas() {
        return new ResponseEntity<>(campanhaService.getCampanhas(), HttpStatus.OK);
    }

    @PostMapping("/campanhas")
    public ResponseEntity<Campanha> cadastraCampanha(@RequestHeader("Authorization") String header, @RequestBody Campanha campanha) throws ServletException {
        if(campanhaService.getCampanha(campanha.getId()).isPresent()) {
            throw new ServletException("Campanha já cadastrada!");
        }

        return new ResponseEntity<>(campanhaService.cadastraCampanha(campanha), HttpStatus.OK);
    }
}
