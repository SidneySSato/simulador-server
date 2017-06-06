package br.com.jumplabel.simulador.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.jumplabel.simulador.domain.Resposta;

/**
 * Spring Data JPA repository for the Resposta entity.
 */
@SuppressWarnings("unused")
public interface RespostaRepository extends JpaRepository<Resposta,Long> {

    @Query("select resposta from Resposta resposta where resposta.pergunta.id = ?1 and resposta.dsResposta = ?2")
    List<Resposta> findByPerguntaIdRespostaDs(Long perguntaId, String dsResposta);

}
