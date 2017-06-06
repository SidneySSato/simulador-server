package br.com.jumplabel.simulador.domain;


import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


/**
 * A RespCorporativa.
 */
@Entity
@Table(name = "TB_RESP_CORPORATIVA")
public class RespCorporativa implements Serializable {
	private static final long serialVersionUID = 1L;

	public RespCorporativa() {}
	
	public RespCorporativa(RespCorporativaId pk) {
		this.pk = pk;
	}
	
	@EmbeddedId
    private RespCorporativaId pk;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("respostaId") 
    @JoinColumn(name = "CD_RESPOSTA")
    private Resposta resposta;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("cntdDomiId") 
    @JoinColumn(name="CD_TC_DOMI")
    private CntdDomi cntdDomi;
    
	
	public RespCorporativaId getPk() {
		return pk;
	}

	public void setPk(RespCorporativaId pk) {
		this.pk = pk;
	}
    
    @Override
    public boolean equals(Object o) {
    	
    	if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		RespCorporativa that = (RespCorporativa) o;

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
        return "RespCorporativa{" +
            "pk=" + pk +
            '}';
    }

	public Resposta getResposta() {
		return resposta;
	}

	public void setResposta(Resposta resposta) {
		this.resposta = resposta;
	}

	public CntdDomi getCntdDomi() {
		return cntdDomi;
	}

	public void setCntdDomi(CntdDomi cntdDomi) {
		this.cntdDomi = cntdDomi;
	}
    

}
