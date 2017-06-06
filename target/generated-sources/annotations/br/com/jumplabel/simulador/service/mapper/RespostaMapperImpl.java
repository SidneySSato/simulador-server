package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.Domi;
import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.RespCorporativa;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.domain.TipoResposta;
import br.com.jumplabel.simulador.service.dto.RespCorporativaDTO;
import br.com.jumplabel.simulador.service.dto.RespostaDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-16T11:45:00-0300",
    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class RespostaMapperImpl implements RespostaMapper {

    @Autowired
    private RespCorporativaMapper respCorporativaMapper;
    @Autowired
    private RangeRespostaMapper rangeRespostaMapper;
    @Autowired
    private RespostaIsMapper respostaIsMapper;

    @Override
    public RespostaDTO respostaToRespostaDTO(Resposta resposta) {
        if ( resposta == null ) {
            return null;
        }

        RespostaDTO respostaDTO = new RespostaDTO();

        respostaDTO.setPerguntaTipoRespostaId( respostaPerguntaTipoRespostaId( resposta ) );
        respostaDTO.setPerguntaInEditavel( respostaPerguntaInEditavel( resposta ) );
        respostaDTO.setPerguntaTipoRespostaDsTipoResposta( respostaPerguntaTipoRespostaDsTipoResposta( resposta ) );
        respostaDTO.setPerguntaDomiNmDomi( respostaPerguntaDomiNmDomi( resposta ) );
        respostaDTO.setPerguntaDsPergunta( respostaPerguntaDsPergunta( resposta ) );
        respostaDTO.setPerguntaDomiId( respostaPerguntaDomiId( resposta ) );
        respostaDTO.setPerguntaId( respostaPerguntaId( resposta ) );
        respostaDTO.setId( resposta.getId() );
        respostaDTO.setDsResposta( resposta.getDsResposta() );
        respostaDTO.setDsCategoria( resposta.getDsCategoria() );
        respostaDTO.setRangeResposta( rangeRespostaMapper.rangeRespostaToRangeRespostaDTO( resposta.getRangeResposta() ) );
        respostaDTO.setRespostaIs( respostaIsMapper.respostaIsToRespostaIsDTO( resposta.getRespostaIs() ) );
        List<RespCorporativaDTO> list = respCorporativaSetToRespCorporativaDTOList( resposta.getRespCorporativas() );
        if ( list != null ) {
            respostaDTO.setRespCorporativas( list );
        }

        return respostaDTO;
    }

    @Override
    public List<RespostaDTO> respostasToRespostaDTOs(List<Resposta> respostas) {
        if ( respostas == null ) {
            return null;
        }

        List<RespostaDTO> list = new ArrayList<RespostaDTO>();
        for ( Resposta resposta : respostas ) {
            list.add( respostaToRespostaDTO( resposta ) );
        }

        return list;
    }

    @Override
    public Resposta respostaDTOToResposta(RespostaDTO respostaDTO) {
        if ( respostaDTO == null ) {
            return null;
        }

        Resposta resposta = new Resposta();

        resposta.setId( respostaDTO.getId() );
        resposta.setDsResposta( respostaDTO.getDsResposta() );
        resposta.setDsCategoria( respostaDTO.getDsCategoria() );
        resposta.setRangeResposta( rangeRespostaMapper.rangeRespostaDTOToRangeResposta( respostaDTO.getRangeResposta() ) );
        resposta.setRespostaIs( respostaIsMapper.respostaIsDTOToRespostaIs( respostaDTO.getRespostaIs() ) );
        Set<RespCorporativa> set = respCorporativaDTOListToRespCorporativaSet( respostaDTO.getRespCorporativas() );
        if ( set != null ) {
            resposta.setRespCorporativas( set );
        }

        return resposta;
    }

    @Override
    public List<Resposta> respostaDTOsToRespostas(List<RespostaDTO> respostaDTOs) {
        if ( respostaDTOs == null ) {
            return null;
        }

        List<Resposta> list = new ArrayList<Resposta>();
        for ( RespostaDTO respostaDTO : respostaDTOs ) {
            list.add( respostaDTOToResposta( respostaDTO ) );
        }

        return list;
    }

    private Long respostaPerguntaTipoRespostaId(Resposta resposta) {

        if ( resposta == null ) {
            return null;
        }
        Pergunta pergunta = resposta.getPergunta();
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

    private String respostaPerguntaInEditavel(Resposta resposta) {

        if ( resposta == null ) {
            return null;
        }
        Pergunta pergunta = resposta.getPergunta();
        if ( pergunta == null ) {
            return null;
        }
        String inEditavel = pergunta.getInEditavel();
        if ( inEditavel == null ) {
            return null;
        }
        return inEditavel;
    }

    private String respostaPerguntaTipoRespostaDsTipoResposta(Resposta resposta) {

        if ( resposta == null ) {
            return null;
        }
        Pergunta pergunta = resposta.getPergunta();
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

    private String respostaPerguntaDomiNmDomi(Resposta resposta) {

        if ( resposta == null ) {
            return null;
        }
        Pergunta pergunta = resposta.getPergunta();
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

    private String respostaPerguntaDsPergunta(Resposta resposta) {

        if ( resposta == null ) {
            return null;
        }
        Pergunta pergunta = resposta.getPergunta();
        if ( pergunta == null ) {
            return null;
        }
        String dsPergunta = pergunta.getDsPergunta();
        if ( dsPergunta == null ) {
            return null;
        }
        return dsPergunta;
    }

    private Long respostaPerguntaDomiId(Resposta resposta) {

        if ( resposta == null ) {
            return null;
        }
        Pergunta pergunta = resposta.getPergunta();
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

    private Long respostaPerguntaId(Resposta resposta) {

        if ( resposta == null ) {
            return null;
        }
        Pergunta pergunta = resposta.getPergunta();
        if ( pergunta == null ) {
            return null;
        }
        Long id = pergunta.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<RespCorporativaDTO> respCorporativaSetToRespCorporativaDTOList(Set<RespCorporativa> set) {
        if ( set == null ) {
            return null;
        }

        List<RespCorporativaDTO> list = new ArrayList<RespCorporativaDTO>();
        for ( RespCorporativa respCorporativa : set ) {
            list.add( respCorporativaMapper.respCorporativaToRespCorporativaDTO( respCorporativa ) );
        }

        return list;
    }

    protected Set<RespCorporativa> respCorporativaDTOListToRespCorporativaSet(List<RespCorporativaDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<RespCorporativa> set = new HashSet<RespCorporativa>();
        for ( RespCorporativaDTO respCorporativaDTO : list ) {
            set.add( respCorporativaMapper.respCorporativaDTOToRespCorporativa( respCorporativaDTO ) );
        }

        return set;
    }
}
