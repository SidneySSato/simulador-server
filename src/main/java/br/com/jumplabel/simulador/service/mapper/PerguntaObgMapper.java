package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.PerguntaObgDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity PerguntaObg and its DTO PerguntaObgDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PerguntaObgMapper {

    PerguntaObgDTO perguntaObgToPerguntaObgDTO(PerguntaObg perguntaObg);

    List<PerguntaObgDTO> perguntaObgsToPerguntaObgDTOs(List<PerguntaObg> perguntaObgs);

    @Mapping(source = "perguntaId", target = "pergunta")
    PerguntaObg perguntaObgDTOToPerguntaObg(PerguntaObgDTO perguntaObgDTO);

    List<PerguntaObg> perguntaObgDTOsToPerguntaObgs(List<PerguntaObgDTO> perguntaObgDTOs);

//    default ProdutoArvore produtoArvoreFromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        ProdutoArvore produtoArvore = new ProdutoArvore();
//        produtoArvore.setId(id);
//        return produtoArvore;
//    }

    default Pergunta perguntaFromId(Long id) {
        if (id == null) {
            return null;
        }
        Pergunta pergunta = new Pergunta();
        pergunta.setId(id);
        return pergunta;
    }
}
