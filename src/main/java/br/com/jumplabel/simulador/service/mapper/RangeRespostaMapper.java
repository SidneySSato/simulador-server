package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.RangeRespostaDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity RangeResposta and its DTO RangeRespostaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RangeRespostaMapper {

    @Mapping(source = "resposta.id", target = "respostaId")
    RangeRespostaDTO rangeRespostaToRangeRespostaDTO(RangeResposta rangeResposta);

    List<RangeRespostaDTO> rangeRespostasToRangeRespostaDTOs(List<RangeResposta> rangeRespostas);

    @Mapping(source = "respostaId", target = "resposta")
    RangeResposta rangeRespostaDTOToRangeResposta(RangeRespostaDTO rangeRespostaDTO);

    List<RangeResposta> rangeRespostaDTOsToRangeRespostas(List<RangeRespostaDTO> rangeRespostaDTOs);

    default Resposta respostaFromId(Long id) {
        if (id == null) {
            return null;
        }
        Resposta resposta = new Resposta();
        resposta.setId(id);
        return resposta;
    }
}
