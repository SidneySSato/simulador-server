package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RespCorporativaId implements java.io.Serializable {

	private static final long serialVersionUID = -6364296915582765477L;
	
	public RespCorporativaId() {}
	
    @Column(name = "CD_RESPOSTA")
    private Long respostaId;

    @Column(name = "CD_TC_DOMI")
    private Long cntdDomiId;
	
	public RespCorporativaId(Long respostaId, Long cntdDomiId) {
		this.respostaId = respostaId;
		this.cntdDomiId = cntdDomiId;
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RespCorporativaId that = (RespCorporativaId) o;

        if (respostaId != null ? !respostaId.equals(that.respostaId) : that.respostaId != null) return false;
        if (cntdDomiId != null ? !cntdDomiId.equals(that.cntdDomiId) : that.cntdDomiId != null)
            return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (respostaId != null ? respostaId.hashCode() : 0);
        result = 31 * result + (cntdDomiId != null ? cntdDomiId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RespCorporativaId{" +
            "respostaId=" + respostaId +
            ", cntdDomiId=" + cntdDomiId +
            '}';
    }
	public Long getRespostaId() {
		return respostaId;
	}
	public void setRespostaId(Long respostaId) {
		this.respostaId = respostaId;
	}
	public Long getCntdDomiId() {
		return cntdDomiId;
	}
	public void setCntdDomiId(Long cntdDomiId) {
		this.cntdDomiId = cntdDomiId;
	}
}
