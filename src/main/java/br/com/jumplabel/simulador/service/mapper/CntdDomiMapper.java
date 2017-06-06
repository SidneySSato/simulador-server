package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.CntdDomiDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity CntdDomi and its DTO CntdDomiDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CntdDomiMapper {

    @Mapping(source = "domi.id", target = "domiId")
    @Mapping(source = "domi.nmDomi", target = "domiNmDomi")
//    @Mapping(source = "compDomiSegd.id", target = "compDomiSegdId")
//    @Mapping(source = "compDomiRamo.id", target = "compDomiRamoId")
    CntdDomiDTO cntdDomiToCntdDomiDTO(CntdDomi cntdDomi);

    List<CntdDomiDTO> cntdDomisToCntdDomiDTOs(List<CntdDomi> cntdDomis);

    @Mapping(source = "domiId", target = "domi")
//    @Mapping(source = "compDomiSegdId", target = "compDomiSegd")
//    @Mapping(source = "compDomiRamoId", target = "compDomiRamo")
    CntdDomi cntdDomiDTOToCntdDomi(CntdDomiDTO cntdDomiDTO);

    List<CntdDomi> cntdDomiDTOsToCntdDomis(List<CntdDomiDTO> cntdDomiDTOs);

    default Domi domiFromId(Long id) {
        if (id == null) {
            return null;
        }
        Domi domi = new Domi();
        domi.setId(id);
        return domi;
    }

    default CompDomiSegd compDomiSegdFromId(Long id) {
        if (id == null) {
            return null;
        }
        CompDomiSegd compDomiSegd = new CompDomiSegd();
        compDomiSegd.setId(id);
        return compDomiSegd;
    }

    default CompDomiRamo compDomiRamoFromId(Long id) {
        if (id == null) {
            return null;
        }
        CompDomiRamo compDomiRamo = new CompDomiRamo();
        compDomiRamo.setId(id);
        return compDomiRamo;
    }
}
