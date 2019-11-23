package psoft.backend.projeto.servicos;

import org.springframework.stereotype.Service;
import psoft.backend.projeto.entidades.Comentario;
import psoft.backend.projeto.excecoes.ComentarioInexistenteException;
import psoft.backend.projeto.excecoes.ComentarioJaExisteException;
import psoft.backend.projeto.repositorios.ComentariosRepository;

import java.util.Optional;

@Service
public class ComentariosService {

    private ComentariosRepository<Comentario, Long> comentariosRepository;

    public ComentariosService(ComentariosRepository<Comentario, Long> comentariosRepository) {
        this.comentariosRepository = comentariosRepository;
    }

    public Comentario adicionaComentario(Comentario comentario) throws ComentarioJaExisteException {
        if (this.comentariosRepository.existsById(comentario.getIdComentario())) {
            throw new ComentarioJaExisteException("Comentario com mesmo id ja existe!");
        }

        return this.comentariosRepository.save(comentario);
    }

    public Comentario removeComentario(Long id) throws ComentarioInexistenteException {
        Optional<Comentario> comentario = this.comentariosRepository.findById(id);

        if (!comentario.isPresent()) {
            throw new ComentarioInexistenteException("Comentario inexistente!");
        }

        comentario.get().setApagado(true);
        this.comentariosRepository.save(comentario.get());
        return comentario.get();
    }

    public Optional<Comentario> getComentario(Long id) {
        return this.comentariosRepository.findById(id);
    }

}
