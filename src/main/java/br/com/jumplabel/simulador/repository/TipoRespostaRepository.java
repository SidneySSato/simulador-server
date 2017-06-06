package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.TipoResposta;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TipoResposta entity.
 */
@SuppressWarnings("unused")
public interface TipoRespostaRepository extends JpaRepository<TipoResposta,Long> {

}
