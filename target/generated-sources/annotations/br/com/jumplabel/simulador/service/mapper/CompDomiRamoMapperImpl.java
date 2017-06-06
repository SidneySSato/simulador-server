package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.CompDomiRamo;
import br.com.jumplabel.simulador.service.dto.CompDomiRamoDTO;
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
public class CompDomiRamoMapperImpl implements CompDomiRamoMapper {

    @Override
    public CompDomiRamoDTO compDomiRamoToCompDomiRamoDTO(CompDomiRamo compDomiRamo) {
        if ( compDomiRamo == null ) {
            return null;
        }

        CompDomiRamoDTO compDomiRamoDTO = new CompDomiRamoDTO();

        compDomiRamoDTO.setId( compDomiRamo.getId() );
        compDomiRamoDTO.setInRamoGene( compDomiRamo.getInRamoGene() );
        compDomiRamoDTO.setCdGrupSusep( compDomiRamo.getCdGrupSusep() );

        return compDomiRamoDTO;
    }

    @Override
    public List<CompDomiRamoDTO> compDomiRamosToCompDomiRamoDTOs(List<CompDomiRamo> compDomiRamos) {
        if ( compDomiRamos == null ) {
            return null;
        }

        List<CompDomiRamoDTO> list = new ArrayList<CompDomiRamoDTO>();
        for ( CompDomiRamo compDomiRamo : compDomiRamos ) {
            list.add( compDomiRamoToCompDomiRamoDTO( compDomiRamo ) );
        }

        return list;
    }

    @Override
    public CompDomiRamo compDomiRamoDTOToCompDomiRamo(CompDomiRamoDTO compDomiRamoDTO) {
        if ( compDomiRamoDTO == null ) {
            return null;
        }

        CompDomiRamo compDomiRamo = new CompDomiRamo();

        compDomiRamo.setId( compDomiRamoDTO.getId() );
        compDomiRamo.setInRamoGene( compDomiRamoDTO.getInRamoGene() );
        compDomiRamo.setCdGrupSusep( compDomiRamoDTO.getCdGrupSusep() );

        return compDomiRamo;
    }

    @Override
    public List<CompDomiRamo> compDomiRamoDTOsToCompDomiRamos(List<CompDomiRamoDTO> compDomiRamoDTOs) {
        if ( compDomiRamoDTOs == null ) {
            return null;
        }

        List<CompDomiRamo> list = new ArrayList<CompDomiRamo>();
        for ( CompDomiRamoDTO compDomiRamoDTO : compDomiRamoDTOs ) {
            list.add( compDomiRamoDTOToCompDomiRamo( compDomiRamoDTO ) );
        }

        return list;
    }
}
