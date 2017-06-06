package br.com.jumplabel.simulador.domain;


import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * A ProdSegdPerg.
 */
@Entity
@Table(name = "TB_PROD_SEGD_PERG")
public class ProdSegdPerg implements Serializable {

    private static final long serialVersionUID = 1L;

	@EmbeddedId
    private ProdSegdPergId pk;

    @Column(name = "DT_INIC_VIGE_PERG")
    private ZonedDateTime dtInicVigePerg;

    @Column(name = "DT_FIM_VIGE_PERG")
    private ZonedDateTime dtFimVigePerg;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "CD_PROD"),
			@JoinColumn(name = "CD_SUBP"),
			@JoinColumn(name = "CD_SEGD_DOMI") })
    @MapsId("prodSegdId") 
    private ProdSegd prodSegd;

    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProdSegdPerg that = (ProdSegdPerg) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;
		return true;    	
    }

    @Override
    public int hashCode() {
    	return (getPk() != null ? getPk().hashCode() : 0);
    }

    @Override
    public String toString() {
        return "ProdSegdPerg{" +
            "pk=" + pk +
            ", dtInicVigePerg='" + dtInicVigePerg + "'" +
            ", dtFimVigePerg='" + dtFimVigePerg + "'" +
            '}';
    }

	public ProdSegdPergId getPk() {
		return pk;
	}

	public void setPk(ProdSegdPergId pk) {
		this.pk = pk;
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

	public ProdSegd getProdSegd() {
		return prodSegd;
	}

	public void setProdSegd(ProdSegd prodSegd) {
		this.prodSegd = prodSegd;
	}
}
