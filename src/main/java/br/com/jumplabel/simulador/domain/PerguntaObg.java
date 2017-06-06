package br.com.jumplabel.simulador.domain;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * A PerguntaObg.
 */
@Entity
@Table(name = "TB_PERGUNTA_OBG")
public class PerguntaObg implements Serializable {

	private static final long serialVersionUID = 5453022471350771552L;

	public PerguntaObg() {}
	
	public PerguntaObg(PerguntaObgId pk) {
		this.pk = pk;
	}
	
	@EmbeddedId
    private PerguntaObgId pk;

    @ManyToOne
	@JoinColumns({
		@JoinColumn(name = "CD_PROD"),
		@JoinColumn(name = "CD_SUBP")})
    @MapsId("prodId") 
    private ProdutoArvore produtoArvore;
    
    
    @ManyToOne
    @JoinColumn(name="CD_PERGUNTA")
    @MapsId("perguntaId")
    private Pergunta pergunta;

    @Override
    public boolean equals(Object o) {
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		PerguntaObg that = (PerguntaObg) o;

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
        return "PerguntaObg{" +
            "pk=" + pk+
            '}';
    }

	public PerguntaObgId getPk() {
		return pk;
	}

	public void setPk(PerguntaObgId pk) {
		this.pk = pk;
	}

	public ProdutoArvore getProdutoArvore() {
		return produtoArvore;
	}

	public void setProdutoArvore(ProdutoArvore produtoArvore) {
		this.produtoArvore = produtoArvore;
	}

	public Pergunta getPergunta() {    	
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
}
