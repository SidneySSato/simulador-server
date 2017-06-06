package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.DomiDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Domi and its DTO DomiDTO.
 */
@Mapper(componentModel = "spring", uses = {CntdDomiMapper.class})
public interface DomiMapper {

    DomiDTO domiToDomiDTO(Domi domi);

    List<DomiDTO> domisToDomiDTOs(List<Domi> domis);

    @Mapping(target = "cntdDomis", ignore = true)
    Domi domiDTOToDomi(DomiDTO domiDTO);

    List<Domi> domiDTOsToDomis(List<DomiDTO> domiDTOs);
}
