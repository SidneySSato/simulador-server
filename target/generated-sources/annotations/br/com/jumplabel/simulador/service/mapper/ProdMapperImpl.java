package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.domain.Prod;
import br.com.jumplabel.simulador.domain.ProdId;
import br.com.jumplabel.simulador.service.dto.ProdDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-19T14:48:55-0300",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class ProdMapperImpl implements ProdMapper {

    @Override
    public ProdDTO prodToProdDTO(Prod prod) {
        if ( prod == null ) {
            return null;
        }

        ProdDTO prodDTO = new ProdDTO();

        prodDTO.setCntdDomiDsCntdDomiLgdo( prodCntdDomiDsCntdDomiLgdo( prod ) );
        prodDTO.setProdId( prodPkProdId( prod ) );
        prodDTO.setSubpId( prodPkSubpId( prod ) );
        prodDTO.setCntdDomiCdCntdDomiLgdo( prodCntdDomiCdCntdDomiLgdo( prod ) );
        prodDTO.setCdProcSusep( prod.getCdProcSusep() );
        prodDTO.setDsProdCorp( prod.getDsProdCorp() );
        prodDTO.setDsProdSubpVc( prod.getDsProdSubpVc() );

        return prodDTO;
    }

    @Override
    public List<ProdDTO> prodsToProdDTOs(List<Prod> prods) {
        if ( prods == null ) {
            return null;
        }

        List<ProdDTO> list = new ArrayList<ProdDTO>();
        for ( Prod prod : prods ) {
            list.add( prodToProdDTO( prod ) );
        }

        return list;
    }

    @Override
    public Prod prodDTOToProd(ProdDTO prodDTO) {
        if ( prodDTO == null ) {
            return null;
        }

        Prod prod = new Prod();

        prod.setCdProcSusep( prodDTO.getCdProcSusep() );
        prod.setDsProdCorp( prodDTO.getDsProdCorp() );
        prod.setDsProdSubpVc( prodDTO.getDsProdSubpVc() );

        return prod;
    }

    @Override
    public List<Prod> prodDTOsToProds(List<ProdDTO> prodDTOs) {
        if ( prodDTOs == null ) {
            return null;
        }

        List<Prod> list = new ArrayList<Prod>();
        for ( ProdDTO prodDTO : prodDTOs ) {
            list.add( prodDTOToProd( prodDTO ) );
        }

        return list;
    }

    private String prodCntdDomiDsCntdDomiLgdo(Prod prod) {

        if ( prod == null ) {
            return null;
        }
        CntdDomi cntdDomi = prod.getCntdDomi();
        if ( cntdDomi == null ) {
            return null;
        }
        String dsCntdDomiLgdo = cntdDomi.getDsCntdDomiLgdo();
        if ( dsCntdDomiLgdo == null ) {
            return null;
        }
        return dsCntdDomiLgdo;
    }

    private String prodPkProdId(Prod prod) {

        if ( prod == null ) {
            return null;
        }
        ProdId pk = prod.getPk();
        if ( pk == null ) {
            return null;
        }
        String prodId = pk.getProdId();
        if ( prodId == null ) {
            return null;
        }
        return prodId;
    }

    private String prodPkSubpId(Prod prod) {

        if ( prod == null ) {
            return null;
        }
        ProdId pk = prod.getPk();
        if ( pk == null ) {
            return null;
        }
        String subpId = pk.getSubpId();
        if ( subpId == null ) {
            return null;
        }
        return subpId;
    }

    private String prodCntdDomiCdCntdDomiLgdo(Prod prod) {

        if ( prod == null ) {
            return null;
        }
        CntdDomi cntdDomi = prod.getCntdDomi();
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
