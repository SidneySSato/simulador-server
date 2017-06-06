package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProdSegdPlan and its DTO ProdSegdPlanDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdSegdPlanMapper {

    ProdSegdPlanDTO prodSegdPlanToProdSegdPlanDTO(ProdSegdPlan prodSegdPlan);

    List<ProdSegdPlanDTO> prodSegdPlansToProdSegdPlanDTOs(List<ProdSegdPlan> prodSegdPlans);

    @Mapping(target = "prodSegdPlanIs", ignore = true)
    @Mapping(target = "prodSegdPlanSegms", ignore = true)
    ProdSegdPlan prodSegdPlanDTOToProdSegdPlan(ProdSegdPlanDTO prodSegdPlanDTO);

    List<ProdSegdPlan> prodSegdPlanDTOsToProdSegdPlans(List<ProdSegdPlanDTO> prodSegdPlanDTOs);
}
