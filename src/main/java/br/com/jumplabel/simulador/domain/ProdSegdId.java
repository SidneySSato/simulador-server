package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProdSegdId implements java.io.Serializable {

	private static final long serialVersionUID = 8636443105395387281L;

	public ProdSegdId() {}
	
    private ProdId prodId;

    @Column(name = "CD_SEGD_DOMI")
    private Long segdDomiId;
    
	public ProdSegdId(ProdId prodId,Long segdDomiId) {
		this.prodId = prodId;
		this.segdDomiId = segdDomiId;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdSegdId that = (ProdSegdId) o;

        if (prodId != null ? !prodId.equals(that.prodId) : that.prodId != null) return false;
        if (segdDomiId != null ? !segdDomiId.equals(that.segdDomiId) : that.segdDomiId != null) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (prodId != null ? prodId.hashCode() : 0);
        result = 31 * result + (segdDomiId != null ? segdDomiId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProdSegdId{" +
            "prodId=" + prodId +
            ", segdDomiId=" + segdDomiId +
            '}';
    }
	public ProdId getProdId() {
		return prodId;
	}
	public void setProdId(ProdId prodId) {
		this.prodId = prodId;
	}
	public Long getSegdDomiId() {
		return segdDomiId;
	}
	public void setSegdDomiId(Long segdDomiId) {
		this.segdDomiId = segdDomiId;
	}
}