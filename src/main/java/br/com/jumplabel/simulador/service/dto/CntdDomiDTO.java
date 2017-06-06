package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the CntdDomi entity.
 */
public class CntdDomiDTO implements Comparable<CntdDomiDTO>, Serializable {

	private static final long serialVersionUID = 2825342501031181443L;

	@JsonInclude(Include.NON_NULL)
    private Long id;

	@JsonInclude(Include.NON_NULL)
    @Size(max = 50)
    private String cdCntdDomiLgdo;

	@JsonInclude(Include.NON_NULL)
    @Size(max = 250)
    private String dsCntdDomiLgdo;

    @JsonInclude(Include.NON_NULL)
    private Long domiId;
    
    @JsonInclude(Include.NON_NULL)
    private String domiNmDomi;

    @JsonInclude(Include.NON_NULL)
    private Long compDomiSegdId;
    
    @JsonInclude(Include.NON_NULL)
    private Long compDomiRamoId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CntdDomiDTO cntdDomiDTO = (CntdDomiDTO) o;

        if ( ! Objects.equals(id, cntdDomiDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CntdDomiDTO{" +
            "id=" + id +
            '}';
    }

	@Override
	public int compareTo(CntdDomiDTO o) {
		return dsCntdDomiLgdo.compareTo(o.dsCntdDomiLgdo);
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDomiId() {
        return domiId;
    }

    public void setDomiId(Long domiId) {
        this.domiId = domiId;
    }


    public String getDomiNmDomi() {
        return domiNmDomi;
    }

    public void setDomiNmDomi(String domiNmDomi) {
        this.domiNmDomi = domiNmDomi;
    }

    public Long getCompDomiSegdId() {
        return compDomiSegdId;
    }

    public void setCompDomiSegdId(Long compDomiSegdId) {
        this.compDomiSegdId = compDomiSegdId;
    }

    public Long getCompDomiRamoId() {
        return compDomiRamoId;
    }

    public void setCompDomiRamoId(Long compDomiRamoId) {
        this.compDomiRamoId = compDomiRamoId;
    }

	public String getCdCntdDomiLgdo() {
		return cdCntdDomiLgdo;
	}

	public void setCdCntdDomiLgdo(String cdCntdDomiLgdo) {
		this.cdCntdDomiLgdo = cdCntdDomiLgdo;
	}

	public String getDsCntdDomiLgdo() {
		return dsCntdDomiLgdo;
	}

	public void setDsCntdDomiLgdo(String dsCntdDomiLgdo) {
		this.dsCntdDomiLgdo = dsCntdDomiLgdo;
	}


}
