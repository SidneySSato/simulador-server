package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the Domi entity.
 */
public class DomiDTO implements Serializable {

	private static final long serialVersionUID = -8735404737404693767L;

	@JsonInclude(Include.NON_NULL)
    private Long id;

    @Size(max = 100)
    @JsonInclude(Include.NON_NULL)
    private String nmDomi;
    
    @JsonInclude(Include.NON_NULL)
    private List<CntdDomiDTO> cntdDomis = null;

	public List<CntdDomiDTO> getCntdDomis() {
		return cntdDomis;
	}

	public void setCntdDomis(List<CntdDomiDTO> cntdDomis) {
		this.cntdDomis = cntdDomis;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNmDomi() {
        return nmDomi;
    }

    public void setNmDomi(String nmDomi) {
        this.nmDomi = nmDomi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DomiDTO domiDTO = (DomiDTO) o;

        if ( ! Objects.equals(id, domiDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DomiDTO{" +
            "id=" + id +
            ", nmDomi='" + nmDomi + "'" +
            '}';
    }
}
