package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.NoArvoreCopiaDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity NoArvoreCopia and its DTO NoArvoreCopiaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NoArvoreCopiaMapper {

    NoArvoreCopiaDTO noArvoreCopiaToNoArvoreCopiaDTO(NoArvoreCopia noArvoreCopia);

    List<NoArvoreCopiaDTO> noArvoreCopiasToNoArvoreCopiaDTOs(List<NoArvoreCopia> noArvoreCopias);

    @Mapping(target = "noArvore", ignore = true)
    NoArvoreCopia noArvoreCopiaDTOToNoArvoreCopia(NoArvoreCopiaDTO noArvoreCopiaDTO);

    List<NoArvoreCopia> noArvoreCopiaDTOsToNoArvoreCopias(List<NoArvoreCopiaDTO> noArvoreCopiaDTOs);
}
