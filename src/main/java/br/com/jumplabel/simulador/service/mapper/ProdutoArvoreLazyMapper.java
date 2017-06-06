package br.com.jumplabel.simulador.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.jumplabel.simulador.domain.ProdutoArvore;
import br.com.jumplabel.simulador.service.dto.ProdutoArvoreDTO;

/**
 * Mapper for the entity ProdutoArvore and its DTO ProdutoArvoreDTO
 * Esse mapeamento nao deve fazer acesso a atributos que nao precisam ser carregados para retornar
 * na criacao dos nos da arvore
 */
@Mapper(componentModel = "spring", uses = {PerguntaObgMapper.class})
public interface ProdutoArvoreLazyMapper {

	@Mapping(source = "pk.prodId", target = "prodId")
	@Mapping(source = "pk.subpId", target = "subpId")
	@Mapping(source = "prod.dsProdCorp", target = "dsProdCorp")
	@Mapping(source = "prod.dsProdSubpVc", target = "dsProdSubpVc")
	@Mapping(source = "prod.cdProcSusep", target = "cdProcSusep")
	@Mapping(source = "prod.cntdDomi.id", target = "cntdDomiCdCntdDomiLgdo")
	@Mapping(source = "prod.cntdDomi.dsCntdDomiLgdo", target = "cntdDomiDsCntdDomiLgdo")
	@Mapping(target = "prodSegdPlanSegms", ignore = true)
	@Mapping(target = "result", ignore = true)
    ProdutoArvoreDTO produtoArvoreToProdutoArvoreDTO(ProdutoArvore produtoArvore);

    List<ProdutoArvoreDTO> produtoArvoresToProdutoArvoreDTOs(List<ProdutoArvore> produtoArvores);

    @Mapping(target = "noArvores", ignore = true)
    @Mapping(target = "perguntaObgs", ignore = true)
    @Mapping(target = "prod", ignore = true)
    @Mapping(target = "pk", ignore = true)
    ProdutoArvore produtoArvoreDTOToProdutoArvore(ProdutoArvoreDTO produtoArvoreDTO);

    List<ProdutoArvore> produtoArvoreDTOsToProdutoArvores(List<ProdutoArvoreDTO> produtoArvoreDTOs);
}