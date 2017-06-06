package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.ProdSegd;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ProdSegd entity.
 */
@SuppressWarnings("unused")
public interface ProdSegdRepository extends JpaRepository<ProdSegd,Long> {

}
