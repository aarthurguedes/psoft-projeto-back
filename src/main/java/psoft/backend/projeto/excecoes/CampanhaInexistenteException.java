package psoft.backend.projeto.excecoes;

public class CampanhaInexistenteException extends Exception {

    private String msg;

    public CampanhaInexistenteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
