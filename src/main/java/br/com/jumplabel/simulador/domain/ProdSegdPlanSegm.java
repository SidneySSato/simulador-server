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
 * A ProdSegdPlanSegm.
 */
@Entity
@Table(name = "TB_PROD_SEGD_PLAN_SEGM")
public class ProdSegdPlanSegm implements Serializable {

    private static final long serialVersionUID = 1L;

	@EmbeddedId
    private ProdSegdPlanSegmId pk;

    @Column(name = "DT_INIC_VIGE_SEGM")
    private ZonedDateTime dtInicVigeSegm;

    @Column(name = "DT_FIM_VIGE_SEGM")
    private ZonedDateTime dtFimVigeSegm;

    /**
     * A ordem do JoinColumn interfere no match da montagem do Where
     * Deve ser levado em conta a ordem de definicao dos atributos no atributo prodSegdPlanId
     */
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
		@JoinColumn(name = "CD_PLAN_DOMI"),
		@JoinColumn(name = "CD_PROD"),
		@JoinColumn(name = "CD_SUBP"),
		@JoinColumn(name = "CD_SEGD_DOMI"),
	})
    @MapsId("prodSegdPlanId")
    private ProdSegdPlan prodSegdPlan;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cntdDomiId") 
    @JoinColumn(name="CD_PLAN_DOMI")
    private CntdDomi cntdDomi;
    
    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProdSegdPlanSegm that = (ProdSegdPlanSegm) o;

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
        return "ProdSegdPlanSegm{" +
            "pk=" + pk +
            ", dtInicVigeSegm='" + dtInicVigeSegm + "'" +
            ", dtFimVigeSegm='" + dtFimVigeSegm + "'" +
            '}';
    }

	public ProdSegdPlanSegmId getPk() {
		return pk;
	}

	public void setPk(ProdSegdPlanSegmId pk) {
		this.pk = pk;
	}

	public ZonedDateTime getDtInicVigeSegm() {
		return dtInicVigeSegm;
	}

	public void setDtInicVigeSegm(ZonedDateTime dtInicVigeSegm) {
		this.dtInicVigeSegm = dtInicVigeSegm;
	}

	public ZonedDateTime getDtFimVigeSegm() {
		return dtFimVigeSegm;
	}

	public void setDtFimVigeSegm(ZonedDateTime dtFimVigeSegm) {
		this.dtFimVigeSegm = dtFimVigeSegm;
	}

	public ProdSegdPlan getProdSegdPlan() {
		return prodSegdPlan;
	}

	public void setProdSegdPlan(ProdSegdPlan prodSegdPlan) {
		this.prodSegdPlan = prodSegdPlan;
	}

	public CntdDomi getCntdDomi() {
		return cntdDomi;
	}

	public void setCntdDomi(CntdDomi cntdDomi) {
		this.cntdDomi = cntdDomi;
	}
}
