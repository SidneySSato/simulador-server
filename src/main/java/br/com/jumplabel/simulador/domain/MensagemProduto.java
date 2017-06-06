package br.com.jumplabel.simulador.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A MensagemProduto.
 */
@Entity
@Table(name = "TB_MENSAGEM_PRODUTO")
public class MensagemProduto implements Serializable {

	private static final long serialVersionUID = -8734819064722175804L;

	public MensagemProduto () {}

    public MensagemProduto (Long id) { 
    	this.id = id;
    }
    @Id
    @Column(name = "CD_MENSAGEM_PRODUTO", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MENSAGEM_PRODUTO_SEQ")
    @GenericGenerator(
            name = "MENSAGEM_PRODUTO_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
            		@Parameter(name = "prefer_sequence_per_entity", value = "true"),
            		@Parameter(name = "optimizer", value = "none"),
                    @Parameter(name = "sequence_name", value = "MENSAGEM_PRODUTO_SEQ")
            }
    )
    private Long id;

    @NotNull
    @Size(max = 120)
    @Column(name = "ds_mensagem", length = 120, nullable = false)
    private String dsMensagem;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.mensagemProdutoId")
    @JsonIgnore
    private Set<NoArvoreMensagemProduto> noArvoreMensagemProdutos = new HashSet<>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsMensagem() {
        return dsMensagem;
    }

    public MensagemProduto dsMensagem(String dsMensagem) {
        this.dsMensagem = dsMensagem;
        return this;
    }

    public void setDsMensagem(String dsMensagem) {
        this.dsMensagem = dsMensagem;
    }

    public Set<NoArvoreMensagemProduto> getNoArvoreMensagemProdutos() {
        return noArvoreMensagemProdutos;
    }

    public MensagemProduto noArvoreMensagemProdutos(Set<NoArvoreMensagemProduto> noArvoreMensagemProdutos) {
        this.noArvoreMensagemProdutos = noArvoreMensagemProdutos;
        return this;
    }

    public MensagemProduto addNoArvoreMensagemProduto(NoArvoreMensagemProduto noArvoreMensagemProduto) {
        this.noArvoreMensagemProdutos.add(noArvoreMensagemProduto);
        noArvoreMensagemProduto.setMensagemProduto(this);
        return this;
    }

    public MensagemProduto removeNoArvoreMensagemProduto(NoArvoreMensagemProduto noArvoreMensagemProduto) {
        this.noArvoreMensagemProdutos.remove(noArvoreMensagemProduto);
        noArvoreMensagemProduto.setMensagemProduto(null);
        return this;
    }

    public void setNoArvoreMensagemProdutos(Set<NoArvoreMensagemProduto> noArvoreMensagemProdutos) {
        this.noArvoreMensagemProdutos = noArvoreMensagemProdutos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MensagemProduto mensagemProduto = (MensagemProduto) o;
        if (mensagemProduto.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, mensagemProduto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MensagemProduto{" +
            "id=" + id +
            ", dsMensagem='" + dsMensagem + "'" +
            '}';
    }
}
