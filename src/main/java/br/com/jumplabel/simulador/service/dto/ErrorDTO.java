package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO para retornar uma mensagem de erro.
 */
public class ErrorDTO implements Serializable {

	private static final long serialVersionUID = 2065495330798066223L;

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

        ErrorDTO tagDTO = (ErrorDTO) o;

        if ( ! Objects.equals(result, tagDTO.result)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
            "result=" + result+
            '}';
    }

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}
}
