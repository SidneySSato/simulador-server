package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A DTO for the CnalProdSegd entity.
 */
public class CnalProdSegdDTO implements Serializable {

	private static final long serialVersionUID = -3651385752757517389L;

	@JsonInclude(Include.NON_NULL)
    private String prodId;
	
	@JsonInclude(Include.NON_NULL)	
    private String subpId;
	
	@JsonInclude(Include.NON_NULL)
    private Long segdDomiId;
	
	@JsonInclude(Include.NON_NULL)	
    private Long cnalDomiId;

	@JsonInclude(Include.NON_NULL)
    private ZonedDateTime dtIniciVigeCnalVend;

	@JsonInclude(Include.NON_NULL)
    private ZonedDateTime dtFimVigeCnalVend;

	/** DS da tabela tb_cntd_domi */
	@JsonInclude(Include.NON_NULL)
	private String cntdDomiDsCntdDomiLgdo;

	@JsonInclude(Include.NON_NULL)
	private String cntdDomiCdCntdDomiLgdo;

	  @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }

	        CnalProdSegdDTO cnalProdSegdDTO = (CnalProdSegdDTO) o;

	        if ( ! Objects.equals(prodId, cnalProdSegdDTO.prodId)) return false;
	        if ( ! Objects.equals(subpId, cnalProdSegdDTO.subpId)) return false;
	        if ( ! Objects.equals(segdDomiId, cnalProdSegdDTO.segdDomiId)) return false;
	        if ( ! Objects.equals(cnalDomiId, cnalProdSegdDTO.cnalDomiId)) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hashCode(prodId) + Objects.hashCode(subpId) + Objects.hashCode(segdDomiId) + Objects.hashCode(cnalDomiId);
	    }

	    @Override
	    public String toString() {
	        return "CnalProdSegdDTO{" +
	            "prodId=" + prodId +
	            "subpId=" + subpId +
	            "segdDomiId=" + segdDomiId +
	            "cnalDomiId=" + cnalDomiId +
	            ", dtIniciVigeCnalVend='" + dtIniciVigeCnalVend + "'" +
	            ", dtFimVigeCnalVend='" + dtFimVigeCnalVend + "'" +
	            '}';
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
    public ZonedDateTime getDtIniciVigeCnalVend() {
        return dtIniciVigeCnalVend;
    }

    public void setDtIniciVigeCnalVend(ZonedDateTime dtIniciVigeCnalVend) {
        this.dtIniciVigeCnalVend = dtIniciVigeCnalVend;
    }
    public ZonedDateTime getDtFimVigeCnalVend() {
        return dtFimVigeCnalVend;
    }

    public void setDtFimVigeCnalVend(ZonedDateTime dtFimVigeCnalVend) {
        this.dtFimVigeCnalVend = dtFimVigeCnalVend;
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

	public void setSegdDomiId(Long segdDomiId) {
		this.segdDomiId = segdDomiId;
	}

	public void setCnalDomiId(Long cnalDomiId) {
		this.cnalDomiId = cnalDomiId;
	}
}