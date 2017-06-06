package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.*;
import br.com.jumplabel.simulador.service.dto.MensagemProdutoDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity MensagemProduto and its DTO MensagemProdutoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MensagemProdutoMapper {

    MensagemProdutoDTO mensagemProdutoToMensagemProdutoDTO(MensagemProduto mensagemProduto);

    List<MensagemProdutoDTO> mensagemProdutosToMensagemProdutoDTOs(List<MensagemProduto> mensagemProdutos);

    @Mapping(target = "noArvoreMensagemProdutos", ignore = true)
    MensagemProduto mensagemProdutoDTOToMensagemProduto(MensagemProdutoDTO mensagemProdutoDTO);

    List<MensagemProduto> mensagemProdutoDTOsToMensagemProdutos(List<MensagemProdutoDTO> mensagemProdutoDTOs);
}
