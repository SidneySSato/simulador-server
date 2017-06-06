package br.com.jumplabel.simulador.domain;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * A ArvoreCanal.
 */
@Entity
@Table(name = "TB_ARVORE_CANAL")
public class ArvoreCanal implements Serializable {

    private static final long serialVersionUID = 1L;

	public ArvoreCanal() {}
	
	public ArvoreCanal(ArvoreCanalId pk) {
		this.pk = pk;
	}
	
	@EmbeddedId
    private ArvoreCanalId pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("arvoreDecisaoId") 
    @JoinColumn(name = "CD_ARVORE_DECISAO")
    private ArvoreDecisao arvoreDecisao;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cntdDomiId") 
    @JoinColumn(name="CD_CNAL_DOMI")
    private CntdDomi cntdDomi;

    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ArvoreCanal that = (ArvoreCanal) o;

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
        return "ArvoreCanal{" +
            "pk=" + pk +
            '}';
    }
    public ArvoreDecisao getArvoreDecisao() {
        return arvoreDecisao;
    }

    public ArvoreCanal arvoreDecisao(ArvoreDecisao arvoreDecisao) {
        this.arvoreDecisao = arvoreDecisao;
        return this;
    }

    public void setArvoreDecisao(ArvoreDecisao arvoreDecisao) {
        this.arvoreDecisao = arvoreDecisao;
    }

    public CntdDomi getCntdDomi() {
        return cntdDomi;
    }

    public ArvoreCanal cntdDomi(CntdDomi cntdDomi) {
        this.cntdDomi = cntdDomi;
        return this;
    }

    public void setCntdDomi(CntdDomi cntdDomi) {
        this.cntdDomi = cntdDomi;
    }
    
	public ArvoreCanalId getPk() {
		return pk;
	}

	public void setPk(ArvoreCanalId pk) {
		this.pk = pk;
	}
    
}
