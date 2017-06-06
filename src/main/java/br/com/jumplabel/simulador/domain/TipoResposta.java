package br.com.jumplabel.simulador.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A TipoResposta.
 */
@Entity
@Table(name = "TB_TIPO_RESPOSTA")
public class TipoResposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_TIPO_RESPOSTA", length = 10, nullable = false)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "DS_TIPO_RESPOSTA", length = 50, nullable = false)
    private String dsTipoResposta;

    @OneToMany(mappedBy = "tipoResposta")
    @JsonIgnore
    private Set<Pergunta> perguntas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsTipoResposta() {
        return dsTipoResposta;
    }

    public TipoResposta dsTipoResposta(String dsTipoResposta) {
        this.dsTipoResposta = dsTipoResposta;
        return this;
    }

    public void setDsTipoResposta(String dsTipoResposta) {
        this.dsTipoResposta = dsTipoResposta;
    }

    public Set<Pergunta> getPerguntas() {
        return perguntas;
    }

    public TipoResposta perguntas(Set<Pergunta> perguntas) {
        this.perguntas = perguntas;
        return this;
    }

    public TipoResposta addPergunta(Pergunta pergunta) {
        this.perguntas.add(pergunta);
        pergunta.setTipoResposta(this);
        return this;
    }

    public TipoResposta removePergunta(Pergunta pergunta) {
        this.perguntas.remove(pergunta);
        pergunta.setTipoResposta(null);
        return this;
    }

    public void setPerguntas(Set<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TipoResposta tipoResposta = (TipoResposta) o;
        if (tipoResposta.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tipoResposta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TipoResposta{" +
            "id=" + id +
            ", dsTipoResposta='" + dsTipoResposta + "'" +
            '}';
    }
}
