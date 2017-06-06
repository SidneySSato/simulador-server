package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.ProdSegdDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProdSegd and its DTO ProdSegdDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdSegdMapper {

    ProdSegdDTO prodSegdToProdSegdDTO(ProdSegd prodSegd);

    List<ProdSegdDTO> prodSegdsToProdSegdDTOs(List<ProdSegd> prodSegds);

    @Mapping(target = "prodSegdPlans", ignore = true)
    @Mapping(target = "prodSegdPergs", ignore = true)
    @Mapping(target = "cnalProdSegds", ignore = true)
    ProdSegd prodSegdDTOToProdSegd(ProdSegdDTO prodSegdDTO);

    List<ProdSegd> prodSegdDTOsToProdSegds(List<ProdSegdDTO> prodSegdDTOs);

}
