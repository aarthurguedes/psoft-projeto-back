package psoft.backend.projeto.entidades;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Campanha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCampanha;

    private String url;
    private String nome;
    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date deadline;

    private String status;
    private double metaArrecadacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "email")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuarioDono;

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL)
    private List<Curtida> curtidas;

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL)
    private List<Doacao> doacoes;

    public Campanha() {}

    public long getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(long idCampanha) {
        this.idCampanha = idCampanha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getMetaArrecadacao() {
        return metaArrecadacao;
    }

    public void setMetaArrecadacao(double metaArrecadacao) {
        this.metaArrecadacao = metaArrecadacao;
    }

    public Usuario getUsuarioDono() {
        return usuarioDono;
    }

    public void setUsuarioDono(Usuario usuarioDono) {
        this.usuarioDono = usuarioDono;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Curtida> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(List<Curtida> curtidas) {
        this.curtidas = curtidas;
    }

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    public double valorArrecadado() {
        double valorArrecadado = 0;

        for (Doacao doacao : this.doacoes) {
            if (doacao != null) {
                valorArrecadado += doacao.getQuantiaDoada();
            }
        }

        return valorArrecadado;
    }

    public boolean atingiuMeta() {
        return this.valorArrecadado() > this.metaArrecadacao;
    }

    public double diferencaParaMeta() {
        return this.metaArrecadacao - this.valorArrecadado();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campanha campanha = (Campanha) o;
        return idCampanha == campanha.idCampanha;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCampanha);
    }
}




