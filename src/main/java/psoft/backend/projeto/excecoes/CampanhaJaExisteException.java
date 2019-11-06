package psoft.backend.projeto.excecoes;

public class CampanhaJaExisteException extends Exception {
    private String msg;

    public CampanhaJaExisteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
