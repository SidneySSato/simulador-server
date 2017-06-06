package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.domain.Prod;
import br.com.jumplabel.simulador.domain.ProdId;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Prod entity.
 */
@SuppressWarnings("unused")
public interface ProdRepository extends JpaRepository<Prod, ProdId> {
	
}
