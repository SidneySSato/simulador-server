package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the RespCorporativa entity.
 */
public class RespCorporativaDTO implements Serializable {

	private static final long serialVersionUID = -744554274691142407L;

	@JsonInclude(Include.NON_NULL)
    private Long respostaId;
    
	/** ID da tabela tb_cntd_domi */
	@JsonInclude(Include.NON_NULL)
	private Long cntdDomiId;
	
	/** DS da tabela tb_cntd_domi */
	@JsonInclude(Include.NON_NULL)
	private String cntdDomiDsCntdDomiLgdo;

	@JsonInclude(Include.NON_NULL)
	private String cntdDomiCdCntdDomiLgdo;
	
	@JsonInclude(Include.NON_NULL)
	private Long domiId;

	public Long getDomiId() {
		return domiId;
	}

	public void setDomiId(Long domiId) {
		this.domiId = domiId;
	}

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

        RespCorporativaDTO respCorporativaDTO = (RespCorporativaDTO) o;

        if (!(Objects.equals(respostaId, respCorporativaDTO.respostaId) 
        		&& Objects.equals(cntdDomiId, respCorporativaDTO.cntdDomiId)))  return false;
        

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(respostaId) + Objects.hashCode(cntdDomiId);
    }

    @Override
    public String toString() {
        return "RespCorporativaDTO{" +
            "respostaId=" + respostaId +
            ", cntdDomiId='" + cntdDomiId + "'" +
            '}';
    }

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}

	public Long getRespostaId() {
		return respostaId;
	}

	public void setRespostaId(Long respostaId) {
		this.respostaId = respostaId;
	}

	public Long getCntdDomiId() {
		return cntdDomiId;
	}

	public void setCntdDomiId(Long cntdDomiId) {
		this.cntdDomiId = cntdDomiId;
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
