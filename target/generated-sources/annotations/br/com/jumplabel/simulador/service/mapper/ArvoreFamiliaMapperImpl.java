package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.ArvoreDecisao;
import br.com.jumplabel.simulador.domain.ArvoreFamilia;
import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.service.dto.ArvoreFamiliaDTO;
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
public class ArvoreFamiliaMapperImpl implements ArvoreFamiliaMapper {

    @Override
    public ArvoreFamiliaDTO arvoreFamiliaToArvoreFamiliaDTO(ArvoreFamilia arvoreFamilia) {
        if ( arvoreFamilia == null ) {
            return null;
        }

        ArvoreFamiliaDTO arvoreFamiliaDTO = new ArvoreFamiliaDTO();

        arvoreFamiliaDTO.setArvoreDecisaoId( arvoreFamiliaArvoreDecisaoId( arvoreFamilia ) );
        arvoreFamiliaDTO.setCntdDomiDsCntdDomiLgdo( arvoreFamiliaCntdDomiDsCntdDomiLgdo( arvoreFamilia ) );
        arvoreFamiliaDTO.setCntdDomiId( arvoreFamiliaCntdDomiId( arvoreFamilia ) );
        arvoreFamiliaDTO.setCntdDomiCdCntdDomiLgdo( arvoreFamiliaCntdDomiCdCntdDomiLgdo( arvoreFamilia ) );

        return arvoreFamiliaDTO;
    }

    @Override
    public List<ArvoreFamiliaDTO> arvoreFamiliasToArvoreFamiliaDTOs(List<ArvoreFamilia> arvoreFamilias) {
        if ( arvoreFamilias == null ) {
            return null;
        }

        List<ArvoreFamiliaDTO> list = new ArrayList<ArvoreFamiliaDTO>();
        for ( ArvoreFamilia arvoreFamilia : arvoreFamilias ) {
            list.add( arvoreFamiliaToArvoreFamiliaDTO( arvoreFamilia ) );
        }

        return list;
    }

    @Override
    public ArvoreFamilia arvoreFamiliaDTOToArvoreFamilia(ArvoreFamiliaDTO arvoreFamiliaDTO) {
        if ( arvoreFamiliaDTO == null ) {
            return null;
        }

        ArvoreFamilia arvoreFamilia = new ArvoreFamilia();

        arvoreFamilia.setArvoreDecisao( arvoreDecisaoFromId( arvoreFamiliaDTO.getArvoreDecisaoId() ) );

        return arvoreFamilia;
    }

    @Override
    public List<ArvoreFamilia> arvoreFamiliaDTOsToArvoreFamilias(List<ArvoreFamiliaDTO> arvoreFamiliaDTOs) {
        if ( arvoreFamiliaDTOs == null ) {
            return null;
        }

        List<ArvoreFamilia> list = new ArrayList<ArvoreFamilia>();
        for ( ArvoreFamiliaDTO arvoreFamiliaDTO : arvoreFamiliaDTOs ) {
            list.add( arvoreFamiliaDTOToArvoreFamilia( arvoreFamiliaDTO ) );
        }

        return list;
    }

    private Long arvoreFamiliaArvoreDecisaoId(ArvoreFamilia arvoreFamilia) {

        if ( arvoreFamilia == null ) {
            return null;
        }
        ArvoreDecisao arvoreDecisao = arvoreFamilia.getArvoreDecisao();
        if ( arvoreDecisao == null ) {
            return null;
        }
        Long id = arvoreDecisao.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String arvoreFamiliaCntdDomiDsCntdDomiLgdo(ArvoreFamilia arvoreFamilia) {

        if ( arvoreFamilia == null ) {
            return null;
        }
        CntdDomi cntdDomi = arvoreFamilia.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        String dsCntdDomiLgdo = cntdDomi.getDsCntdDomiLgdo();
        if ( dsCntdDomiLgdo == null ) {
            return null;
        }
        return dsCntdDomiLgdo;
    }

    private Long arvoreFamiliaCntdDomiId(ArvoreFamilia arvoreFamilia) {

        if ( arvoreFamilia == null ) {
            return null;
        }
        CntdDomi cntdDomi = arvoreFamilia.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        Long id = cntdDomi.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String arvoreFamiliaCntdDomiCdCntdDomiLgdo(ArvoreFamilia arvoreFamilia) {

        if ( arvoreFamilia == null ) {
            return null;
        }
        CntdDomi cntdDomi = arvoreFamilia.getCntdDomi();
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
