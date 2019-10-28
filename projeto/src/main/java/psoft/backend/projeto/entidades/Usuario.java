package psoft.backend.projeto.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Usuario {

    @Id
    private String email;

    private String primeiroNome;
    private String ultimoNome;
    private String numCartaoCredito;
    private String senha;

    public Usuario(String email, String primeiroNome, String ultimoNome, String numCartaoCredito, String senha) {
        this.email = email;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.numCartaoCredito = numCartaoCredito;
        this.senha = senha;
    }

    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getNumCartaoCredito() {
        return numCartaoCredito;
    }

    public void setNumCartaoCredito(String numCartaoCredito) {
        this.numCartaoCredito = numCartaoCredito;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return email.equals(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", primeiroNome='" + primeiroNome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", numCartaoCredito='" + numCartaoCredito + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
