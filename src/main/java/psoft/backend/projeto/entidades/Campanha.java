package psoft.backend.projeto.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;
    private String url;
    private String descricao;
    private String deadline;
    private String status;
    private double metaArrecadacao;
    private String doacoes;
    private String usuarioDono;
    private String comentarios;
    private int likes;

    public Campanha(String nome, String url, String descricao, String deadline, double metaArrecadacao, String doacoes,
                    String usuarioDono, String comentarios, int likes) {
        this.nome = nome;
        this.url = url;
        this.descricao = descricao;
        this.deadline = deadline;
        this.metaArrecadacao = metaArrecadacao;
        this.doacoes = doacoes;
        this.usuarioDono = usuarioDono;
        this.comentarios = comentarios;
        this.likes = likes;
        this.status = "ativo";
    }

    public Campanha() {
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDeadline() { return deadline; }

    public void setDeadline(String deadline) { this.deadline = deadline; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public double getMetaArrecadacao() { return metaArrecadacao; }

    public void setMetaArrecadacao(double metaArrecadacao) { this.metaArrecadacao = metaArrecadacao; }

    public String getDoacoes() { return doacoes; }

    public void setDoacoes(String doacoes) { this.doacoes = doacoes; }

    public String getUsuarioDono() { return usuarioDono; }

    public void setUsuarioDono(String usuarioDono) { this.usuarioDono = usuarioDono; }

    public String getComentarios() { return comentarios; }

    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

    public int getLikes() { return likes; }

    public void setLikes(int likes) { this.likes = likes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campanha campanha = (Campanha) o;
        return id == campanha.id &&
                Objects.equals(url, campanha.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }
}
