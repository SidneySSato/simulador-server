package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.Pergunta;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the Pergunta entity.
 */
@SuppressWarnings("unused")
public interface PerguntaRepository extends JpaRepository<Pergunta,Long> {

//    @Query("select distinct pergunta from Pergunta pergunta left join fetch pergunta.tags")
//    List<Pergunta> findAllWithEagerRelationships();

//    @Query("select pergunta from Pergunta pergunta left join fetch pergunta.tags where pergunta.id =:id")
//    Pergunta findOneWithEagerRelationships(@Param("id") Long id);

}
