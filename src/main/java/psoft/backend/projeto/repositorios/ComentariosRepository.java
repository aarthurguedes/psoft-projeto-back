package psoft.backend.projeto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psoft.backend.projeto.entidades.Comentario;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ComentariosRepository<T, ID extends Serializable> extends JpaRepository<Comentario, Long> {

    List<Comentario> findByCampanhaIdCampanha(long id);
}
