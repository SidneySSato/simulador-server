package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.NoArvoreCopia;
import br.com.jumplabel.simulador.service.dto.NoArvoreCopiaDTO;
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
public class NoArvoreCopiaMapperImpl implements NoArvoreCopiaMapper {

    @Override
    public NoArvoreCopiaDTO noArvoreCopiaToNoArvoreCopiaDTO(NoArvoreCopia noArvoreCopia) {
        if ( noArvoreCopia == null ) {
            return null;
        }

        NoArvoreCopiaDTO noArvoreCopiaDTO = new NoArvoreCopiaDTO();

        noArvoreCopiaDTO.setId( noArvoreCopia.getId() );
        noArvoreCopiaDTO.setDsNoArvoreCopia( noArvoreCopia.getDsNoArvoreCopia() );
        noArvoreCopiaDTO.setDtCopia( noArvoreCopia.getDtCopia() );

        return noArvoreCopiaDTO;
    }

    @Override
    public List<NoArvoreCopiaDTO> noArvoreCopiasToNoArvoreCopiaDTOs(List<NoArvoreCopia> noArvoreCopias) {
        if ( noArvoreCopias == null ) {
            return null;
        }

        List<NoArvoreCopiaDTO> list = new ArrayList<NoArvoreCopiaDTO>();
        for ( NoArvoreCopia noArvoreCopia : noArvoreCopias ) {
            list.add( noArvoreCopiaToNoArvoreCopiaDTO( noArvoreCopia ) );
        }

        return list;
    }

    @Override
    public NoArvoreCopia noArvoreCopiaDTOToNoArvoreCopia(NoArvoreCopiaDTO noArvoreCopiaDTO) {
        if ( noArvoreCopiaDTO == null ) {
            return null;
        }

        NoArvoreCopia noArvoreCopia = new NoArvoreCopia();

        noArvoreCopia.setId( noArvoreCopiaDTO.getId() );
        noArvoreCopia.setDsNoArvoreCopia( noArvoreCopiaDTO.getDsNoArvoreCopia() );
        noArvoreCopia.setDtCopia( noArvoreCopiaDTO.getDtCopia() );

        return noArvoreCopia;
    }

    @Override
    public List<NoArvoreCopia> noArvoreCopiaDTOsToNoArvoreCopias(List<NoArvoreCopiaDTO> noArvoreCopiaDTOs) {
        if ( noArvoreCopiaDTOs == null ) {
            return null;
        }

        List<NoArvoreCopia> list = new ArrayList<NoArvoreCopia>();
        for ( NoArvoreCopiaDTO noArvoreCopiaDTO : noArvoreCopiaDTOs ) {
            list.add( noArvoreCopiaDTOToNoArvoreCopia( noArvoreCopiaDTO ) );
        }

        return list;
    }
}
