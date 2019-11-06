package psoft.backend.projeto.servicos;

import org.springframework.stereotype.Service;
import psoft.backend.projeto.entidades.Campanha;
import psoft.backend.projeto.excecoes.CampanhaJaExisteException;
import psoft.backend.projeto.repositorios.CampanhaRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CampanhaService {

    private CampanhaRepository<Campanha, Long> campanhaRepository;

    public CampanhaService(CampanhaRepository<Campanha, Long> campanhaRepository) {
        this.campanhaRepository = campanhaRepository;
    }

    public Campanha cadastraCampanha(Campanha campanha) throws CampanhaJaExisteException {
        if (campanhaRepository.existsByUrl(campanha.getUrl())) {
            throw new CampanhaJaExisteException("Campanha com mesma url ja existe!");
        }

        return this.campanhaRepository.save(campanha);
    }

    public Optional<Campanha> getCampanha(long id) {
        return this.campanhaRepository.findById(id);
    }

    public Optional<Campanha> getCampanha(String url) {
        return this.campanhaRepository.findByUrl(url);
    }

    public List<Campanha> getCampanhas() {
        return this.campanhaRepository.findAll();
    }
}
