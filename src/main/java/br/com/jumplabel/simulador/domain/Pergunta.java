package br.com.jumplabel.simulador.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Pergunta.
 */
@Entity
@Table(name = "TB_PERGUNTA")
public class Pergunta implements Serializable {

    private static final long serialVersionUID = 1L;

    public Pergunta() {}
    
    public Pergunta(Long id) {
    	this.id = id;
    }

    @Id
    @Column(name = "CD_PERGUNTA", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PERGUNTA_SEQ")
    @GenericGenerator(
            name = "PERGUNTA_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @Parameter(name = "optimizer", value = "none"),
                    @Parameter(name = "sequence_name", value = "PERGUNTA_SEQ")
            }
    )
    private Long id;

    @NotNull
    @Size(max = 80)
    @Column(name = "DS_PERGUNTA", length = 80, nullable = false)
    private String dsPergunta;

    @JsonIgnore
    @Size(max = 1)
    @Column(name = "IN_EDITAVEL", length = 1)
    private String inEditavel;
    
    @JsonIgnore
    @OneToMany(mappedBy = "pk.pergunta", cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
    private Set<PerguntaTag> perguntaTags = new HashSet<PerguntaTag>(0);

    @OneToMany(mappedBy = "pergunta", cascade=CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Resposta> respostas = new HashSet<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "pergunta", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PerguntaObg> perguntaObg = new HashSet<>();

    @JoinColumn(name="CD_DOMI")
    @ManyToOne(fetch = FetchType.LAZY)
    private Domi domi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CD_TIPO_RESPOSTA")
    private TipoResposta tipoResposta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsPergunta() {
        return dsPergunta;
    }

    public Pergunta dsPergunta(String dsPergunta) {
        this.dsPergunta = dsPergunta;
        return this;
    }

    public Pergunta inEditavel(String inEditavel) {
        this.inEditavel = inEditavel;
        return this;
    }
    
    public void setDsPergunta(String dsPergunta) {
        this.dsPergunta = dsPergunta;
    }

    public Set<PerguntaTag> getPerguntaTags() {
        return perguntaTags;
    }

    public Pergunta perguntaTags(Set<PerguntaTag> perguntaTags) {
        this.perguntaTags = perguntaTags;
        return this;
    }

    public Pergunta addPerguntaTag(PerguntaTag perguntaTag) {
        this.perguntaTags.add(perguntaTag);
        perguntaTag.setPergunta(this);
        return this;
    }

    public Pergunta removePerguntaTag(PerguntaTag perguntaTag) {
        this.perguntaTags.remove(perguntaTag);
        perguntaTag.setPergunta(null);
        return this;
    }

    public void setPerguntaTags(Set<PerguntaTag> perguntaTags) {
        this.perguntaTags = perguntaTags;
    }

	public Pergunta respostas(Set<Resposta> respostas) {
        this.respostas = respostas;
        return this;
    }

    public Pergunta addResposta(Resposta resposta) {
        this.respostas.add(resposta);
        resposta.setPergunta(this);
        return this;
    }

    public Pergunta removeResposta(Resposta resposta) {
        this.respostas.remove(resposta);
        resposta.setPergunta(null);
        return this;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }
    
    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Domi getDomi() {
        return domi;
    }

    public Pergunta domi(Domi domi) {
        this.domi = domi;
        return this;
    }

    public void setDomi(Domi domi) {
        this.domi = domi;
    }

    public TipoResposta getTipoResposta() {
        return tipoResposta;
    }

    public Pergunta tipoResposta(TipoResposta tipoResposta) {
        this.tipoResposta = tipoResposta;
        return this;
    }

    public void setTipoResposta(TipoResposta tipoResposta) {
        this.tipoResposta = tipoResposta;
    }
    
    public Pergunta perguntaObg(Set<PerguntaObg> perguntaObg){
		this.perguntaObg = perguntaObg;
		return this;
	}
    
    public Pergunta addPerguntaObg(PerguntaObg perguntaObg){
    	this.perguntaObg.add(perguntaObg);
    	perguntaObg.setPergunta(this);
    	return this;
    }
    
    public Pergunta removePerguntaObg(PerguntaObg perguntaObg) {
        this.perguntaObg.remove(perguntaObg);
        perguntaObg.setPergunta(null);
        return this;
    }
	
	public Set<PerguntaObg> getPerguntaObg() {
		return perguntaObg;
	}

	public void setPerguntaObg(Set<PerguntaObg> perguntaObg) {
		this.perguntaObg = perguntaObg;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pergunta pergunta = (Pergunta) o;
        if (pergunta.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, pergunta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pergunta{" +
            "id=" + id +
            ", dsPergunta='" + dsPergunta + "'" +
            ", inEditavel='" + inEditavel + "'" +
            '}';
    }

	public String getInEditavel() {
		return inEditavel;
	}

	public void setInEditavel(String inEditavel) {
		this.inEditavel = inEditavel;
	}
	
}
