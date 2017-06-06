package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the ArvoreCanal entity.
 */
public class ArvoreCanalDTO implements Serializable {

	private static final long serialVersionUID = 3868799690691886610L;

    private Long arvoreDecisaoId;

	/** ID da tabela tb_cntd_domi */
	@JsonInclude(Include.NON_NULL)
	private Long cntdDomiId;
	
	/** DS da tabela tb_cntd_domi */
	@JsonInclude(Include.NON_NULL)
	private String cntdDomiDsCntdDomiLgdo;

	@JsonInclude(Include.NON_NULL)
	private String cntdDomiCdCntdDomiLgdo;

    /**
     * Atributo auxiliar para verificar se o registro será inserido no banco de dados
     * true para inserir e false para nao inserir ou apagar
     */
    @JsonInclude(Include.NON_NULL)
    private Boolean inserir;

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

        ArvoreCanalDTO arvoreCanalDTO = (ArvoreCanalDTO) o;

        if (!(Objects.equals(arvoreDecisaoId, arvoreCanalDTO.arvoreDecisaoId) 
        		&& Objects.equals(cntdDomiId, arvoreCanalDTO.cntdDomiId)))  return false;
        

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(arvoreDecisaoId) + Objects.hashCode(cntdDomiId);
    }

    @Override
    public String toString() {
        return "ArvoreCanalDTO{" +
            "arvoreDecisaoId=" + arvoreDecisaoId +
            ", cntdDomiId='" + cntdDomiId + "'" +
            '}';
    }

	public Long getArvoreDecisaoId() {
		return arvoreDecisaoId;
	}

	public void setArvoreDecisaoId(Long arvoreDecisaoId) {
		this.arvoreDecisaoId = arvoreDecisaoId;
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

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}

	public Boolean getInserir() {
		return inserir;
	}

	public void setInserir(Boolean inserir) {
		this.inserir = inserir;
	}
}
