package psoft.backend.projeto.excecoes;

public class CurtidaJaExisteException extends Exception {

    private String msg;

    public CurtidaJaExisteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
