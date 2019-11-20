package psoft.backend.projeto.entidades;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String msg;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    private Campanha campanha;

    @OneToOne
    private Comentario comentarioPai;

    @OneToMany(mappedBy = "comentarioPai")
    private List<Comentario> respostas;

    public Comentario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comentario that = (Comentario) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

