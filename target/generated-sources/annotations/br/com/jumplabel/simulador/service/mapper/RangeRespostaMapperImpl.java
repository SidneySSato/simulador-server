package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.RangeResposta;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.service.dto.RangeRespostaDTO;
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
public class RangeRespostaMapperImpl implements RangeRespostaMapper {

    @Override
    public RangeRespostaDTO rangeRespostaToRangeRespostaDTO(RangeResposta rangeResposta) {
        if ( rangeResposta == null ) {
            return null;
        }

        RangeRespostaDTO rangeRespostaDTO = new RangeRespostaDTO();

        rangeRespostaDTO.setRespostaId( rangeRespostaRespostaId( rangeResposta ) );
        rangeRespostaDTO.setRangeInicio( rangeResposta.getRangeInicio() );
        rangeRespostaDTO.setRangeFinal( rangeResposta.getRangeFinal() );

        return rangeRespostaDTO;
    }

    @Override
    public List<RangeRespostaDTO> rangeRespostasToRangeRespostaDTOs(List<RangeResposta> rangeRespostas) {
        if ( rangeRespostas == null ) {
            return null;
        }

        List<RangeRespostaDTO> list = new ArrayList<RangeRespostaDTO>();
        for ( RangeResposta rangeResposta : rangeRespostas ) {
            list.add( rangeRespostaToRangeRespostaDTO( rangeResposta ) );
        }

        return list;
    }

    @Override
    public RangeResposta rangeRespostaDTOToRangeResposta(RangeRespostaDTO rangeRespostaDTO) {
        if ( rangeRespostaDTO == null ) {
            return null;
        }

        RangeResposta rangeResposta = new RangeResposta();

        rangeResposta.setResposta( respostaFromId( rangeRespostaDTO.getRespostaId() ) );
        rangeResposta.setRangeInicio( rangeRespostaDTO.getRangeInicio() );
        rangeResposta.setRangeFinal( rangeRespostaDTO.getRangeFinal() );

        return rangeResposta;
    }

    @Override
    public List<RangeResposta> rangeRespostaDTOsToRangeRespostas(List<RangeRespostaDTO> rangeRespostaDTOs) {
        if ( rangeRespostaDTOs == null ) {
            return null;
        }

        List<RangeResposta> list = new ArrayList<RangeResposta>();
        for ( RangeRespostaDTO rangeRespostaDTO : rangeRespostaDTOs ) {
            list.add( rangeRespostaDTOToRangeResposta( rangeRespostaDTO ) );
        }

        return list;
    }

    private Long rangeRespostaRespostaId(RangeResposta rangeResposta) {

        if ( rangeResposta == null ) {
            return null;
        }
        Resposta resposta = rangeResposta.getResposta();
        if ( resposta == null ) {
            return null;
        }
        Long id = resposta.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
