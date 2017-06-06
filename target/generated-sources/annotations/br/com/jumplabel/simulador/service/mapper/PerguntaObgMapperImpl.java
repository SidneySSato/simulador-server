package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.PerguntaObg;
import br.com.jumplabel.simulador.service.dto.PerguntaObgDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-19T14:04:44-0300",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class PerguntaObgMapperImpl implements PerguntaObgMapper {

    @Override
    public PerguntaObgDTO perguntaObgToPerguntaObgDTO(PerguntaObg perguntaObg) {
        if ( perguntaObg == null ) {
            return null;
        }

        PerguntaObgDTO perguntaObgDTO = new PerguntaObgDTO();

        return perguntaObgDTO;
    }

    @Override
    public List<PerguntaObgDTO> perguntaObgsToPerguntaObgDTOs(List<PerguntaObg> perguntaObgs) {
        if ( perguntaObgs == null ) {
            return null;
        }

        List<PerguntaObgDTO> list = new ArrayList<PerguntaObgDTO>();
        for ( PerguntaObg perguntaObg : perguntaObgs ) {
            list.add( perguntaObgToPerguntaObgDTO( perguntaObg ) );
        }

        return list;
    }

    @Override
    public PerguntaObg perguntaObgDTOToPerguntaObg(PerguntaObgDTO perguntaObgDTO) {
        if ( perguntaObgDTO == null ) {
            return null;
        }

        PerguntaObg perguntaObg = new PerguntaObg();

        perguntaObg.setPergunta( perguntaFromId( perguntaObgDTO.getPerguntaId() ) );

        return perguntaObg;
    }

    @Override
    public List<PerguntaObg> perguntaObgDTOsToPerguntaObgs(List<PerguntaObgDTO> perguntaObgDTOs) {
        if ( perguntaObgDTOs == null ) {
            return null;
        }

        List<PerguntaObg> list = new ArrayList<PerguntaObg>();
        for ( PerguntaObgDTO perguntaObgDTO : perguntaObgDTOs ) {
            list.add( perguntaObgDTOToPerguntaObg( perguntaObgDTO ) );
        }

        return list;
    }
}
