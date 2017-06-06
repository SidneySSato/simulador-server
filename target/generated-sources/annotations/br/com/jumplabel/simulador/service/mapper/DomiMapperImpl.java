package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.domain.Domi;
import br.com.jumplabel.simulador.service.dto.CntdDomiDTO;
import br.com.jumplabel.simulador.service.dto.DomiDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-16T11:45:00-0300",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class DomiMapperImpl implements DomiMapper {

    @Autowired
    private CntdDomiMapper cntdDomiMapper;

    @Override
    public DomiDTO domiToDomiDTO(Domi domi) {
        if ( domi == null ) {
            return null;
        }

        DomiDTO domiDTO = new DomiDTO();

        List<CntdDomiDTO> list = cntdDomiSetToCntdDomiDTOList( domi.getCntdDomis() );
        if ( list != null ) {
            domiDTO.setCntdDomis( list );
        }
        domiDTO.setId( domi.getId() );
        domiDTO.setNmDomi( domi.getNmDomi() );

        return domiDTO;
    }

    @Override
    public List<DomiDTO> domisToDomiDTOs(List<Domi> domis) {
        if ( domis == null ) {
            return null;
        }

        List<DomiDTO> list = new ArrayList<DomiDTO>();
        for ( Domi domi : domis ) {
            list.add( domiToDomiDTO( domi ) );
        }

        return list;
    }

    @Override
    public Domi domiDTOToDomi(DomiDTO domiDTO) {
        if ( domiDTO == null ) {
            return null;
        }

        Domi domi = new Domi();

        domi.setId( domiDTO.getId() );
        domi.setNmDomi( domiDTO.getNmDomi() );

        return domi;
    }

    @Override
    public List<Domi> domiDTOsToDomis(List<DomiDTO> domiDTOs) {
        if ( domiDTOs == null ) {
            return null;
        }

        List<Domi> list = new ArrayList<Domi>();
        for ( DomiDTO domiDTO : domiDTOs ) {
            list.add( domiDTOToDomi( domiDTO ) );
        }

        return list;
    }

    protected List<CntdDomiDTO> cntdDomiSetToCntdDomiDTOList(Set<CntdDomi> set) {
        if ( set == null ) {
            return null;
        }

        List<CntdDomiDTO> list = new ArrayList<CntdDomiDTO>();
        for ( CntdDomi cntdDomi : set ) {
            list.add( cntdDomiMapper.cntdDomiToCntdDomiDTO( cntdDomi ) );
        }

        return list;
    }
}
