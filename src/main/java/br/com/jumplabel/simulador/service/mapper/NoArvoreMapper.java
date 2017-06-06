package br.com.jumplabel.simulador.service.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.jumplabel.simulador.domain.ArvoreDecisao;
import br.com.jumplabel.simulador.domain.NoArvore;
import br.com.jumplabel.simulador.domain.NoArvoreMensagemProduto;
import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.ProdutoArvore;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.service.dto.MensagemProdutoDTO;
import br.com.jumplabel.simulador.service.dto.NoArvoreDTO;
import br.com.jumplabel.simulador.service.dto.PerguntaDTO;
import br.com.jumplabel.simulador.service.dto.ProdutoArvoreDTO;
import br.com.jumplabel.simulador.service.dto.RespostaDTO;

/**
 * Mapper for the entity NoArvore and its DTO NoArvoreDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public abstract class NoArvoreMapper {

    @Autowired
    private ProdSegdPlanSegmMapper prodSegdPlanSegmMapper;

	
//	@Mapping(source = "id", target = "noId")
//    @Mapping(source = "pai.id", target = "noPaiId")
//    @Mapping(source = "pergunta.id", target = "perguntaId")
//	@Mapping(source = "pergunta.dsPergunta", target = "perguntaDsPergunta")
//    @Mapping(source = "arvoreDecisao.id", target = "arvoreDecisaoId")
//	@Mapping(source = "noArvoreMensagemProdutos", target = "mensagemProdutos")
//	@Mapping(source = "resposta", target = "resposta")
//	@Mapping(source = "noArvore", target = "resposta")
//	@Mapping(target = "result", ignore = true)
//    NoArvoreDTO noArvoreToNoArvoreDTO(NoArvore noArvore);

    public NoArvoreDTO noArvoreToNoArvoreDTO(NoArvore noArvore) {
        if ( noArvore == null ) {
            return null;
        }
        NoArvoreDTO noArvoreDTO = new NoArvoreDTO();
        noArvoreDTO.setResposta( map( noArvore.getResposta() ) );
        noArvoreDTO.setArvoreDecisaoId( noArvoreArvoreDecisaoId( noArvore ) );
        noArvoreDTO.setPerguntaDsPergunta( noArvorePerguntaDsPergunta( noArvore ) );
        noArvoreDTO.setNoPaiId( noArvorePaiId( noArvore ) );
        
        if (NoArvoreDTO.TIPO_NO_PRODUTO.equals(noArvore.getCdTipoNo())) {
        	ProdutoArvore produtoArvore = noArvore.getProdutoArvore();
            ProdutoArvoreDTO produtoArvoreDTO = new ProdutoArvoreDTO();
            if (produtoArvore != null) {
	            if (produtoArvore.getPk() != null) {
	            	produtoArvoreDTO.setProdId(produtoArvore.getPk().getProdId());
	            	produtoArvoreDTO.setSubpId(produtoArvore.getPk().getSubpId());
				}
	            
	            if (produtoArvore.getProd() != null) {
	            	produtoArvoreDTO.setDsProdCorp(produtoArvore.getProd().getDsProdCorp());
	            	produtoArvoreDTO.setDsProdSubpVc(produtoArvore.getProd().getDsProdSubpVc());
	            	produtoArvoreDTO.setCdProcSusep(produtoArvore.getProd().getCdProcSusep());
	            	 if (produtoArvore.getProd().getCntdDomi() != null) {
	            		 produtoArvoreDTO.setCntdDomiCdCntdDomiLgdo(produtoArvore.getProd().getCntdDomi().getCdCntdDomiLgdo());
	            		 produtoArvoreDTO.setCntdDomiDsCntdDomiLgdo(produtoArvore.getProd().getCntdDomi().getDsCntdDomiLgdo());
	            	 }
				}
	            noArvoreDTO.setProdutoArvore(produtoArvoreDTO);
            }
		}
        
        Set<MensagemProdutoDTO> set = map( noArvore.getNoArvoreMensagemProdutos() );
        if ( set != null ) {
            noArvoreDTO.setMensagemProdutos( set );
        }
        noArvoreDTO.setNoId( noArvore.getId() );
        noArvoreDTO.setPerguntaId( noArvorePerguntaId( noArvore ) );
        noArvoreDTO.setCdTipoNo( noArvore.getCdTipoNo() );
        noArvoreDTO.setProdSegdPlanSegm( prodSegdPlanSegmMapper.prodSegdPlanSegmToProdSegdPlanSegmDTO( noArvore.getProdSegdPlanSegm() ) );

        Set<NoArvoreDTO> set_ = noArvoreSetToNoArvoreDTOSet( noArvore.getFilhos() );
        if ( set_ != null ) {
            noArvoreDTO.setFilhos( set_ );
        }
        return noArvoreDTO;
    }
	
	private RespostaDTO map(Resposta resposta) {
        if ( resposta == null ) {
            return null;
        }
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setId(resposta.getId());
        Long tipoRespostaId = resposta.getPergunta().getTipoResposta().getId();
        // TIPO_RESPOSTA=1        
        if (PerguntaDTO.TIPO_RESPOSTA_LISTA
        		.equals(tipoRespostaId)) {
        	respostaDTO.setDsResposta(resposta.getDsResposta());
        //TIPO_RESPOSTA=2
		} else if (PerguntaDTO.TIPO_RESPOSTA_INPUT_DADOS
        		.equals(tipoRespostaId)) {
			if(RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_NUMERICO.equals(resposta.getDsCategoria())) {
				StringBuilder stb = new StringBuilder();
				stb.append("Entre ");
				stb.append(resposta.getRangeResposta().getRangeInicio());
				stb.append(" a ");
				if (resposta.getRangeResposta().getRangeFinal() == null) {
					stb.append("inderteminado");	
				} else {
					stb.append(resposta.getRangeResposta().getRangeFinal());
				}
				
				respostaDTO.setDsResposta(stb.toString());
			
			/* para TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_TODOS e 
			TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_ESPECIFICO */
			} else {
				respostaDTO.setDsResposta(resposta.getDsResposta());	
			}
			
		//TIPO_RESPOSTA=3	
		} else if (PerguntaDTO.TIPO_RESPOSTA_DADOS_CORPORATIVOS
        		.equals(tipoRespostaId)) {
			respostaDTO.setDsResposta(resposta.getDsResposta());
			
		} else {
			throw new RuntimeException("TIPO_RESPOSTA nao esperado, TIPO_RESPOSTA=" + resposta.getPergunta().getTipoResposta()); 
		}
        return respostaDTO;
    }
	
	private Set<MensagemProdutoDTO> map(Set<NoArvoreMensagemProduto> setNoArvoreMensagemProduto) {
        if ( setNoArvoreMensagemProduto == null ) {
            return null;
        }
        Set<MensagemProdutoDTO> list = new HashSet<MensagemProdutoDTO>();
        MensagemProdutoDTO mensagemProdutoDTO = null;
        for ( NoArvoreMensagemProduto noArvoreMensagemProduto : setNoArvoreMensagemProduto ) {
        	mensagemProdutoDTO = new MensagemProdutoDTO();
        	mensagemProdutoDTO.setDsMensagem(noArvoreMensagemProduto.getMensagemProduto().getDsMensagem());
        	mensagemProdutoDTO.setId(noArvoreMensagemProduto.getPk().getMensagemProdutoId());
            list.add(mensagemProdutoDTO);
        }
        return list;
    }

	abstract List<NoArvoreDTO> noArvoresToNoArvoreDTOs(List<NoArvore> noArvores);
    
	private Long noArvoreArvoreDecisaoId(NoArvore noArvore) {
		if (noArvore == null) {
			return null;
		}
		ArvoreDecisao arvoreDecisao = noArvore.getArvoreDecisao();
		if (arvoreDecisao == null) {
			return null;
		}
		Long id = arvoreDecisao.getId();
		if (id == null) {
			return null;
		}
		return id;
	}

	private Long noArvorePerguntaId(NoArvore noArvore) {
		if (noArvore == null) {
			return null;
		}
		Pergunta pergunta = noArvore.getPergunta();
		if (pergunta == null) {
			return null;
		}
		Long id = pergunta.getId();
		if (id == null) {
			return null;
		}
		return id;
	}
	
	private String noArvorePerguntaDsPergunta(NoArvore noArvore) {
		if (noArvore == null) {
			return null;
		}
		Pergunta pergunta = noArvore.getPergunta();
		if (pergunta == null) {
			return null;
		}
		String dsPergunta = pergunta.getDsPergunta();
		if (dsPergunta == null) {
			return null;
		}
		return dsPergunta;
	}
	
    private Long noArvorePaiId(NoArvore noArvore) {
        if ( noArvore == null ) {
            return null;
        }
        NoArvore pai = noArvore.getPai();
        if ( pai == null ) {
            return null;
        }
        Long id = pai.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
    
    protected Set<NoArvoreDTO> noArvoreSetToNoArvoreDTOSet(Set<NoArvore> set) {
        if ( set == null ) {
            return null;
        }
        Set<NoArvoreDTO> set_ = new HashSet<NoArvoreDTO>();
        for ( NoArvore noArvore : set ) {
            set_.add( noArvoreToNoArvoreDTO( noArvore ) );
        }
        return set_;
    }
}