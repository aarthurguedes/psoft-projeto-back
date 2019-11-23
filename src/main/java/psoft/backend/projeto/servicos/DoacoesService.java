package psoft.backend.projeto.servicos;

import org.springframework.stereotype.Service;
import psoft.backend.projeto.entidades.Doacao;
import psoft.backend.projeto.excecoes.DoacaoJaExisteException;
import psoft.backend.projeto.repositorios.DoacoesRepository;

@Service
public class DoacoesService {

    private DoacoesRepository<Doacao, Long> doacoesRepository;

    public DoacoesService(DoacoesRepository<Doacao, Long> doacoesRepository) {
        this.doacoesRepository = doacoesRepository;
    }

    public Doacao adicionaDoacao(Doacao doacao) throws DoacaoJaExisteException {
        if (this.doacoesRepository.existsById(doacao.getIdDoacao())) {
            throw new DoacaoJaExisteException("Doação com mesmo id ja cadastrada!");
        }

        return this.doacoesRepository.save(doacao);
    }
}
