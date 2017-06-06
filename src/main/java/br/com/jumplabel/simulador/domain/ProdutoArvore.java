package br.com.jumplabel.simulador.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A ProdutoArvore.
 */
@Entity
@Table(name = "TB_PRODUTO_ARVORE")
public class ProdutoArvore implements Serializable {

	private static final long serialVersionUID = 3961455609374994765L;

	public ProdutoArvore() {}
	
	public ProdutoArvore(ProdId pk) {
		this.pk = pk;
	}
	
	@EmbeddedId
    private ProdId pk;

    @Size(max = 1)
    @Column(name = "IN_PRODUTO_SEM_OFERTA", length = 1)
    private String inProdutoSemOferta;

    @Size(max = 4)
    @Column(name = "CD_SITU_PROD_ARVORE", length = 4)
    private String cdSituProdArvore;

    @OneToMany(mappedBy = "produtoArvore", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<NoArvore> noArvores = new HashSet<>();

    @OneToMany(mappedBy = "produtoArvore", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<PerguntaObg> perguntaObgs = new HashSet<>();

    @OneToOne(mappedBy = "produtoArvore")
    @JoinColumns({
		@JoinColumn(name = "CD_PROD", referencedColumnName="pk.prodId"),
		@JoinColumn(name = "CD_SUBP", referencedColumnName="pk.subpId") })
    @JsonIgnore
    private Prod prod;

    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ProdutoArvore that = (ProdutoArvore) o;

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
        return "ProdutoArvore{" +
            "pk=" + pk +
            ", inProdutoSemOferta='" + inProdutoSemOferta + "'" +
            ", cdSituProdArvore='" + cdSituProdArvore + "'" +
            '}';
    }

	public ProdId getPk() {
		return pk;
	}

	public void setPk(ProdId pk) {
		this.pk = pk;
	}
	
   public String getInProdutoSemOferta() {
        return inProdutoSemOferta;
    }

    public ProdutoArvore inProdutoSemOferta(String inProdutoSemOferta) {
        this.inProdutoSemOferta = inProdutoSemOferta;
        return this;
    }

    public void setInProdutoSemOferta(String inProdutoSemOferta) {
        this.inProdutoSemOferta = inProdutoSemOferta;
    }

    public String getCdSituProdArvore() {
        return cdSituProdArvore;
    }

    public ProdutoArvore cdSituProdArvore(String cdSituProdArvore) {
        this.cdSituProdArvore = cdSituProdArvore;
        return this;
    }

    public void setCdSituProdArvore(String cdSituProdArvore) {
        this.cdSituProdArvore = cdSituProdArvore;
    }

    public Set<NoArvore> getNoArvores() {
        return noArvores;
    }

    public ProdutoArvore noArvores(Set<NoArvore> noArvores) {
        this.noArvores = noArvores;
        return this;
    }

    public ProdutoArvore addNoArvore(NoArvore noArvore) {
        this.noArvores.add(noArvore);
        noArvore.setProdutoArvore(this);
        return this;
    }

    public ProdutoArvore removeNoArvore(NoArvore noArvore) {
        this.noArvores.remove(noArvore);
        noArvore.setProdutoArvore(null);
        return this;
    }

    public void setNoArvores(Set<NoArvore> noArvores) {
        this.noArvores = noArvores;
    }

    public Set<PerguntaObg> getPerguntaObgs() {
        return perguntaObgs;
    }

    public ProdutoArvore perguntaObgs(Set<PerguntaObg> perguntaObgs) {
        this.perguntaObgs = perguntaObgs;
        return this;
    }

    public ProdutoArvore addPerguntaObg(PerguntaObg perguntaObg) {
        this.perguntaObgs.add(perguntaObg);
        perguntaObg.setProdutoArvore(this);
        return this;
    }

    public ProdutoArvore removePerguntaObg(PerguntaObg perguntaObg) {
        this.perguntaObgs.remove(perguntaObg);
        perguntaObg.setProdutoArvore(null);
        return this;
    }

    public void setPerguntaObgs(Set<PerguntaObg> perguntaObgs) {
        this.perguntaObgs = perguntaObgs;
    }

    public Prod getProd() {
        return prod;
    }

    public ProdutoArvore prod(Prod prod) {
        this.prod = prod;
        return this;
    }

    public void setProd(Prod prod) {
        this.prod = prod;
    }	
}