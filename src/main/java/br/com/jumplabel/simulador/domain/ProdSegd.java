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
 * A ProdSegd.
 */
@Entity
@Table(name = "TB_PROD_SEGD")
public class ProdSegd implements Serializable {

	private static final long serialVersionUID = -7312564113973406794L;

	@EmbeddedId
    private ProdSegdId pk;

    @Column(name = "DT_INIC_VIGE_PROD_SEGD")
    private ZonedDateTime dtInicVigeProdSegd;

    @Column(name = "DT_FIM_VIGE_PROD_SEGD")
    private ZonedDateTime dtFimVigeProdSegd;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "CD_PROD"),
			@JoinColumn(name = "CD_SUBP") })
    @MapsId("prodId") 
    private Prod prod;
    
    @OneToMany(mappedBy = "prodSegd", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ProdSegdPlan> prodSegdPlans = new HashSet<>();

    @OneToMany(mappedBy = "prodSegd")
    @JsonIgnore
    private Set<ProdSegdPerg> prodSegdPergs = new HashSet<>();

    @OneToMany(mappedBy = "prodSegd")
    @JsonIgnore
    private Set<CnalProdSegd> cnalProdSegds = new HashSet<>();

    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProdSegd that = (ProdSegd) o;

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
        return "ProdSegd{" +
            "pk=" + pk +
            ", dtInicVigeProdSegd='" + dtInicVigeProdSegd + "'" +
            ", dtFimVigeProdSegd='" + dtFimVigeProdSegd + "'" +
            '}';

    }

	public ProdSegdId getPk() {
		return pk;
	}

	public void setPk(ProdSegdId pk) {
		this.pk = pk;
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

	public Prod getProd() {
		return prod;
	}

	public void setProd(Prod prod) {
		this.prod = prod;
	}

	public Set<ProdSegdPlan> getProdSegdPlans() {
		return prodSegdPlans;
	}

	public void setProdSegdPlans(Set<ProdSegdPlan> prodSegdPlans) {
		this.prodSegdPlans = prodSegdPlans;
	}

	public Set<ProdSegdPerg> getProdSegdPergs() {
		return prodSegdPergs;
	}

	public void setProdSegdPergs(Set<ProdSegdPerg> prodSegdPergs) {
		this.prodSegdPergs = prodSegdPergs;
	}

	public Set<CnalProdSegd> getCnalProdSegds() {
		return cnalProdSegds;
	}

	public void setCnalProdSegds(Set<CnalProdSegd> cnalProdSegds) {
		this.cnalProdSegds = cnalProdSegds;
	}
}
