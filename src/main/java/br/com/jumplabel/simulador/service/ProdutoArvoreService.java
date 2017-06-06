package br.com.jumplabel.simulador.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.PerguntaObg;
import br.com.jumplabel.simulador.domain.PerguntaObgId;
import br.com.jumplabel.simulador.domain.ProdId;
import br.com.jumplabel.simulador.domain.ProdutoArvore;
import br.com.jumplabel.simulador.repository.ProdutoArvoreRepository;
import br.com.jumplabel.simulador.service.dto.PerguntaObgDTO;
import br.com.jumplabel.simulador.service.dto.ProdutoArvoreDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.mapper.ProdutoArvoreMapper;

/**
 * Service Implementation for managing ProdutoArvore.
 */
@Service
@Transactional
public class ProdutoArvoreService {

    private final Logger log = LoggerFactory.getLogger(ProdutoArvoreService.class);
    
    private final ProdutoArvoreRepository produtoArvoreRepository;

    private final ProdutoArvoreMapper produtoArvoreMapper;

    public ProdutoArvoreService(ProdutoArvoreRepository produtoArvoreRepository, ProdutoArvoreMapper produtoArvoreMapper) {
        this.produtoArvoreRepository = produtoArvoreRepository;
        this.produtoArvoreMapper = produtoArvoreMapper;
    }

    public ResultDTO validateDelete(String prodId, String subpId) {
        log.debug("Request to validate delete ProdutoArvore : {}", "prodId=" + prodId + " e subpId=" + subpId);
        ResultDTO message = new ResultDTO();
        
        ProdutoArvore result = produtoArvoreRepository.findOne(new ProdId(prodId, subpId));
        // tem que existir a entidade
        if (result == null) {
        	return ResultDTO.getValorNaoEncontradoResultError("prodId e subpId", "ProdutoArvore");
        }
        return message;
    }
    
    /**
     * Valida os dados para create/update da produtoArvore
     * @param listaRespostaDTO objeto a ser validado
     * @return ResultDTO com os erros encontrados ou instancia ResultDTO sem nenhum erro
     */
    @Transactional(readOnly = true)    
    public ResultDTO validateCreateProdutoArvore(ProdutoArvoreDTO produtoArvoreDTO) {
    	log.debug("Request to validate produtoArvoreDTO");
        ResultDTO message = new ResultDTO();
        
		if (StringUtils.isEmpty(produtoArvoreDTO.getCdSituProdArvore())) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("cdSituProdArvore", "");
		}
		if (StringUtils.isEmpty(produtoArvoreDTO.getInProdutoSemOferta())) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("inProdutoSemOferta", "");
		}
		
		// TODO add das validacoes de perg obritorias
        return message;
    }
    
    /**
     * Save a produtoArvore.
     *
     * @param produtoArvoreDTO the entity to save
     * @return the persisted entity
     */
    public ProdId save(ProdutoArvoreDTO produtoArvoreDTO ) {

    	log.debug("Request to save ArvoreDecisao : {}", produtoArvoreDTO);

    	ProdutoArvore produtoArvore = null;
    	    	
    			
    	produtoArvore = produtoArvoreRepository.getOne(new ProdId(produtoArvoreDTO.getProdId(), 
    			produtoArvoreDTO.getSubpId()));
    	
        // para atualizacao da arvore
        if (produtoArvoreDTO.getProdId() != null && produtoArvoreDTO.getSubpId() != null) {
        	
//        	produtoArvoreRepository.getOne(id)
        	
        	produtoArvore.setCdSituProdArvore(produtoArvoreDTO.getCdSituProdArvore());
        	produtoArvore.setInProdutoSemOferta(produtoArvoreDTO.getInProdutoSemOferta());
        	
        	// manutencao de perguntaObgs
            // map onde a chave eh PerguntaObgId e o value a PerguntaObg
            Map<PerguntaObgId, PerguntaObg> mapPerguntaObgByPerguntaObgId = produtoArvore.getPerguntaObgs().stream()
    				.collect(Collectors.toMap(PerguntaObg::getPk, perguntaObg -> perguntaObg));
            
        	if (produtoArvoreDTO.getPerguntaObgs() != null) {
        		for (PerguntaObgDTO perguntaObgDTO : produtoArvoreDTO.getPerguntaObgs()) {
        			
        			ProdId prodId = new ProdId(produtoArvoreDTO.getProdId(), produtoArvoreDTO.getSubpId());
        			
        			PerguntaObgId pk = new PerguntaObgId(perguntaObgDTO.getPerguntaId(), prodId);        			
        			if (perguntaObgDTO.getInserir()) {
        				// se NAO estiver no map, precisa ser inserido
        				if (mapPerguntaObgByPerguntaObgId.get(pk) == null) {
        					addPerguntaObg(produtoArvore, perguntaObgDTO);
						}
					// deletar a perguntaObg
        			} else {
        				PerguntaObg perguntaObg = new PerguntaObg();
        				perguntaObg.setPk(pk);
        				produtoArvore.removePerguntaObg(perguntaObg);
					}
        		}
            // para criar uma nova produtoArvore
        	}
        } else {
        	produtoArvore = produtoArvoreMapper.produtoArvoreDTOToProdutoArvore(produtoArvoreDTO);
        	produtoArvore = produtoArvoreRepository.saveAndFlush(produtoArvore);
        	
        	if (produtoArvoreDTO.getPerguntaObgs() != null) {
        		for (PerguntaObgDTO perguntaObgDTO : produtoArvoreDTO.getPerguntaObgs()) {
        			addPerguntaObg(produtoArvore, perguntaObgDTO);
        		}
			}
        }
        
        produtoArvore = produtoArvoreRepository.save(produtoArvore);
        return produtoArvore.getPk();
    }

    /**
     *  Get all the produtoArvores.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ProdutoArvoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProdutoArvores");
        Page<ProdutoArvore> result = produtoArvoreRepository.findAll(pageable);
        return result.map(produtoArvore -> produtoArvoreMapper.produtoArvoreToProdutoArvoreDTO(produtoArvore));
    }


    /**
     *  get all the produtoArvores where Prod is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ProdutoArvoreDTO> findAllWhereProdIsNull() {
        log.debug("Request to get all produtoArvores where Prod is null");
        return StreamSupport
            .stream(produtoArvoreRepository.findAll().spliterator(), false)
            .filter(produtoArvore -> produtoArvore.getProd() == null)
            .map(produtoArvoreMapper::produtoArvoreToProdutoArvoreDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one produtoArvore by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProdutoArvoreDTO findOne(String prodId, String subpId) {
        ProdutoArvore produtoArvore = produtoArvoreRepository.findOne(new ProdId(prodId, subpId));
        ProdutoArvoreDTO produtoArvoreDTO = produtoArvoreMapper.produtoArvoreToProdutoArvoreDTO(produtoArvore);
        return produtoArvoreDTO;
    }

    /**
     *  Delete the  produtoArvore by id.
     *
     *  @param id the id of the entity
     */
    public void delete(String prodId, String subpId) {
        log.debug("Request to delete ProdutoArvore : {}", "prodId=" + prodId + " e subpId=" + subpId);
        produtoArvoreRepository.delete(new ProdId(prodId, subpId));
    }
    
	private void addPerguntaObg(ProdutoArvore produtoArvore, PerguntaObgDTO perguntaObgDTO) {
		PerguntaObg perguntaObg = new PerguntaObg();
		
		Pergunta pergunta = new Pergunta();
		pergunta.setId(perguntaObgDTO.getPerguntaId());
		perguntaObg.setPergunta(pergunta);
		perguntaObg.setProdutoArvore(produtoArvore);
		
		PerguntaObgId pk = new PerguntaObgId(perguntaObgDTO.getPerguntaId(), 
				produtoArvore.getPk());
		perguntaObg.setPk(pk);
		
		produtoArvore.addPerguntaObg(perguntaObg);
	}
	
	  /**
     *  Consulta todas os ArvoreProdutos a partir da lista de canais
     *
     *  @param canalIds Lista dos Ids dos canais para filtrar os produtos
     *  @return Lista com os produtoArvore conforme o filtro de canal e produto ativo
     */
    @Transactional(readOnly = true) 
    public List<ProdutoArvoreDTO> findAllProdutoArvoreByListCanal(List<Long> canalIds) {
        log.debug("Request to get all canal ProdutoArvoreDTO : {canalIds}=" + canalIds);
        
        Set<ProdutoArvore> produtoArvoreResultList = new HashSet<ProdutoArvore>();
        List<ProdutoArvore> produtoArvoreList = null;
        if (canalIds != null && !canalIds.isEmpty()) {
        	for (Long canalId : canalIds) {
        		produtoArvoreList = 
        				produtoArvoreRepository.findAllProdutoArvoreByCanalId(canalId, 
        						ResultDTO.DTO_CONSTANTE_ATIVO,
        						ResultDTO.DTO_CONSTANTE_NAO);
        		
        		// caso a lista seja vazia, a interseccao sera vazia e nao precisa mais buscar os itens
        		if (produtoArvoreList == null || produtoArvoreList.isEmpty()) {
        			produtoArvoreResultList.clear();
        			break;
				} else {
					produtoArvoreResultList.addAll(produtoArvoreList);
					produtoArvoreResultList.retainAll(produtoArvoreList);
				}
			}
        
       	// caso nao passe nenhum canalId, sera retornado todos os produtos
		} else {
 			produtoArvoreResultList.addAll(produtoArvoreRepository.findAllProdutoArvore(
 					ResultDTO.DTO_CONSTANTE_ATIVO,
 					ResultDTO.DTO_CONSTANTE_NAO));
		}

    	// busca o produto sem oferta que nao tem canal associado
    	produtoArvoreResultList.addAll(produtoArvoreRepository.findProduto(ResultDTO.DTO_CONSTANTE_ATIVO, 
    																	   ResultDTO.DTO_CONSTANTE_SIM));

        List<ProdutoArvoreDTO> listProdutoArvoreDTO = produtoArvoreMapper
        		.produtoArvoresToProdutoArvoreDTOs(new ArrayList<ProdutoArvore>(produtoArvoreResultList));
        Collections.sort(listProdutoArvoreDTO);
        return listProdutoArvoreDTO;
        
    }
}
