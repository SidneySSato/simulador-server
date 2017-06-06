package br.com.jumplabel.simulador.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Domi.
 */
@Entity
@Table(name = "TB_DOMI")
public class Domi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_DOMI", length = 10, nullable = false)
    private Long id;

    @Size(max = 100)
    @Column(name = "NM_DOMI", length = 100)
    private String nmDomi;

    @OneToMany(mappedBy = "domi")
    @JsonIgnore
    private Set<CntdDomi> cntdDomis = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmDomi() {
        return nmDomi;
    }

    public Domi nmDomi(String nmDomi) {
        this.nmDomi = nmDomi;
        return this;
    }

    public void setNmDomi(String nmDomi) {
        this.nmDomi = nmDomi;
    }

    public Set<CntdDomi> getCntdDomis() {
        return cntdDomis;
    }

    public Domi cntdDomis(Set<CntdDomi> cntdDomis) {
        this.cntdDomis = cntdDomis;
        return this;
    }

    public Domi addCntdDomi(CntdDomi cntdDomi) {
        this.cntdDomis.add(cntdDomi);
        cntdDomi.setDomi(this);
        return this;
    }

    public Domi removeCntdDomi(CntdDomi cntdDomi) {
        this.cntdDomis.remove(cntdDomi);
        cntdDomi.setDomi(null);
        return this;
    }

    public void setCntdDomis(Set<CntdDomi> cntdDomis) {
        this.cntdDomis = cntdDomis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Domi domi = (Domi) o;
        if (domi.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, domi.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Domi{" +
            "id=" + id +
            ", nmDomi='" + nmDomi + "'" +
            '}';
    }
}
