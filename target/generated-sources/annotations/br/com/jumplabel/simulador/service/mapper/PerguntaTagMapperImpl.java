package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.PerguntaTag;
import br.com.jumplabel.simulador.domain.PerguntaTagId;
import br.com.jumplabel.simulador.domain.Tag;
import br.com.jumplabel.simulador.service.dto.PerguntaTagDTO;
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
public class PerguntaTagMapperImpl implements PerguntaTagMapper {

    @Override
    public PerguntaTagDTO perguntaTagToPerguntaTagDTO(PerguntaTag perguntaTag) {
        if ( perguntaTag == null ) {
            return null;
        }

        PerguntaTagDTO perguntaTagDTO = new PerguntaTagDTO();

        perguntaTagDTO.setTagId( perguntaTagPkTagId( perguntaTag ) );
        perguntaTagDTO.setPerguntaId( perguntaTagPkPerguntaId( perguntaTag ) );

        return perguntaTagDTO;
    }

    @Override
    public List<PerguntaTagDTO> perguntaTagsToPerguntaTagDTOs(List<PerguntaTag> perguntaTags) {
        if ( perguntaTags == null ) {
            return null;
        }

        List<PerguntaTagDTO> list = new ArrayList<PerguntaTagDTO>();
        for ( PerguntaTag perguntaTag : perguntaTags ) {
            list.add( perguntaTagToPerguntaTagDTO( perguntaTag ) );
        }

        return list;
    }

    @Override
    public PerguntaTag perguntaTagDTOToPerguntaTag(PerguntaTagDTO perguntaTagDTO) {
        if ( perguntaTagDTO == null ) {
            return null;
        }

        PerguntaTag perguntaTag = new PerguntaTag();

        perguntaTag.setPergunta( perguntaFromId( perguntaTagDTO.getPerguntaId() ) );
        perguntaTag.setTag( tagFromId( perguntaTagDTO.getTagId() ) );

        return perguntaTag;
    }

    @Override
    public List<PerguntaTag> perguntaTagDTOsToPerguntaTags(List<PerguntaTagDTO> perguntaTagDTOs) {
        if ( perguntaTagDTOs == null ) {
            return null;
        }

        List<PerguntaTag> list = new ArrayList<PerguntaTag>();
        for ( PerguntaTagDTO perguntaTagDTO : perguntaTagDTOs ) {
            list.add( perguntaTagDTOToPerguntaTag( perguntaTagDTO ) );
        }

        return list;
    }

    private Long perguntaTagPkTagId(PerguntaTag perguntaTag) {

        if ( perguntaTag == null ) {
            return null;
        }
        PerguntaTagId pk = perguntaTag.getPk();
        if ( pk == null ) {
            return null;
        }
        Tag tag = pk.getTag();
        if ( tag == null ) {
            return null;
        }
        Long id = tag.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long perguntaTagPkPerguntaId(PerguntaTag perguntaTag) {

        if ( perguntaTag == null ) {
            return null;
        }
        PerguntaTagId pk = perguntaTag.getPk();
        if ( pk == null ) {
            return null;
        }
        Pergunta pergunta = pk.getPergunta();
        if ( pergunta == null ) {
            return null;
        }
        Long id = pergunta.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
