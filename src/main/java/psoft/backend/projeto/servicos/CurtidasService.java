package psoft.backend.projeto.servicos;

import org.springframework.stereotype.Service;
import psoft.backend.projeto.entidades.Curtida;
import psoft.backend.projeto.excecoes.CurtidaInexistenteException;
import psoft.backend.projeto.excecoes.CurtidaJaExisteException;
import psoft.backend.projeto.repositorios.CurtidasRepository;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurtidasService {

    private CurtidasRepository<Curtida, Long> curtidasRepository;

    public CurtidasService(CurtidasRepository<Curtida, Long> curtidasRepository) {
        this.curtidasRepository = curtidasRepository;
    }

    private boolean usuarioCurtiuCampanha(String emailUsuario, Long idCampanha) {
        return this.curtidasRepository.findByCampanhaIdCampanha(idCampanha).stream()
                .filter(curtida -> curtida.getUsuarioDoLike().equals(emailUsuario))
                .collect(Collectors.toList()).size() != 0;
    }

    public Curtida adicionaLike(Curtida curtida) throws CurtidaJaExisteException {
        if (this.curtidasRepository.existsById(curtida.getIdCurtida()) &&
                usuarioCurtiuCampanha(curtida.getUsuarioDoLike(), curtida.getCampanha().getIdCampanha())) {
            throw new CurtidaJaExisteException("Curtida ja existe!");
        }

        return this.curtidasRepository.save(curtida);
    }

    public Curtida removeLike(Long id) throws CurtidaInexistenteException {
        Optional<Curtida> curtida = this.curtidasRepository.findById(id);

        if (!curtida.isPresent()) {
            throw new CurtidaInexistenteException("Curtida inexistente!");
        }

        this.curtidasRepository.delete(curtida.get());
        return curtida.get();
    }

    public Optional<Curtida> getCurtida(Long id) {
        return this.curtidasRepository.findById(id);
    }
}
