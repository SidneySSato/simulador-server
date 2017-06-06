package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PerguntaObgId implements java.io.Serializable {

	private static final long serialVersionUID = -6364296915582765477L;
	
	public PerguntaObgId() {}
	
	public PerguntaObgId(Long perguntaId, ProdId prodId) {
		this.perguntaId = perguntaId;
		this.prodId = prodId;
	}
	
    private ProdId prodId;
	
    @Column(name = "CD_PERGUNTA")
    private Long perguntaId;
	
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerguntaObgId that = (PerguntaObgId) o;

        if (prodId != null ? !prodId.equals(that.prodId) : that.prodId != null) return false;
        if (perguntaId != null ? !perguntaId.equals(that.perguntaId) : that.perguntaId != null)
            return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (prodId != null ? prodId.hashCode() : 0);
        result = 31 * result + (perguntaId != null ? perguntaId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PerguntaObgId{" +
            "prodId=" + prodId +
            "perguntaId=" + perguntaId +
            '}';
    }
	
	public ProdId getProdId() {
		return prodId;
	}

	public void setProdId(ProdId prodId) {
		this.prodId = prodId;
	}

	public Long getPerguntaId() {
		return perguntaId;
	}

	public void setPerguntaId(Long perguntaId) {
		this.perguntaId = perguntaId;
	}
}