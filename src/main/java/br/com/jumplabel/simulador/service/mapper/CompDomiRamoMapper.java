package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.CompDomiRamoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity CompDomiRamo and its DTO CompDomiRamoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompDomiRamoMapper {

    CompDomiRamoDTO compDomiRamoToCompDomiRamoDTO(CompDomiRamo compDomiRamo);

    List<CompDomiRamoDTO> compDomiRamosToCompDomiRamoDTOs(List<CompDomiRamo> compDomiRamos);

    @Mapping(target = "cntdDomi", ignore = true)
    CompDomiRamo compDomiRamoDTOToCompDomiRamo(CompDomiRamoDTO compDomiRamoDTO);

    List<CompDomiRamo> compDomiRamoDTOsToCompDomiRamos(List<CompDomiRamoDTO> compDomiRamoDTOs);
}
