package psoft.backend.projeto.entidades;

import javax.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String url;
    private String nome;
    private String descricao;
    private Date deadline;
    private String status;
    private double metaArrecadacao;
    private String doacoes;
    private String usuarioDono;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "campanha", fetch = FetchType.EAGER)
    private List<Comentario> comentarios;

    private int likes;

    public Campanha(String nome, String url, String descricao, String status, String deadline, double metaArrecadacao,
                    String doacoes, String usuarioDono, List<Comentario> comentarios, int likes) throws ParseException {
        this.nome = nome;
        this.url = url;
        this.descricao = descricao;
        Date data = new SimpleDateFormat("yyyy-MM-dd").parse(deadline);
        this.deadline = data;
        this.metaArrecadacao = metaArrecadacao;
        this.doacoes = doacoes;
        this.usuarioDono = usuarioDono;
        this.comentarios = comentarios;
        this.likes = likes;
        this.status = status;
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

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public double getMetaArrecadacao() { return metaArrecadacao; }

    public void setMetaArrecadacao(double metaArrecadacao) { this.metaArrecadacao = metaArrecadacao; }

    public String getUsuarioDono() { return usuarioDono; }

    public void setUsuarioDono(String usuarioDono) { this.usuarioDono = usuarioDono; }

    public int getLikes() { return likes; }

    public void setLikes(int likes) { this.likes = likes; }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

    public String getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(String doacoes) {
        this.doacoes = doacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campanha campanha = (Campanha) o;
        return id == campanha.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
