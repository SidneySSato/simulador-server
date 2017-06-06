package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.RespostaIs;
import br.com.jumplabel.simulador.service.dto.RespostaIsDTO;
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
public class RespostaIsMapperImpl implements RespostaIsMapper {

    @Override
    public RespostaIsDTO respostaIsToRespostaIsDTO(RespostaIs respostaIs) {
        if ( respostaIs == null ) {
            return null;
        }

        RespostaIsDTO respostaIsDTO = new RespostaIsDTO();

        respostaIsDTO.setPergunta2Id( respostaIsPergunta2Id( respostaIs ) );
        respostaIsDTO.setPergunta1Id( respostaIsPergunta1Id( respostaIs ) );
        respostaIsDTO.setId( respostaIs.getId() );
        respostaIsDTO.setCdTipoIs( respostaIs.getCdTipoIs() );

        return respostaIsDTO;
    }

    @Override
    public List<RespostaIsDTO> respostaIsToRespostaIsDTOs(List<RespostaIs> respostaIs) {
        if ( respostaIs == null ) {
            return null;
        }

        List<RespostaIsDTO> list = new ArrayList<RespostaIsDTO>();
        for ( RespostaIs respostaIs_ : respostaIs ) {
            list.add( respostaIsToRespostaIsDTO( respostaIs_ ) );
        }

        return list;
    }

    @Override
    public RespostaIs respostaIsDTOToRespostaIs(RespostaIsDTO respostaIsDTO) {
        if ( respostaIsDTO == null ) {
            return null;
        }

        RespostaIs respostaIs = new RespostaIs();

        respostaIs.setPergunta1( perguntaFromId( respostaIsDTO.getPergunta1Id() ) );
        respostaIs.setPergunta2( perguntaFromId( respostaIsDTO.getPergunta2Id() ) );
        respostaIs.setId( respostaIsDTO.getId() );
        respostaIs.setCdTipoIs( respostaIsDTO.getCdTipoIs() );

        return respostaIs;
    }

    @Override
    public List<RespostaIs> respostaIsDTOsToRespostaIs(List<RespostaIsDTO> respostaIsDTOs) {
        if ( respostaIsDTOs == null ) {
            return null;
        }

        List<RespostaIs> list = new ArrayList<RespostaIs>();
        for ( RespostaIsDTO respostaIsDTO : respostaIsDTOs ) {
            list.add( respostaIsDTOToRespostaIs( respostaIsDTO ) );
        }

        return list;
    }

    private Long respostaIsPergunta2Id(RespostaIs respostaIs) {

        if ( respostaIs == null ) {
            return null;
        }
        Pergunta pergunta2 = respostaIs.getPergunta2();
        if ( pergunta2 == null ) {
            return null;
        }
        Long id = pergunta2.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long respostaIsPergunta1Id(RespostaIs respostaIs) {

        if ( respostaIs == null ) {
            return null;
        }
        Pergunta pergunta1 = respostaIs.getPergunta1();
        if ( pergunta1 == null ) {
            return null;
        }
        Long id = pergunta1.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
