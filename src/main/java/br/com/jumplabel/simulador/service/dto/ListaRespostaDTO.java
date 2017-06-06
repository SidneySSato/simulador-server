package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the Resposta entity.
 */
public class ListaRespostaDTO implements Serializable {

	private static final long serialVersionUID = -7455367151973826434L;

    @JsonInclude(Include.NON_NULL)
    private Long perguntaId;
    
    /**
     * Campo da pergunta, porem eh enviado na api de resposta
     */
    @JsonInclude(Include.NON_NULL)
    private Long perguntaTipoRespostaId;

    @JsonInclude(Include.NON_NULL)
    private String perguntaTipoRespostaDsTipoResposta;

    /**
     * Campo da pergunta, porem eh enviado na api de resposta
     */
	@JsonInclude(Include.NON_NULL)
    private Long perguntaDomiId;

    @JsonInclude(Include.NON_NULL)
    private String perguntaDomiNmDomi;

	/**
     * Campo da pergunta, porem eh enviado na api de resposta
     */
    @JsonInclude(Include.NON_NULL)
    private String perguntaInEditavel;

    private List<RespostaDTO> respostas;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ListaRespostaDTO respostaDTO = (ListaRespostaDTO) o;

        if ( ! Objects.equals(perguntaId, respostaDTO.perguntaId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(perguntaId);
    }

    @Override
    public String toString() {
        return "RespostaDTO{" +
            "perguntaId='" + perguntaId + "'" +
            ", perguntaTipoRespostaId='" + perguntaTipoRespostaId + "'" +
            ", perguntaDomiId='" + perguntaDomiId + "'" +
            '}';
    }

	public Long getPerguntaId() {
		return perguntaId;
	}

	public void setPerguntaId(Long perguntaId) {
		this.perguntaId = perguntaId;
	}

	public Long getPerguntaTipoRespostaId() {
		return perguntaTipoRespostaId;
	}

	public void setPerguntaTipoRespostaId(Long perguntaTipoRespostaId) {
		this.perguntaTipoRespostaId = perguntaTipoRespostaId;
	}

	public String getPerguntaTipoRespostaDsTipoResposta() {
		return perguntaTipoRespostaDsTipoResposta;
	}

	public void setPerguntaTipoRespostaDsTipoResposta(String perguntaTipoRespostaDsTipoResposta) {
		this.perguntaTipoRespostaDsTipoResposta = perguntaTipoRespostaDsTipoResposta;
	}

	public Long getPerguntaDomiId() {
		return perguntaDomiId;
	}

	public void setPerguntaDomiId(Long perguntaDomiId) {
		this.perguntaDomiId = perguntaDomiId;
	}

	public String getPerguntaDomiNmDomi() {
		return perguntaDomiNmDomi;
	}

	public void setPerguntaDomiNmDomi(String perguntaDomiNmDomi) {
		this.perguntaDomiNmDomi = perguntaDomiNmDomi;
	}

	public String getPerguntaInEditavel() {
		return perguntaInEditavel;
	}

	public void setPerguntaInEditavel(String perguntaInEditavel) {
		this.perguntaInEditavel = perguntaInEditavel;
	}

	public List<RespostaDTO> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<RespostaDTO> respostas) {
		this.respostas = respostas;
	}
}