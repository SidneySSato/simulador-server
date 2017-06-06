package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.ProdSegd;
import br.com.jumplabel.simulador.service.dto.ProdSegdDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-19T14:48:55-0300",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class ProdSegdMapperImpl implements ProdSegdMapper {

    @Override
    public ProdSegdDTO prodSegdToProdSegdDTO(ProdSegd prodSegd) {
        if ( prodSegd == null ) {
            return null;
        }

        ProdSegdDTO prodSegdDTO = new ProdSegdDTO();

        prodSegdDTO.setDtFimVigeProdSegd( prodSegd.getDtFimVigeProdSegd() );
        prodSegdDTO.setDtInicVigeProdSegd( prodSegd.getDtInicVigeProdSegd() );

        return prodSegdDTO;
    }

    @Override
    public List<ProdSegdDTO> prodSegdsToProdSegdDTOs(List<ProdSegd> prodSegds) {
        if ( prodSegds == null ) {
            return null;
        }

        List<ProdSegdDTO> list = new ArrayList<ProdSegdDTO>();
        for ( ProdSegd prodSegd : prodSegds ) {
            list.add( prodSegdToProdSegdDTO( prodSegd ) );
        }

        return list;
    }

    @Override
    public ProdSegd prodSegdDTOToProdSegd(ProdSegdDTO prodSegdDTO) {
        if ( prodSegdDTO == null ) {
            return null;
        }

        ProdSegd prodSegd = new ProdSegd();

        prodSegd.setDtFimVigeProdSegd( prodSegdDTO.getDtFimVigeProdSegd() );
        prodSegd.setDtInicVigeProdSegd( prodSegdDTO.getDtInicVigeProdSegd() );

        return prodSegd;
    }

    @Override
    public List<ProdSegd> prodSegdDTOsToProdSegds(List<ProdSegdDTO> prodSegdDTOs) {
        if ( prodSegdDTOs == null ) {
            return null;
        }

        List<ProdSegd> list = new ArrayList<ProdSegd>();
        for ( ProdSegdDTO prodSegdDTO : prodSegdDTOs ) {
            list.add( prodSegdDTOToProdSegd( prodSegdDTO ) );
        }

        return list;
    }
}
