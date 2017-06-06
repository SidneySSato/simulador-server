package br.com.jumplabel.simulador.domain;


import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * A NoArvoreMensagemProduto.
 */
@Entity
@Table(name = "TB_NO_ARVORE_MENSAGEM_PRODUTO")
@AssociationOverrides({
	@AssociationOverride(name = "pk.noArvoreId",
		joinColumns = @JoinColumn(name = "CD_NO_ARVORE")),
	@AssociationOverride(name = "pk.mensagemProdutoId",
		joinColumns = @JoinColumn(name = "CD_MENSAGEM_PRODUTO")) })
public class NoArvoreMensagemProduto implements Serializable {
	
	private static final long serialVersionUID = -6308946232602354170L;
	
    public NoArvoreMensagemProduto() { }
    
	@EmbeddedId
	private NoArvoreMensagemProdutoId pk;
    
    @ManyToOne
    @JoinColumn(name="CD_NO_ARVORE")
    @MapsId("noArvoreId") 
    private NoArvore noArvore;

    @ManyToOne
    @JoinColumn(name="CD_MENSAGEM_PRODUTO")
    @MapsId("mensagemProdutoId") 
    private MensagemProduto mensagemProduto;

    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		NoArvoreMensagemProduto that = (NoArvoreMensagemProduto) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;
		return true;    	
    }

    @Override
    public int hashCode() {
    	return (getPk() != null ? getPk().hashCode() : 0);
    }

    @Override
    public String toString() {
        return "NoArvoreMensagemProduto{" +
            "pk=" + pk +
            '}';
    }
    
	public NoArvoreMensagemProdutoId getPk() {
		return pk;
	}

	public void setPk(NoArvoreMensagemProdutoId pk) {
		this.pk = pk;
	}

	public NoArvore getNoArvore() {
		return noArvore;
	}

	public void setNoArvore(NoArvore noArvore) {
		this.noArvore = noArvore;
	}

	public MensagemProduto getMensagemProduto() {
		return mensagemProduto;
	}

	public void setMensagemProduto(MensagemProduto mensagemProduto) {
		this.mensagemProduto = mensagemProduto;
	}
}
