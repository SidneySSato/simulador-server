package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the ProdSegdPlanSegm entity.
 */
public class ProdSegdPlanSegmDTO implements Serializable {

	private static final long serialVersionUID = 2704940200056460816L;

	@JsonInclude(Include.NON_NULL)
	private Long planDomiId;
	@JsonInclude(Include.NON_NULL)
	private Long segdDomiId;
	@JsonInclude(Include.NON_NULL)
	private Long segmDomiId;
	@JsonInclude(Include.NON_NULL)
	private String prodId;
	@JsonInclude(Include.NON_NULL)
	private String subpId;
	
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

        ProdSegdPlanSegmDTO prodSegdPlanSegmDTO = (ProdSegdPlanSegmDTO) o;

        if ( ! Objects.equals(planDomiId, prodSegdPlanSegmDTO.planDomiId)) return false;
        if ( ! Objects.equals(segdDomiId, prodSegdPlanSegmDTO.segdDomiId)) return false;
        if ( ! Objects.equals(segmDomiId, prodSegdPlanSegmDTO.segmDomiId)) return false;
        if ( ! Objects.equals(prodId, prodSegdPlanSegmDTO.prodId)) return false;
        if ( ! Objects.equals(subpId, prodSegdPlanSegmDTO.subpId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(planDomiId)+Objects.hashCode(segdDomiId)+
        		Objects.hashCode(segmDomiId)+Objects.hashCode(prodId)+Objects.hashCode(subpId);
    }

    @Override
    public String toString() {
        return "ProdSegdPlanSegmDTO{" +
            "planDomiId=" + planDomiId +
            "segdDomiId=" + segdDomiId +
            "segmDomiId=" + segmDomiId +
            "prodId=" + prodId +
            "subpId=" + subpId +
            '}';
    }

	public Long getPlanDomiId() {
		return planDomiId;
	}

	public void setPlanDomiId(Long planDomiId) {
		this.planDomiId = planDomiId;
	}

	public Long getSegdDomiId() {
		return segdDomiId;
	}

	public void setSegdDomiId(Long segdDomiId) {
		this.segdDomiId = segdDomiId;
	}

	public Long getSegmDomiId() {
		return segmDomiId;
	}

	public void setSegmDomiId(Long segmDomiId) {
		this.segmDomiId = segmDomiId;
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
}
