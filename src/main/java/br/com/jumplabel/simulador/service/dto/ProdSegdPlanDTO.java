package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the ProdSegdPlan entity.
 */
public class ProdSegdPlanDTO implements Serializable {

	private static final long serialVersionUID = -2680840931966295012L;

    // ID prodSegdPlanSegm
	@JsonInclude(Include.NON_NULL)
    private String prodId;
	@JsonInclude(Include.NON_NULL)	
    private String subpId;
	@JsonInclude(Include.NON_NULL)
    private Long segdDomiId;
	@JsonInclude(Include.NON_NULL)
    private Long planDomiId;
	@JsonInclude(Include.NON_NULL)
    private Long segmDomiId;
    // ID prodSegdPlanSegm
	
	/** DS da tabela tb_cntd_domi */
	@JsonInclude(Include.NON_NULL)
	private String segdDomiDsCntdDomiLgdo;

	/** DS da tabela tb_cntd_domi */
	@JsonInclude(Include.NON_NULL)
	private String planDomiDsCntdDomiLgdo;

	/** DS da tabela tb_cntd_domi */
	@JsonInclude(Include.NON_NULL)
	private String segmDomiDsCntdDomiLgdo;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProdSegdPlanDTO prodSegdPlanDTO = (ProdSegdPlanDTO) o;

        if ( ! Objects.equals(prodId, prodSegdPlanDTO.prodId)) return false;
        if ( ! Objects.equals(subpId, prodSegdPlanDTO.subpId)) return false;
        if ( ! Objects.equals(segdDomiId, prodSegdPlanDTO.segdDomiId)) return false;
        if ( ! Objects.equals(planDomiId, prodSegdPlanDTO.planDomiId)) return false;
        if ( ! Objects.equals(segmDomiId, prodSegdPlanDTO.segmDomiId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prodId) + Objects.hashCode(subpId) + 
        	   Objects.hashCode(segdDomiId) +Objects.hashCode(planDomiId) +Objects.hashCode(segmDomiId);
    }

    @Override
    public String toString() {
        return "ProdSegdPlanDTO{" +
            "prodId=" + prodId +
            "subpId=" + subpId +
            "segdDomiId=" + segdDomiId +
            "planDomiId=" + planDomiId +
            "segmDomiId=" + segmDomiId +
            '}';
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

	public Long getSegdDomiId() {
		return segdDomiId;
	}

	public void setSegdDomiId(Long segdDomiId) {
		this.segdDomiId = segdDomiId;
	}

	public Long getPlanDomiId() {
		return planDomiId;
	}

	public void setPlanDomiId(Long planDomiId) {
		this.planDomiId = planDomiId;
	}

	public Long getSegmDomiId() {
		return segmDomiId;
	}

	public void setSegmDomiId(Long segmDomiId) {
		this.segmDomiId = segmDomiId;
	}
	public String getSegdDomiDsCntdDomiLgdo() {
		return segdDomiDsCntdDomiLgdo;
	}

	public void setSegdDomiDsCntdDomiLgdo(String segdDomiDsCntdDomiLgdo) {
		this.segdDomiDsCntdDomiLgdo = segdDomiDsCntdDomiLgdo;
	}

	public String getPlanDomiDsCntdDomiLgdo() {
		return planDomiDsCntdDomiLgdo;
	}

	public void setPlanDomiDsCntdDomiLgdo(String planDomiDsCntdDomiLgdo) {
		this.planDomiDsCntdDomiLgdo = planDomiDsCntdDomiLgdo;
	}

	public String getSegmDomiDsCntdDomiLgdo() {
		return segmDomiDsCntdDomiLgdo;
	}

	public void setSegmDomiDsCntdDomiLgdo(String segmDomiDsCntdDomiLgdo) {
		this.segmDomiDsCntdDomiLgdo = segmDomiDsCntdDomiLgdo;
	}	
}
