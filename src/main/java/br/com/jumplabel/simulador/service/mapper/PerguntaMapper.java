package br.com.jumplabel.simulador.service.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.Domi;
import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.PerguntaTag;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.domain.Tag;
import br.com.jumplabel.simulador.domain.TipoResposta;
import br.com.jumplabel.simulador.service.dto.PerguntaDTO;
import br.com.jumplabel.simulador.service.dto.TagDTO;

/**
 * Mapper for the entity Pergunta and its DTO PerguntaDTO.
 */
@Mapper(componentModel = "spring", uses = {RespostaMapper.class, TipoRespostaMapper.class })
public interface PerguntaMapper {

    @Mapping(source = "domi.id", target = "domiId")
    @Mapping(source = "domi.nmDomi", target = "domiNmDomi")
    @Mapping(source = "perguntaTags", target = "tags")
    @Mapping(source = "tipoResposta.id", target = "tipoRespostaId")
    @Mapping(source = "tipoResposta.dsTipoResposta", target = "tipoRespostaDsTipoResposta")
    PerguntaDTO perguntaToPerguntaDTO(Pergunta pergunta);

    default Set<TagDTO> map(Set<PerguntaTag> setPerguntaTag) {
    
        if ( setPerguntaTag == null ) {
            return null;
        }

        Set<TagDTO> list = new HashSet<TagDTO>();

        TagDTO tagDTO = null;
        for ( PerguntaTag perguntaTag : setPerguntaTag ) {
        	tagDTO = new TagDTO();
        	tagDTO.setDsTag(perguntaTag.getTag().getDsTag());
        	tagDTO.setId(perguntaTag.getPk().getTag().getId());
        	
            list.add(tagDTO);
        }

        return list;

    }
    
    List<PerguntaDTO> perguntasToPerguntaDTOs(List<Pergunta> perguntas);

    @Mapping(source = "domiId", target = "domi")
    @Mapping(source = "tipoRespostaId", target = "tipoResposta")
    Pergunta perguntaDTOToPergunta(PerguntaDTO perguntaDTO);

    List<Pergunta> perguntaDTOsToPerguntas(List<PerguntaDTO> perguntaDTOs);

    default Tag tagFromId(Long id) {
        if (id == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setId(id);
        return tag;
    }
    
    default Resposta respostaFromId(Long id) {
        if (id == null) {
            return null;
        }
        Resposta resposta = new Resposta();
        
        resposta.setId(id);
        return resposta;
    }

    default TipoResposta tipoRespostaFromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoResposta tipoResposta = new TipoResposta();
        tipoResposta.setId(id);
        return tipoResposta;
    }
    
    default Domi domiFromId(Long id) {
        if (id == null) {
            return null;
        }
        Domi domi = new Domi();
        domi.setId(id);
        return domi;
    }
}
