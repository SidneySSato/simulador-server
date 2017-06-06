package br.com.jumplabel.simulador.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.RespCorporativa;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.service.dto.RespCorporativaDTO;

/**
 * Mapper for the entity RespCorporativa and its DTO RespCorporativaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RespCorporativaMapper {

    @Mapping(source = "resposta.id", target = "respostaId")
    @Mapping(source = "cntdDomi.id", target = "cntdDomiId")
    @Mapping(source = "cntdDomi.cdCntdDomiLgdo", target = "cntdDomiCdCntdDomiLgdo")
    @Mapping(source = "cntdDomi.dsCntdDomiLgdo", target = "cntdDomiDsCntdDomiLgdo")
    @Mapping(source = "cntdDomi.domi.id", target = "domiId")
    RespCorporativaDTO respCorporativaToRespCorporativaDTO(RespCorporativa respCorporativa);

    List<RespCorporativaDTO> respCorporativasToRespCorporativaDTOs(List<RespCorporativa> respCorporativas);

    @Mapping(source = "respostaId", target = "resposta")
    RespCorporativa respCorporativaDTOToRespCorporativa(RespCorporativaDTO respCorporativaDTO);

    List<RespCorporativa> respCorporativaDTOsToRespCorporativas(List<RespCorporativaDTO> respCorporativaDTOs);
    
    default Resposta respostaFromId(Long id) {
        if (id == null) {
            return null;
        }
        Resposta resposta = new Resposta();
        resposta.setId(id);
        return resposta;
    }
}
