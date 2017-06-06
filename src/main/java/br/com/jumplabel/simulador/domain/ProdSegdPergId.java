package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProdSegdPergId implements java.io.Serializable {

	private static final long serialVersionUID = 8636443105395387281L;

	public ProdSegdPergId() {}
	
	@Column(name = "CD_PERG_DOMI")
    private Long pergDomiId;

	private ProdSegdId prodSegdId;
	
	public ProdSegdPergId(Long pergDomiId, ProdSegdId prodSegdId) {
		this.pergDomiId = pergDomiId;
		this.prodSegdId = prodSegdId;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProdSegdPergId that = (ProdSegdPergId) o;

        if (prodSegdId != null ? !prodSegdId.equals(that.prodSegdId) : that.prodSegdId != null) return false;
        if (pergDomiId != null ? !pergDomiId.equals(that.pergDomiId) : that.pergDomiId != null) return false;
        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (prodSegdId != null ? prodSegdId.hashCode() : 0);
        result = 31 * result + (pergDomiId != null ? pergDomiId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProdSegdPergId{" +
        		"pergDomiId=" + pergDomiId +
        		"prodSegdId=" + prodSegdId +
            '}';
    }
	public Long getPergDomiId() {
		return pergDomiId;
	}
	public void setPergDomiId(Long pergDomiId) {
		this.pergDomiId = pergDomiId;
	}
	public ProdSegdId getProdSegdId() {
		return prodSegdId;
	}
	public void setProdSegdId(ProdSegdId prodSegdId) {
		this.prodSegdId = prodSegdId;
	}
}
