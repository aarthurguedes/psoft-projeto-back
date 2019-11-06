package psoft.backend.projeto.excecoes;

public class UsuarioJaExisteException extends Exception{
    private String msg;

    public UsuarioJaExisteException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
