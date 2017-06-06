package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the PerguntaTag entity.
 */
public class PerguntaTagDTO implements Serializable {

	private static final long serialVersionUID = -5140806269717435021L;


	@JsonInclude(Include.NON_NULL)
    private Long perguntaId;
    
	@JsonInclude(Include.NON_NULL)
    private String perguntaDsPergunta;

	@JsonInclude(Include.NON_NULL)
    private Long tagId;
    
	@JsonInclude(Include.NON_NULL)
    private String tagDsTag;

	@JsonInclude(Include.NON_NULL)
	private ResultDTO result;	

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

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }


    public String getTagDsTag() {
        return tagDsTag;
    }

    public void setTagDsTag(String tagDsTag) {
        this.tagDsTag = tagDsTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PerguntaTagDTO perguntaTagDTO = (PerguntaTagDTO) o;

        if (!(Objects.equals(perguntaId, perguntaTagDTO.perguntaId) && 
        	  Objects.equals(tagId, perguntaTagDTO.tagId))) 
        	return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(perguntaId) + Objects.hashCode(tagId);
    }

    @Override
    public String toString() {
        return "PerguntaTagDTO{" +
            "perguntaId=" + perguntaId +
            "tagId=" + tagId +
            '}';
    }

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}
}
