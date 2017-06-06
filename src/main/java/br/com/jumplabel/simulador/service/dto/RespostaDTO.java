package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the Resposta entity.
 */
public class RespostaDTO implements Serializable {

	private static final long serialVersionUID = -7455367151973826434L;

	// Utilizado por Dados do tipo
	public static final String TIPO_RESPOSTA_INPUT_CATEGORIA_NUMERICO = "NUMERICO";
	public static final String TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_TODOS = "ALPHANUMERICO_TODOS";
	public static final String TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_ESPECIFICO = "ALPHANUMERICO_ESPECIFICO";
	
	public static final String TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_TODOS_VALOR_DEFAULT = "Todos";

	
    @JsonInclude(Include.NON_NULL)
	private Long id;

    @JsonInclude(Include.NON_NULL)
    @Size(max = 80)
    private String dsResposta;

    @JsonInclude(Include.NON_NULL)
    @Size(max = 80)
    private String dsCategoria;

    @JsonInclude(Include.NON_NULL)
    private Long perguntaId;
    
    /**
     * Atributo auxiliar para verificar se o registro será inserido no banco de dados
     * true para inserir e false para nao inserir ou apagar
     */
    @JsonInclude(Include.NON_NULL)
    private Boolean inserir;

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

    @JsonInclude(Include.NON_NULL)
    private String perguntaDsPergunta;

	@JsonInclude(Include.NON_NULL)
    private RangeRespostaDTO rangeResposta = null;

	@JsonInclude(Include.NON_NULL)
	private List<RespCorporativaDTO> respCorporativas = null;

	@JsonInclude(Include.NON_NULL)
	private RespostaIsDTO respostaIs = null;
	
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

        RespostaDTO respostaDTO = (RespostaDTO) o;

        if ( ! Objects.equals(id, respostaDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RespostaDTO{" +
            "id=" + id +
            ", perguntaId='" + perguntaId + "'" +
            ", dsResposta='" + dsResposta + "'" +
            ", dsCategoria='" + dsCategoria + "'" +
            ", inserir='" + inserir + "'" +
            '}';
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDsResposta() {
		return dsResposta;
	}

	public void setDsResposta(String dsResposta) {
		this.dsResposta = dsResposta;
	}

	public String getDsCategoria() {
		return dsCategoria;
	}

	public void setDsCategoria(String dsCategoria) {
		this.dsCategoria = dsCategoria;
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

	public String getPerguntaDsPergunta() {
		return perguntaDsPergunta;
	}

	public void setPerguntaDsPergunta(String perguntaDsPergunta) {
		this.perguntaDsPergunta = perguntaDsPergunta;
	}

	public RangeRespostaDTO getRangeResposta() {
		return rangeResposta;
	}

	public void setRangeResposta(RangeRespostaDTO rangeResposta) {
		this.rangeResposta = rangeResposta;
	}

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}

	public RespostaIsDTO getRespostaIs() {
		return respostaIs;
	}

	public void setRespostaIs(RespostaIsDTO respostaIs) {
		this.respostaIs = respostaIs;
	}

	public Boolean getInserir() {
		return inserir;
	}

	public void setInserir(Boolean inserir) {
		this.inserir = inserir;
	}

	public List<RespCorporativaDTO> getRespCorporativas() {
		return respCorporativas;
	}

	public void setRespCorporativas(List<RespCorporativaDTO> respCorporativas) {
		this.respCorporativas = respCorporativas;
	}
}