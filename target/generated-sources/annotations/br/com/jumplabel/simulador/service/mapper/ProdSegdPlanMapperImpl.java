package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.ProdSegdPlan;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanDTO;
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
public class ProdSegdPlanMapperImpl implements ProdSegdPlanMapper {

    @Override
    public ProdSegdPlanDTO prodSegdPlanToProdSegdPlanDTO(ProdSegdPlan prodSegdPlan) {
        if ( prodSegdPlan == null ) {
            return null;
        }

        ProdSegdPlanDTO prodSegdPlanDTO = new ProdSegdPlanDTO();

        return prodSegdPlanDTO;
    }

    @Override
    public List<ProdSegdPlanDTO> prodSegdPlansToProdSegdPlanDTOs(List<ProdSegdPlan> prodSegdPlans) {
        if ( prodSegdPlans == null ) {
            return null;
        }

        List<ProdSegdPlanDTO> list = new ArrayList<ProdSegdPlanDTO>();
        for ( ProdSegdPlan prodSegdPlan : prodSegdPlans ) {
            list.add( prodSegdPlanToProdSegdPlanDTO( prodSegdPlan ) );
        }

        return list;
    }

    @Override
    public ProdSegdPlan prodSegdPlanDTOToProdSegdPlan(ProdSegdPlanDTO prodSegdPlanDTO) {
        if ( prodSegdPlanDTO == null ) {
            return null;
        }

        ProdSegdPlan prodSegdPlan = new ProdSegdPlan();

        return prodSegdPlan;
    }

    @Override
    public List<ProdSegdPlan> prodSegdPlanDTOsToProdSegdPlans(List<ProdSegdPlanDTO> prodSegdPlanDTOs) {
        if ( prodSegdPlanDTOs == null ) {
            return null;
        }

        List<ProdSegdPlan> list = new ArrayList<ProdSegdPlan>();
        for ( ProdSegdPlanDTO prodSegdPlanDTO : prodSegdPlanDTOs ) {
            list.add( prodSegdPlanDTOToProdSegdPlan( prodSegdPlanDTO ) );
        }

        return list;
    }
}
