package psoft.backend.projeto.excecoes;

public class CurtidaInexistenteException extends Exception{

    private String msg;

    public CurtidaInexistenteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
