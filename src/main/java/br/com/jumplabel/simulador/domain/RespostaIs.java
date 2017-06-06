package br.com.jumplabel.simulador.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RespostaIs.
 */
@Entity
@Table(name = "TB_RESPOSTA_IS")
public class RespostaIs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_RANGE_RESPOSTA", length = 10, nullable = false)
    private Long id;

    @Size(max = 4)
    @Column(name = "cd_tipo_is", length = 4)
    private String cdTipoIs;

    @MapsId
    @OneToOne(mappedBy = "respostaIs")
    @JoinColumn(name = "CD_RESPOSTA_IS")
    @JsonIgnore
    private Resposta resposta;
    
    @OneToOne
    @JoinColumn(unique = true, name="CD_PERGUNTA_1")
    private Pergunta pergunta1;

    @OneToOne
    @JoinColumn(unique = true, name="CD_PERGUNTA_2")
    private Pergunta pergunta2;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RespostaIs respostaIs = (RespostaIs) o;
        if (respostaIs.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, respostaIs.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RespostaIs{" +
            "id=" + id +
            ", cdTipoIs='" + cdTipoIs + "'" +
            '}';
    }   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCdTipoIs() {
        return cdTipoIs;
    }

    public RespostaIs cdTipoIs(String cdTipoIs) {
        this.cdTipoIs = cdTipoIs;
        return this;
    }

    public void setCdTipoIs(String cdTipoIs) {
        this.cdTipoIs = cdTipoIs;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public RespostaIs resposta(Resposta resposta) {
        this.resposta = resposta;
        return this;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }

    public Pergunta getPergunta1() {
        return pergunta1;
    }

    public RespostaIs pergunta1(Pergunta pergunta) {
        this.pergunta1 = pergunta;
        return this;
    }

    public void setPergunta1(Pergunta pergunta) {
        this.pergunta1 = pergunta;
    }

    public Pergunta getPergunta2() {
        return pergunta2;
    }

    public RespostaIs pergunta2(Pergunta pergunta) {
        this.pergunta2 = pergunta;
        return this;
    }

    public void setPergunta2(Pergunta pergunta) {
        this.pergunta2 = pergunta;
    }
}