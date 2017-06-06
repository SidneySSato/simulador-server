package br.com.jumplabel.simulador.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NoArvoreMensagemProdutoId implements java.io.Serializable {

	private static final long serialVersionUID = -6364296915582765477L;
	
	public NoArvoreMensagemProdutoId() {}
	
	public NoArvoreMensagemProdutoId(Long noArvoreId, Long mensagemProdutoId) {
		this.mensagemProdutoId = mensagemProdutoId;
		this.noArvoreId = noArvoreId;
	}
	
    @Column(name = "CD_MENSAGEM_PRODUTO")
    private Long mensagemProdutoId;

    @Column(name = "CD_NO_ARVORE")
    private Long noArvoreId;

	
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoArvoreMensagemProdutoId that = (NoArvoreMensagemProdutoId) o;

        if (mensagemProdutoId != null ? !mensagemProdutoId.equals(that.mensagemProdutoId) : that.mensagemProdutoId != null) return false;
        if (noArvoreId != null ? !noArvoreId.equals(that.noArvoreId) : that.noArvoreId != null)
            return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (mensagemProdutoId != null ? mensagemProdutoId.hashCode() : 0);
        result = 31 * result + (noArvoreId != null ? noArvoreId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NoArvoreMensagemProdutoId{" +
            "mensagemProdutoId=" + mensagemProdutoId +
            "noArvoreId=" + noArvoreId +
            '}';
    }

	public Long getMensagemProdutoId() {
		return mensagemProdutoId;
	}

	public void setMensagemProdutoId(Long mensagemProdutoId) {
		this.mensagemProdutoId = mensagemProdutoId;
	}

	public Long getNoArvoreId() {
		return noArvoreId;
	}

	public void setNoArvoreId(Long noArvoreId) {
		this.noArvoreId = noArvoreId;
	}	
}
