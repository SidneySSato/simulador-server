package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanSegmDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProdSegdPlanSegm and its DTO ProdSegdPlanSegmDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdSegdPlanSegmMapper {

	
	@Mapping(source = "pk.segmDomiId", target = "segmDomiId")
	@Mapping(source = "pk.prodSegdPlanId.planDomiId", target = "planDomiId")
	@Mapping(source = "pk.prodSegdPlanId.prodSegdId.segdDomiId", target = "segdDomiId")
	@Mapping(source = "pk.prodSegdPlanId.prodSegdId.prodId.prodId", target = "prodId")
	@Mapping(source = "pk.prodSegdPlanId.prodSegdId.prodId.subpId", target = "subpId")
    ProdSegdPlanSegmDTO prodSegdPlanSegmToProdSegdPlanSegmDTO(ProdSegdPlanSegm prodSegdPlanSegm);

    List<ProdSegdPlanSegmDTO> prodSegdPlanSegmsToProdSegdPlanSegmDTOs(List<ProdSegdPlanSegm> prodSegdPlanSegms);

	@Mapping(target = "pk", ignore = true)
	@Mapping(target = "dtInicVigeSegm", ignore = true)
	@Mapping(target = "dtFimVigeSegm", ignore = true)
	@Mapping(target = "prodSegdPlan", ignore = true)
    ProdSegdPlanSegm prodSegdPlanSegmDTOToProdSegdPlanSegm(ProdSegdPlanSegmDTO prodSegdPlanSegmDTO);

    List<ProdSegdPlanSegm> prodSegdPlanSegmDTOsToProdSegdPlanSegms(List<ProdSegdPlanSegmDTO> prodSegdPlanSegmDTOs);
}
