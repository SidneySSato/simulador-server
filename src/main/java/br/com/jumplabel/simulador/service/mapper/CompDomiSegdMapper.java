package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.CompDomiSegdDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity CompDomiSegd and its DTO CompDomiSegdDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompDomiSegdMapper {

    CompDomiSegdDTO compDomiSegdToCompDomiSegdDTO(CompDomiSegd compDomiSegd);

    List<CompDomiSegdDTO> compDomiSegdsToCompDomiSegdDTOs(List<CompDomiSegd> compDomiSegds);

    @Mapping(target = "cntdDomi", ignore = true)
    CompDomiSegd compDomiSegdDTOToCompDomiSegd(CompDomiSegdDTO compDomiSegdDTO);

    List<CompDomiSegd> compDomiSegdDTOsToCompDomiSegds(List<CompDomiSegdDTO> compDomiSegdDTOs);
}
