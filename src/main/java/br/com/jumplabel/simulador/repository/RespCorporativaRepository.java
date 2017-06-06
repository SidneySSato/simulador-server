package br.com.jumplabel.simulador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumplabel.simulador.domain.RespCorporativa;
import br.com.jumplabel.simulador.domain.RespCorporativaId;

/**
 * Spring Data JPA repository for the RespCorporativa entity.
 */
@SuppressWarnings("unused")
public interface RespCorporativaRepository extends JpaRepository<RespCorporativa,RespCorporativaId> {

}
