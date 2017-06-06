package br.com.jumplabel.simulador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumplabel.simulador.domain.ProdSegdPlanSegm;
import br.com.jumplabel.simulador.domain.ProdSegdPlanSegmId;

/**
 * Spring Data JPA repository for the ProdSegdPlanSegm entity.
 */
public interface ProdSegdPlanSegmRepository extends JpaRepository<ProdSegdPlanSegm, ProdSegdPlanSegmId> {

}
