package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.ArvoreCanal;
import br.com.jumplabel.simulador.domain.ArvoreCanalId;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ArvoreCanal entity.
 */
@SuppressWarnings("unused")
public interface ArvoreCanalRepository extends JpaRepository<ArvoreCanal,ArvoreCanalId> {

}
