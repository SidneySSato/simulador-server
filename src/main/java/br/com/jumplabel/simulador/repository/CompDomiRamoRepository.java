package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.CompDomiRamo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CompDomiRamo entity.
 */
@SuppressWarnings("unused")
public interface CompDomiRamoRepository extends JpaRepository<CompDomiRamo,Long> {

}
