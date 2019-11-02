package psoft.backend.projeto.servicos;

import org.springframework.stereotype.Service;
import psoft.backend.projeto.entidades.Campanha;
import psoft.backend.projeto.repositorios.CampanhaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CampanhaService {

    private CampanhaRepository<Campanha, Integer> campanhaRepository;

    public CampanhaService(CampanhaRepository<Campanha, Integer> campanhaRepository) {
        this.campanhaRepository = campanhaRepository;
    }

    public Campanha cadastraCampanha(Campanha campanha) {
        return this.campanhaRepository.save(campanha);
    }

    public Optional<Campanha> getCampanha(int id) {
        return this.campanhaRepository.findById(id);
    }

    public List<Campanha> getCampanhas() {
        return this.campanhaRepository.findAll();
    }
}
