package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * A DTO for the ProdSegdPlanIs entity.
 */
public class ProdSegdPlanIsDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2596047466491554867L;

	private Long id;

    private BigDecimal vlCapiMaxi;


    private Long prodSegdPlanId;
    

    private String prodSegdPlanDtInicVigePlan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getVlCapiMaxi() {
        return vlCapiMaxi;
    }

    public void setVlCapiMaxi(BigDecimal vlCapiMaxi) {
        this.vlCapiMaxi = vlCapiMaxi;
    }

    public Long getProdSegdPlanId() {
        return prodSegdPlanId;
    }

    public void setProdSegdPlanId(Long prodSegdPlanId) {
        this.prodSegdPlanId = prodSegdPlanId;
    }


    public String getProdSegdPlanDtInicVigePlan() {
        return prodSegdPlanDtInicVigePlan;
    }

    public void setProdSegdPlanDtInicVigePlan(String prodSegdPlanDtInicVigePlan) {
        this.prodSegdPlanDtInicVigePlan = prodSegdPlanDtInicVigePlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProdSegdPlanIsDTO prodSegdPlanIsDTO = (ProdSegdPlanIsDTO) o;

        if ( ! Objects.equals(id, prodSegdPlanIsDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ProdSegdPlanIsDTO{" +
            "id=" + id +
            ", vlCapiMaxi='" + vlCapiMaxi + "'" +
            '}';
    }
}
