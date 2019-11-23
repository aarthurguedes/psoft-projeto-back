package psoft.backend.projeto.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Curtida {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCurtida;

    private String usuarioDoLike;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCampanha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Campanha campanha;

    public Curtida() {
    }

    public long getIdCurtida() {
        return idCurtida;
    }

    public void setIdCurtida(long idCurtida) {
        this.idCurtida = idCurtida;
    }

    public String getUsuarioDoLike() {
        return usuarioDoLike;
    }

    public void setUsuarioDoLike(String usuarioDoLike) {
        this.usuarioDoLike = usuarioDoLike;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curtida curtida = (Curtida) o;
        return idCurtida == curtida.idCurtida;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurtida);
    }
}
