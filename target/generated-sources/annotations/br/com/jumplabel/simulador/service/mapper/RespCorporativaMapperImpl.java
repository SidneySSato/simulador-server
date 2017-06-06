package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.domain.Domi;
import br.com.jumplabel.simulador.domain.RespCorporativa;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.service.dto.RespCorporativaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-16T11:45:00-0300",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class RespCorporativaMapperImpl implements RespCorporativaMapper {

    @Override
    public RespCorporativaDTO respCorporativaToRespCorporativaDTO(RespCorporativa respCorporativa) {
        if ( respCorporativa == null ) {
            return null;
        }

        RespCorporativaDTO respCorporativaDTO = new RespCorporativaDTO();

        respCorporativaDTO.setDomiId( respCorporativaCntdDomiDomiId( respCorporativa ) );
        respCorporativaDTO.setCntdDomiDsCntdDomiLgdo( respCorporativaCntdDomiDsCntdDomiLgdo( respCorporativa ) );
        respCorporativaDTO.setCntdDomiId( respCorporativaCntdDomiId( respCorporativa ) );
        respCorporativaDTO.setRespostaId( respCorporativaRespostaId( respCorporativa ) );
        respCorporativaDTO.setCntdDomiCdCntdDomiLgdo( respCorporativaCntdDomiCdCntdDomiLgdo( respCorporativa ) );

        return respCorporativaDTO;
    }

    @Override
    public List<RespCorporativaDTO> respCorporativasToRespCorporativaDTOs(List<RespCorporativa> respCorporativas) {
        if ( respCorporativas == null ) {
            return null;
        }

        List<RespCorporativaDTO> list = new ArrayList<RespCorporativaDTO>();
        for ( RespCorporativa respCorporativa : respCorporativas ) {
            list.add( respCorporativaToRespCorporativaDTO( respCorporativa ) );
        }

        return list;
    }

    @Override
    public RespCorporativa respCorporativaDTOToRespCorporativa(RespCorporativaDTO respCorporativaDTO) {
        if ( respCorporativaDTO == null ) {
            return null;
        }

        RespCorporativa respCorporativa = new RespCorporativa();

        respCorporativa.setResposta( respostaFromId( respCorporativaDTO.getRespostaId() ) );

        return respCorporativa;
    }

    @Override
    public List<RespCorporativa> respCorporativaDTOsToRespCorporativas(List<RespCorporativaDTO> respCorporativaDTOs) {
        if ( respCorporativaDTOs == null ) {
            return null;
        }

        List<RespCorporativa> list = new ArrayList<RespCorporativa>();
        for ( RespCorporativaDTO respCorporativaDTO : respCorporativaDTOs ) {
            list.add( respCorporativaDTOToRespCorporativa( respCorporativaDTO ) );
        }

        return list;
    }

    private Long respCorporativaCntdDomiDomiId(RespCorporativa respCorporativa) {

        if ( respCorporativa == null ) {
            return null;
        }
        CntdDomi cntdDomi = respCorporativa.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        Domi domi = cntdDomi.getDomi();
        if ( domi == null ) {
            return null;
        }
        Long id = domi.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String respCorporativaCntdDomiDsCntdDomiLgdo(RespCorporativa respCorporativa) {

        if ( respCorporativa == null ) {
            return null;
        }
        CntdDomi cntdDomi = respCorporativa.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        String dsCntdDomiLgdo = cntdDomi.getDsCntdDomiLgdo();
        if ( dsCntdDomiLgdo == null ) {
            return null;
        }
        return dsCntdDomiLgdo;
    }

    private Long respCorporativaCntdDomiId(RespCorporativa respCorporativa) {

        if ( respCorporativa == null ) {
            return null;
        }
        CntdDomi cntdDomi = respCorporativa.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        Long id = cntdDomi.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long respCorporativaRespostaId(RespCorporativa respCorporativa) {

        if ( respCorporativa == null ) {
            return null;
        }
        Resposta resposta = respCorporativa.getResposta();
        if ( resposta == null ) {
            return null;
        }
        Long id = resposta.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String respCorporativaCntdDomiCdCntdDomiLgdo(RespCorporativa respCorporativa) {

        if ( respCorporativa == null ) {
            return null;
        }
        CntdDomi cntdDomi = respCorporativa.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        String cdCntdDomiLgdo = cntdDomi.getCdCntdDomiLgdo();
        if ( cdCntdDomiLgdo == null ) {
            return null;
        }
        return cdCntdDomiLgdo;
    }
}
