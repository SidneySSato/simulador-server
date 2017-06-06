package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.ArvoreCanal;
import br.com.jumplabel.simulador.domain.ArvoreDecisao;
import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.service.dto.ArvoreCanalDTO;
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
public class ArvoreCanalMapperImpl implements ArvoreCanalMapper {

    @Override
    public ArvoreCanalDTO arvoreCanalToArvoreCanalDTO(ArvoreCanal arvoreCanal) {
        if ( arvoreCanal == null ) {
            return null;
        }

        ArvoreCanalDTO arvoreCanalDTO = new ArvoreCanalDTO();

        arvoreCanalDTO.setArvoreDecisaoId( arvoreCanalArvoreDecisaoId( arvoreCanal ) );
        arvoreCanalDTO.setCntdDomiDsCntdDomiLgdo( arvoreCanalCntdDomiDsCntdDomiLgdo( arvoreCanal ) );
        arvoreCanalDTO.setCntdDomiId( arvoreCanalCntdDomiId( arvoreCanal ) );
        arvoreCanalDTO.setCntdDomiCdCntdDomiLgdo( arvoreCanalCntdDomiCdCntdDomiLgdo( arvoreCanal ) );

        return arvoreCanalDTO;
    }

    @Override
    public List<ArvoreCanalDTO> arvoreCanaisToArvoreCanalDTOs(List<ArvoreCanal> arvoreCanais) {
        if ( arvoreCanais == null ) {
            return null;
        }

        List<ArvoreCanalDTO> list = new ArrayList<ArvoreCanalDTO>();
        for ( ArvoreCanal arvoreCanal : arvoreCanais ) {
            list.add( arvoreCanalToArvoreCanalDTO( arvoreCanal ) );
        }

        return list;
    }

    @Override
    public ArvoreCanal arvoreCanalDTOToArvoreCanal(ArvoreCanalDTO arvoreCanalDTO) {
        if ( arvoreCanalDTO == null ) {
            return null;
        }

        ArvoreCanal arvoreCanal = new ArvoreCanal();

        arvoreCanal.setArvoreDecisao( arvoreDecisaoFromId( arvoreCanalDTO.getArvoreDecisaoId() ) );

        return arvoreCanal;
    }

    @Override
    public List<ArvoreCanal> arvoreCanalDTOsToArvoreCanais(List<ArvoreCanalDTO> arvoreCanalDTOs) {
        if ( arvoreCanalDTOs == null ) {
            return null;
        }

        List<ArvoreCanal> list = new ArrayList<ArvoreCanal>();
        for ( ArvoreCanalDTO arvoreCanalDTO : arvoreCanalDTOs ) {
            list.add( arvoreCanalDTOToArvoreCanal( arvoreCanalDTO ) );
        }

        return list;
    }

    private Long arvoreCanalArvoreDecisaoId(ArvoreCanal arvoreCanal) {

        if ( arvoreCanal == null ) {
            return null;
        }
        ArvoreDecisao arvoreDecisao = arvoreCanal.getArvoreDecisao();
        if ( arvoreDecisao == null ) {
            return null;
        }
        Long id = arvoreDecisao.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String arvoreCanalCntdDomiDsCntdDomiLgdo(ArvoreCanal arvoreCanal) {

        if ( arvoreCanal == null ) {
            return null;
        }
        CntdDomi cntdDomi = arvoreCanal.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        String dsCntdDomiLgdo = cntdDomi.getDsCntdDomiLgdo();
        if ( dsCntdDomiLgdo == null ) {
            return null;
        }
        return dsCntdDomiLgdo;
    }

    private Long arvoreCanalCntdDomiId(ArvoreCanal arvoreCanal) {

        if ( arvoreCanal == null ) {
            return null;
        }
        CntdDomi cntdDomi = arvoreCanal.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        Long id = cntdDomi.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String arvoreCanalCntdDomiCdCntdDomiLgdo(ArvoreCanal arvoreCanal) {

        if ( arvoreCanal == null ) {
            return null;
        }
        CntdDomi cntdDomi = arvoreCanal.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        String cdCntdDomiLgdo = cntdDomi.getCdCntdDomiLgdo();
        if ( cdCntdDomiLgdo == null ) {
            return null;
        }
        return cdCntdDomiLgdo;
    }
}
