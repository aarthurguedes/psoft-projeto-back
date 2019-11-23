package psoft.backend.projeto.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import psoft.backend.projeto.entidades.Comentario;

@Repository
public interface ComentariosRepository<T, ID extends Serializable> extends JpaRepository<Comentario, Long> {
}
