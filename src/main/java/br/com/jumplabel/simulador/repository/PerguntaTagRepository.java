package br.com.jumplabel.simulador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumplabel.simulador.domain.PerguntaTag;
import br.com.jumplabel.simulador.domain.PerguntaTagId;

/**
 * Spring Data JPA repository for the PerguntaTag entity.
 */
public interface PerguntaTagRepository extends JpaRepository<PerguntaTag,PerguntaTagId> {

}
