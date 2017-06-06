package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.NoArvore;
import br.com.jumplabel.simulador.service.dto.NoArvoreDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-19T14:48:59-0300",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class NoArvoreMapperImpl extends NoArvoreMapper {

    @Override
    List<NoArvoreDTO> noArvoresToNoArvoreDTOs(List<NoArvore> noArvores) {
        if ( noArvores == null ) {
            return null;
        }

        List<NoArvoreDTO> list = new ArrayList<NoArvoreDTO>();
        for ( NoArvore noArvore : noArvores ) {
            list.add( noArvoreToNoArvoreDTO( noArvore ) );
        }

        return list;
    }
}
