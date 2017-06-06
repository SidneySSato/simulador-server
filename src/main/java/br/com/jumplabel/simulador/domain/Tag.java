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
 * A Tag.
 */
@Entity
@Table(name = "TB_TAG")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    public Tag () {}

    public Tag (Long id) { 
    	this.id = id;
    }
    
    @Id
    @Column(name = "CD_TAG", length = 10, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TAG_SEQ")
    @GenericGenerator(
            name = "TAG_SEQ",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
            		@Parameter(name = "prefer_sequence_per_entity", value = "true"),
            		@Parameter(name = "optimizer", value = "none"),
                    @Parameter(name = "sequence_name", value = "TAG_SEQ")
            }
    )
    private Long id;
    
    @NotNull
    @Size(max = 50)
    @Column(name = "DS_TAG", length = 50, nullable = false)
    private String dsTag;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.tag")
    @JsonIgnore
    private Set<PerguntaTag> perguntaTags = new HashSet<PerguntaTag>(0);
    
    public Set<PerguntaTag> getPerguntaTags() {
        return perguntaTags;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsTag() {
        return dsTag;
    }

    public Tag dsTag(String dsTag) {
        this.dsTag = dsTag;
        return this;
    }

    public void setDsTag(String dsTag) {
        this.dsTag = dsTag;
    }

    public Tag perguntaTags(Set<PerguntaTag> perguntaTags) {
        this.perguntaTags = perguntaTags;
        return this;
    }

    public Tag addPerguntaTag(PerguntaTag perguntaTag) {
        this.perguntaTags.add(perguntaTag);
        perguntaTag.setTag(this);
        return this;
    }

    public Tag removePerguntaTag(PerguntaTag perguntaTag) {
        this.perguntaTags.remove(perguntaTag);
        perguntaTag.setTag(null);
        return this;
    }

    public void setPerguntaTags(Set<PerguntaTag> perguntaTags) {
        this.perguntaTags = perguntaTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        if (tag.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Tag{" +
            "id=" + id +
            ", dsTag='" + dsTag + "'" +
            '}';
    }
}
