package br.com.jumplabel.simulador.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.service.dto.RespostaDTO;

/**
 * Mapper for the entity Resposta and its DTO RespostaDTO.
 */
@Mapper(componentModel = "spring", uses = {RespCorporativaMapper.class, RangeRespostaMapper.class, RespostaIsMapper.class })
public interface RespostaMapper {

    @Mapping(source = "pergunta.id", target = "perguntaId")
    @Mapping(source = "pergunta.dsPergunta", target = "perguntaDsPergunta")
    @Mapping(source = "pergunta.tipoResposta.id", target = "perguntaTipoRespostaId")
    @Mapping(source = "pergunta.tipoResposta.dsTipoResposta", target = "perguntaTipoRespostaDsTipoResposta")
    @Mapping(source = "pergunta.domi.id", target = "perguntaDomiId")
    @Mapping(source = "pergunta.domi.nmDomi", target = "perguntaDomiNmDomi")
    @Mapping(source = "pergunta.inEditavel", target = "perguntaInEditavel")
    RespostaDTO respostaToRespostaDTO(Resposta resposta);

    List<RespostaDTO> respostasToRespostaDTOs(List<Resposta> respostas);

    Resposta respostaDTOToResposta(RespostaDTO respostaDTO);

    List<Resposta> respostaDTOsToRespostas(List<RespostaDTO> respostaDTOs);
}