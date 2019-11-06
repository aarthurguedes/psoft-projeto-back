package psoft.backend.projeto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psoft.backend.projeto.entidades.Campanha;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface CampanhaRepository<T, ID extends Serializable> extends JpaRepository<Campanha, Long> {

    Optional<Campanha> findByUrl(String url);
    Boolean existsByUrl(String url);
}