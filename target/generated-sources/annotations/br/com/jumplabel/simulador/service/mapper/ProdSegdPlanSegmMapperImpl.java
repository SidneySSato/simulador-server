package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.ProdId;
import br.com.jumplabel.simulador.domain.ProdSegdId;
import br.com.jumplabel.simulador.domain.ProdSegdPlanId;
import br.com.jumplabel.simulador.domain.ProdSegdPlanSegm;
import br.com.jumplabel.simulador.domain.ProdSegdPlanSegmId;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanSegmDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-19T14:48:58-0300",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class ProdSegdPlanSegmMapperImpl implements ProdSegdPlanSegmMapper {

    @Override
    public ProdSegdPlanSegmDTO prodSegdPlanSegmToProdSegdPlanSegmDTO(ProdSegdPlanSegm prodSegdPlanSegm) {
        if ( prodSegdPlanSegm == null ) {
            return null;
        }

        ProdSegdPlanSegmDTO prodSegdPlanSegmDTO = new ProdSegdPlanSegmDTO();

        prodSegdPlanSegmDTO.setPlanDomiId( prodSegdPlanSegmPkProdSegdPlanIdPlanDomiId( prodSegdPlanSegm ) );
        prodSegdPlanSegmDTO.setProdId( prodSegdPlanSegmPkProdSegdPlanIdProdSegdIdProdIdProdId( prodSegdPlanSegm ) );
        prodSegdPlanSegmDTO.setSegmDomiId( prodSegdPlanSegmPkSegmDomiId( prodSegdPlanSegm ) );
        prodSegdPlanSegmDTO.setSubpId( prodSegdPlanSegmPkProdSegdPlanIdProdSegdIdProdIdSubpId( prodSegdPlanSegm ) );
        prodSegdPlanSegmDTO.setSegdDomiId( prodSegdPlanSegmPkProdSegdPlanIdProdSegdIdSegdDomiId( prodSegdPlanSegm ) );

        return prodSegdPlanSegmDTO;
    }

    @Override
    public List<ProdSegdPlanSegmDTO> prodSegdPlanSegmsToProdSegdPlanSegmDTOs(List<ProdSegdPlanSegm> prodSegdPlanSegms) {
        if ( prodSegdPlanSegms == null ) {
            return null;
        }

        List<ProdSegdPlanSegmDTO> list = new ArrayList<ProdSegdPlanSegmDTO>();
        for ( ProdSegdPlanSegm prodSegdPlanSegm : prodSegdPlanSegms ) {
            list.add( prodSegdPlanSegmToProdSegdPlanSegmDTO( prodSegdPlanSegm ) );
        }

        return list;
    }

    @Override
    public ProdSegdPlanSegm prodSegdPlanSegmDTOToProdSegdPlanSegm(ProdSegdPlanSegmDTO prodSegdPlanSegmDTO) {
        if ( prodSegdPlanSegmDTO == null ) {
            return null;
        }

        ProdSegdPlanSegm prodSegdPlanSegm = new ProdSegdPlanSegm();

        return prodSegdPlanSegm;
    }

    @Override
    public List<ProdSegdPlanSegm> prodSegdPlanSegmDTOsToProdSegdPlanSegms(List<ProdSegdPlanSegmDTO> prodSegdPlanSegmDTOs) {
        if ( prodSegdPlanSegmDTOs == null ) {
            return null;
        }

        List<ProdSegdPlanSegm> list = new ArrayList<ProdSegdPlanSegm>();
        for ( ProdSegdPlanSegmDTO prodSegdPlanSegmDTO : prodSegdPlanSegmDTOs ) {
            list.add( prodSegdPlanSegmDTOToProdSegdPlanSegm( prodSegdPlanSegmDTO ) );
        }

        return list;
    }

    private Long prodSegdPlanSegmPkProdSegdPlanIdPlanDomiId(ProdSegdPlanSegm prodSegdPlanSegm) {

        if ( prodSegdPlanSegm == null ) {
            return null;
        }
        ProdSegdPlanSegmId pk = prodSegdPlanSegm.getPk();
        if ( pk == null ) {
            return null;
        }
        ProdSegdPlanId prodSegdPlanId = pk.getProdSegdPlanId();
        if ( prodSegdPlanId == null ) {
            return null;
        }
        Long planDomiId = prodSegdPlanId.getPlanDomiId();
        if ( planDomiId == null ) {
            return null;
        }
        return planDomiId;
    }

    private String prodSegdPlanSegmPkProdSegdPlanIdProdSegdIdProdIdProdId(ProdSegdPlanSegm prodSegdPlanSegm) {

        if ( prodSegdPlanSegm == null ) {
            return null;
        }
        ProdSegdPlanSegmId pk = prodSegdPlanSegm.getPk();
        if ( pk == null ) {
            return null;
        }
        ProdSegdPlanId prodSegdPlanId = pk.getProdSegdPlanId();
        if ( prodSegdPlanId == null ) {
            return null;
        }
        ProdSegdId prodSegdId = prodSegdPlanId.getProdSegdId();
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

    private Long prodSegdPlanSegmPkSegmDomiId(ProdSegdPlanSegm prodSegdPlanSegm) {

        if ( prodSegdPlanSegm == null ) {
            return null;
        }
        ProdSegdPlanSegmId pk = prodSegdPlanSegm.getPk();
        if ( pk == null ) {
            return null;
        }
        Long segmDomiId = pk.getSegmDomiId();
        if ( segmDomiId == null ) {
            return null;
        }
        return segmDomiId;
    }

    private String prodSegdPlanSegmPkProdSegdPlanIdProdSegdIdProdIdSubpId(ProdSegdPlanSegm prodSegdPlanSegm) {

        if ( prodSegdPlanSegm == null ) {
            return null;
        }
        ProdSegdPlanSegmId pk = prodSegdPlanSegm.getPk();
        if ( pk == null ) {
            return null;
        }
        ProdSegdPlanId prodSegdPlanId = pk.getProdSegdPlanId();
        if ( prodSegdPlanId == null ) {
            return null;
        }
        ProdSegdId prodSegdId = prodSegdPlanId.getProdSegdId();
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

    private Long prodSegdPlanSegmPkProdSegdPlanIdProdSegdIdSegdDomiId(ProdSegdPlanSegm prodSegdPlanSegm) {

        if ( prodSegdPlanSegm == null ) {
            return null;
        }
        ProdSegdPlanSegmId pk = prodSegdPlanSegm.getPk();
        if ( pk == null ) {
            return null;
        }
        ProdSegdPlanId prodSegdPlanId = pk.getProdSegdPlanId();
        if ( prodSegdPlanId == null ) {
            return null;
        }
        ProdSegdId prodSegdId = prodSegdPlanId.getProdSegdId();
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
