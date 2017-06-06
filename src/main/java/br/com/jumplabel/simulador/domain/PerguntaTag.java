package br.com.jumplabel.simulador.domain;


import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * A PerguntaTag.
 */
@Entity
@Table(name = "TB_PERGUNTA_TAG")
@AssociationOverrides({
	@AssociationOverride(name = "pk.pergunta",
		joinColumns = @JoinColumn(name = "CD_PERGUNTA")),
	@AssociationOverride(name = "pk.tag",
		joinColumns = @JoinColumn(name = "CD_TAG")) })
public class PerguntaTag implements Serializable {

    private static final long serialVersionUID = 1L;

    private PerguntaTagId pk = new PerguntaTagId();
    
    @ManyToOne
    @JoinColumn(name="CD_PERGUNTA")
    private Pergunta pergunta;

    @ManyToOne
    @JoinColumn(name="CD_TAG")
    private Tag tag;

	@EmbeddedId
	public PerguntaTagId getPk() {
		return pk;
	}

	public void setPk(PerguntaTagId pk) {
		this.pk = pk;
	}
    
	@Transient
	public Pergunta gePergunta() {
		return getPk().getPergunta();
	}

	public void setPergunta(Pergunta pergunta) {
		getPk().setPergunta(pergunta);
	}

	@Transient
	public Tag getTag() {
		return getPk().getTag();
	}

	public void setTag(Tag tag) {
		getPk().setTag(tag);
	}
	
	
    public PerguntaTag() { }

    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		PerguntaTag that = (PerguntaTag) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;
		return true;    	
    }

    @Override
    public int hashCode() {
    	return (getPk() != null ? getPk().hashCode() : 0);
    }

    @Override
    public String toString() {
        return "PerguntaTag{" +
            "pk=" + pk +
            '}';
    }
}
