package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the PerguntaObg entity.
 */
public class PerguntaObgDTO implements Serializable {

	private static final long serialVersionUID = -3249884814743267173L;

	@JsonInclude(Include.NON_NULL)
    private String prodId;
	
	@JsonInclude(Include.NON_NULL)	
    private String subpId;
	
    private Long perguntaId;

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

        PerguntaObgDTO perguntaObgDTO = (PerguntaObgDTO) o;

        if ( ! Objects.equals(prodId, perguntaObgDTO.prodId)) return false;
        if ( ! Objects.equals(subpId, perguntaObgDTO.subpId)) return false;
        if ( ! Objects.equals(perguntaId, perguntaObgDTO.perguntaId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(prodId) + Objects.hashCode(subpId) + Objects.hashCode(perguntaId);
    }

    @Override
    public String toString() {
        return "PerguntaObgDTO{" +
            "prodId=" + prodId +
            "subpId=" + subpId +
            "perguntaId=" + perguntaId +
            '}';
    }

	public Long getPerguntaId() {
		return perguntaId;
	}

	public void setPerguntaId(Long perguntaId) {
		this.perguntaId = perguntaId;
	}

	public Boolean getInserir() {
		return inserir;
	}

	public void setInserir(Boolean inserir) {
		this.inserir = inserir;
	}

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}
}
