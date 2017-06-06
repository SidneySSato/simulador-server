package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the NoArvore entity.
 */
public class NoArvoreDTO extends NoArvoreCadastroDTO implements  Serializable {

	private static final long serialVersionUID = -1976331725291006143L;

	/**
	 * Descricao da resposta, essa descricao eh construida a partir da regra
	 * de negocio para cada tipo de resposta (range, dados corporativos, resposta_is) 
	 */
	@JsonInclude(Include.NON_NULL)
    private RespostaDTO resposta;


	/** Lista de mensagens produto */
	@JsonInclude(Include.NON_NULL)
    private Set<NoArvoreDTO> filhos = null;


    @Override
    public String toString() {
        return super.toString();
    }

	public Set<NoArvoreDTO> getFilhos() {
		return filhos;
	}

	public void setFilhos(Set<NoArvoreDTO> filhos) {
		this.filhos = filhos;
	}

	public RespostaDTO getResposta() {
		return resposta;
	}

	public void setResposta(RespostaDTO resposta) {
		this.resposta = resposta;
	}
}
