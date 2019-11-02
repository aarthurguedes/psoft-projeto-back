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
    private int id;

    private String nome;

    @Id
    private String url;

    private String descricao;
    private Date deadline;
    private String status;
    private double metaArrecadacao;
    private List<String> doacoes;
    private Usuario usuarioDono;
    private List<String> comentarios;
    private int likes;

    public Campanha(String nome, String url, String descricao, Date deadline, double metaArrecadacao, List<String> doacoes,
                    Usuario usuarioDono, List<String> comentarios, int likes) {
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

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Date getDeadline() { return deadline; }

    public void setDeadline(Date deadline) { this.deadline = deadline; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public double getMetaArrecadacao() { return metaArrecadacao; }

    public void setMetaArrecadacao(double metaArrecadacao) { this.metaArrecadacao = metaArrecadacao; }

    public List<String> getDoacoes() { return doacoes; }

    public void setDoacoes(List<String> doacoes) { this.doacoes = doacoes; }

    public Usuario getUsuarioDono() { return usuarioDono; }

    public void setUsuarioDono(Usuario usuarioDono) { this.usuarioDono = usuarioDono; }

    public List<String> getComentarios() { return comentarios; }

    public void setComentarios(List<String> comentarios) { this.comentarios = comentarios; }

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