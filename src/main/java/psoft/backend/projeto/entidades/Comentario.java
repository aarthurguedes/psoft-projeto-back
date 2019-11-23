package psoft.backend.projeto.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idComentario;

    private String msg;
    private String emailUsuarioDono;
    private boolean apagado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCampanha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Campanha campanha;

    @OneToOne
    private Comentario comentarioPai;

    @OneToMany(mappedBy = "comentarioPai")
    private List<Comentario> respostas;

    public Comentario() {
    }

    public long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(long id) {
        this.idComentario = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Comentario getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(Comentario comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    public List<Comentario> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Comentario> respostas) {
        this.respostas = respostas;
    }

    public String getEmailUsuarioDono() {
        return emailUsuarioDono;
    }

    public boolean isApagado() {
        return apagado;
    }

    public void setApagado(boolean apagado) {
        this.apagado = apagado;
    }

    public void setEmailUsuarioDono(String emailUsuarioDono) {
        this.emailUsuarioDono = emailUsuarioDono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return idComentario == that.idComentario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComentario);
    }
}
