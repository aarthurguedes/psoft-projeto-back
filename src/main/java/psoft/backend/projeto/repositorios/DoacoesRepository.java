package psoft.backend.projeto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psoft.backend.projeto.entidades.Doacao;

import java.io.Serializable;

@Repository
public interface DoacoesRepository<T, ID extends Serializable> extends JpaRepository<Doacao, Long> {
}
