package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.NoArvoreCopia;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the NoArvoreCopia entity.
 */
@SuppressWarnings("unused")
public interface NoArvoreCopiaRepository extends JpaRepository<NoArvoreCopia,Long> {

}
