package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the Prod entity.
 */
public class ProdDTO implements Serializable {

	private static final long serialVersionUID = 5892284611184916228L;

	@JsonInclude(Include.NON_NULL)
    private String prodId;
	
	@JsonInclude(Include.NON_NULL)	
    private String subpId;
	
	/** DS da tabela tb_cntd_domi */
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

  @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProdDTO prodDTO = (ProdDTO) o;

        if ( ! Objects.equals(prodId, prodDTO.prodId)) return false;
        if ( ! Objects.equals(subpId, prodDTO.subpId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prodId) + Objects.hashCode(prodId);
    }

    @Override
    public String toString() {
        return "ProdDTO{" +
            "prodId=" + prodId +
            "subpId=" + subpId +
            ", dsProdSubpVc='" + dsProdSubpVc + "'" +
            ", dsProdCorp='" + dsProdCorp + "'" +
            ", cdProcSusep='" + cdProcSusep + "'" +
            '}';
    }	
   
	public String getDsProdSubpVc() {
        return dsProdSubpVc;
    }

    public String getCntdDomiDsCntdDomiLgdo() {
		return cntdDomiDsCntdDomiLgdo;
	}

	public void setCntdDomiDsCntdDomiLgdo(String cntdDomiDsCntdDomiLgdo) {
		this.cntdDomiDsCntdDomiLgdo = cntdDomiDsCntdDomiLgdo;
	}

	public String getCntdDomiCdCntdDomiLgdo() {
		return cntdDomiCdCntdDomiLgdo;
	}

	public void setCntdDomiCdCntdDomiLgdo(String cntdDomiCdCntdDomiLgdo) {
		this.cntdDomiCdCntdDomiLgdo = cntdDomiCdCntdDomiLgdo;
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
}
