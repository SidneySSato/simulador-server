package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * A DTO for the TipoResposta entity.
 */
public class TipoRespostaDTO implements Serializable {

	private static final long serialVersionUID = 6659402433906154845L;

	private Long id;

    @NotNull
    @Size(max = 50)
    private String dsTipoResposta;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDsTipoResposta() {
        return dsTipoResposta;
    }

    public void setDsTipoResposta(String dsTipoResposta) {
        this.dsTipoResposta = dsTipoResposta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipoRespostaDTO tipoRespostaDTO = (TipoRespostaDTO) o;

        if ( ! Objects.equals(id, tipoRespostaDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TipoRespostaDTO{" +
            "id=" + id +
            ", dsTipoResposta='" + dsTipoResposta + "'" +
            '}';
    }
}
