package br.com.jumplabel.simulador.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A ProdSegdPlan.
 */
@Entity
@Table(name = "TB_PROD_SEGD_PLAN")
public class ProdSegdPlan implements Serializable {

	private static final long serialVersionUID = -6815296094140818472L;

	@EmbeddedId
    private ProdSegdPlanId pk;

    @Column(name = "DT_INIC_VIGE_PLAN")
    private ZonedDateTime dtInicVigePlan;

    @Column(name = "DT_FIM_VIGE_PLAN")
    private ZonedDateTime dtFimVigePlan;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "CD_PROD"),
			@JoinColumn(name = "CD_SUBP"),
			@JoinColumn(name = "CD_SEGD_DOMI") })
    @MapsId("prodSegdId")
    private ProdSegd prodSegd;

    @OneToMany(mappedBy = "prodSegdPlan", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ProdSegdPlanIs> prodSegdPlanIs = new HashSet<>();

    @OneToMany(mappedBy = "prodSegdPlan", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ProdSegdPlanSegm> prodSegdPlanSegms = new HashSet<>();

    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProdSegdPlan that = (ProdSegdPlan) o;

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
        return "ProdSegdPlan{" +
            "pk=" + pk +
            ", dtInicVigePlan='" + dtInicVigePlan + "'" +
            ", dtFimVigePlan='" + dtFimVigePlan + "'" +
            '}';
    }

	public ProdSegdPlanId getPk() {
		return pk;
	}

	public void setPk(ProdSegdPlanId pk) {
		this.pk = pk;
	}

	public ZonedDateTime getDtInicVigePlan() {
		return dtInicVigePlan;
	}

	public void setDtInicVigePlan(ZonedDateTime dtInicVigePlan) {
		this.dtInicVigePlan = dtInicVigePlan;
	}

	public ZonedDateTime getDtFimVigePlan() {
		return dtFimVigePlan;
	}

	public void setDtFimVigePlan(ZonedDateTime dtFimVigePlan) {
		this.dtFimVigePlan = dtFimVigePlan;
	}

	public ProdSegd getProdSegd() {
		return prodSegd;
	}

	public void setProdSegd(ProdSegd prodSegd) {
		this.prodSegd = prodSegd;
	}

	public Set<ProdSegdPlanIs> getProdSegdPlanIs() {
		return prodSegdPlanIs;
	}

	public void setProdSegdPlanIs(Set<ProdSegdPlanIs> prodSegdPlanIs) {
		this.prodSegdPlanIs = prodSegdPlanIs;
	}

	public Set<ProdSegdPlanSegm> getProdSegdPlanSegms() {
		return prodSegdPlanSegms;
	}

	public void setProdSegdPlanSegms(Set<ProdSegdPlanSegm> prodSegdPlanSegms) {
		this.prodSegdPlanSegms = prodSegdPlanSegms;
	}
}
