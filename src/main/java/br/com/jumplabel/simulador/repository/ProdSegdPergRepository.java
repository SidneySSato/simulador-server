package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.ProdSegdPerg;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ProdSegdPerg entity.
 */
@SuppressWarnings("unused")
public interface ProdSegdPergRepository extends JpaRepository<ProdSegdPerg,Long> {

}
