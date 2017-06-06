package br.com.jumplabel.simulador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.ArvoreDecisao;
import br.com.jumplabel.simulador.domain.MensagemProduto;
import br.com.jumplabel.simulador.domain.NoArvore;
import br.com.jumplabel.simulador.domain.NoArvoreMensagemProduto;
import br.com.jumplabel.simulador.domain.NoArvoreMensagemProdutoId;
import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.ProdId;
import br.com.jumplabel.simulador.domain.ProdSegdId;
import br.com.jumplabel.simulador.domain.ProdSegdPlanId;
import br.com.jumplabel.simulador.domain.ProdSegdPlanSegm;
import br.com.jumplabel.simulador.domain.ProdSegdPlanSegmId;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.repository.ArvoreDecisaoRepository;
import br.com.jumplabel.simulador.repository.MensagemProdutoRepository;
import br.com.jumplabel.simulador.repository.NoArvoreMensagemProdutoRepository;
import br.com.jumplabel.simulador.repository.NoArvoreRepository;
import br.com.jumplabel.simulador.repository.PerguntaRepository;
import br.com.jumplabel.simulador.repository.ProdSegdPlanSegmRepository;
import br.com.jumplabel.simulador.service.dto.MensagemProdutoDTO;
import br.com.jumplabel.simulador.service.dto.NoArvoreCadastroDTO;
import br.com.jumplabel.simulador.service.dto.NoArvoreDTO;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanSegmDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.mapper.NoArvoreMapper;

/**
 * Service Implementation for managing NoArvore.
 */
@Service
@Transactional
public class NoArvoreService {

    private final Logger log = LoggerFactory.getLogger(NoArvoreService.class);
    
    private final NoArvoreRepository noArvoreRepository;
    private final MensagemProdutoRepository mensagemProdutoRepository;
    private final NoArvoreMensagemProdutoRepository noArvoreMensagemProdutoRepository;
    private final ProdSegdPlanSegmRepository prodSegdPlanSegmRepository;
    
    private final PerguntaRepository perguntaRepository;

    private final NoArvoreMapper noArvoreMapper;
    private final ArvoreDecisaoRepository arvoreDecisaoRepository;
    private final RespostaService respostaService;

    public NoArvoreService(NoArvoreRepository noArvoreRepository, NoArvoreMapper noArvoreMapper,
    		PerguntaRepository perguntaRepository, ArvoreDecisaoRepository arvoreDecisaoRepository,
    		RespostaService respostaService,
    		MensagemProdutoRepository mensagemProdutoRepository, NoArvoreMensagemProdutoRepository noArvoreMensagemProdutoRepository,
    		ProdSegdPlanSegmRepository prodSegdPlanSegmRepository) {
        this.noArvoreRepository = noArvoreRepository;
        this.noArvoreMapper = noArvoreMapper;
        this.perguntaRepository = perguntaRepository;
        this.arvoreDecisaoRepository = arvoreDecisaoRepository;
        this.respostaService = respostaService;
        this.mensagemProdutoRepository = mensagemProdutoRepository;
        this.noArvoreMensagemProdutoRepository = noArvoreMensagemProdutoRepository;
        this.prodSegdPlanSegmRepository = prodSegdPlanSegmRepository;
    }

    
    public ResultDTO validate(NoArvoreCadastroDTO noArvoreCadastroDTO) {

    	//TODO arrumar validate
    	return new ResultDTO();
    	
    	// validar mensagemPrdouto repetido no tipo produtoSemOferta
    	
    	
//    	if (listaNoArvoreDTO.getPerguntaTipoRespostaId() != null) {
//			if (listaRespostaDTO.getPerguntaTipoRespostaId().equals(PerguntaDTO.TIPO_RESPOSTA_INPUT_DADOS)) {
//				return validateRespostaTipoInput(listaRespostaDTO);
//			} else if (listaRespostaDTO.getPerguntaTipoRespostaId().equals(PerguntaDTO.TIPO_RESPOSTA_DADOS_CORPORATIVOS)) {
//				return validateRespostaTipoDadosCorporativos(listaRespostaDTO);
//			} else if (listaRespostaDTO.getPerguntaTipoRespostaId().equals(PerguntaDTO.TIPO_RESPOSTA_LISTA)) {
//				return validateRespostaTipoLista(listaRespostaDTO);
//			} else {
//				return ResultDTO.getValidacaoResultError(ErrorConstants.ERR_VALIDATION, "perguntaTipoRespostaId precisa ser 1(TIPO_RESPOSTA_LISTA), 2(TIPO_RESPOSTA_INPUT_DADOS) ou 3(TIPO_RESPOSTA_DADOS_CORPORATIVOS)");
//			}
//    	} else {
//    		return ResultDTO.getValidacaoCampoObrigatorioResultError("perguntaTipoRespostaId", "");
//    	}
    }
    
    /**
     * Save a listaNoArvoreDTO.
     *
     * Realiza o merge da lista de nos
     * @param listaNoArvoreDTO
     * @return id da arvoreDecisao pos merge
     */
    public Long save(NoArvoreCadastroDTO noArvoreCadastroDTO) {
    	
    	ArvoreDecisao arvoreDecisao = arvoreDecisaoRepository.getOne(noArvoreCadastroDTO.getArvoreDecisaoId());
		NoArvore noArvore = new NoArvore();
		noArvore.setArvoreDecisao(arvoreDecisao);
		noArvore.setCdTipoNo(noArvoreCadastroDTO.getCdTipoNo());
		
		if (noArvoreCadastroDTO.getNoPaiId() != null) {
			NoArvore noArvorePai = new NoArvore(noArvoreCadastroDTO.getNoPaiId());
			noArvore.setPai(noArvorePai);
		}
    	
    	if (NoArvoreDTO.TIPO_NO_PERGUNTA.equals(noArvoreCadastroDTO.getCdTipoNo())) {
    		saveNoArvoreTipoPergunta(noArvoreCadastroDTO, arvoreDecisao, noArvore);
		} else if (NoArvoreDTO.TIPO_NO_PRODUTO.equals(noArvoreCadastroDTO.getCdTipoNo())) {
			if (ResultDTO.DTO_CONSTANTE_SIM.equals(noArvoreCadastroDTO.getProdutoArvore().getInProdutoSemOferta())) {
				saveNoArvoreProdutoSemOferta(noArvoreCadastroDTO);
			} else if (ResultDTO.DTO_CONSTANTE_NAO.equals(noArvoreCadastroDTO.getProdutoArvore().getInProdutoSemOferta())) {
				saveNoArvoreProduto(noArvoreCadastroDTO.getArvoreDecisaoId(),
						  noArvoreCadastroDTO.getNoPaiId(),
						  noArvoreCadastroDTO.getCdTipoNo(),
						  noArvoreCadastroDTO.getProdutoArvore().getProdId(),
						  noArvoreCadastroDTO.getProdutoArvore().getSubpId());
			} else {
				throw new RuntimeException("Operacao nao executada, pois ocorreu erro de validacao de campo obrigatorio, noArvoreCadastroDTO.getProdutoArvore().getInProdutoSemOferta()="
						+ noArvoreCadastroDTO.getProdutoArvore().getInProdutoSemOferta());
			}
		} else if (NoArvoreDTO.TIPO_NO_PLANO.equals(noArvoreCadastroDTO.getCdTipoNo())) {
			saveNoArvorePlano(noArvoreCadastroDTO, noArvore);
		} else {
			throw new RuntimeException("Operacao nao executada, pois ocorreu erro de validacao de campo obrigatorio, cdTipoNo=" + noArvoreCadastroDTO.getCdTipoNo());
		}
    
    	return noArvoreCadastroDTO.getArvoreDecisaoId();
    }


	private void saveNoArvoreTipoPergunta(NoArvoreCadastroDTO noArvoreCadastroDTO, ArvoreDecisao arvoreDecisao,
			NoArvore noArvore) {
		// realiza a busca das respostas, grava a pergunta e respostas no noArvore
		Pergunta pergunta = perguntaRepository.getOne(noArvoreCadastroDTO.getPerguntaId());
		noArvore.setPergunta(pergunta);
		for (Resposta resposta : pergunta.getRespostas()) {
			NoArvore noArvoreResposta = new NoArvore();
			noArvoreResposta.setArvoreDecisao(arvoreDecisao);
			noArvoreResposta.setCdTipoNo(NoArvoreDTO.TIPO_NO_RESPOSTA);
			noArvoreResposta.setResposta(resposta);
			noArvore.addFilho(noArvoreResposta);
		}
		
		noArvoreRepository.save(noArvore);
	}


    /**
     * Salva o noArvore do tipo Produto sem oferta
     * @param noArvoreCadastroDTO
     * @param noArvore
     */
	private void saveNoArvoreProdutoSemOferta(NoArvoreCadastroDTO noArvoreCadastroDTO) {
		
		NoArvore noArvore = null;
		if (noArvoreCadastroDTO.getNoId() == null) {
			// primeiro grava os dados do noArvore			
			Long idNoArvore = saveNoArvoreProduto(noArvoreCadastroDTO.getArvoreDecisaoId(),
												  noArvoreCadastroDTO.getNoPaiId(),
												  noArvoreCadastroDTO.getCdTipoNo(),
												  noArvoreCadastroDTO.getProdutoArvore().getProdId(),
												  noArvoreCadastroDTO.getProdutoArvore().getSubpId());
			
			noArvore = noArvoreRepository.getOne(idNoArvore); 
			// atualiza a lista de mensagens
			for (MensagemProdutoDTO mensagemProdutoDTO: noArvoreCadastroDTO.getMensagemProdutos()) {
				if (mensagemProdutoDTO.getInserir()) {
					MensagemProduto mensagemProduto = mensagemProdutoRepository.getOne(mensagemProdutoDTO.getId());
					NoArvoreMensagemProduto noArvoreMensagemProduto = new NoArvoreMensagemProduto();
					noArvoreMensagemProduto.setMensagemProduto(mensagemProduto);
					noArvoreMensagemProduto.setNoArvore(noArvore);
					
					NoArvoreMensagemProdutoId noArvoreMensagemProdutoPK = new 
							NoArvoreMensagemProdutoId(noArvore.getId(),	mensagemProduto.getId());
					noArvoreMensagemProduto.setPk(noArvoreMensagemProdutoPK);
					noArvore.addNoArvoreMensagemProduto(noArvoreMensagemProduto);
				} 
			}
			
		// quando ja existir o no arvore 
		} else {
			noArvore = noArvoreRepository.getOne(noArvoreCadastroDTO.getNoId());
			
			// atualiza a lista de mensagens
			for (MensagemProdutoDTO mensagemProdutoDTO: noArvoreCadastroDTO.getMensagemProdutos()) {
				
				NoArvoreMensagemProdutoId noArvoreMensagemProdutoId = 
						new NoArvoreMensagemProdutoId(noArvoreCadastroDTO.getNoId(), mensagemProdutoDTO.getId());

				if (mensagemProdutoDTO.getInserir()) {
					
					// se nao existir o relacionamento, eh incluido o relacionamento 
					NoArvoreMensagemProduto arvoreMensagemProduto = noArvoreMensagemProdutoRepository.findOne(noArvoreMensagemProdutoId);
					if(arvoreMensagemProduto == null) {
						MensagemProduto mensagemProduto = mensagemProdutoRepository.getOne(mensagemProdutoDTO.getId());
						
						NoArvoreMensagemProduto noArvoreMensagemProduto = new NoArvoreMensagemProduto();
						noArvoreMensagemProduto.setMensagemProduto(mensagemProduto);
						noArvoreMensagemProduto.setNoArvore(noArvore);
						
						NoArvoreMensagemProdutoId noArvoreMensagemProdutoPK = new NoArvoreMensagemProdutoId(noArvore.getId(), 
								mensagemProduto.getId());
						noArvoreMensagemProduto.setPk(noArvoreMensagemProdutoPK);
						noArvore.addNoArvoreMensagemProduto(noArvoreMensagemProduto);
					}
					
				// para apagar o relacionamento
				} else {
					noArvoreMensagemProdutoRepository.delete(noArvoreMensagemProdutoId);
				}
			}
		}
		
		// grava as mensangensProduto
		noArvoreRepository.save(noArvore);
	}

	/**
	 * Salva o noArvore do tipo Produto MANUALMENTE
	 * @param arvoreDecisaoId
	 * @param noPaiId
	 * @param cdTipoNo
	 * @param prodId
	 * @param subpId
	 * @return ID do no salvo
	 */
	private Long saveNoArvoreProduto(Long arvoreDecisaoId, Long noPaiId, String cdTipoNo, String prodId, String subpId) {

		// obtem o id manualmente
		Long nextSequence = noArvoreRepository.getNextSequence();
		
		/* salva o no do tipo produto manualmente, pois o hibernate nao permite mapear atributos diferentes para a mesma coluna
		 * com insert e update
		 * NoArvore.produtoArvore.pk e NoArvore.prodSegdPlanSegm.pk.prodSegdPlanId.prodSegdId.prodId.prodId e
		 * NoArvore.prodSegdPlanSegm.pk.prodSegdPlanId.prodSegdId.prodId.subpId    
		*/
		noArvoreRepository.insertNoArvoreTipoProduto(nextSequence, arvoreDecisaoId, 
				                                     noPaiId, 
				                                     cdTipoNo, 
				                                     prodId, 
				                                     subpId);
		noArvoreRepository.flush();
		return nextSequence;
	}
	
	/**
     * Salva o noArvore do tipo Plano
     * @param noArvoreCadastroDTO
     * @param noArvore
     */
	private void saveNoArvorePlano(NoArvoreCadastroDTO noArvoreCadastroDTO, NoArvore noArvore) {
		if (noArvoreCadastroDTO.getNoId() == null) {
			
			ProdSegdPlanSegmDTO dto = noArvoreCadastroDTO.getProdSegdPlanSegm();
			ProdId prodId = new ProdId(dto.getProdId(), dto.getSubpId());
			ProdSegdId prodSegdId = new ProdSegdId(prodId, dto.getSegdDomiId());
			
			ProdSegdPlanId prodSegdPlanId = new ProdSegdPlanId(dto.getPlanDomiId(), prodSegdId);
			ProdSegdPlanSegmId prodSegdPlanSegmId = new ProdSegdPlanSegmId(dto.getSegmDomiId(), prodSegdPlanId);

			ProdSegdPlanSegm prodSegdPlanSegm = prodSegdPlanSegmRepository.getOne(prodSegdPlanSegmId);
			noArvore.setProdSegdPlanSegm(prodSegdPlanSegm);
			
			noArvore = noArvoreRepository.save(noArvore);
		}
	}

    /**
     *  Get all the noArvores.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<NoArvoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoArvores");
        Page<NoArvore> result = noArvoreRepository.findAll(pageable);
        return result.map(noArvore -> noArvoreMapper.noArvoreToNoArvoreDTO(noArvore));
    }

    /**
     *  Get one noArvore by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public NoArvoreDTO findOne(Long id) {
        log.debug("Request to get NoArvore : {}", id);
        NoArvore noArvore = noArvoreRepository.findOne(id);
        NoArvoreDTO noArvoreDTO = noArvoreMapper.noArvoreToNoArvoreDTO(noArvore);
        return noArvoreDTO;
    }

    /**
     *  Get one noArvore by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public NoArvoreDTO findNoArvorePai(Long arvoreDecisaoId) {
        log.debug("Request to get NoArvore, arvoreDecisaoId=", arvoreDecisaoId);
        NoArvore noArvore = noArvoreRepository.findNoArvorePai(arvoreDecisaoId);
        NoArvoreDTO noArvoreDTO = noArvoreMapper.noArvoreToNoArvoreDTO(noArvore);
        noArvoreDTO.setResposta(respostaService.map(noArvore.getResposta(), noArvore.getPergunta()));
        
        return noArvoreDTO;
    }
    
    /**
     *  Delete the  noArvore by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete NoArvore : {}", id);
        noArvoreRepository.delete(id);
    }
}
