package br.com.jumplabel.simulador.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * A DTO for the RangeResposta entity.
 */
public class RangeRespostaDTO implements Serializable {

	private static final long serialVersionUID = 8411599406959463728L;

    @JsonInclude(Include.NON_NULL)
    private Long rangeInicio;

    @JsonInclude(Include.NON_NULL)
    private Long rangeFinal;

    @JsonInclude(Include.NON_NULL)
    private Long respostaId;
    

	@JsonInclude(Include.NON_NULL)
	private ResultDTO result;	
    
    public Long getRangeInicio() {
        return rangeInicio;
    }

    public void setRangeInicio(Long rangeInicio) {
        this.rangeInicio = rangeInicio;
    }
    public Long getRangeFinal() {
        return rangeFinal;
    }

    public void setRangeFinal(Long rangeFinal) {
        this.rangeFinal = rangeFinal;
    }

    public Long getRespostaId() {
        return respostaId;
    }

    public void setRespostaId(Long respostaId) {
        this.respostaId = respostaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RangeRespostaDTO rangeRespostaDTO = (RangeRespostaDTO) o;

        if ( ! Objects.equals(respostaId, rangeRespostaDTO.respostaId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(respostaId);
    }

    @Override
    public String toString() {
        return "RangeRespostaDTO{" +
        	"respostaId='" + respostaId + "'" +
            ", rangeInicio='" + rangeInicio + "'" +
            ", rangeFinal='" + rangeFinal + "'" +
            '}';
    }

	public ResultDTO getResult() {
		return result;
	}

	public void setResult(ResultDTO result) {
		this.result = result;
	}
	
	/**
	 * Valida se tem overlap entre this e that
	 * @param that
	 * @return true caso tenha overlap entre this e that e false caso contrario
	 */
    public boolean overlaps(RangeRespostaDTO that) {

    	// o rangeFinal pode ser null, nesse caso nao precisa validar o that.rangeFinal
    	if (that.rangeFinal != null) {
    		return this.rangeInicio <= that.rangeFinal && that.rangeInicio <= this.rangeFinal;	
		} else {
			return that.rangeInicio <= this.rangeFinal;
		}
    }
    
    
    /**
     * Valida se na lista tem algum objeto com overlap de range 
     * @param ranges
     * @return retorna true caso NAO exista overlap e false caso exista overlap
     */
    public static boolean isNonoverlapping(List<RangeRespostaDTO> ranges) {
        for (int i = 0; i < ranges.size(); i++)
            for (int j = i + 1; j < ranges.size(); j++)
                if (ranges.get(i).overlaps(ranges.get(j)))
                    return false;
        return true;
    }
}