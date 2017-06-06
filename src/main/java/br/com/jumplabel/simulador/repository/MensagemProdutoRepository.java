package br.com.jumplabel.simulador.repository;

import br.com.jumplabel.simulador.domain.MensagemProduto;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MensagemProduto entity.
 */
@SuppressWarnings("unused")
public interface MensagemProdutoRepository extends JpaRepository<MensagemProduto,Long> {

}
