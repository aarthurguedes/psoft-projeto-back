package psoft.backend.projeto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psoft.backend.projeto.entidades.Curtida;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CurtidasRepository<T, ID extends Serializable> extends JpaRepository<Curtida, Long> {

    List<Curtida> findByCampanhaIdCampanha(Long idCampanha);
}