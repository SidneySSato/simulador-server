package br.com.jumplabel.simulador.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PerguntaTagId implements java.io.Serializable {

	private static final long serialVersionUID = -6364296915582765477L;
	
	public PerguntaTagId() {}
	
	public PerguntaTagId(Long perguntaId, Long tagId) {
		Pergunta pergunta = new Pergunta();
		pergunta.setId(perguntaId);
		this.pergunta = pergunta;
		
		Tag tag = new Tag();
		tag.setId(tagId);
		this.tag = tag;
	}
	
	
	private Pergunta pergunta;
    private Tag tag;

    @JoinColumn(name="CD_PERGUNTA")
	@ManyToOne
	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	@JoinColumn(name="CD_TAG")
	@ManyToOne
	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerguntaTagId that = (PerguntaTagId) o;

        if (pergunta != null ? !pergunta.equals(that.pergunta) : that.pergunta != null) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null)
            return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result;
        result = (pergunta != null ? pergunta.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PerguntaTagId{" +
            "perguntaId=" + pergunta.getId() +
            "tagId=" + tag.getId() +
            '}';
    }
}
