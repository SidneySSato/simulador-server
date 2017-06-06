package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.CompDomiSegd;
import br.com.jumplabel.simulador.service.dto.CompDomiSegdDTO;
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
public class CompDomiSegdMapperImpl implements CompDomiSegdMapper {

    @Override
    public CompDomiSegdDTO compDomiSegdToCompDomiSegdDTO(CompDomiSegd compDomiSegd) {
        if ( compDomiSegd == null ) {
            return null;
        }

        CompDomiSegdDTO compDomiSegdDTO = new CompDomiSegdDTO();

        compDomiSegdDTO.setId( compDomiSegd.getId() );
        compDomiSegdDTO.setNrCnpjSegd( compDomiSegd.getNrCnpjSegd() );
        compDomiSegdDTO.setCdSusepSegd( compDomiSegd.getCdSusepSegd() );

        return compDomiSegdDTO;
    }

    @Override
    public List<CompDomiSegdDTO> compDomiSegdsToCompDomiSegdDTOs(List<CompDomiSegd> compDomiSegds) {
        if ( compDomiSegds == null ) {
            return null;
        }

        List<CompDomiSegdDTO> list = new ArrayList<CompDomiSegdDTO>();
        for ( CompDomiSegd compDomiSegd : compDomiSegds ) {
            list.add( compDomiSegdToCompDomiSegdDTO( compDomiSegd ) );
        }

        return list;
    }

    @Override
    public CompDomiSegd compDomiSegdDTOToCompDomiSegd(CompDomiSegdDTO compDomiSegdDTO) {
        if ( compDomiSegdDTO == null ) {
            return null;
        }

        CompDomiSegd compDomiSegd = new CompDomiSegd();

        compDomiSegd.setId( compDomiSegdDTO.getId() );
        compDomiSegd.setNrCnpjSegd( compDomiSegdDTO.getNrCnpjSegd() );
        compDomiSegd.setCdSusepSegd( compDomiSegdDTO.getCdSusepSegd() );

        return compDomiSegd;
    }

    @Override
    public List<CompDomiSegd> compDomiSegdDTOsToCompDomiSegds(List<CompDomiSegdDTO> compDomiSegdDTOs) {
        if ( compDomiSegdDTOs == null ) {
            return null;
        }

        List<CompDomiSegd> list = new ArrayList<CompDomiSegd>();
        for ( CompDomiSegdDTO compDomiSegdDTO : compDomiSegdDTOs ) {
            list.add( compDomiSegdDTOToCompDomiSegd( compDomiSegdDTO ) );
        }

        return list;
    }
}
