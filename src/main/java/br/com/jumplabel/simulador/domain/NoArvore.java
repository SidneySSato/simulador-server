package br.com.jumplabel.simulador.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A NoArvore.
 */
@Entity
@Table(name = "TB_NO_ARVORE")
public class NoArvore implements Serializable {

	private static final long serialVersionUID = 1L;

	public NoArvore() {}

	public NoArvore(Long id) {
		this.id = id;
	}

	@Id
    @Column(name = "CD_NO_ARVORE", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NO_ARVORE_SEQ")
    @GenericGenerator(
            name = "NO_ARVORE_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
            		@Parameter(name = "prefer_sequence_per_entity", value = "true"),
            		@Parameter(name = "optimizer", value = "none"),
                    @Parameter(name = "sequence_name", value = "NO_ARVORE_SEQ")
            }
	)
	private Long id;

    @OneToOne
    @JoinColumn(unique = true, name="CD_PERGUNTA")
    private Pergunta pergunta;

    @ManyToOne
    @JoinColumn(name = "CD_NO_ARVORE_PAI")
    private NoArvore pai;
    
    @OneToMany(mappedBy="pai", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private Set<NoArvore> filhos = new HashSet<>(); 	
    
    @Size(max = 4)
    @Column(name = "CD_TIPO_NO", length = 4)
    private String cdTipoNo;

    @ManyToOne(fetch = FetchType.LAZY)
   	@JoinColumns({
   		@JoinColumn(name = "CD_PROD", insertable = false, updatable = false),
   		@JoinColumn(name = "CD_SUBP", insertable = false, updatable = false)})    
    private ProdutoArvore produtoArvore;

    @ManyToOne
    @JoinColumn(name="CD_ARVORE_DECISAO")
    private ArvoreDecisao arvoreDecisao;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CD_PLAN_DOMI", updatable = false),
		@JoinColumn(name = "CD_PROD", updatable = false),
		@JoinColumn(name = "CD_SUBP", updatable = false),
		@JoinColumn(name = "CD_SEGD_DOMI", updatable = false),
		@JoinColumn(name = "CD_SEGM_DOMI", updatable = false)}
	) 
    private ProdSegdPlanSegm prodSegdPlanSegm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="CD_RESPOSTA")
    private Resposta resposta;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true)
    @PrimaryKeyJoinColumn
    private NoArvoreCopia noArvoreCopia;

    @JsonIgnore
    @OneToMany(mappedBy = "pk.noArvoreId", cascade=CascadeType.ALL, fetch = FetchType.LAZY)    
    private Set<NoArvoreMensagemProduto> noArvoreMensagemProdutos = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NoArvore noArvore = (NoArvore) o;
        if (noArvore.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, noArvore.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "NoArvore{" +
            "id=" + id +
            ", cdTipoNo='" + cdTipoNo + "'" +
            ", pai='" + pai + "'" +
            '}';
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCdTipoNo() {
        return cdTipoNo;
    }

    public NoArvore cdTipoNo(String cdTipoNo) {
        this.cdTipoNo = cdTipoNo;
        return this;
    }

    public void setCdTipoNo(String cdTipoNo) {
        this.cdTipoNo = cdTipoNo;
    }

    public ProdutoArvore getProdutoArvore() {
        return produtoArvore;
    }

    public NoArvore produtoArvore(ProdutoArvore produtoArvore) {
        this.produtoArvore = produtoArvore;
        return this;
    }

    public void setProdutoArvore(ProdutoArvore produtoArvore) {
        this.produtoArvore = produtoArvore;
    }

    public ArvoreDecisao getArvoreDecisao() {
        return arvoreDecisao;
    }

    public NoArvore arvoreDecisao(ArvoreDecisao arvoreDecisao) {
        this.arvoreDecisao = arvoreDecisao;
        return this;
    }

    public void setArvoreDecisao(ArvoreDecisao arvoreDecisao) {
        this.arvoreDecisao = arvoreDecisao;
    }

    public ProdSegdPlanSegm getProdSegdPlanSegm() {
        return prodSegdPlanSegm;
    }

    public NoArvore prodSegdPlanSegm(ProdSegdPlanSegm prodSegdPlanSegm) {
        this.prodSegdPlanSegm = prodSegdPlanSegm;
        return this;
    }

    public void setProdSegdPlanSegm(ProdSegdPlanSegm prodSegdPlanSegm) {
        this.prodSegdPlanSegm = prodSegdPlanSegm;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public NoArvore resposta(Resposta resposta) {
        this.resposta = resposta;
        return this;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }

    public NoArvoreCopia getNoArvoreCopia() {
        return noArvoreCopia;
    }

    public NoArvore noArvoreCopia(NoArvoreCopia noArvoreCopia) {
        this.noArvoreCopia = noArvoreCopia;
        return this;
    }

    public void setNoArvoreCopia(NoArvoreCopia noArvoreCopia) {
        this.noArvoreCopia = noArvoreCopia;
    }

    public Set<NoArvoreMensagemProduto> getNoArvoreMensagemProdutos() {
        return noArvoreMensagemProdutos;
    }

    public NoArvore noArvoreMensagemProdutos(Set<NoArvoreMensagemProduto> noArvoreMensagemProdutos) {
        this.noArvoreMensagemProdutos = noArvoreMensagemProdutos;
        return this;
    }

    public NoArvore addFilho(NoArvore noArvore) {
        this.filhos.add(noArvore);
        noArvore.setPai(this);
        return this;
    }

    public NoArvore removeFilho(NoArvore noArvore) {
        this.filhos.remove(noArvore);
        noArvore.setPai(null);
        return this;
    }

    public NoArvore addNoArvoreMensagemProduto(NoArvoreMensagemProduto noArvoreMensagemProduto) {
        this.noArvoreMensagemProdutos.add(noArvoreMensagemProduto);
        noArvoreMensagemProduto.setNoArvore(this);
        return this;
    }

    public NoArvore removeNoArvoreMensagemProduto(NoArvoreMensagemProduto noArvoreMensagemProduto) {
        this.noArvoreMensagemProdutos.remove(noArvoreMensagemProduto);
        noArvoreMensagemProduto.setNoArvore(null);
        return this;
    }

    public void setNoArvoreMensagemProdutos(Set<NoArvoreMensagemProduto> noArvoreMensagemProdutos) {
        this.noArvoreMensagemProdutos = noArvoreMensagemProdutos;
    }
	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public NoArvore getPai() {
		return pai;
	}

	public void setPai(NoArvore pai) {
		this.pai = pai;
	}

	public Set<NoArvore> getFilhos() {
		return filhos;
	}

	public void setFilhos(Set<NoArvore> filhos) {
		this.filhos = filhos;
	}    
}
