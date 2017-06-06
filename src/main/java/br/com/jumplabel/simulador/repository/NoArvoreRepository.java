package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.domain.NoArvore;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * Spring Data JPA repository for the NoArvore entity.
 */
@SuppressWarnings("unused")
public interface NoArvoreRepository extends JpaRepository<NoArvore,Long> {

	@Query("select noArvore "
	+ " FROM NoArvore AS noArvore"
	+ " WHERE noArvore.arvoreDecisao.id = :arvoreDecisaoId"
	+ " AND noArvore.pai.id = null)")
	NoArvore findNoArvorePai(@Param("arvoreDecisaoId") Long arvoreDecisaoId);
	
//	@Query(value = "insert into commit_activity_link (commit_id, activity_id) VALUES (?1, ?2)", nativeQuery = true)
//	NoArvore insertNoArvoreTipoProduto(@Param("arvoreDecisaoId") Long arvoreDecisaoId);
	
//	@Query("insert into NoArvore (c.pai.id, c.cdTipoNo, c.prodSegdPlanSegm.pk.prodSegdPlanId.prodSegdId.prodId.prodId, "
//			+ "                     c.prodSegdPlanSegm.pk.prodSegdPlanId.prodSegdId.prodId.subpId) "
//			+ "                        values (:idPai,:tipoNo,:prodId,:subpId)")
//	void insertNoArvoreTipoProduto(@Param("idPai") Long idPai,@Param("tipoNo") String tipoNo, 
//								@Param("prodId") String prodId,
//								@Param("subpId") String subpId);
	@Modifying
	@Query(value = "insert into TB_NO_ARVORE (CD_NO_ARVORE, CD_ARVORE_DECISAO, CD_NO_ARVORE_PAI, "
			+ "                               CD_TIPO_NO, CD_PROD, CD_SUBP) "
			+ "                 VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
	void insertNoArvoreTipoProduto(Long id, Long arvoreDecisaoId, Long idPai, 
								   String tipoNo, String prodId, String subpId);

	@Query(value = "SELECT NO_ARVORE_SEQ.NEXTVAL FROM DUAL" , nativeQuery = true)
	Long getNextSequence();


}
