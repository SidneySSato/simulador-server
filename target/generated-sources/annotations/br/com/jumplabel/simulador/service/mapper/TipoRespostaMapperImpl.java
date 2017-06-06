package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.TipoResposta;
import br.com.jumplabel.simulador.service.dto.TipoRespostaDTO;
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
public class TipoRespostaMapperImpl implements TipoRespostaMapper {

    @Override
    public TipoRespostaDTO tipoRespostaToTipoRespostaDTO(TipoResposta tipoResposta) {
        if ( tipoResposta == null ) {
            return null;
        }

        TipoRespostaDTO tipoRespostaDTO = new TipoRespostaDTO();

        tipoRespostaDTO.setId( tipoResposta.getId() );
        tipoRespostaDTO.setDsTipoResposta( tipoResposta.getDsTipoResposta() );

        return tipoRespostaDTO;
    }

    @Override
    public List<TipoRespostaDTO> tipoRespostasToTipoRespostaDTOs(List<TipoResposta> tipoRespostas) {
        if ( tipoRespostas == null ) {
            return null;
        }

        List<TipoRespostaDTO> list = new ArrayList<TipoRespostaDTO>();
        for ( TipoResposta tipoResposta : tipoRespostas ) {
            list.add( tipoRespostaToTipoRespostaDTO( tipoResposta ) );
        }

        return list;
    }

    @Override
    public TipoResposta tipoRespostaDTOToTipoResposta(TipoRespostaDTO tipoRespostaDTO) {
        if ( tipoRespostaDTO == null ) {
            return null;
        }

        TipoResposta tipoResposta = new TipoResposta();

        tipoResposta.setId( tipoRespostaDTO.getId() );
        tipoResposta.setDsTipoResposta( tipoRespostaDTO.getDsTipoResposta() );

        return tipoResposta;
    }

    @Override
    public List<TipoResposta> tipoRespostaDTOsToTipoRespostas(List<TipoRespostaDTO> tipoRespostaDTOs) {
        if ( tipoRespostaDTOs == null ) {
            return null;
        }

        List<TipoResposta> list = new ArrayList<TipoResposta>();
        for ( TipoRespostaDTO tipoRespostaDTO : tipoRespostaDTOs ) {
            list.add( tipoRespostaDTOToTipoResposta( tipoRespostaDTO ) );
        }

        return list;
    }
}
