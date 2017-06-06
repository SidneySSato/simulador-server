package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.ProdSegdPergDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ProdSegdPerg and its DTO ProdSegdPergDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProdSegdPergMapper {

    ProdSegdPergDTO prodSegdPergToProdSegdPergDTO(ProdSegdPerg prodSegdPerg);

    List<ProdSegdPergDTO> prodSegdPergsToProdSegdPergDTOs(List<ProdSegdPerg> prodSegdPergs);

    ProdSegdPerg prodSegdPergDTOToProdSegdPerg(ProdSegdPergDTO prodSegdPergDTO);

    List<ProdSegdPerg> prodSegdPergDTOsToProdSegdPergs(List<ProdSegdPergDTO> prodSegdPergDTOs);
}
