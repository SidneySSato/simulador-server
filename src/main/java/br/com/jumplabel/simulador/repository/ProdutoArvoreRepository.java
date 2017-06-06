package br.com.jumplabel.simulador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jumplabel.simulador.domain.ProdId;
import br.com.jumplabel.simulador.domain.ProdutoArvore;

/**
 * Spring Data JPA repository for the ProdutoArvore entity.
 */
public interface ProdutoArvoreRepository extends JpaRepository<ProdutoArvore,ProdId> {

	@EntityGraph( attributePaths = "prod.prodSegds.prodSegdPlans", type = EntityGraphType.LOAD)
	@Query("select DISTINCT prodarv "
	+ " FROM ProdutoArvore AS prodarv, Prod AS prod, ProdSegd as prodSegd, CnalProdSegd as cnalProdSegd"
	+ " WHERE prodarv.cdSituProdArvore = :prodArvSituacao"
	+ " AND prodarv.pk = prod.pk "
	+ " AND prod.pk = prodSegd.pk.prodId"
	+ " AND prodSegd.pk = cnalProdSegd.pk.prodSegdId"
	+ " AND cnalProdSegd.pk.cnalDomiId = :canalId"	
	+ " AND prodarv.inProdutoSemOferta = :inProdutoSemOferta "	
	+ "    )")
	List<ProdutoArvore> findAllProdutoArvoreByCanalId(@Param("canalId") Long canalId, 
													  @Param("prodArvSituacao") String prodArvSituacao,
													  @Param("inProdutoSemOferta") String inProdutoSemOferta);

	@EntityGraph( attributePaths = "prod.prodSegds.prodSegdPlans", type = EntityGraphType.LOAD)
	@Query("select DISTINCT prodarv "
	+ " FROM ProdutoArvore AS prodarv, Prod AS prod, ProdSegd as prodSegd, CnalProdSegd as cnalProdSegd"
	+ " WHERE prodarv.cdSituProdArvore = :prodArvSituacao"
	+ " AND prodarv.pk = prod.pk "
	+ " AND prod.pk = prodSegd.pk.prodId"
	+ " AND prodSegd.pk = cnalProdSegd.pk.prodSegdId"
	+ " AND prodarv.inProdutoSemOferta = :inProdutoSemOferta "
	+ "    )")
	List<ProdutoArvore> findAllProdutoArvore(@Param("prodArvSituacao") String prodArvSituacao,
											 @Param("inProdutoSemOferta") String inProdutoSemOferta);

	@EntityGraph( attributePaths = "prod.prodSegds.prodSegdPlans", type = EntityGraphType.LOAD)
	@Query("select DISTINCT prodarv "
			+ " FROM ProdutoArvore AS prodarv "
			+ " WHERE prodarv.cdSituProdArvore = :prodArvSituacao"
			+ " AND prodarv.inProdutoSemOferta = :inProdutoSemOferta "
			+ "    )")
	List<ProdutoArvore> findProduto(@Param("prodArvSituacao") String prodArvSituacao,
									@Param("inProdutoSemOferta") String inProdutoSemOferta);
}
