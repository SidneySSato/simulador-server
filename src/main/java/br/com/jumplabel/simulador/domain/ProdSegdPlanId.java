package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProdSegdPlanId implements java.io.Serializable {

	private static final long serialVersionUID = 8636443105395387281L;

	public ProdSegdPlanId() {}
	
	@Column(name = "CD_PLAN_DOMI")
    private Long planDomiId;

	private ProdSegdId prodSegdId;
	
	public ProdSegdPlanId(Long planDomiId, ProdSegdId prodSegdId) {
		this.planDomiId = planDomiId;
		this.prodSegdId = prodSegdId;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdSegdPlanId that = (ProdSegdPlanId) o;

        if (prodSegdId != null ? !prodSegdId.equals(that.prodSegdId) : that.prodSegdId != null) return false;
        if (planDomiId != null ? !planDomiId.equals(that.planDomiId) : that.planDomiId != null) return false;
        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (prodSegdId != null ? prodSegdId.hashCode() : 0);
        result = 31 * result + (planDomiId != null ? planDomiId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProdSegdPlanId{" +
        		"planDomiId='" + planDomiId + "'" +
        		", prodSegdId='" + prodSegdId + "'" +
            '}';
    }
	public Long getPergDomiId() {
		return planDomiId;
	}
	public void setPergDomiId(Long pergDomiId) {
		this.planDomiId = pergDomiId;
	}
	public ProdSegdId getProdSegdId() {
		return prodSegdId;
	}
	public void setProdSegdId(ProdSegdId prodSegdId) {
		this.prodSegdId = prodSegdId;
	}
	public Long getPlanDomiId() {
		return planDomiId;
	}
	public void setPlanDomiId(Long planDomiId) {
		this.planDomiId = planDomiId;
	}
}
