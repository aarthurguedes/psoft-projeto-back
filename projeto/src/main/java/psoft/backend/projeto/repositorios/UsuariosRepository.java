package psoft.backend.projeto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import psoft.backend.projeto.entidades.Usuario;

import java.io.Serializable;

@Repository
public interface UsuariosRepository<T, ID extends Serializable> extends JpaRepository<Usuario, String> {
}
