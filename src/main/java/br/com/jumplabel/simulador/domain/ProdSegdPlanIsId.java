package br.com.jumplabel.simulador.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProdSegdPlanIsId implements java.io.Serializable {

	private static final long serialVersionUID = 8636443105395387281L;

	public ProdSegdPlanIsId() {}
	
	@Column(name = "CD_MUNI_DOMI")
    private Long muniDomiId;

	@Column(name = "CD_UF_DOMI")
    private Long ufDomiId;

	@Column(name = "NR_IDAD_MINI")
    private Long nrIdadMini;

	@Column(name = "VL_CAPI_MINI")
    private BigDecimal vlCapiMini;

	private ProdSegdPlanId prodSegdPlanId;
	
	public ProdSegdPlanIsId(Long muniDomiId, 
			Long ufDomiId,
			ProdSegdPlanId prodSegdPlanId,
			Long nrIdadMini,
			BigDecimal vlCapiMini) {
		this.muniDomiId = muniDomiId;
		this.ufDomiId = ufDomiId;
		this.nrIdadMini = nrIdadMini;
		this.vlCapiMini = vlCapiMini;
		this.prodSegdPlanId = prodSegdPlanId;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdSegdPlanIsId that = (ProdSegdPlanIsId) o;
        if (muniDomiId != null ? !muniDomiId.equals(that.muniDomiId) : that.muniDomiId != null) return false;
        if (ufDomiId != null ? !ufDomiId.equals(that.ufDomiId) : that.ufDomiId != null) return false;
        if (nrIdadMini != null ? !nrIdadMini.equals(that.nrIdadMini) : that.nrIdadMini != null) return false;
        if (vlCapiMini != null ? !vlCapiMini.equals(that.vlCapiMini) : that.vlCapiMini != null) return false;
        if (prodSegdPlanId != null ? !prodSegdPlanId.equals(that.prodSegdPlanId) : that.prodSegdPlanId != null) return false;        
        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (prodSegdPlanId != null ? prodSegdPlanId.hashCode() : 0);
        result = 31 * result + (muniDomiId != null ? muniDomiId.hashCode() : 0);
        result = 31 * result + (ufDomiId != null ? ufDomiId.hashCode() : 0);
        result = 31 * result + (nrIdadMini != null ? nrIdadMini.hashCode() : 0);
        result = 31 * result + (vlCapiMini != null ? vlCapiMini.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProdSegdPlanId{" +
        		"prodSegdPlanId=" + prodSegdPlanId +
        		"muniDomiId=" + muniDomiId +
        		"ufDomiId=" + ufDomiId +
        		"nrIdadMini=" + nrIdadMini +
        		"vlCapiMini=" + vlCapiMini +        		
            '}';
    }
	public ProdSegdPlanId getProdSegdPlanId() {
		return prodSegdPlanId;
	}
	public void setProdSegdPlanId(ProdSegdPlanId prodSegdPlanId) {
		this.prodSegdPlanId = prodSegdPlanId;
	}
	public Long getMuniDomiId() {
		return muniDomiId;
	}
	public void setMuniDomiId(Long muniDomiId) {
		this.muniDomiId = muniDomiId;
	}
	public Long getUfDomiId() {
		return ufDomiId;
	}
	public void setUfDomiId(Long ufDomiId) {
		this.ufDomiId = ufDomiId;
	}
	public Long getNrIdadMini() {
		return nrIdadMini;
	}
	public void setNrIdadMini(Long nrIdadMini) {
		this.nrIdadMini = nrIdadMini;
	}
	public BigDecimal getVlCapiMini() {
		return vlCapiMini;
	}
	public void setVlCapiMini(BigDecimal vlCapiMini) {
		this.vlCapiMini = vlCapiMini;
	}
}
