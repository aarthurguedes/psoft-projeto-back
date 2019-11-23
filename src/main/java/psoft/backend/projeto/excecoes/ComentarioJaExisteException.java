package psoft.backend.projeto.excecoes;

public class ComentarioJaExisteException extends Exception {

    private String msg;

    public ComentarioJaExisteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
