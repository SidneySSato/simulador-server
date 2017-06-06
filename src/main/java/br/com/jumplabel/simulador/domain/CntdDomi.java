package br.com.jumplabel.simulador.domain;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * A CntdDomi.
 */
@Entity
@Table(name = "TB_CNTD_DOMI")
public class CntdDomi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_CNTD_DOMI", length = 10, nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "CD_CNTD_DOMI_LGDO", length = 50)
    private String cdCntdDomiLgdo;

    @Size(max = 250)
    @Column(name = "DS_CNTD_DOMI_LGDO", length = 250)
    private String dsCntdDomiLgdo;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "CD_DOMI")
    private Domi domi;

    /**
     * TODO verificar se vai ser utilizado essa entidade, pois na busca de familias a partir de produto,
     * esta sendo realizado selects a mais para buscar o CompDomiSegd e CompDomiRamo
     */
    
//    @OneToOne(optional=true, fetch=FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    private CompDomiSegd compDomiSegd;

//    @OneToOne(optional=true, fetch=FetchType.LAZY)
//    @PrimaryKeyJoinColumn
//    private CompDomiRamo compDomiRamo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCdCntdDomiLgdo() {
        return cdCntdDomiLgdo;
    }

    public CntdDomi cdCntdDomiLgdo(String cdCntdDomiLgdo) {
        this.cdCntdDomiLgdo = cdCntdDomiLgdo;
        return this;
    }//

    public void setCdCntdDomiLgdo(String cdCntdDomiLgdo) {
        this.cdCntdDomiLgdo = cdCntdDomiLgdo;
    }

    public String getDsCntdDomiLgdo() {
        return dsCntdDomiLgdo;
    }

    public CntdDomi dsCntdDomiLgdo(String dsCntdDomiLgdo) {
        this.dsCntdDomiLgdo = dsCntdDomiLgdo;
        return this;
    }

    public void setDsCntdDomiLgdo(String dsCntdDomiLgdo) {
        this.dsCntdDomiLgdo = dsCntdDomiLgdo;
    }

    public Domi getDomi() {
        return domi;
    }

    public CntdDomi domi(Domi domi) {
        this.domi = domi;
        return this;
    }

    public void setDomi(Domi domi) {
        this.domi = domi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CntdDomi cntdDomi = (CntdDomi) o;
        if (cntdDomi.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, cntdDomi.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CntdDomi{" +
            "id=" + id +
            ", cdCntdDomiLgdo='" + cdCntdDomiLgdo + "'" +
            ", dsCntdDomiLgdo='" + dsCntdDomiLgdo + "'" +
            '}';
    }
}
