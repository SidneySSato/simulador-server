package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.Size;


/**
 * A DTO for the RespostaIs entity.
 */
public class RespostaIsDTO implements Serializable {

	private static final long serialVersionUID = 5808347559117159368L;

	private Long id;

    @Size(max = 4)
    private String cdTipoIs;


    private Long pergunta1Id;
    
    private Long pergunta2Id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCdTipoIs() {
        return cdTipoIs;
    }

    public void setCdTipoIs(String cdTipoIs) {
        this.cdTipoIs = cdTipoIs;
    }

    public Long getPergunta1Id() {
        return pergunta1Id;
    }

    public void setPergunta1Id(Long perguntaId) {
        this.pergunta1Id = perguntaId;
    }

    public Long getPergunta2Id() {
        return pergunta2Id;
    }

    public void setPergunta2Id(Long perguntaId) {
        this.pergunta2Id = perguntaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RespostaIsDTO respostaIsDTO = (RespostaIsDTO) o;

        if ( ! Objects.equals(id, respostaIsDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RespostaIsDTO{" +
            "id=" + id +
            ", cdTipoIs='" + cdTipoIs + "'" +
            '}';
    }
}
