package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Size;


/**
 * A DTO for the CompDomiRamo entity.
 */
public class CompDomiRamoDTO implements Serializable {

	private static final long serialVersionUID = -4019785585909789806L;

	private Long id;

    @Size(max = 1)
    private String inRamoGene;

    @Size(max = 4)
    private String cdGrupSusep;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getInRamoGene() {
        return inRamoGene;
    }

    public void setInRamoGene(String inRamoGene) {
        this.inRamoGene = inRamoGene;
    }
    public String getCdGrupSusep() {
        return cdGrupSusep;
    }

    public void setCdGrupSusep(String cdGrupSusep) {
        this.cdGrupSusep = cdGrupSusep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompDomiRamoDTO compDomiRamoDTO = (CompDomiRamoDTO) o;

        if ( ! Objects.equals(id, compDomiRamoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CompDomiRamoDTO{" +
            "id=" + id +
            ", inRamoGene='" + inRamoGene + "'" +
            ", cdGrupSusep='" + cdGrupSusep + "'" +
            '}';
    }
}
