package br.com.jumplabel.simulador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.jumplabel.simulador.domain.CntdDomi;

/**
 * Spring Data JPA repository for the CntdDomi entity.
 */
public interface CntdDomiRepository extends JpaRepository<CntdDomi,Long> {

//	@Query("select DISTINCT cntdDomi from CntdDomi cntdDomi "
//			+ " where cntdDomi IN (select prod.cntdDomi "
//								+ "	FROM ProdutoArvore AS prodarv, Prod AS prod "
//								+ "	WHERE prod.pk = prodarv.pk)")
	
	@Query("select DISTINCT cntdDomi "
	+ " FROM ProdutoArvore AS prodarv, Prod AS prod, CntdDomi AS cntdDomi"
	+ " WHERE prodarv.cdSituProdArvore = :prodArvSituacao"
	+ " AND prodarv.pk = prod.pk "			
	+ " AND cntdDomi.id = prod.cntdDomi.id)")
	List<CntdDomi> findAllArvoreFamilia(@Param("prodArvSituacao") String prodArvSituacao);

	@Query("select DISTINCT cntdDomi "
	+ " FROM ProdutoArvore AS prodarv, Prod AS prod, ProdSegd as prodSegd, CnalProdSegd as cnalProdSegd, CntdDomi AS cntdDomi"
	+ " WHERE prodarv.cdSituProdArvore = :prodArvSituacao"
	+ " AND prodarv.pk = prod.pk "
	+ " AND prod.cntdDomi.id = :familiaId"
	+ " AND prod.pk = prodSegd.pk.prodId"
	+ " AND prodSegd.pk = cnalProdSegd.pk.prodSegdId"
	+ " AND cntdDomi.id = cnalProdSegd.cntdDomi.id"
	+ "    )")
	List<CntdDomi> findAllArvoreCanal(@Param("familiaId") Long familiaId, @Param("prodArvSituacao") String prodArvSituacao);
	
	@Query("select DISTINCT cntdDomi "
	+ " FROM ProdutoArvore AS prodarv, Prod AS prod, ProdSegd as prodSegd, CnalProdSegd as cnalProdSegd, CntdDomi AS cntdDomi"
	+ " WHERE prodarv.cdSituProdArvore = :prodArvSituacao"
	+ " AND prodarv.pk = prod.pk "
	+ " AND prod.pk = prodSegd.pk.prodId"
	+ " AND prodSegd.pk = cnalProdSegd.pk.prodSegdId"
	+ " AND cntdDomi.id = cnalProdSegd.cntdDomi.id"
	+ "    )")
	List<CntdDomi> findAllArvoreCanal(@Param("prodArvSituacao") String prodArvSituacao);
	
}