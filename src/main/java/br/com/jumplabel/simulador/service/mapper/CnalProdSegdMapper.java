package br.com.jumplabel.simulador.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.CnalProdSegd;
import br.com.jumplabel.simulador.service.dto.CnalProdSegdDTO;

/**
 * Mapper for the entity CnalProdSegd and its DTO CnalProdSegdDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CnalProdSegdMapper {

    @Mapping(source = "cntdDomi.cdCntdDomiLgdo", target = "cntdDomiCdCntdDomiLgdo")
    @Mapping(source = "cntdDomi.dsCntdDomiLgdo", target = "cntdDomiDsCntdDomiLgdo")
    @Mapping(source = "pk.cnalDomiId", target = "cnalDomiId")
    @Mapping(source = "pk.prodSegdId.segdDomiId", target = "segdDomiId")
    @Mapping(source = "pk.prodSegdId.prodId.prodId", target = "prodId")
    @Mapping(source = "pk.prodSegdId.prodId.subpId", target = "subpId")
	CnalProdSegdDTO cnalProdSegdToCnalProdSegdDTO(CnalProdSegd cnalProdSegd);

    List<CnalProdSegdDTO> cnalProdSegdsToCnalProdSegdDTOs(List<CnalProdSegd> cnalProdSegds);

    @Mapping(target = "cntdDomi", ignore = true)
    @Mapping(target = "pk", ignore = true)
    @Mapping(target = "prodSegd", ignore = true)
    CnalProdSegd cnalProdSegdDTOToCnalProdSegd(CnalProdSegdDTO cnalProdSegdDTO);

    List<CnalProdSegd> cnalProdSegdDTOsToCnalProdSegds(List<CnalProdSegdDTO> cnalProdSegdDTOs);


}
