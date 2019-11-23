package psoft.backend.projeto.excecoes;

public class UsuarioInexistenteException extends Exception {

    private String msg;

    public UsuarioInexistenteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
