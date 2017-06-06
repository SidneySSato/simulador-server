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
 * A CompDomiRamo.
 */
@Entity
@Table(name = "TB_COMP_DOMI_RAMO")
public class CompDomiRamo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_CNTD_DOMI_RAMO", length = 10, nullable = false)
    private Long id;

    @Size(max = 1)
    @Column(name = "IN_RAMO_GENE", length = 1)
    private String inRamoGene;

    @Size(max = 4)
    @Column(name = "CD_GRUP_SUSEP", length = 4)
    private String cdGrupSusep;

    /**
     * TODO verificar se vai ser utilizado essa entidade, pois na busca de familias a partir de produto,
     * esta sendo realizado selects a mais para buscar o CompDomiSegd e CompDomiRamo
     */
    
//    @MapsId
//    @OneToOne(mappedBy = "compDomiRamo")
//    @JoinColumn(name = "CD_CNTD_DOMI_RAMO")
//    @JsonIgnore
    private CntdDomi cntdDomi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInRamoGene() {
        return inRamoGene;
    }

    public CompDomiRamo inRamoGene(String inRamoGene) {
        this.inRamoGene = inRamoGene;
        return this;
    }

    public void setInRamoGene(String inRamoGene) {
        this.inRamoGene = inRamoGene;
    }

    public String getCdGrupSusep() {
        return cdGrupSusep;
    }

    public CompDomiRamo cdGrupSusep(String cdGrupSusep) {
        this.cdGrupSusep = cdGrupSusep;
        return this;
    }

    public void setCdGrupSusep(String cdGrupSusep) {
        this.cdGrupSusep = cdGrupSusep;
    }

    public CntdDomi getCntdDomi() {
        return cntdDomi;
    }

    public CompDomiRamo cntdDomi(CntdDomi cntdDomi) {
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
        CompDomiRamo compDomiRamo = (CompDomiRamo) o;
        if (compDomiRamo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, compDomiRamo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CompDomiRamo{" +
            "id=" + id +
            ", inRamoGene='" + inRamoGene + "'" +
            ", cdGrupSusep='" + cdGrupSusep + "'" +
            '}';
    }
}
