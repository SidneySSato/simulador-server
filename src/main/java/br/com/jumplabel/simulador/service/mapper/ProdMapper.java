package br.com.jumplabel.simulador.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.Prod;
import br.com.jumplabel.simulador.service.dto.ProdDTO;

/**
 * Mapper for the entity Prod and its DTO ProdDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdMapper {

	@Mapping(source = "pk.prodId", target = "prodId")
	@Mapping(source = "pk.subpId", target = "subpId")
    @Mapping(source = "cntdDomi.cdCntdDomiLgdo", target = "cntdDomiCdCntdDomiLgdo")
    @Mapping(source = "cntdDomi.dsCntdDomiLgdo", target = "cntdDomiDsCntdDomiLgdo")
    ProdDTO prodToProdDTO(Prod prod);

    List<ProdDTO> prodsToProdDTOs(List<Prod> prods);

    @Mapping(target = "prodSegds", ignore = true)
    Prod prodDTOToProd(ProdDTO prodDTO);

    List<Prod> prodDTOsToProds(List<ProdDTO> prodDTOs);
}
