package br.com.jumplabel.simulador.service.mapper;

import br.com.jumplabel.simulador.domain.MensagemProduto;
import br.com.jumplabel.simulador.service.dto.MensagemProdutoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-05-19T14:48:58-0300",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 1.2.100.v20160418-1457, environment: Java 1.8.0_92 (Oracle Corporation)"
)
@Component
public class MensagemProdutoMapperImpl implements MensagemProdutoMapper {

    @Override
    public MensagemProdutoDTO mensagemProdutoToMensagemProdutoDTO(MensagemProduto mensagemProduto) {
        if ( mensagemProduto == null ) {
            return null;
        }

        MensagemProdutoDTO mensagemProdutoDTO = new MensagemProdutoDTO();

        mensagemProdutoDTO.setDsMensagem( mensagemProduto.getDsMensagem() );
        mensagemProdutoDTO.setId( mensagemProduto.getId() );

        return mensagemProdutoDTO;
    }

    @Override
    public List<MensagemProdutoDTO> mensagemProdutosToMensagemProdutoDTOs(List<MensagemProduto> mensagemProdutos) {
        if ( mensagemProdutos == null ) {
            return null;
        }

        List<MensagemProdutoDTO> list = new ArrayList<MensagemProdutoDTO>();
        for ( MensagemProduto mensagemProduto : mensagemProdutos ) {
            list.add( mensagemProdutoToMensagemProdutoDTO( mensagemProduto ) );
        }

        return list;
    }

    @Override
    public MensagemProduto mensagemProdutoDTOToMensagemProduto(MensagemProdutoDTO mensagemProdutoDTO) {
        if ( mensagemProdutoDTO == null ) {
            return null;
        }

        MensagemProduto mensagemProduto = new MensagemProduto();

        mensagemProduto.setDsMensagem( mensagemProdutoDTO.getDsMensagem() );
        mensagemProduto.setId( mensagemProdutoDTO.getId() );

        return mensagemProduto;
    }

    @Override
    public List<MensagemProduto> mensagemProdutoDTOsToMensagemProdutos(List<MensagemProdutoDTO> mensagemProdutoDTOs) {
        if ( mensagemProdutoDTOs == null ) {
            return null;
        }

        List<MensagemProduto> list = new ArrayList<MensagemProduto>();
        for ( MensagemProdutoDTO mensagemProdutoDTO : mensagemProdutoDTOs ) {
            list.add( mensagemProdutoDTOToMensagemProduto( mensagemProdutoDTO ) );
        }

        return list;
    }
}
