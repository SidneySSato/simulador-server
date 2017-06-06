package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.TipoRespostaDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity TipoResposta and its DTO TipoRespostaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoRespostaMapper {

    TipoRespostaDTO tipoRespostaToTipoRespostaDTO(TipoResposta tipoResposta);

    List<TipoRespostaDTO> tipoRespostasToTipoRespostaDTOs(List<TipoResposta> tipoRespostas);

    @Mapping(target = "perguntas", ignore = true)
    TipoResposta tipoRespostaDTOToTipoResposta(TipoRespostaDTO tipoRespostaDTO);

    List<TipoResposta> tipoRespostaDTOsToTipoRespostas(List<TipoRespostaDTO> tipoRespostaDTOs);
}
