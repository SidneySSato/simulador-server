package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the ArvoreDecisao entity.
 */
public class ArvoreDecisaoDTO implements Serializable {

	private static final long serialVersionUID = 4342210792209490324L;

	private Long id;

    @Size(max = 80)
    private String dsArvoreDecisao;

    private Long qtProdutos;

    private Long qtPlanos;

    @Size(max = 4)
    private String cdSituArvore;

	@JsonInclude(Include.NON_NULL)
	private Set<ArvoreFamiliaDTO> arvoreFamilias = null;
	
	@JsonInclude(Include.NON_NULL)
	private Set<ArvoreCanalDTO> arvoreCanais = null;

	@JsonInclude(Include.NON_NULL)
    private ResultDTO result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getDsArvoreDecisao() {
        return dsArvoreDecisao;
    }

    public void setDsArvoreDecisao(String dsArvoreDecisao) {
        this.dsArvoreDecisao = dsArvoreDecisao;
    }
    public Long getQtProdutos() {
        return qtProdutos;
    }

    public void setQtProdutos(Long qtProdutos) {
        this.qtProdutos = qtProdutos;
    }
    public Long getQtPlanos() {
        return qtPlanos;
    }

    public void setQtPlanos(Long qtPlanos) {
        this.qtPlanos = qtPlanos;
    }
    public String getCdSituArvore() {
        return cdSituArvore;
    }

    public void setCdSituArvore(String cdSituArvore) {
        this.cdSituArvore = cdSituArvore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArvoreDecisaoDTO arvoreDecisaoDTO = (ArvoreDecisaoDTO) o;

        if ( ! Objects.equals(id, arvoreDecisaoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ArvoreDecisaoDTO{" +
            "id=" + id +
            ", dsArvoreDecisao='" + dsArvoreDecisao + "'" +
            ", qtProdutos='" + qtProdutos + "'" +
            ", qtPlanos='" + qtPlanos + "'" +
            ", cdSituArvore='" + cdSituArvore + "'" +
            '}';
    }

	public Set<ArvoreFamiliaDTO> getArvoreFamilias() {
		return arvoreFamilias;
	}

	public void setArvoreFamilias(Set<ArvoreFamiliaDTO> arvoreFamilias) {
		this.arvoreFamilias = arvoreFamilias;
	}

	public Set<ArvoreCanalDTO> getArvoreCanais() {
		return arvoreCanais;
	}

	public void setArvoreCanais(Set<ArvoreCanalDTO> arvoreCanais) {
		this.arvoreCanais = arvoreCanais;
	}

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}
}
