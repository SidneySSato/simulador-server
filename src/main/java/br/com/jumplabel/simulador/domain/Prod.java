package br.com.jumplabel.simulador.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Prod.
 */
@Entity
@Table(name = "TB_PROD")
public class Prod implements Serializable {

	private static final long serialVersionUID = -8228712329033662841L;

	public Prod() {}
	
	public Prod(ProdId pk) {
		this.pk = pk;
	}
	
	@EmbeddedId
    private ProdId pk;

    @Size(max = 60)
    @Column(name = "DS_PROD_SUBP_VC", length = 60)
    private String dsProdSubpVc;

    @Size(max = 40)
    @Column(name = "DS_PROD_CORP", length = 40)
    private String dsProdCorp;

    @Size(max = 22)
    @Column(name = "CD_PROC_SUSEP", length = 22)
    private String cdProcSusep;

//    @Size(max = 22)
//    @Column(name = "CD_RAMO_DOMI", length = 10)
//    private Long cdRamoDomi;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CD_RAMO_DOMI")
    private CntdDomi cntdDomi;

    @OneToMany(mappedBy = "prod", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ProdSegd> prodSegds = new HashSet<>();
   
    @OneToOne()
    @PrimaryKeyJoinColumn
    private ProdutoArvore produtoArvore;
    
    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Prod that = (Prod) o;

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
        return "Prod{" +
            "pk=" + pk +
            ", dsProdSubpVc='" + dsProdSubpVc + "'" +
            ", dsProdCorp='" + dsProdCorp + "'" +
            ", cdProcSusep='" + cdProcSusep + "'" +
            '}';

    }


	public CntdDomi getCntdDomi() {
		return cntdDomi;
	}

	public void setCntdDomi(CntdDomi cntdDomi) {
		this.cntdDomi = cntdDomi;
	}

	public ProdId getPk() {
		return pk;
	}

	public void setPk(ProdId pk) {
		this.pk = pk;
	}

	public String getDsProdSubpVc() {
		return dsProdSubpVc;
	}

	public void setDsProdSubpVc(String dsProdSubpVc) {
		this.dsProdSubpVc = dsProdSubpVc;
	}

	public String getDsProdCorp() {
		return dsProdCorp;
	}

	public void setDsProdCorp(String dsProdCorp) {
		this.dsProdCorp = dsProdCorp;
	}

	public String getCdProcSusep() {
		return cdProcSusep;
	}

	public void setCdProcSusep(String cdProcSusep) {
		this.cdProcSusep = cdProcSusep;
	}

	public Set<ProdSegd> getProdSegds() {
		return prodSegds;
	}

	public void setProdSegds(Set<ProdSegd> prodSegds) {
		this.prodSegds = prodSegds;
	}

	public ProdutoArvore getProdutoArvore() {
		return produtoArvore;
	}

	public void setProdutoArvore(ProdutoArvore produtoArvore) {
		this.produtoArvore = produtoArvore;
	}
}
