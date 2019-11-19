package psoft.backend.projeto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.awt.*;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idComentario;
    private String msg;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idCampanha")
    @JsonIgnore
    private Campanha campanha;

    /*
    @OneToMany(mappedBy = "comentario", fetch = FetchType.EAGER)
    private List<Comentario> respostas; */

}
