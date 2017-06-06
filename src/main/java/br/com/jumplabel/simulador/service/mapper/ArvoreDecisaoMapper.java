package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.ArvoreDecisaoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity ArvoreDecisao and its DTO ArvoreDecisaoDTO.
 */
@Mapper(componentModel = "spring", uses = {ArvoreFamiliaMapper.class, ArvoreCanalMapper.class})
public interface ArvoreDecisaoMapper {

    ArvoreDecisaoDTO arvoreDecisaoToArvoreDecisaoDTO(ArvoreDecisao arvoreDecisao);

    List<ArvoreDecisaoDTO> arvoreDecisaosToArvoreDecisaoDTOs(List<ArvoreDecisao> arvoreDecisaos);

    @Mapping(target = "arvoreFamilias", ignore = true)
    @Mapping(target = "arvoreCanais", ignore = true)
    ArvoreDecisao arvoreDecisaoDTOToArvoreDecisao(ArvoreDecisaoDTO arvoreDecisaoDTO);

    List<ArvoreDecisao> arvoreDecisaoDTOsToArvoreDecisaos(List<ArvoreDecisaoDTO> arvoreDecisaoDTOs);
}
