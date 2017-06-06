package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Size;


/**
 * A DTO for the CompDomiSegd entity.
 */
public class CompDomiSegdDTO implements Serializable {

	private static final long serialVersionUID = -6547585600584411551L;

	private Long id;

    private Long nrCnpjSegd;

    @Size(max = 5)
    private String cdSusepSegd;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getNrCnpjSegd() {
        return nrCnpjSegd;
    }

    public void setNrCnpjSegd(Long nrCnpjSegd) {
        this.nrCnpjSegd = nrCnpjSegd;
    }
    public String getCdSusepSegd() {
        return cdSusepSegd;
    }

    public void setCdSusepSegd(String cdSusepSegd) {
        this.cdSusepSegd = cdSusepSegd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompDomiSegdDTO compDomiSegdDTO = (CompDomiSegdDTO) o;

        if ( ! Objects.equals(id, compDomiSegdDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CompDomiSegdDTO{" +
            "id=" + id +
            ", nrCnpjSegd='" + nrCnpjSegd + "'" +
            ", cdSusepSegd='" + cdSusepSegd + "'" +
            '}';
    }
}
