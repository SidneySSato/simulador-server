package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.Domi;
import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.domain.TipoResposta;
import br.com.jumplabel.simulador.service.dto.PerguntaDTO;
import br.com.jumplabel.simulador.service.dto.RespostaDTO;
import br.com.jumplabel.simulador.service.dto.TagDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-16T11:44:59-0300",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class PerguntaMapperImpl implements PerguntaMapper {

    @Autowired
    private RespostaMapper respostaMapper;

    @Override
    public PerguntaDTO perguntaToPerguntaDTO(Pergunta pergunta) {
        if ( pergunta == null ) {
            return null;
        }

        PerguntaDTO perguntaDTO = new PerguntaDTO();

        perguntaDTO.setDomiNmDomi( perguntaDomiNmDomi( pergunta ) );
        perguntaDTO.setDomiId( perguntaDomiId( pergunta ) );
        perguntaDTO.setTipoRespostaId( perguntaTipoRespostaId( pergunta ) );
        perguntaDTO.setTipoRespostaDsTipoResposta( perguntaTipoRespostaDsTipoResposta( pergunta ) );
        Set<TagDTO> set = map( pergunta.getPerguntaTags() );
        if ( set != null ) {
            perguntaDTO.setTags( set );
        }
        perguntaDTO.setId( pergunta.getId() );
        perguntaDTO.setDsPergunta( pergunta.getDsPergunta() );
        perguntaDTO.setInEditavel( pergunta.getInEditavel() );
        List<RespostaDTO> list = respostaSetToRespostaDTOList( pergunta.getRespostas() );
        if ( list != null ) {
            perguntaDTO.setRespostas( list );
        }

        return perguntaDTO;
    }

    @Override
    public List<PerguntaDTO> perguntasToPerguntaDTOs(List<Pergunta> perguntas) {
        if ( perguntas == null ) {
            return null;
        }

        List<PerguntaDTO> list = new ArrayList<PerguntaDTO>();
        for ( Pergunta pergunta : perguntas ) {
            list.add( perguntaToPerguntaDTO( pergunta ) );
        }

        return list;
    }

    @Override
    public Pergunta perguntaDTOToPergunta(PerguntaDTO perguntaDTO) {
        if ( perguntaDTO == null ) {
            return null;
        }

        Pergunta pergunta = new Pergunta();

        pergunta.setDomi( domiFromId( perguntaDTO.getDomiId() ) );
        pergunta.setTipoResposta( tipoRespostaFromId( perguntaDTO.getTipoRespostaId() ) );
        pergunta.setId( perguntaDTO.getId() );
        pergunta.setDsPergunta( perguntaDTO.getDsPergunta() );
        Set<Resposta> set = respostaDTOListToRespostaSet( perguntaDTO.getRespostas() );
        if ( set != null ) {
            pergunta.setRespostas( set );
        }
        pergunta.setInEditavel( perguntaDTO.getInEditavel() );

        return pergunta;
    }

    @Override
    public List<Pergunta> perguntaDTOsToPerguntas(List<PerguntaDTO> perguntaDTOs) {
        if ( perguntaDTOs == null ) {
            return null;
        }

        List<Pergunta> list = new ArrayList<Pergunta>();
        for ( PerguntaDTO perguntaDTO : perguntaDTOs ) {
            list.add( perguntaDTOToPergunta( perguntaDTO ) );
        }

        return list;
    }

    private String perguntaDomiNmDomi(Pergunta pergunta) {

        if ( pergunta == null ) {
            return null;
        }
        Domi domi = pergunta.getDomi();
        if ( domi == null ) {
            return null;
        }
        String nmDomi = domi.getNmDomi();
        if ( nmDomi == null ) {
            return null;
        }
        return nmDomi;
    }

    private Long perguntaDomiId(Pergunta pergunta) {

        if ( pergunta == null ) {
            return null;
        }
        Domi domi = pergunta.getDomi();
        if ( domi == null ) {
            return null;
        }
        Long id = domi.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long perguntaTipoRespostaId(Pergunta pergunta) {

        if ( pergunta == null ) {
            return null;
        }
        TipoResposta tipoResposta = pergunta.getTipoResposta();
        if ( tipoResposta == null ) {
            return null;
        }
        Long id = tipoResposta.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String perguntaTipoRespostaDsTipoResposta(Pergunta pergunta) {

        if ( pergunta == null ) {
            return null;
        }
        TipoResposta tipoResposta = pergunta.getTipoResposta();
        if ( tipoResposta == null ) {
            return null;
        }
        String dsTipoResposta = tipoResposta.getDsTipoResposta();
        if ( dsTipoResposta == null ) {
            return null;
        }
        return dsTipoResposta;
    }

    protected List<RespostaDTO> respostaSetToRespostaDTOList(Set<Resposta> set) {
        if ( set == null ) {
            return null;
        }

        List<RespostaDTO> list = new ArrayList<RespostaDTO>();
        for ( Resposta resposta : set ) {
            list.add( respostaMapper.respostaToRespostaDTO( resposta ) );
        }

        return list;
    }

    protected Set<Resposta> respostaDTOListToRespostaSet(List<RespostaDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Resposta> set = new HashSet<Resposta>();
        for ( RespostaDTO respostaDTO : list ) {
            set.add( respostaMapper.respostaDTOToResposta( respostaDTO ) );
        }

        return set;
    }
}
