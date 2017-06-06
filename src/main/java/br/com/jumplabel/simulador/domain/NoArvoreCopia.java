package br.com.jumplabel.simulador.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A NoArvoreCopia.
 */
@Entity
@Table(name = "TB_NO_ARVORE_COPIA")
public class NoArvoreCopia implements Serializable {

	private static final long serialVersionUID = 8400395557907969068L;

	@Id
    @Column(name = "CD_NO_ARVORE_COPIA", length = 10, nullable = false)
    private Long id;

    @Size(max = 60)
    @Column(name = "ds_no_arvore_copia", length = 60)
    private String dsNoArvoreCopia;

    @Column(name = "dt_copia")
    private ZonedDateTime dtCopia;

    @MapsId
    @OneToOne(mappedBy = "noArvoreCopia")
    @JoinColumn(name = "CD_NO_ARVORE_COPIA")
    @JsonIgnore
    private NoArvore noArvore;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NoArvoreCopia noArvoreCopia = (NoArvoreCopia) o;
        if (noArvoreCopia.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, noArvoreCopia.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "NoArvoreCopia{" +
            "id=" + id +
            ", dsNoArvoreCopia='" + dsNoArvoreCopia + "'" +
            ", dtCopia='" + dtCopia + "'" +
            '}';
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsNoArvoreCopia() {
        return dsNoArvoreCopia;
    }

    public NoArvoreCopia dsNoArvoreCopia(String dsNoArvoreCopia) {
        this.dsNoArvoreCopia = dsNoArvoreCopia;
        return this;
    }

    public void setDsNoArvoreCopia(String dsNoArvoreCopia) {
        this.dsNoArvoreCopia = dsNoArvoreCopia;
    }

    public ZonedDateTime getDtCopia() {
        return dtCopia;
    }

    public NoArvoreCopia dtCopia(ZonedDateTime dtCopia) {
        this.dtCopia = dtCopia;
        return this;
    }

    public void setDtCopia(ZonedDateTime dtCopia) {
        this.dtCopia = dtCopia;
    }

    public NoArvore getNoArvore() {
        return noArvore;
    }

    public NoArvoreCopia noArvore(NoArvore noArvore) {
        this.noArvore = noArvore;
        return this;
    }

    public void setNoArvore(NoArvore noArvore) {
        this.noArvore = noArvore;
    }    
}
