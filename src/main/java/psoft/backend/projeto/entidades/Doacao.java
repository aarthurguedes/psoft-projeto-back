package psoft.backend.projeto.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idDoacao;

    private double quantiaDoada;

    @Temporal(TemporalType.DATE)
    private Date dataDoacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCampanha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Campanha campanha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;

    public Doacao() {
    }

    public long getIdDoacao() {
        return idDoacao;
    }

    public void setIdDoacao(long idDoacao) {
        this.idDoacao = idDoacao;
    }

    public double getQuantiaDoada() {
        return quantiaDoada;
    }

    public void setQuantiaDoada(double quantiaDoada) {
        this.quantiaDoada = quantiaDoada;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Date getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(Date dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doacao doacao = (Doacao) o;
        return idDoacao == doacao.idDoacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDoacao);
    }
}
