package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.CnalProdSegd;
import br.com.jumplabel.simulador.domain.CnalProdSegdId;
import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.domain.ProdId;
import br.com.jumplabel.simulador.domain.ProdSegdId;
import br.com.jumplabel.simulador.service.dto.CnalProdSegdDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-19T14:48:59-0300",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class CnalProdSegdMapperImpl implements CnalProdSegdMapper {

    @Override
    public CnalProdSegdDTO cnalProdSegdToCnalProdSegdDTO(CnalProdSegd cnalProdSegd) {
        if ( cnalProdSegd == null ) {
            return null;
        }

        CnalProdSegdDTO cnalProdSegdDTO = new CnalProdSegdDTO();

        cnalProdSegdDTO.setCnalDomiId( cnalProdSegdPkCnalDomiId( cnalProdSegd ) );
        cnalProdSegdDTO.setSubpId( cnalProdSegdPkProdSegdIdProdIdSubpId( cnalProdSegd ) );
        cnalProdSegdDTO.setCntdDomiDsCntdDomiLgdo( cnalProdSegdCntdDomiDsCntdDomiLgdo( cnalProdSegd ) );
        cnalProdSegdDTO.setProdId( cnalProdSegdPkProdSegdIdProdIdProdId( cnalProdSegd ) );
        cnalProdSegdDTO.setCntdDomiCdCntdDomiLgdo( cnalProdSegdCntdDomiCdCntdDomiLgdo( cnalProdSegd ) );
        cnalProdSegdDTO.setSegdDomiId( cnalProdSegdPkProdSegdIdSegdDomiId( cnalProdSegd ) );
        cnalProdSegdDTO.setDtFimVigeCnalVend( cnalProdSegd.getDtFimVigeCnalVend() );
        cnalProdSegdDTO.setDtIniciVigeCnalVend( cnalProdSegd.getDtIniciVigeCnalVend() );

        return cnalProdSegdDTO;
    }

    @Override
    public List<CnalProdSegdDTO> cnalProdSegdsToCnalProdSegdDTOs(List<CnalProdSegd> cnalProdSegds) {
        if ( cnalProdSegds == null ) {
            return null;
        }

        List<CnalProdSegdDTO> list = new ArrayList<CnalProdSegdDTO>();
        for ( CnalProdSegd cnalProdSegd : cnalProdSegds ) {
            list.add( cnalProdSegdToCnalProdSegdDTO( cnalProdSegd ) );
        }

        return list;
    }

    @Override
    public CnalProdSegd cnalProdSegdDTOToCnalProdSegd(CnalProdSegdDTO cnalProdSegdDTO) {
        if ( cnalProdSegdDTO == null ) {
            return null;
        }

        CnalProdSegd cnalProdSegd = new CnalProdSegd();

        cnalProdSegd.setDtFimVigeCnalVend( cnalProdSegdDTO.getDtFimVigeCnalVend() );
        cnalProdSegd.setDtIniciVigeCnalVend( cnalProdSegdDTO.getDtIniciVigeCnalVend() );

        return cnalProdSegd;
    }

    @Override
    public List<CnalProdSegd> cnalProdSegdDTOsToCnalProdSegds(List<CnalProdSegdDTO> cnalProdSegdDTOs) {
        if ( cnalProdSegdDTOs == null ) {
            return null;
        }

        List<CnalProdSegd> list = new ArrayList<CnalProdSegd>();
        for ( CnalProdSegdDTO cnalProdSegdDTO : cnalProdSegdDTOs ) {
            list.add( cnalProdSegdDTOToCnalProdSegd( cnalProdSegdDTO ) );
        }

        return list;
    }

    private Long cnalProdSegdPkCnalDomiId(CnalProdSegd cnalProdSegd) {

        if ( cnalProdSegd == null ) {
            return null;
        }
        CnalProdSegdId pk = cnalProdSegd.getPk();
        if ( pk == null ) {
            return null;
        }
        Long cnalDomiId = pk.getCnalDomiId();
        if ( cnalDomiId == null ) {
            return null;
        }
        return cnalDomiId;
    }

    private String cnalProdSegdPkProdSegdIdProdIdSubpId(CnalProdSegd cnalProdSegd) {

        if ( cnalProdSegd == null ) {
            return null;
        }
        CnalProdSegdId pk = cnalProdSegd.getPk();
        if ( pk == null ) {
            return null;
        }
        ProdSegdId prodSegdId = pk.getProdSegdId();
        if ( prodSegdId == null ) {
            return null;
        }
        ProdId prodId = prodSegdId.getProdId();
        if ( prodId == null ) {
            return null;
        }
        String subpId = prodId.getSubpId();
        if ( subpId == null ) {
            return null;
        }
        return subpId;
    }

    private String cnalProdSegdCntdDomiDsCntdDomiLgdo(CnalProdSegd cnalProdSegd) {

        if ( cnalProdSegd == null ) {
            return null;
        }
        CntdDomi cntdDomi = cnalProdSegd.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        String dsCntdDomiLgdo = cntdDomi.getDsCntdDomiLgdo();
        if ( dsCntdDomiLgdo == null ) {
            return null;
        }
        return dsCntdDomiLgdo;
    }

    private String cnalProdSegdPkProdSegdIdProdIdProdId(CnalProdSegd cnalProdSegd) {

        if ( cnalProdSegd == null ) {
            return null;
        }
        CnalProdSegdId pk = cnalProdSegd.getPk();
        if ( pk == null ) {
            return null;
        }
        ProdSegdId prodSegdId = pk.getProdSegdId();
        if ( prodSegdId == null ) {
            return null;
        }
        ProdId prodId = prodSegdId.getProdId();
        if ( prodId == null ) {
            return null;
        }
        String prodId_ = prodId.getProdId();
        if ( prodId_ == null ) {
            return null;
        }
        return prodId_;
    }

    private String cnalProdSegdCntdDomiCdCntdDomiLgdo(CnalProdSegd cnalProdSegd) {

        if ( cnalProdSegd == null ) {
            return null;
        }
        CntdDomi cntdDomi = cnalProdSegd.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        String cdCntdDomiLgdo = cntdDomi.getCdCntdDomiLgdo();
        if ( cdCntdDomiLgdo == null ) {
            return null;
        }
        return cdCntdDomiLgdo;
    }

    private Long cnalProdSegdPkProdSegdIdSegdDomiId(CnalProdSegd cnalProdSegd) {

        if ( cnalProdSegd == null ) {
            return null;
        }
        CnalProdSegdId pk = cnalProdSegd.getPk();
        if ( pk == null ) {
            return null;
        }
        ProdSegdId prodSegdId = pk.getProdSegdId();
        if ( prodSegdId == null ) {
            return null;
        }
        Long segdDomiId = prodSegdId.getSegdDomiId();
        if ( segdDomiId == null ) {
            return null;
        }
        return segdDomiId;
    }
}
