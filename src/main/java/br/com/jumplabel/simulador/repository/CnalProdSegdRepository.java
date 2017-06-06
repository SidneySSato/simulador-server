package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.CnalProdSegd;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CnalProdSegd entity.
 */
@SuppressWarnings("unused")
public interface CnalProdSegdRepository extends JpaRepository<CnalProdSegd,Long> {

}
