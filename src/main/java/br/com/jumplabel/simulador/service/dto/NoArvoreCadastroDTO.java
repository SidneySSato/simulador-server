package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * DTO utilizado para enviar o request com os dados de noArvore
 */
public class NoArvoreCadastroDTO implements Serializable {

	private static final long serialVersionUID = -1976331725291006143L;

	// Utilizado por CD_TIPO_NO
	public static final String TIPO_NO_PERGUNTA = "PERG";
	public static final String TIPO_NO_RESPOSTA = "RESP";
	public static final String TIPO_NO_PLANO = "PLAN";
	public static final String TIPO_NO_PRODUTO = "PROD";
	
	@JsonInclude(Include.NON_NULL)
    private Long noId;

	@JsonInclude(Include.NON_NULL)
    private Long noPaiId;

	@JsonInclude(Include.NON_NULL)
    private Long arvoreDecisaoId;

    @Size(max = 4)
	@JsonInclude(Include.NON_NULL)
    private String cdTipoNo;
	
	@JsonInclude(Include.NON_NULL)
    private Long perguntaId;

	@JsonInclude(Include.NON_NULL)
    private String perguntaDsPergunta;

    @JsonInclude(Include.NON_NULL)
    private ProdutoArvoreDTO produtoArvore;

	@JsonInclude(Include.NON_NULL)
	private ProdSegdPlanSegmDTO prodSegdPlanSegm;
	
	@JsonInclude(Include.NON_NULL)
	private Set<MensagemProdutoDTO> mensagemProdutos;
	
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

        NoArvoreCadastroDTO noArvoreDTO = (NoArvoreCadastroDTO) o;

        if ( ! Objects.equals(noId, noArvoreDTO.noId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(noId);
    }

    @Override
    public String toString() {
        return "ListaNoArvoreDTO{" +
            "id=" + arvoreDecisaoId +
            '}';
    }

	public Long getArvoreDecisaoId() {
		return arvoreDecisaoId;
	}

	public void setArvoreDecisaoId(Long arvoreDecisaoId) {
		this.arvoreDecisaoId = arvoreDecisaoId;
	}

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}

	public Long getPerguntaId() {
		return perguntaId;
	}

	public void setPerguntaId(Long perguntaId) {
		this.perguntaId = perguntaId;
	}

	public String getPerguntaDsPergunta() {
		return perguntaDsPergunta;
	}

	public void setPerguntaDsPergunta(String perguntaDsPergunta) {
		this.perguntaDsPergunta = perguntaDsPergunta;
	}

	public Long getNoId() {
		return noId;
	}

	public void setNoId(Long noId) {
		this.noId = noId;
	}

	public Long getNoPaiId() {
		return noPaiId;
	}

	public void setNoPaiId(Long noPaiId) {
		this.noPaiId = noPaiId;
	}

	public String getCdTipoNo() {
		return cdTipoNo;
	}

	public void setCdTipoNo(String cdTipoNo) {
		this.cdTipoNo = cdTipoNo;
	}

	public Set<MensagemProdutoDTO> getMensagemProdutos() {
		return mensagemProdutos;
	}

	public void setMensagemProdutos(Set<MensagemProdutoDTO> mensagemProdutos) {
		this.mensagemProdutos = mensagemProdutos;
	}

	public ProdutoArvoreDTO getProdutoArvore() {
		return produtoArvore;
	}

	public void setProdutoArvore(ProdutoArvoreDTO produtoArvore) {
		this.produtoArvore = produtoArvore;
	}

	public ProdSegdPlanSegmDTO getProdSegdPlanSegm() {
		return prodSegdPlanSegm;
	}

	public void setProdSegdPlanSegm(ProdSegdPlanSegmDTO prodSegdPlanSegm) {
		this.prodSegdPlanSegm = prodSegdPlanSegm;
	}
}
