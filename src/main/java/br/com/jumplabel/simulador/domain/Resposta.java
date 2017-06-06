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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Resposta.
 */
@Entity
@Table(name = "TB_RESPOSTA")
public class Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    public Resposta() {}
    
    public Resposta(Long id) {
    	this.id = id;
    }


    @Id
    @Column(name = "CD_RESPOSTA", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="RESPOSTA_SEQ")
    @GenericGenerator(
            name = "RESPOSTA_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @Parameter(name = "optimizer", value = "none"),
                    @Parameter(name = "sequence_name", value = "RESPOSTA_SEQ")
            }
    )
    private Long id;

    @Size(max = 80)
    @Column(name = "DS_RESPOSTA", length = 80, nullable = false)
    private String dsResposta;

    @Size(max = 80)
    @Column(name = "DS_CATEGORIA", length = 80)
    private String dsCategoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CD_PERGUNTA")
    private Pergunta pergunta;
    
    @OneToMany(mappedBy = "resposta", cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    @JsonIgnore
    private Set<RespCorporativa> respCorporativas = new HashSet<RespCorporativa>(0);

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    @PrimaryKeyJoinColumn
    private RangeResposta rangeResposta;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    @PrimaryKeyJoinColumn
    private RespostaIs respostaIs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsResposta() {
        return dsResposta;
    }

    public Resposta dsResposta(String dsResposta) {
        this.dsResposta = dsResposta;
        return this;
    }

    public void setDsResposta(String dsResposta) {
        this.dsResposta = dsResposta;
    }

    public String getDsCategoria() {
        return dsCategoria;
    }

    public Resposta dsCategoria(String dsCategoria) {
        this.dsCategoria = dsCategoria;
        return this;
    }
    
    public void setDsCategoria(String dsCategoria) {
        this.dsCategoria = dsCategoria;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public Resposta pergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
        return this;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resposta resposta = (Resposta) o;
        if (resposta.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, resposta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Resposta{" +
            "id=" + id +
            ", dsResposta='" + dsResposta + "'" +
            ", dsCategoria='" + dsCategoria + "'" +
            '}';
    }

	public RangeResposta getRangeResposta() {
		return rangeResposta;
	}

	public void setRangeResposta(RangeResposta rangeResposta) {
		this.rangeResposta = rangeResposta;
	}

	public RespostaIs getRespostaIs() {
		return respostaIs;
	}

	public void setRespostaIs(RespostaIs respostaIs) {
		this.respostaIs = respostaIs;
	}

    public Resposta respCorporativas(Set<RespCorporativa> respCorporativas) {
        this.respCorporativas = respCorporativas;
        return this;
    }

    public Resposta addRespCorporativa(RespCorporativa respCorporativa) {
        this.respCorporativas.add(respCorporativa);
        respCorporativa.setResposta(this);
        return this;
    }

    public Resposta removeRespCorporativa(RespCorporativa respCorporativa) {
        this.respCorporativas.remove(respCorporativa);
        respCorporativa.setResposta(null);
        return this;
    }
	
	public Set<RespCorporativa> getRespCorporativas() {
		return respCorporativas;
	}

	public void setRespCorporativas(Set<RespCorporativa> respCorporativas) {
		this.respCorporativas = respCorporativas;
	}
}