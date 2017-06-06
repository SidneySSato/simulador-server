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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A ArvoreDecisao.
 */
@Entity
@Table(name = "TB_ARVORE_DECISAO")
public class ArvoreDecisao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_ARVORE_DECISAO", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ARVORE_DECISAO_SEQ")
    @GenericGenerator(
            name = "ARVORE_DECISAO_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "prefer_sequence_per_entity", value = "true"),
                    @Parameter(name = "optimizer", value = "none"),
                    @Parameter(name = "sequence_name", value = "ARVORE_DECISAO_SEQ")
            }
    )    private Long id;
    @Size(max = 80)
    @Column(name = "ds_arvore_decisao", length = 80)
    private String dsArvoreDecisao;

    @Column(name = "qt_produtos")
    private Long qtProdutos;

    @Column(name = "qt_planos")
    private Long qtPlanos;

    @Size(max = 4)
    @Column(name = "cd_situ_arvore", length = 4)
    private String cdSituArvore;

    @OneToMany(mappedBy = "arvoreDecisao", cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    @JsonIgnore
    private Set<ArvoreFamilia> arvoreFamilias = new HashSet<ArvoreFamilia>(0);

    @OneToMany(mappedBy = "arvoreDecisao", cascade=CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    @JsonIgnore
    private Set<ArvoreCanal> arvoreCanais = new HashSet<ArvoreCanal>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsArvoreDecisao() {
        return dsArvoreDecisao;
    }

    public ArvoreDecisao dsArvoreDecisao(String dsArvoreDecisao) {
        this.dsArvoreDecisao = dsArvoreDecisao;
        return this;
    }

    public void setDsArvoreDecisao(String dsArvoreDecisao) {
        this.dsArvoreDecisao = dsArvoreDecisao;
    }

    public Long getQtProdutos() {
        return qtProdutos;
    }

    public ArvoreDecisao qtProdutos(Long qtProdutos) {
        this.qtProdutos = qtProdutos;
        return this;
    }

    public void setQtProdutos(Long qtProdutos) {
        this.qtProdutos = qtProdutos;
    }

    public Long getQtPlanos() {
        return qtPlanos;
    }

    public ArvoreDecisao qtPlanos(Long qtPlanos) {
        this.qtPlanos = qtPlanos;
        return this;
    }

    public void setQtPlanos(Long qtPlanos) {
        this.qtPlanos = qtPlanos;
    }

    public String getCdSituArvore() {
        return cdSituArvore;
    }

    public ArvoreDecisao cdSituArvore(String cdSituArvore) {
        this.cdSituArvore = cdSituArvore;
        return this;
    }

    public void setCdSituArvore(String cdSituArvore) {
        this.cdSituArvore = cdSituArvore;
    }

    public Set<ArvoreFamilia> getArvoreFamilias() {
        return arvoreFamilias;
    }

    public ArvoreDecisao arvoreFamilias(Set<ArvoreFamilia> arvoreFamilias) {
        this.arvoreFamilias = arvoreFamilias;
        return this;
    }

    public ArvoreDecisao addArvoreFamilia(ArvoreFamilia arvoreFamilia) {
        this.arvoreFamilias.add(arvoreFamilia);
        arvoreFamilia.setArvoreDecisao(this);
        return this;
    }

    public ArvoreDecisao removeArvoreFamilia(ArvoreFamilia arvoreFamilia) {
        this.arvoreFamilias.remove(arvoreFamilia);
        arvoreFamilia.setArvoreDecisao(null);
        return this;
    }

    public void setArvoreFamilias(Set<ArvoreFamilia> arvoreFamilias) {
        this.arvoreFamilias = arvoreFamilias;
    }

    public Set<ArvoreCanal> getArvoreCanais() {
        return arvoreCanais;
    }

    public ArvoreDecisao arvoreCanais(Set<ArvoreCanal> arvoreCanais) {
        this.arvoreCanais = arvoreCanais;
        return this;
    }

    public ArvoreDecisao addArvoreCanal(ArvoreCanal arvoreCanal) {
        this.arvoreCanais.add(arvoreCanal);
        arvoreCanal.setArvoreDecisao(this);
        return this;
    }

    public ArvoreDecisao removeArvoreCanal(ArvoreCanal arvoreCanal) {
        this.arvoreCanais.remove(arvoreCanal);
        arvoreCanal.setArvoreDecisao(null);
        return this;
    }

    public void setArvoreCanais(Set<ArvoreCanal> arvoreCanais) {
        this.arvoreCanais = arvoreCanais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArvoreDecisao arvoreDecisao = (ArvoreDecisao) o;
        if (arvoreDecisao.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, arvoreDecisao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ArvoreDecisao{" +
            "id=" + id +
            ", dsArvoreDecisao='" + dsArvoreDecisao + "'" +
            ", qtProdutos='" + qtProdutos + "'" +
            ", qtPlanos='" + qtPlanos + "'" +
            ", cdSituArvore='" + cdSituArvore + "'" +
            '}';
    }
}
