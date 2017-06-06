package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.RespostaIsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity RespostaIs and its DTO RespostaIsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RespostaIsMapper {

    @Mapping(source = "pergunta1.id", target = "pergunta1Id")
    @Mapping(source = "pergunta2.id", target = "pergunta2Id")
    RespostaIsDTO respostaIsToRespostaIsDTO(RespostaIs respostaIs);

    List<RespostaIsDTO> respostaIsToRespostaIsDTOs(List<RespostaIs> respostaIs);

    @Mapping(target = "resposta", ignore = true)
    @Mapping(source = "pergunta1Id", target = "pergunta1")
    @Mapping(source = "pergunta2Id", target = "pergunta2")
    RespostaIs respostaIsDTOToRespostaIs(RespostaIsDTO respostaIsDTO);

    List<RespostaIs> respostaIsDTOsToRespostaIs(List<RespostaIsDTO> respostaIsDTOs);

    default Pergunta perguntaFromId(Long id) {
        if (id == null) {
            return null;
        }
        Pergunta pergunta = new Pergunta();
        pergunta.setId(id);
        return pergunta;
    }
}
