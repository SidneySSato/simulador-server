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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A CompDomiSegd.
 */
@Entity
@Table(name = "TB_COMP_DOMI_SEGD")
public class CompDomiSegd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_CNTD_DOMI_SEGD", length = 10, nullable = false)
    private Long id;

    @Column(name = "NR_CNPJ_SEGD")
    private Long nrCnpjSegd;

    @Size(max = 5)
    @Column(name = "CD_SUSEP_SEGD", length = 5)
    private String cdSusepSegd;

    /**
     * TODO verificar se vai ser utilizado essa entidade, pois na busca de familias a partir de produto,
     * esta sendo realizado selects a mais para buscar o CompDomiSegd e CompDomiRamo
     */
    
//    @MapsId
//    @OneToOne(mappedBy = "compDomiSegd")
//    @JoinColumn(name = "CD_CNTD_DOMI_SEGD")
//    @JsonIgnore
    private CntdDomi cntdDomi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNrCnpjSegd() {
        return nrCnpjSegd;
    }

    public CompDomiSegd nrCnpjSegd(Long nrCnpjSegd) {
        this.nrCnpjSegd = nrCnpjSegd;
        return this;
    }

    public void setNrCnpjSegd(Long nrCnpjSegd) {
        this.nrCnpjSegd = nrCnpjSegd;
    }

    public String getCdSusepSegd() {
        return cdSusepSegd;
    }

    public CompDomiSegd cdSusepSegd(String cdSusepSegd) {
        this.cdSusepSegd = cdSusepSegd;
        return this;
    }

    public void setCdSusepSegd(String cdSusepSegd) {
        this.cdSusepSegd = cdSusepSegd;
    }

    public CntdDomi getCntdDomi() {
        return cntdDomi;
    }

    public CompDomiSegd cntdDomi(CntdDomi cntdDomi) {
        this.cntdDomi = cntdDomi;
        return this;
    }

    public void setCntdDomi(CntdDomi cntdDomi) {
        this.cntdDomi = cntdDomi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompDomiSegd compDomiSegd = (CompDomiSegd) o;
        if (compDomiSegd.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, compDomiSegd.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CompDomiSegd{" +
            "id=" + id +
            ", nrCnpjSegd='" + nrCnpjSegd + "'" +
            ", cdSusepSegd='" + cdSusepSegd + "'" +
            '}';
    }
}
