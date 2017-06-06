package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanIsDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProdSegdPlanIs and its DTO ProdSegdPlanIsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdSegdPlanIsMapper {

    ProdSegdPlanIsDTO prodSegdPlanIsToProdSegdPlanIsDTO(ProdSegdPlanIs prodSegdPlanIs);

    List<ProdSegdPlanIsDTO> prodSegdPlanIsToProdSegdPlanIsDTOs(List<ProdSegdPlanIs> prodSegdPlanIs);

    ProdSegdPlanIs prodSegdPlanIsDTOToProdSegdPlanIs(ProdSegdPlanIsDTO prodSegdPlanIsDTO);

    List<ProdSegdPlanIs> prodSegdPlanIsDTOsToProdSegdPlanIs(List<ProdSegdPlanIsDTO> prodSegdPlanIsDTOs);

}
