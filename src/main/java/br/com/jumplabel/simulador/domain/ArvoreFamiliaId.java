package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArvoreFamiliaId implements java.io.Serializable {

	private static final long serialVersionUID = -6364296915582765477L;
	
	public ArvoreFamiliaId() {}
	
    @Column(name = "CD_ARVORE_DECISAO")
    private Long arvoreDecisaoId;

    @Column(name = "CD_RAMO_DOMI")
    private Long cntdDomiId;
	
	public ArvoreFamiliaId(Long arvoreDecisaoId, Long cntdDomiId) {
		this.arvoreDecisaoId = arvoreDecisaoId;
		this.cntdDomiId = cntdDomiId;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArvoreFamiliaId that = (ArvoreFamiliaId) o;

        if (arvoreDecisaoId != null ? !arvoreDecisaoId.equals(that.arvoreDecisaoId) : that.arvoreDecisaoId != null) return false;
        if (cntdDomiId != null ? !cntdDomiId.equals(that.cntdDomiId) : that.cntdDomiId != null)
            return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (arvoreDecisaoId != null ? arvoreDecisaoId.hashCode() : 0);
        result = 31 * result + (cntdDomiId != null ? cntdDomiId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArvoreFamiliaId{" +
            "arvoreDecisaoId=" + arvoreDecisaoId +
            ", cntdDomiId=" + cntdDomiId +
            '}';
    }
	public Long getArvoreDecisaoId() {
		return arvoreDecisaoId;
	}
	public void setArvoreDecisaoId(Long arvoreDecisaoId) {
		this.arvoreDecisaoId = arvoreDecisaoId;
	}
	public Long getCntdDomiId() {
		return cntdDomiId;
	}
	public void setCntdDomiId(Long cntdDomiId) {
		this.cntdDomiId = cntdDomiId;
	}
}