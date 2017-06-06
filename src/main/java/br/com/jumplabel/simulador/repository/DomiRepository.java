package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.Domi;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Domi entity.
 */
@SuppressWarnings("unused")
public interface DomiRepository extends JpaRepository<Domi,Long> {

}
