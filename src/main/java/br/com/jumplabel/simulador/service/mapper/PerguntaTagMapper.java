package br.com.jumplabel.simulador.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.PerguntaTag;
import br.com.jumplabel.simulador.domain.Tag;
import br.com.jumplabel.simulador.service.dto.PerguntaTagDTO;

/**
 * Mapper for the entity PerguntaTag and its DTO PerguntaTagDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PerguntaTagMapper {

	@Mapping(source = "pk.pergunta.id", target = "perguntaId")
	@Mapping(source = "pk.tag.id", target = "tagId")
    PerguntaTagDTO perguntaTagToPerguntaTagDTO(PerguntaTag perguntaTag);

    List<PerguntaTagDTO> perguntaTagsToPerguntaTagDTOs(List<PerguntaTag> perguntaTags);

    @Mapping(source = "perguntaId", target = "pergunta")
    @Mapping(source = "tagId", target = "tag")
    PerguntaTag perguntaTagDTOToPerguntaTag(PerguntaTagDTO perguntaTagDTO);

    List<PerguntaTag> perguntaTagDTOsToPerguntaTags(List<PerguntaTagDTO> perguntaTagDTOs);

    default Pergunta perguntaFromId(Long id) {
        if (id == null) {
            return null;
        }
        Pergunta pergunta = new Pergunta();
        pergunta.setId(id);
        return pergunta;
    }

    default Tag tagFromId(Long id) {
        if (id == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setId(id);
        return tag;
    }
}
