package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.ArvoreFamilia;
import br.com.jumplabel.simulador.domain.ArvoreFamiliaId;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ArvoreFamilia entity.
 */
@SuppressWarnings("unused")
public interface ArvoreFamiliaRepository extends JpaRepository<ArvoreFamilia,ArvoreFamiliaId> {


	
}
