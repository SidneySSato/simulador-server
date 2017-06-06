package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.RespostaIs;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RespostaIs entity.
 */
@SuppressWarnings("unused")
public interface RespostaIsRepository extends JpaRepository<RespostaIs,Long> {

}
