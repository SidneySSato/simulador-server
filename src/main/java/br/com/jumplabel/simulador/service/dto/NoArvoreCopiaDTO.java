package br.com.jumplabel.simulador.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the NoArvoreCopia entity.
 */
public class NoArvoreCopiaDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1194605862971985458L;

	private Long id;

    @Size(max = 60)
    private String dsNoArvoreCopia;

    private ZonedDateTime dtCopia;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDsNoArvoreCopia() {
        return dsNoArvoreCopia;
    }

    public void setDsNoArvoreCopia(String dsNoArvoreCopia) {
        this.dsNoArvoreCopia = dsNoArvoreCopia;
    }
    public ZonedDateTime getDtCopia() {
        return dtCopia;
    }

    public void setDtCopia(ZonedDateTime dtCopia) {
        this.dtCopia = dtCopia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NoArvoreCopiaDTO noArvoreCopiaDTO = (NoArvoreCopiaDTO) o;

        if ( ! Objects.equals(id, noArvoreCopiaDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "NoArvoreCopiaDTO{" +
            "id=" + id +
            ", dsNoArvoreCopia='" + dsNoArvoreCopia + "'" +
            ", dtCopia='" + dtCopia + "'" +
            '}';
    }
}
