package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.ArvoreDecisao;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ArvoreDecisao entity.
 */
@SuppressWarnings("unused")
public interface ArvoreDecisaoRepository extends JpaRepository<ArvoreDecisao,Long> {

}
