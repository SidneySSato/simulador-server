package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CnalProdSegdId implements java.io.Serializable {

	private static final long serialVersionUID = 8636443105395387281L;

	public CnalProdSegdId() {}
	
    private ProdSegdId prodSegdId;
	
    @Column(name = "CD_CNAL_DOMI")
    private Long cnalDomiId;
    
	public CnalProdSegdId(ProdSegdId prodSegdId, Long cnalDomiId) {
		this.cnalDomiId = cnalDomiId;
		this.prodSegdId = prodSegdId;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CnalProdSegdId that = (CnalProdSegdId) o;

        if (prodSegdId != null ? !prodSegdId.equals(that.prodSegdId) : that.prodSegdId != null) return false;
        if (cnalDomiId != null ? !cnalDomiId.equals(that.cnalDomiId) : that.cnalDomiId != null) return false;
        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (prodSegdId != null ? prodSegdId.hashCode() : 0);
        result = 31 * result + (cnalDomiId != null ? cnalDomiId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CnalProdSegdId{" +
        		"cnalDomiId=" + cnalDomiId +
        		"prodSegdId=" + prodSegdId +
            '}';
    }
	public ProdSegdId getProdSegdId() {
		return prodSegdId;
	}
	public void setProdSegdId(ProdSegdId prodSegdId) {
		this.prodSegdId = prodSegdId;
	}
	public Long getCnalDomiId() {
		return cnalDomiId;
	}
	public void setCnalDomiId(Long cnalDomiId) {
		this.cnalDomiId = cnalDomiId;
	}
}
