package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the MensagemProduto entity.
 */
public class MensagemProdutoDTO implements Serializable {

	private static final long serialVersionUID = 5652248564999187054L;

	private Long id;

    @NotNull
    @Size(max = 120)
    private String dsMensagem;

    /**
     * Atributo auxiliar para verificar se o registro será inserido no banco de dados
     * true para inserir e false para nao inserir ou apagar
     */
    @JsonInclude(Include.NON_NULL)
    private Boolean inserir;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MensagemProdutoDTO mensagemProdutoDTO = (MensagemProdutoDTO) o;

        if ( ! Objects.equals(id, mensagemProdutoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MensagemProdutoDTO{" +
            "id=" + id +
            ", dsMensagem='" + dsMensagem + "'" +
            '}';
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDsMensagem() {
        return dsMensagem;
    }

    public void setDsMensagem(String dsMensagem) {
        this.dsMensagem = dsMensagem;
    }

	public Boolean getInserir() {
		return inserir;
	}

	public void setInserir(Boolean inserir) {
		this.inserir = inserir;
	}
}