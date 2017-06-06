package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the ProdutoArvore entity.
 */
public class ProdutoArvoreDTO implements Comparable<ProdutoArvoreDTO>, Serializable {

	private static final long serialVersionUID = -4453945682235984647L;
	
	@JsonInclude(Include.NON_NULL)
    private String prodId;
	
	@JsonInclude(Include.NON_NULL)	
    private String subpId;
    /**
     * Indica se o produto esta sem oferta: 
     * Domínio:
     * S - Sim  
     * N - Não
     */
    @Size(max = 1)
    private String inProdutoSemOferta;

	/**
	 * 	Código  que identifica a situaçao do produto utilizado na arvore.
	 * 	Domínio:
	 * 	A - ATIVO
	 * 	I - INATIVO
	 */
    @Size(max = 4)
    private String cdSituProdArvore;

	@JsonInclude(Include.NON_NULL)
	private String cntdDomiDsCntdDomiLgdo;
    
	@JsonInclude(Include.NON_NULL)
	private String cntdDomiCdCntdDomiLgdo;
	
	@JsonInclude(Include.NON_NULL)
    @Size(max = 60)
    private String dsProdSubpVc;

	@JsonInclude(Include.NON_NULL)
    @Size(max = 40)
    private String dsProdCorp;

	@JsonInclude(Include.NON_NULL)
    @Size(max = 22)
    private String cdProcSusep;
    
	@JsonInclude(Include.NON_NULL)
	private Set<ProdSegdPlanSegmDTO> prodSegdPlanSegms = null;
	
	@JsonInclude(Include.NON_NULL)
    private Set<PerguntaObgDTO> perguntaObgs = null;
	
	@JsonInclude(Include.NON_NULL)
	private ResultDTO result;	
	
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }

	        ProdutoArvoreDTO produtoArvoreDTO = (ProdutoArvoreDTO) o;

	        if ( ! Objects.equals(prodId, produtoArvoreDTO.prodId)) return false;
	        if ( ! Objects.equals(subpId, produtoArvoreDTO.subpId)) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hashCode(prodId) + Objects.hashCode(subpId);
	    }

	    @Override
	    public String toString() {
	        return "ProdutoArvoreDTO{" +
	            "prodId=" + prodId +
	            ", subpId='" + subpId + "'" +
	            ", inProdutoSemOferta='" + inProdutoSemOferta + "'" +
	            ", cdSituProdArvore='" + cdSituProdArvore + "'" +
	            '}';
	    }
	    
		@Override
		public int compareTo(ProdutoArvoreDTO o) {
			return dsProdCorp.compareTo(o.dsProdCorp);
		}
	
    public String getInProdutoSemOferta() {
        return inProdutoSemOferta;
    }

    public void setInProdutoSemOferta(String inProdutoSemOferta) {
        this.inProdutoSemOferta = inProdutoSemOferta;
    }
    public String getCdSituProdArvore() {
        return cdSituProdArvore;
    }

    public void setCdSituProdArvore(String cdSituProdArvore) {
        this.cdSituProdArvore = cdSituProdArvore;
    }

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getSubpId() {
		return subpId;
	}

	public void setSubpId(String subpId) {
		this.subpId = subpId;
	}

	public Set<PerguntaObgDTO> getPerguntaObgs() {
		return perguntaObgs;
	}

	public void setPerguntaObgs(Set<PerguntaObgDTO> perguntaObgs) {
		this.perguntaObgs = perguntaObgs;
	}

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}

	public String getCntdDomiCdCntdDomiLgdo() {
		return cntdDomiCdCntdDomiLgdo;
	}

	public void setCntdDomiCdCntdDomiLgdo(String cntdDomiCdCntdDomiLgdo) {
		this.cntdDomiCdCntdDomiLgdo = cntdDomiCdCntdDomiLgdo;
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

	public String getCntdDomiDsCntdDomiLgdo() {
		return cntdDomiDsCntdDomiLgdo;
	}

	public void setCntdDomiDsCntdDomiLgdo(String cntdDomiDsCntdDomiLgdo) {
		this.cntdDomiDsCntdDomiLgdo = cntdDomiDsCntdDomiLgdo;
	}

	public Set<ProdSegdPlanSegmDTO> getProdSegdPlanSegms() {
		return prodSegdPlanSegms;
	}

	public void setProdSegdPlanSegms(Set<ProdSegdPlanSegmDTO> prodSegdPlanSegms) {
		this.prodSegdPlanSegms = prodSegdPlanSegms;
	}
}