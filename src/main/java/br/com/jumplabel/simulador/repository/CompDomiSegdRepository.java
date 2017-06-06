package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.CompDomiSegd;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CompDomiSegd entity.
 */
@SuppressWarnings("unused")
public interface CompDomiSegdRepository extends JpaRepository<CompDomiSegd,Long> {

}
