package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.ProdSegdPlanIs;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanIsDTO;
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
public class ProdSegdPlanIsMapperImpl implements ProdSegdPlanIsMapper {

    @Override
    public ProdSegdPlanIsDTO prodSegdPlanIsToProdSegdPlanIsDTO(ProdSegdPlanIs prodSegdPlanIs) {
        if ( prodSegdPlanIs == null ) {
            return null;
        }

        ProdSegdPlanIsDTO prodSegdPlanIsDTO = new ProdSegdPlanIsDTO();

        prodSegdPlanIsDTO.setVlCapiMaxi( prodSegdPlanIs.getVlCapiMaxi() );

        return prodSegdPlanIsDTO;
    }

    @Override
    public List<ProdSegdPlanIsDTO> prodSegdPlanIsToProdSegdPlanIsDTOs(List<ProdSegdPlanIs> prodSegdPlanIs) {
        if ( prodSegdPlanIs == null ) {
            return null;
        }

        List<ProdSegdPlanIsDTO> list = new ArrayList<ProdSegdPlanIsDTO>();
        for ( ProdSegdPlanIs prodSegdPlanIs_ : prodSegdPlanIs ) {
            list.add( prodSegdPlanIsToProdSegdPlanIsDTO( prodSegdPlanIs_ ) );
        }

        return list;
    }

    @Override
    public ProdSegdPlanIs prodSegdPlanIsDTOToProdSegdPlanIs(ProdSegdPlanIsDTO prodSegdPlanIsDTO) {
        if ( prodSegdPlanIsDTO == null ) {
            return null;
        }

        ProdSegdPlanIs prodSegdPlanIs = new ProdSegdPlanIs();

        prodSegdPlanIs.setVlCapiMaxi( prodSegdPlanIsDTO.getVlCapiMaxi() );

        return prodSegdPlanIs;
    }

    @Override
    public List<ProdSegdPlanIs> prodSegdPlanIsDTOsToProdSegdPlanIs(List<ProdSegdPlanIsDTO> prodSegdPlanIsDTOs) {
        if ( prodSegdPlanIsDTOs == null ) {
            return null;
        }

        List<ProdSegdPlanIs> list = new ArrayList<ProdSegdPlanIs>();
        for ( ProdSegdPlanIsDTO prodSegdPlanIsDTO : prodSegdPlanIsDTOs ) {
            list.add( prodSegdPlanIsDTOToProdSegdPlanIs( prodSegdPlanIsDTO ) );
        }

        return list;
    }
}
