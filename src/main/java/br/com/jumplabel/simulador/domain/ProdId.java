package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProdId implements java.io.Serializable {

	private static final long serialVersionUID = 8636443105395387281L;

	public ProdId() {}
	
    @Column(name = "CD_PROD")
    private String prodId;

    @Column(name = "CD_SUBP")
    private String subpId;
	
	public ProdId(String prodId, String subpId) {
		this.prodId = prodId;
		this.subpId = subpId;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdId that = (ProdId) o;

        if (prodId != null ? !prodId.equals(that.prodId) : that.prodId != null) return false;
        if (subpId != null ? !subpId.equals(that.subpId) : that.subpId != null)
            return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (prodId != null ? prodId.hashCode() : 0);
        result = 31 * result + (subpId != null ? subpId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProdId{" +
            "prodId=" + prodId +
            ", subpId=" + subpId +
            '}';
    }
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getSubpId() {
		return subpId;
	}
	public void setSubpId(String subpId) {
		this.subpId = subpId;
	}
}
