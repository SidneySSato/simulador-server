package br.com.jumplabel.simulador.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.ArvoreDecisao;
import br.com.jumplabel.simulador.domain.ArvoreFamilia;
import br.com.jumplabel.simulador.service.dto.ArvoreFamiliaDTO;

/**
 * Mapper for the entity ArvoreFamilia and its DTO ArvoreFamiliaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ArvoreFamiliaMapper {

    @Mapping(source = "arvoreDecisao.id", target = "arvoreDecisaoId")
    @Mapping(source = "cntdDomi.id", target = "cntdDomiId")
    @Mapping(source = "cntdDomi.cdCntdDomiLgdo", target = "cntdDomiCdCntdDomiLgdo")
    @Mapping(source = "cntdDomi.dsCntdDomiLgdo", target = "cntdDomiDsCntdDomiLgdo")
    ArvoreFamiliaDTO arvoreFamiliaToArvoreFamiliaDTO(ArvoreFamilia arvoreFamilia);

    List<ArvoreFamiliaDTO> arvoreFamiliasToArvoreFamiliaDTOs(List<ArvoreFamilia> arvoreFamilias);

    @Mapping(source = "arvoreDecisaoId", target = "arvoreDecisao")
    ArvoreFamilia arvoreFamiliaDTOToArvoreFamilia(ArvoreFamiliaDTO arvoreFamiliaDTO);

    List<ArvoreFamilia> arvoreFamiliaDTOsToArvoreFamilias(List<ArvoreFamiliaDTO> arvoreFamiliaDTOs);

    default ArvoreDecisao arvoreDecisaoFromId(Long id) {
        if (id == null) {
            return null;
        }
        ArvoreDecisao arvoreDecisao = new ArvoreDecisao();
        arvoreDecisao.setId(id);
        return arvoreDecisao;
    }
}
