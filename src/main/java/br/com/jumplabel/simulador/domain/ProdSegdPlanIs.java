package br.com.jumplabel.simulador.domain;


import java.io.Serializable;
import java.math.BigDecimal;

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
 * A ProdSegdPlanIs.
 */
@Entity
@Table(name = "TB_PROD_SEGD_PLAN_IS")
public class ProdSegdPlanIs implements Serializable {

	private static final long serialVersionUID = -6547842534075542711L;

	@EmbeddedId
    private ProdSegdPlanIsId pk;

    @Column(name = "VL_CAPI_MAXI", precision=15, scale=2)
    private BigDecimal vlCapiMaxi;

    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "CD_PROD"),
			@JoinColumn(name = "CD_SUBP"),
			@JoinColumn(name = "CD_SEGD_DOMI"),
			@JoinColumn(name = "CD_PLAN_DOMI")})
    @MapsId("prodSegdPlanId")
    private ProdSegdPlan prodSegdPlan;

    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProdSegdPlanIs that = (ProdSegdPlanIs) o;

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
        return "ProdSegdPlanIs{" +
            "pk=" + pk +
            ", vlCapiMaxi='" + vlCapiMaxi + "'" +
            '}';
    }

	public ProdSegdPlanIsId getPk() {
		return pk;
	}

	public void setPk(ProdSegdPlanIsId pk) {
		this.pk = pk;
	}

	public BigDecimal getVlCapiMaxi() {
		return vlCapiMaxi;
	}

	public void setVlCapiMaxi(BigDecimal vlCapiMaxi) {
		this.vlCapiMaxi = vlCapiMaxi;
	}

	public ProdSegdPlan getProdSegdPlan() {
		return prodSegdPlan;
	}

	public void setProdSegdPlan(ProdSegdPlan prodSegdPlan) {
		this.prodSegdPlan = prodSegdPlan;
	}
}
