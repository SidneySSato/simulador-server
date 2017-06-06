package br.com.jumplabel.simulador.service.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.CnalProdSegd;
import br.com.jumplabel.simulador.domain.Prod;
import br.com.jumplabel.simulador.domain.ProdSegd;
import br.com.jumplabel.simulador.domain.ProdSegdPlan;
import br.com.jumplabel.simulador.domain.ProdSegdPlanSegm;
import br.com.jumplabel.simulador.domain.ProdutoArvore;
import br.com.jumplabel.simulador.service.dto.CnalProdSegdDTO;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanSegmDTO;
import br.com.jumplabel.simulador.service.dto.ProdutoArvoreDTO;

/**
 * Mapper for the entity ProdutoArvore and its DTO ProdutoArvoreDTO.
 */
@Mapper(componentModel = "spring", uses = {PerguntaObgMapper.class})
public interface ProdutoArvoreMapper {

	@Mapping(source = "pk.prodId", target = "prodId")
	@Mapping(source = "pk.subpId", target = "subpId")
	@Mapping(source = "prod.dsProdCorp", target = "dsProdCorp")
	@Mapping(source = "prod.dsProdSubpVc", target = "dsProdSubpVc")
	@Mapping(source = "prod.cdProcSusep", target = "cdProcSusep")
	@Mapping(source = "prod.cntdDomi.id", target = "cntdDomiCdCntdDomiLgdo")
	@Mapping(source = "prod.cntdDomi.dsCntdDomiLgdo", target = "cntdDomiDsCntdDomiLgdo")
	@Mapping(source = "prod", target = "prodSegdPlanSegms")
	@Mapping(target = "result", ignore = true)
    ProdutoArvoreDTO produtoArvoreToProdutoArvoreDTO(ProdutoArvore produtoArvore);

    List<ProdutoArvoreDTO> produtoArvoresToProdutoArvoreDTOs(List<ProdutoArvore> produtoArvores);

    @Mapping(target = "noArvores", ignore = true)
    @Mapping(target = "perguntaObgs", ignore = true)
    @Mapping(target = "prod", ignore = true)
    @Mapping(target = "pk", ignore = true)
    ProdutoArvore produtoArvoreDTOToProdutoArvore(ProdutoArvoreDTO produtoArvoreDTO);

    List<ProdutoArvore> produtoArvoreDTOsToProdutoArvores(List<ProdutoArvoreDTO> produtoArvoreDTOs);

    default Set<CnalProdSegdDTO> map(Set<ProdSegd> prodSegds) {
        if ( prodSegds == null) {
            return null;
        } 
        
        Set<CnalProdSegdDTO> cnalProdSegdDTOSet = new HashSet<>();
        for (ProdSegd prodSegd : prodSegds) {
			if (prodSegd.getCnalProdSegds() != null) {
				for (CnalProdSegd cnalProdSegd : prodSegd.getCnalProdSegds()) {
					CnalProdSegdDTO cnalProdSegdDTO = new CnalProdSegdDTO();
					
					// set da pk
					cnalProdSegdDTO.setProdId(cnalProdSegd.getPk().getProdSegdId().getProdId().getProdId());
					cnalProdSegdDTO.setSubpId(cnalProdSegd.getPk().getProdSegdId().getProdId().getSubpId());
					cnalProdSegdDTO.setSegdDomiId(cnalProdSegd.getPk().getProdSegdId().getSegdDomiId());
					cnalProdSegdDTO.setCnalDomiId(cnalProdSegd.getPk().getCnalDomiId());
							
					// set da descricao do dominio
					if (cnalProdSegd.getCntdDomi() != null) {
						cnalProdSegdDTO.setCntdDomiCdCntdDomiLgdo(cnalProdSegd.getCntdDomi().getCdCntdDomiLgdo());	
						cnalProdSegdDTO.setCntdDomiDsCntdDomiLgdo(cnalProdSegd.getCntdDomi().getDsCntdDomiLgdo());
					}
					
					cnalProdSegdDTOSet.add(cnalProdSegdDTO);
				}
			}
			
		}
        return cnalProdSegdDTOSet;
    }
	
	default Set<ProdSegdPlanSegmDTO> map(Prod prod) {
        if ( prod == null || prod.getProdSegds() == null) {
            return null;
        } 
        
        Set<ProdSegdPlanSegmDTO> prodSegdPlanSegmSet = new HashSet<>();
        for (ProdSegd prodSegd : prod.getProdSegds()) {
			if (prodSegd.getProdSegdPlans() != null) {
				for (ProdSegdPlan prodSegdPlan : prodSegd.getProdSegdPlans()) {
					if (prodSegdPlan.getProdSegdPlanSegms() != null) {
						for (ProdSegdPlanSegm prodSegdPlanSegm : prodSegdPlan.getProdSegdPlanSegms()) {
							ProdSegdPlanSegmDTO prodSegdPlanSegmDTO = new ProdSegdPlanSegmDTO();
							
							// set da pk
							prodSegdPlanSegmDTO.setProdId(prodSegdPlanSegm.getPk().getProdSegdPlanId().getProdSegdId().getProdId().getProdId());
							prodSegdPlanSegmDTO.setSubpId(prodSegdPlanSegm.getPk().getProdSegdPlanId().getProdSegdId().getProdId().getSubpId());
							prodSegdPlanSegmDTO.setSegdDomiId(prodSegdPlanSegm.getPk().getProdSegdPlanId().getProdSegdId().getSegdDomiId());
							prodSegdPlanSegmDTO.setPlanDomiId(prodSegdPlanSegm.getPk().getProdSegdPlanId().getPlanDomiId());
							prodSegdPlanSegmDTO.setSegmDomiId(prodSegdPlanSegm.getPk().getSegmDomiId());
									
							// set da descricao do dominio
							if (prodSegdPlanSegm.getCntdDomi() != null) {
								prodSegdPlanSegmDTO.setCntdDomiCdCntdDomiLgdo(prodSegdPlanSegm.getCntdDomi().getCdCntdDomiLgdo());	
								prodSegdPlanSegmDTO.setCntdDomiDsCntdDomiLgdo(prodSegdPlanSegm.getCntdDomi().getDsCntdDomiLgdo());
							}
							
							prodSegdPlanSegmSet.add(prodSegdPlanSegmDTO);
						}
					}
					
				}
			}
		}
        return prodSegdPlanSegmSet;
    }
}