package psoft.backend.projeto.repositorios;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psoft.backend.projeto.entidades.Comentario;

@Repository
public interface ComentariosRepository<T, ID extends Serializable> extends JpaRepository<Comentario, Long> {
	List<Comentario> findByCampanha_Id(Long id);
}
