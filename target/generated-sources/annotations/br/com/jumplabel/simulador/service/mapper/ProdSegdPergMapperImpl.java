package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.ProdSegdPerg;
import br.com.jumplabel.simulador.service.dto.ProdSegdPergDTO;
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
public class ProdSegdPergMapperImpl implements ProdSegdPergMapper {

    @Override
    public ProdSegdPergDTO prodSegdPergToProdSegdPergDTO(ProdSegdPerg prodSegdPerg) {
        if ( prodSegdPerg == null ) {
            return null;
        }

        ProdSegdPergDTO prodSegdPergDTO = new ProdSegdPergDTO();

        prodSegdPergDTO.setDtInicVigePerg( prodSegdPerg.getDtInicVigePerg() );
        prodSegdPergDTO.setDtFimVigePerg( prodSegdPerg.getDtFimVigePerg() );

        return prodSegdPergDTO;
    }

    @Override
    public List<ProdSegdPergDTO> prodSegdPergsToProdSegdPergDTOs(List<ProdSegdPerg> prodSegdPergs) {
        if ( prodSegdPergs == null ) {
            return null;
        }

        List<ProdSegdPergDTO> list = new ArrayList<ProdSegdPergDTO>();
        for ( ProdSegdPerg prodSegdPerg : prodSegdPergs ) {
            list.add( prodSegdPergToProdSegdPergDTO( prodSegdPerg ) );
        }

        return list;
    }

    @Override
    public ProdSegdPerg prodSegdPergDTOToProdSegdPerg(ProdSegdPergDTO prodSegdPergDTO) {
        if ( prodSegdPergDTO == null ) {
            return null;
        }

        ProdSegdPerg prodSegdPerg = new ProdSegdPerg();

        prodSegdPerg.setDtInicVigePerg( prodSegdPergDTO.getDtInicVigePerg() );
        prodSegdPerg.setDtFimVigePerg( prodSegdPergDTO.getDtFimVigePerg() );

        return prodSegdPerg;
    }

    @Override
    public List<ProdSegdPerg> prodSegdPergDTOsToProdSegdPergs(List<ProdSegdPergDTO> prodSegdPergDTOs) {
        if ( prodSegdPergDTOs == null ) {
            return null;
        }

        List<ProdSegdPerg> list = new ArrayList<ProdSegdPerg>();
        for ( ProdSegdPergDTO prodSegdPergDTO : prodSegdPergDTOs ) {
            list.add( prodSegdPergDTOToProdSegdPerg( prodSegdPergDTO ) );
        }

        return list;
    }
}
