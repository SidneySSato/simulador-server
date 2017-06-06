package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.domain.Domi;
import br.com.jumplabel.simulador.service.dto.CntdDomiDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-16T11:45:01-0300",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class CntdDomiMapperImpl implements CntdDomiMapper {

    @Override
    public CntdDomiDTO cntdDomiToCntdDomiDTO(CntdDomi cntdDomi) {
        if ( cntdDomi == null ) {
            return null;
        }

        CntdDomiDTO cntdDomiDTO = new CntdDomiDTO();

        cntdDomiDTO.setDomiNmDomi( cntdDomiDomiNmDomi( cntdDomi ) );
        cntdDomiDTO.setDomiId( cntdDomiDomiId( cntdDomi ) );
        cntdDomiDTO.setId( cntdDomi.getId() );
        cntdDomiDTO.setCdCntdDomiLgdo( cntdDomi.getCdCntdDomiLgdo() );
        cntdDomiDTO.setDsCntdDomiLgdo( cntdDomi.getDsCntdDomiLgdo() );

        return cntdDomiDTO;
    }

    @Override
    public List<CntdDomiDTO> cntdDomisToCntdDomiDTOs(List<CntdDomi> cntdDomis) {
        if ( cntdDomis == null ) {
            return null;
        }

        List<CntdDomiDTO> list = new ArrayList<CntdDomiDTO>();
        for ( CntdDomi cntdDomi : cntdDomis ) {
            list.add( cntdDomiToCntdDomiDTO( cntdDomi ) );
        }

        return list;
    }

    @Override
    public CntdDomi cntdDomiDTOToCntdDomi(CntdDomiDTO cntdDomiDTO) {
        if ( cntdDomiDTO == null ) {
            return null;
        }

        CntdDomi cntdDomi = new CntdDomi();

        cntdDomi.setDomi( domiFromId( cntdDomiDTO.getDomiId() ) );
        cntdDomi.setId( cntdDomiDTO.getId() );
        cntdDomi.setCdCntdDomiLgdo( cntdDomiDTO.getCdCntdDomiLgdo() );
        cntdDomi.setDsCntdDomiLgdo( cntdDomiDTO.getDsCntdDomiLgdo() );

        return cntdDomi;
    }

    @Override
    public List<CntdDomi> cntdDomiDTOsToCntdDomis(List<CntdDomiDTO> cntdDomiDTOs) {
        if ( cntdDomiDTOs == null ) {
            return null;
        }

        List<CntdDomi> list = new ArrayList<CntdDomi>();
        for ( CntdDomiDTO cntdDomiDTO : cntdDomiDTOs ) {
            list.add( cntdDomiDTOToCntdDomi( cntdDomiDTO ) );
        }

        return list;
    }

    private String cntdDomiDomiNmDomi(CntdDomi cntdDomi) {

        if ( cntdDomi == null ) {
            return null;
        }
        Domi domi = cntdDomi.getDomi();
        if ( domi == null ) {
            return null;
        }
        String nmDomi = domi.getNmDomi();
        if ( nmDomi == null ) {
            return null;
        }
        return nmDomi;
    }

    private Long cntdDomiDomiId(CntdDomi cntdDomi) {

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
}
