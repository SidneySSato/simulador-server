package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.RangeResposta;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RangeResposta entity.
 */
@SuppressWarnings("unused")
public interface RangeRespostaRepository extends JpaRepository<RangeResposta,Long> {

}
