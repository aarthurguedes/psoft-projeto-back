package psoft.backend.projeto.excecoes;

public class DoacaoJaExisteException extends Exception {

    private String msg;

    public DoacaoJaExisteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
