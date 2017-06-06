package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the Pergunta entity.
 */
public class PerguntaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Long TIPO_RESPOSTA_LISTA = Long.valueOf(1);
	public static final Long TIPO_RESPOSTA_INPUT_DADOS = Long.valueOf(2);
	public static final Long TIPO_RESPOSTA_DADOS_CORPORATIVOS = Long.valueOf(3);
	
	public PerguntaDTO() {}
	public PerguntaDTO(Long id) {
		this.id = id;
	}

	@JsonInclude(Include.NON_NULL)	
	private Long id;

	@JsonInclude(Include.NON_NULL)
    @NotNull
    @NotEmpty
    @Size(max = 80)
    private String dsPergunta;

    @JsonInclude(Include.NON_NULL)
    private String inEditavel;
	
	@JsonInclude(Include.NON_NULL)
    private Set<TagDTO> tags = null;
	
	@JsonInclude(Include.NON_NULL)
    private List<RespostaDTO> respostas = null;

	@JsonInclude(Include.NON_NULL)
    private Long domiId;
    
	@JsonInclude(Include.NON_NULL)
    private String domiNmDomi;
	
    private Long tipoRespostaId;
    
    private String tipoRespostaDsTipoResposta;
	
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

        PerguntaDTO perguntaDTO = (PerguntaDTO) o;

        if ( ! Objects.equals(id, perguntaDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PerguntaDTO{" +
            "id=" + id +
            ", dsPergunta='" + dsPergunta + "'" +
            ", inEditavel='" + inEditavel + "'" +
            '}';
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDsPergunta() {
		return dsPergunta;
	}
	public void setDsPergunta(String dsPergunta) {
		this.dsPergunta = dsPergunta;
	}
	public String getInEditavel() {
		return inEditavel;
	}
	public void setInEditavel(String inEditavel) {
		this.inEditavel = inEditavel;
	}
	public Set<TagDTO> getTags() {
		return tags;
	}
	public void setTags(Set<TagDTO> tags) {
		this.tags = tags;
	}
	public List<RespostaDTO> getRespostas() {
		return respostas;
	}
	public void setRespostas(List<RespostaDTO> respostas) {
		this.respostas = respostas;
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
	public Long getTipoRespostaId() {
		return tipoRespostaId;
	}
	public void setTipoRespostaId(Long tipoRespostaId) {
		this.tipoRespostaId = tipoRespostaId;
	}
	public String getTipoRespostaDsTipoResposta() {
		return tipoRespostaDsTipoResposta;
	}
	public void setTipoRespostaDsTipoResposta(String tipoRespostaDsTipoResposta) {
		this.tipoRespostaDsTipoResposta = tipoRespostaDsTipoResposta;
	}
	public ResultDTO getResult() {
		return result;
	}
	public void setResult(ResultDTO result) {
		this.result = result;
	}
}
