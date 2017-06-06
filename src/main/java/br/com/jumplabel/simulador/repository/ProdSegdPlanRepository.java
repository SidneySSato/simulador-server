package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.ProdSegdPlan;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ProdSegdPlan entity.
 */
@SuppressWarnings("unused")
public interface ProdSegdPlanRepository extends JpaRepository<ProdSegdPlan,Long> {

}
