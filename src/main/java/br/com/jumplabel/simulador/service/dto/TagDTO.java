package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the Tag entity.
 */
public class TagDTO implements Serializable {

	private static final long serialVersionUID = 2065495330798066223L;

    @JsonInclude(Include.NON_NULL)
	private Long id;

    @JsonInclude(Include.NON_NULL)
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String dsTag;
    
    @JsonInclude(Include.NON_NULL)
    private ResultDTO result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDsTag() {
        return dsTag;
    }

    public void setDsTag(String dsTag) {
        this.dsTag = dsTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TagDTO tagDTO = (TagDTO) o;

        if ( ! Objects.equals(id, tagDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TagDTO{" +
            "id=" + id +
            ", dsTag='" + dsTag + "'" +
            '}';
    }

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}
}
