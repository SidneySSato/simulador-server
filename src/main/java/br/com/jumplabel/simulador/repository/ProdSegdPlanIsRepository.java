package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.ProdSegdPlanIs;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ProdSegdPlanIs entity.
 */
@SuppressWarnings("unused")
public interface ProdSegdPlanIsRepository extends JpaRepository<ProdSegdPlanIs,Long> {

}
