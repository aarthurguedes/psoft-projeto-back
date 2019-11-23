package psoft.backend.projeto.excecoes;

public class ComentarioInexistenteException extends Exception {

    private String msg;

    public ComentarioInexistenteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
