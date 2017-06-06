package br.com.jumplabel.simulador.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the ProdSegdPerg entity.
 */
public class ProdSegdPergDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3877646784794999630L;

	private Long id;

    private ZonedDateTime dtInicVigePerg;

    private ZonedDateTime dtFimVigePerg;


    private Long prodId;
    

    private String prodDtInicVigeProd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getDtInicVigePerg() {
        return dtInicVigePerg;
    }

    public void setDtInicVigePerg(ZonedDateTime dtInicVigePerg) {
        this.dtInicVigePerg = dtInicVigePerg;
    }
    public ZonedDateTime getDtFimVigePerg() {
        return dtFimVigePerg;
    }

    public void setDtFimVigePerg(ZonedDateTime dtFimVigePerg) {
        this.dtFimVigePerg = dtFimVigePerg;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }


    public String getProdDtInicVigeProd() {
        return prodDtInicVigeProd;
    }

    public void setProdDtInicVigeProd(String prodDtInicVigeProd) {
        this.prodDtInicVigeProd = prodDtInicVigeProd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProdSegdPergDTO prodSegdPergDTO = (ProdSegdPergDTO) o;

        if ( ! Objects.equals(id, prodSegdPergDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProdSegdPergDTO{" +
            "id=" + id +
            ", dtInicVigePerg='" + dtInicVigePerg + "'" +
            ", dtFimVigePerg='" + dtFimVigePerg + "'" +
            '}';
    }
}
