package br.com.jumplabel.simulador.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.ArvoreCanal;
import br.com.jumplabel.simulador.domain.ArvoreDecisao;
import br.com.jumplabel.simulador.service.dto.ArvoreCanalDTO;

/**
 * Mapper for the entity ArvoreCanal and its DTO ArvoreCanalDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ArvoreCanalMapper {

    @Mapping(source = "arvoreDecisao.id", target = "arvoreDecisaoId")
    @Mapping(source = "cntdDomi.id", target = "cntdDomiId")
    @Mapping(source = "cntdDomi.cdCntdDomiLgdo", target = "cntdDomiCdCntdDomiLgdo")
    @Mapping(source = "cntdDomi.dsCntdDomiLgdo", target = "cntdDomiDsCntdDomiLgdo")
    ArvoreCanalDTO arvoreCanalToArvoreCanalDTO(ArvoreCanal arvoreCanal);

    List<ArvoreCanalDTO> arvoreCanaisToArvoreCanalDTOs(List<ArvoreCanal> arvoreCanais);

    @Mapping(source = "arvoreDecisaoId", target = "arvoreDecisao")
    ArvoreCanal arvoreCanalDTOToArvoreCanal(ArvoreCanalDTO arvoreCanalDTO);

    List<ArvoreCanal> arvoreCanalDTOsToArvoreCanais(List<ArvoreCanalDTO> arvoreCanalDTOs);

    default ArvoreDecisao arvoreDecisaoFromId(Long id) {
        if (id == null) {
            return null;
        }
        ArvoreDecisao arvoreDecisao = new ArvoreDecisao();
        arvoreDecisao.setId(id);
        return arvoreDecisao;
    }
}
