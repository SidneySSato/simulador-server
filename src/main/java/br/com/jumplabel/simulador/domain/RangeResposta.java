package br.com.jumplabel.simulador.domain;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * A RangeResposta.
 */
@Entity
@Table(name = "TB_RANGE_RESPOSTA")
public class RangeResposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_RANGE_RESPOSTA", length = 10, nullable = false)
    private Long id;

    @Column(name = "RANGE_INICIO")
    private Long rangeInicio;

    @Column(name = "RANGE_FINAL")
    private Long rangeFinal;
    
    @MapsId
    @OneToOne(mappedBy = "rangeResposta")
    @JoinColumn(name = "CD_RANGE_RESPOSTA")
    @JsonIgnore
    private Resposta resposta;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RangeResposta rangeResposta = (RangeResposta) o;
        if (rangeResposta.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, rangeResposta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RangeResposta{" +
            "id=" + id +
            ", rangeInicio='" + rangeInicio + "'" +
            ", rangeFinal='" + rangeFinal + "'" +
            '}';
    }   
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRangeInicio() {
        return rangeInicio;
    }

    public RangeResposta rangeInicio(Long rangeInicio) {
        this.rangeInicio = rangeInicio;
        return this;
    }

    public void setRangeInicio(Long rangeInicio) {
        this.rangeInicio = rangeInicio;
    }

    public Long getRangeFinal() {
        return rangeFinal;
    }

    public RangeResposta rangeFinal(Long rangeFinal) {
        this.rangeFinal = rangeFinal;
        return this;
    }

    public void setRangeFinal(Long rangeFinal) {
        this.rangeFinal = rangeFinal;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public RangeResposta resposta(Resposta resposta) {
        this.resposta = resposta;
        return this;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }
}