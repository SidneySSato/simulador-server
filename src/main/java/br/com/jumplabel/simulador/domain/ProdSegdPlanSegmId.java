package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProdSegdPlanSegmId implements java.io.Serializable {

	private static final long serialVersionUID = 8636443105395387281L;

	public ProdSegdPlanSegmId() {}
	
	@Column(name = "CD_SEGM_DOMI")
    private Long segmDomiId;

	private ProdSegdPlanId prodSegdPlanId;
	
	public ProdSegdPlanSegmId(Long segmDomiId, 
			ProdSegdPlanId prodSegdPlanId) {
		this.segmDomiId = segmDomiId;
		this.prodSegdPlanId = prodSegdPlanId;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdSegdPlanSegmId that = (ProdSegdPlanSegmId) o;
        if (segmDomiId != null ? !segmDomiId.equals(that.segmDomiId) : that.segmDomiId != null) return false;
        if (prodSegdPlanId != null ? !prodSegdPlanId.equals(that.prodSegdPlanId) : that.prodSegdPlanId != null) return false;        
        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (prodSegdPlanId != null ? prodSegdPlanId.hashCode() : 0);
        result = 31 * result + (segmDomiId != null ? segmDomiId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProdSegdPlanId{" +
        		"prodSegdPlanId='" + prodSegdPlanId + "'" +
        		", segmDomiId='" + segmDomiId + "'" +
            '}';
    }
	public ProdSegdPlanId getProdSegdPlanId() {
		return prodSegdPlanId;
	}
	public void setProdSegdPlanId(ProdSegdPlanId prodSegdPlanId) {
		this.prodSegdPlanId = prodSegdPlanId;
	}
	public Long getSegmDomiId() {
		return segmDomiId;
	}
	public void setSegmDomiId(Long segmDomiId) {
		this.segmDomiId = segmDomiId;
	}

}
