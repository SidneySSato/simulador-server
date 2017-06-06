package br.com.jumplabel.simulador.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the ProdSegd entity.
 */
public class ProdSegdDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8687007656226071294L;

	private Long id;

    private ZonedDateTime dtInicVigeProdSegd;

    private ZonedDateTime dtFimVigeProdSegd;


    private Long prodId;
    

    private String prodDsProdSubpVc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getDtInicVigeProdSegd() {
        return dtInicVigeProdSegd;
    }

    public void setDtInicVigeProdSegd(ZonedDateTime dtInicVigeProdSegd) {
        this.dtInicVigeProdSegd = dtInicVigeProdSegd;
    }
    public ZonedDateTime getDtFimVigeProdSegd() {
        return dtFimVigeProdSegd;
    }

    public void setDtFimVigeProdSegd(ZonedDateTime dtFimVigeProdSegd) {
        this.dtFimVigeProdSegd = dtFimVigeProdSegd;
    }

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }


    public String getProdDsProdSubpVc() {
        return prodDsProdSubpVc;
    }

    public void setProdDsProdSubpVc(String prodDsProdSubpVc) {
        this.prodDsProdSubpVc = prodDsProdSubpVc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProdSegdDTO prodSegdDTO = (ProdSegdDTO) o;

        if ( ! Objects.equals(id, prodSegdDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProdSegdDTO{" +
            "id=" + id +
            ", dtInicVigeProdSegd='" + dtInicVigeProdSegd + "'" +
            ", dtFimVigeProdSegd='" + dtFimVigeProdSegd + "'" +
            '}';
    }
}
