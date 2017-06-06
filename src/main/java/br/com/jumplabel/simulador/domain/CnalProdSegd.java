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
 * A CnalProdSegd.
 */
@Entity
@Table(name = "TB_CNAL_PROD_SEGD")
public class CnalProdSegd implements Serializable {

	private static final long serialVersionUID = 8035045260398641886L;

	@EmbeddedId
    private CnalProdSegdId pk;

    @Column(name = "DT_INIC_VIGE_CNAL_VEND")
    private ZonedDateTime dtIniciVigeCnalVend;

    @Column(name = "DT_FIM_VIGE_CNAL_VEND")
    private ZonedDateTime dtFimVigeCnalVend;

    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "CD_PROD"),
			@JoinColumn(name = "CD_SUBP"),
			@JoinColumn(name = "CD_SEGD_DOMI") })
    @MapsId("prodSegdId") 
    private ProdSegd prodSegd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CD_CNAL_DOMI", insertable = false, updatable = false)
    private CntdDomi cntdDomi;
    
	@Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CnalProdSegd that = (CnalProdSegd) o;

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
        return "CnalProdSegd{" +
            "pk=" + pk +
            ", dtIniciVigeCnalVend='" + dtIniciVigeCnalVend + "'" +
            ", dtFimVigeCnalVend='" + dtFimVigeCnalVend + "'" +
            '}';

    }    
    
    public ZonedDateTime getDtIniciVigeCnalVend() {
		return dtIniciVigeCnalVend;
	}

	public void setDtIniciVigeCnalVend(ZonedDateTime dtIniciVigeCnalVend) {
		this.dtIniciVigeCnalVend = dtIniciVigeCnalVend;
	}

	public ZonedDateTime getDtFimVigeCnalVend() {
		return dtFimVigeCnalVend;
	}

	public void setDtFimVigeCnalVend(ZonedDateTime dtFimVigeCnalVend) {
		this.dtFimVigeCnalVend = dtFimVigeCnalVend;
	}

	public CntdDomi getCntdDomi() {
		return cntdDomi;
	}

	public void setCntdDomi(CntdDomi cntdDomi) {
		this.cntdDomi = cntdDomi;
	}

	public CnalProdSegdId getPk() {
		return pk;
	}

	public void setPk(CnalProdSegdId pk) {
		this.pk = pk;
	}

	public ProdSegd getProdSegd() {
		return prodSegd;
	}

	public void setProdSegd(ProdSegd prodSegd) {
		this.prodSegd = prodSegd;
	}
}
