package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.ArvoreCanal;
import br.com.jumplabel.simulador.domain.ArvoreDecisao;
import br.com.jumplabel.simulador.domain.ArvoreFamilia;
import br.com.jumplabel.simulador.service.dto.ArvoreCanalDTO;
import br.com.jumplabel.simulador.service.dto.ArvoreDecisaoDTO;
import br.com.jumplabel.simulador.service.dto.ArvoreFamiliaDTO;
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
public class ArvoreDecisaoMapperImpl implements ArvoreDecisaoMapper {

    @Autowired
    private ArvoreFamiliaMapper arvoreFamiliaMapper;
    @Autowired
    private ArvoreCanalMapper arvoreCanalMapper;

    @Override
    public ArvoreDecisaoDTO arvoreDecisaoToArvoreDecisaoDTO(ArvoreDecisao arvoreDecisao) {
        if ( arvoreDecisao == null ) {
            return null;
        }

        ArvoreDecisaoDTO arvoreDecisaoDTO = new ArvoreDecisaoDTO();

        arvoreDecisaoDTO.setId( arvoreDecisao.getId() );
        arvoreDecisaoDTO.setDsArvoreDecisao( arvoreDecisao.getDsArvoreDecisao() );
        arvoreDecisaoDTO.setQtProdutos( arvoreDecisao.getQtProdutos() );
        arvoreDecisaoDTO.setQtPlanos( arvoreDecisao.getQtPlanos() );
        arvoreDecisaoDTO.setCdSituArvore( arvoreDecisao.getCdSituArvore() );
        Set<ArvoreFamiliaDTO> set = arvoreFamiliaSetToArvoreFamiliaDTOSet( arvoreDecisao.getArvoreFamilias() );
        if ( set != null ) {
            arvoreDecisaoDTO.setArvoreFamilias( set );
        }
        Set<ArvoreCanalDTO> set_ = arvoreCanalSetToArvoreCanalDTOSet( arvoreDecisao.getArvoreCanais() );
        if ( set_ != null ) {
            arvoreDecisaoDTO.setArvoreCanais( set_ );
        }

        return arvoreDecisaoDTO;
    }

    @Override
    public List<ArvoreDecisaoDTO> arvoreDecisaosToArvoreDecisaoDTOs(List<ArvoreDecisao> arvoreDecisaos) {
        if ( arvoreDecisaos == null ) {
            return null;
        }

        List<ArvoreDecisaoDTO> list = new ArrayList<ArvoreDecisaoDTO>();
        for ( ArvoreDecisao arvoreDecisao : arvoreDecisaos ) {
            list.add( arvoreDecisaoToArvoreDecisaoDTO( arvoreDecisao ) );
        }

        return list;
    }

    @Override
    public ArvoreDecisao arvoreDecisaoDTOToArvoreDecisao(ArvoreDecisaoDTO arvoreDecisaoDTO) {
        if ( arvoreDecisaoDTO == null ) {
            return null;
        }

        ArvoreDecisao arvoreDecisao = new ArvoreDecisao();

        arvoreDecisao.setId( arvoreDecisaoDTO.getId() );
        arvoreDecisao.setDsArvoreDecisao( arvoreDecisaoDTO.getDsArvoreDecisao() );
        arvoreDecisao.setQtProdutos( arvoreDecisaoDTO.getQtProdutos() );
        arvoreDecisao.setQtPlanos( arvoreDecisaoDTO.getQtPlanos() );
        arvoreDecisao.setCdSituArvore( arvoreDecisaoDTO.getCdSituArvore() );

        return arvoreDecisao;
    }

    @Override
    public List<ArvoreDecisao> arvoreDecisaoDTOsToArvoreDecisaos(List<ArvoreDecisaoDTO> arvoreDecisaoDTOs) {
        if ( arvoreDecisaoDTOs == null ) {
            return null;
        }

        List<ArvoreDecisao> list = new ArrayList<ArvoreDecisao>();
        for ( ArvoreDecisaoDTO arvoreDecisaoDTO : arvoreDecisaoDTOs ) {
            list.add( arvoreDecisaoDTOToArvoreDecisao( arvoreDecisaoDTO ) );
        }

        return list;
    }

    protected Set<ArvoreFamiliaDTO> arvoreFamiliaSetToArvoreFamiliaDTOSet(Set<ArvoreFamilia> set) {
        if ( set == null ) {
            return null;
        }

        Set<ArvoreFamiliaDTO> set_ = new HashSet<ArvoreFamiliaDTO>();
        for ( ArvoreFamilia arvoreFamilia : set ) {
            set_.add( arvoreFamiliaMapper.arvoreFamiliaToArvoreFamiliaDTO( arvoreFamilia ) );
        }

        return set_;
    }

    protected Set<ArvoreCanalDTO> arvoreCanalSetToArvoreCanalDTOSet(Set<ArvoreCanal> set) {
        if ( set == null ) {
            return null;
        }

        Set<ArvoreCanalDTO> set_ = new HashSet<ArvoreCanalDTO>();
        for ( ArvoreCanal arvoreCanal : set ) {
            set_.add( arvoreCanalMapper.arvoreCanalToArvoreCanalDTO( arvoreCanal ) );
        }

        return set_;
    }
}
