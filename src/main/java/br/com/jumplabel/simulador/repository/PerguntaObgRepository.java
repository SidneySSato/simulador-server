package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.PerguntaObg;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PerguntaObg entity.
 */
@SuppressWarnings("unused")
public interface PerguntaObgRepository extends JpaRepository<PerguntaObg,Long> {

}
