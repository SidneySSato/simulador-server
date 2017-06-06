package br.com.jumplabel.simulador.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jumplabel.simulador.domain.NoArvoreMensagemProduto;
import br.com.jumplabel.simulador.domain.NoArvoreMensagemProdutoId;

/**
 * Spring Data JPA repository for the NoArvoreMensagemProduto entity.
 */
public interface NoArvoreMensagemProdutoRepository extends JpaRepository<NoArvoreMensagemProduto,NoArvoreMensagemProdutoId> {

}
