package psoft.backend.projeto.servicos;

import org.springframework.stereotype.Service;
import psoft.backend.projeto.comparators.ComparadorDataCampanha;
import psoft.backend.projeto.comparators.ComparadorDiferencaParaMeta;
import psoft.backend.projeto.comparators.ComparadorLikeCampanha;
import psoft.backend.projeto.entidades.Campanha;
import psoft.backend.projeto.excecoes.CampanhaInexistenteException;
import psoft.backend.projeto.excecoes.CampanhaJaExisteException;
import psoft.backend.projeto.repositorios.CampanhaRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CampanhaService {

    private CampanhaRepository<Campanha, Long> campanhaRepository;

    public CampanhaService(CampanhaRepository<Campanha, Long> campanhaRepository) {
        this.campanhaRepository = campanhaRepository;
    }

    private void atualizaStatus(Campanha campanha) {
        Date deadline = campanha.getDeadline();
        Date dataAtual = new Date();

        if (!campanha.atingiuMeta() && deadline.compareTo(dataAtual) == 0) {
            campanha.setStatus("vencida");
            this.campanhaRepository.save(campanha);
        } else if (campanha.atingiuMeta() && deadline.compareTo(dataAtual) == 0) {
            campanha.setStatus("concluída");
            this.campanhaRepository.save(campanha);
        }
    }

    public Campanha cadastraCampanha(Campanha campanha) throws CampanhaJaExisteException {
        if (campanhaRepository.existsByUrl(campanha.getUrl())) {
            throw new CampanhaJaExisteException("Campanha com mesma url ja existe!");
        }

        return this.campanhaRepository.save(campanha);
    }

    public Optional<Campanha> getCampanha(String url) throws CampanhaInexistenteException {
        if (!this.campanhaRepository.existsByUrl(url)) {
            throw new CampanhaInexistenteException("Campanha inexistente");
        }

        Optional<Campanha> campanha = this.campanhaRepository.findByUrl(url);
        atualizaStatus(campanha.get());
        return campanha;
    }

    public List<Campanha> listaCampanhasPorCriterio(String criterio) {
        List<Campanha> campanhas = new ArrayList<>(this.campanhaRepository.findAll());

        if (criterio.equals("diferencaMeta")) {
            Collections.sort(campanhas, new ComparadorDiferencaParaMeta());
            return campanhas.stream().filter(campanha -> {
                atualizaStatus(campanha);
                return campanha.getStatus().equals("ativa");
            }).collect(Collectors.toList());
        } else if (criterio.equals("like")) {
            Collections.sort(campanhas, new ComparadorLikeCampanha());
            return campanhas.stream().filter(campanha -> {
                atualizaStatus(campanha);
                return campanha.getStatus().equals("ativa");
            }).collect(Collectors.toList());
        } else {
            Collections.sort(campanhas, new ComparadorDataCampanha());
            return campanhas.stream().filter(campanha -> {
                atualizaStatus(campanha);
                return campanha.getStatus().equals("ativa");
            }).collect(Collectors.toList());
        }
    }

    public List<Campanha> getCampanhasPeloNome(String substring, boolean retornarTodas) {
        if (retornarTodas) {
            return this.campanhaRepository.findByNomeContainingIgnoreCase(substring);
        } else {
            return this.campanhaRepository.findByNomeContainingIgnoreCase(substring).stream()
                    .filter(campanha -> campanha.getStatus().equals("ativa"))
                    .collect(Collectors.toList());
        }
    }
}
