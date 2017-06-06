package br.com.jumplabel.simulador.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.ArvoreCanal;
import br.com.jumplabel.simulador.domain.ArvoreCanalId;
import br.com.jumplabel.simulador.domain.ArvoreDecisao;
import br.com.jumplabel.simulador.domain.ArvoreFamilia;
import br.com.jumplabel.simulador.domain.ArvoreFamiliaId;
import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.repository.ArvoreDecisaoRepository;
import br.com.jumplabel.simulador.repository.CntdDomiRepository;
import br.com.jumplabel.simulador.service.dto.ArvoreCanalDTO;
import br.com.jumplabel.simulador.service.dto.ArvoreDecisaoDTO;
import br.com.jumplabel.simulador.service.dto.ArvoreFamiliaDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.mapper.ArvoreDecisaoMapper;

/**
 * Service Implementation for managing ArvoreDecisao.
 */
@Service
@Transactional
public class ArvoreDecisaoService {

    private final Logger log = LoggerFactory.getLogger(ArvoreDecisaoService.class);
    
    private final ArvoreDecisaoRepository arvoreDecisaoRepository;

    private final ArvoreDecisaoMapper arvoreDecisaoMapper;
    
    private final CntdDomiRepository cntdDomiRepository;
    

    public ArvoreDecisaoService(ArvoreDecisaoRepository arvoreDecisaoRepository, ArvoreDecisaoMapper arvoreDecisaoMapper,
    		CntdDomiRepository cntdDomiRepository) {
        this.arvoreDecisaoRepository = arvoreDecisaoRepository;
        this.arvoreDecisaoMapper = arvoreDecisaoMapper;
        this.cntdDomiRepository = cntdDomiRepository;
    }

    
    public ResultDTO validateDelete(Long id) {
        log.debug("Request to validate delete arvoreDecisao : {}", "id=" + id);
        ResultDTO message = new ResultDTO();
        ArvoreDecisao result = arvoreDecisaoRepository.findOne(id);
        // tem que existir a entidade
        if (result == null) {
        	return ResultDTO.getValorNaoEncontradoResultError("id", "ArvoreDecisao");
        }
        return message;
    }
    /**
     * Valida os dados para create/update da arvoreDecisao
     * @param listaRespostaDTO objeto a ser validado
     * @return ResultDTO com os erros encontrados ou instancia ResultDTO sem nenhum erro
     */
    @Transactional(readOnly = true)    
    public ResultDTO validateCreateArvoreDecisao(ArvoreDecisaoDTO arvoreDecisaoDTO) {
    	log.debug("Request to validate arvoreDecisaoDTO");
        ResultDTO message = new ResultDTO();
        
		if (StringUtils.isEmpty(arvoreDecisaoDTO.getDsArvoreDecisao())) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("dsArvoreDecisao", "");
		}
		if (StringUtils.isEmpty(arvoreDecisaoDTO.getCdSituArvore())) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("cdSituArvore", "");
		}
		ResultDTO dto = ResultDTO.getValidacaoTipoCharAtivoInativo(StringUtils.upperCase(arvoreDecisaoDTO.getCdSituArvore()), "cdSituArvore");
		if (dto != null) {
			return dto;
		}

		if (arvoreDecisaoDTO.getQtProdutos() == null || arvoreDecisaoDTO.getQtProdutos() < 1) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("qtProdutos", "");
		}
		
		if (arvoreDecisaoDTO.getQtPlanos() == null || arvoreDecisaoDTO.getQtPlanos() < 1) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("qtPlanos", "");
		}
		
		ArvoreDecisao found = existsArvoreDecisaoDuplicadaByDescricao(arvoreDecisaoDTO.getId(), arvoreDecisaoDTO.getDsArvoreDecisao());
		if (found != null) {
			return ResultDTO.getValorRepetidoResultError("dsArvoreDecisao", "ArvoreDecisao", "Já existe uma ArvoreDecisao com id=" + found.getId() + " com o dsArvoreDecisao=" + found.getDsArvoreDecisao() +" repetido.");
		}
		
		// atualizacao da arvore
		if (arvoreDecisaoDTO.getId() != null) {
			ArvoreDecisao arvoreDecisao = arvoreDecisaoRepository.getOne(arvoreDecisaoDTO.getId());
			
			if (arvoreDecisaoDTO.getArvoreCanais() != null) {
				// valida se tem pelo menos um canal na lista apos delecao dos canais
				for (ArvoreCanalDTO arvoreCanalDTO : arvoreDecisaoDTO.getArvoreCanais()) {
					
					// se for apagar o canal da lista
					if (!arvoreCanalDTO.getInserir()) {
						ArvoreCanal arvoreCanal = new ArvoreCanal();
						arvoreCanal.setPk(new ArvoreCanalId(arvoreDecisaoDTO.getId(), arvoreCanalDTO.getCntdDomiId()));
		        		arvoreDecisao.removeArvoreCanal(arvoreCanal);							
					}
				}
				
				if (arvoreDecisao.getArvoreCanais().isEmpty()) {
					return ResultDTO.getValidacaoCampoListaObrigatorioResultError("", "arvoreCanais", "Precisa ter pelo menos 1 canal cadastrado na arvoreDecisao");
				}
			}
			
			if (arvoreDecisaoDTO.getArvoreFamilias() != null) {
				// valida se tem pelo menos uma arvore na lista apos delecao das familias
				for (ArvoreFamiliaDTO arvoreFamiliaDTO : arvoreDecisaoDTO.getArvoreFamilias()) {
					
					// se for apagar o canal da lista
					if (!arvoreFamiliaDTO.getInserir()) {
						ArvoreFamilia arvoreFamilia = new ArvoreFamilia();
						arvoreFamilia.setPk(new ArvoreFamiliaId(arvoreDecisaoDTO.getId(), arvoreFamiliaDTO.getCntdDomiId()));
						arvoreDecisao.removeArvoreFamilia(arvoreFamilia);							
					}
				}
				
				if (arvoreDecisao.getArvoreFamilias().isEmpty()) {
					return ResultDTO.getValidacaoCampoListaObrigatorioResultError("", "arvoreFamilias", "Precisa ter pelo menos 1 famillia cadastrada na arvoreDecisao");
				}
				
			}
			
		// nova arvore
		} else {

			if (arvoreDecisaoDTO.getArvoreCanais() == null || arvoreDecisaoDTO.getArvoreCanais().isEmpty()) {
				return ResultDTO.getValidacaoCampoListaObrigatorioResultError("", "arvoreCanais", "Precisa ter pelo menos 1 canal cadastrado na arvoreDecisao");
			}
			if (arvoreDecisaoDTO.getArvoreFamilias() == null || arvoreDecisaoDTO.getArvoreFamilias().isEmpty()) {
				return ResultDTO.getValidacaoCampoListaObrigatorioResultError("", "arvoreFamilias", "Precisa ter pelo menos 1 famillia cadastrada na arvoreDecisao");
			}
		}
		
		
		
		
		if (arvoreDecisaoDTO.getArvoreCanais() != null && !arvoreDecisaoDTO.getArvoreCanais().isEmpty()) {
			for (ArvoreCanalDTO dTO : arvoreDecisaoDTO.getArvoreCanais()) {
				if (dTO.getCntdDomiId() == null) {
					return ResultDTO.getValidacaoCampoObrigatorioResultError("cntdDomiId", "arvoreCanal");
				} else {
					if (!cntdDomiRepository.exists(dTO.getCntdDomiId())) {
						return ResultDTO.getValidacaoFKResultError("cntdDomi.id", "arvoreCanal.cntdDomiId=" + dTO.getCntdDomiId(), "arvoreCanal");
					}
				}
			}
		}
		if (arvoreDecisaoDTO.getArvoreFamilias() != null && !arvoreDecisaoDTO.getArvoreFamilias().isEmpty()) {
			for (ArvoreFamiliaDTO dTO : arvoreDecisaoDTO.getArvoreFamilias()) {
				if (dTO.getCntdDomiId() == null) {
					return ResultDTO.getValidacaoCampoObrigatorioResultError("cntdDomiId", "arvoreFamilia");
				} else {
					if (!cntdDomiRepository.exists(dTO.getCntdDomiId())) {
						return ResultDTO.getValidacaoFKResultError("cntdDomi.id", "arvoreFamilia.cntdDomiId=" + dTO.getCntdDomiId(), "arvoreFamilia");
					}
				}
			}
		}
        return message;
    }
    
    /**
     * Save a arvoreDecisao.
     *
     * @param arvoreDecisaoDTO the entity to save
     * @return id da arvoreDecisao
     */
    public Long save(ArvoreDecisaoDTO arvoreDecisaoDTO) {
        log.debug("Request to save ArvoreDecisao : {}", arvoreDecisaoDTO);

        ArvoreDecisao arvoreDecisao = null;
        
        // para atualizacao da arvore
        if (arvoreDecisaoDTO.getId() != null) {
        	arvoreDecisao = arvoreDecisaoRepository.getOne(arvoreDecisaoDTO.getId());
        	
        	arvoreDecisao.setDsArvoreDecisao(arvoreDecisaoDTO.getDsArvoreDecisao());
        	arvoreDecisao.setQtProdutos(arvoreDecisaoDTO.getQtProdutos());
        	arvoreDecisao.setQtPlanos(arvoreDecisaoDTO.getQtPlanos());
        	arvoreDecisao.setCdSituArvore(arvoreDecisaoDTO.getCdSituArvore());
        	
        	// manutencao de arvore familia
            // map onde a chave eh ArvoreFamiliaId e o value a ArvoreFamilia
            Map<ArvoreFamiliaId, ArvoreFamilia> mapArvoreFamiliaByArvoreFamiliaId = arvoreDecisao.getArvoreFamilias().stream()
    				.collect(Collectors.toMap(ArvoreFamilia::getPk, arvoreFamilia -> arvoreFamilia));
            
        	if (arvoreDecisaoDTO.getArvoreFamilias() != null) {
        		for (ArvoreFamiliaDTO arvoreFamiliaDTO : arvoreDecisaoDTO.getArvoreFamilias()) {
        			ArvoreFamiliaId pk = new ArvoreFamiliaId(arvoreDecisao.getId(), arvoreFamiliaDTO.getCntdDomiId());        			
        			if (arvoreFamiliaDTO.getInserir()) {
        				// se NAO estiver no map, precisa ser inserido
        				if (mapArvoreFamiliaByArvoreFamiliaId.get(pk) == null) {
        					addArvoreFamilia(arvoreDecisao, arvoreFamiliaDTO);
						}
					// deletar a arvoreFamilia
        			} else {
        				ArvoreFamilia arvoreFamilia = new ArvoreFamilia();
        				arvoreFamilia.setPk(pk);
        				arvoreDecisao.removeArvoreFamilia(arvoreFamilia);
					}
        		}
			}
        	
        	// manutencao de Arvore Canal
            // map onde a chave eh ArvoreCanalId e o value a ArvoreCanal
            Map<ArvoreCanalId, ArvoreCanal> mapArvoreCanalByArvoreCanalId = arvoreDecisao.getArvoreCanais().stream()
    				.collect(Collectors.toMap(ArvoreCanal::getPk, arvoreCanal -> arvoreCanal));
            
        	if (arvoreDecisaoDTO.getArvoreCanais() != null) {
        		for (ArvoreCanalDTO arvoreCanalDTO : arvoreDecisaoDTO.getArvoreCanais()) {
        			ArvoreCanalId pk = new ArvoreCanalId(arvoreDecisao.getId(), arvoreCanalDTO.getCntdDomiId());        			
        			if (arvoreCanalDTO.getInserir()) {
        				
        				// se NAO estiver no map, precisa ser inserido
        				if (mapArvoreCanalByArvoreCanalId.get(pk) == null) {
        					addArvoreCanal(arvoreDecisao, arvoreCanalDTO);
						}
					// deletar a arvoreCanal
        			} else {
        				ArvoreCanal arvoreCanal = new ArvoreCanal();
        				arvoreCanal.setPk(pk);
        				arvoreDecisao.removeArvoreCanal(arvoreCanal);
					}
        		}
			}
        
        // para criar uma nova arvoreDecisao
        } else {
        	arvoreDecisao = arvoreDecisaoMapper.arvoreDecisaoDTOToArvoreDecisao(arvoreDecisaoDTO);
        	arvoreDecisao = arvoreDecisaoRepository.saveAndFlush(arvoreDecisao);
        	
        	if (arvoreDecisaoDTO.getArvoreFamilias() != null) {
        		for (ArvoreFamiliaDTO arvoreFamiliaDTO : arvoreDecisaoDTO.getArvoreFamilias()) {
        			addArvoreFamilia(arvoreDecisao, arvoreFamiliaDTO);
        		}
			}
        	
        	if (arvoreDecisaoDTO.getArvoreCanais() != null) {
        		for (ArvoreCanalDTO arvoreCanalDTO : arvoreDecisaoDTO.getArvoreCanais()) {
        			addArvoreCanal(arvoreDecisao, arvoreCanalDTO);
				}
			}
        }
        
        arvoreDecisao = arvoreDecisaoRepository.save(arvoreDecisao);
        
        return arvoreDecisao.getId();
    }


	private void addArvoreCanal(ArvoreDecisao arvoreDecisao, ArvoreCanalDTO arvoreCanalDTO) {
		ArvoreCanal arvoreCanal = new ArvoreCanal();
		
		CntdDomi cntdDomi = new CntdDomi();
		cntdDomi.setId(arvoreCanalDTO.getCntdDomiId());
		arvoreCanal.setCntdDomi(cntdDomi);
		arvoreCanal.setArvoreDecisao(arvoreDecisao);
		
		ArvoreCanalId pk = new ArvoreCanalId(arvoreDecisao.getId(), arvoreCanalDTO.getCntdDomiId());        			
		
		arvoreCanal.setPk(pk);
		arvoreDecisao.addArvoreCanal(arvoreCanal);
	}


	private void addArvoreFamilia(ArvoreDecisao arvoreDecisao, ArvoreFamiliaDTO arvoreFamiliaDTO) {
		ArvoreFamilia arvoreFamilia = new ArvoreFamilia();
		
		CntdDomi cntdDomi = new CntdDomi();
		cntdDomi.setId(arvoreFamiliaDTO.getCntdDomiId());
		arvoreFamilia.setCntdDomi(cntdDomi);
		arvoreFamilia.setArvoreDecisao(arvoreDecisao);
		
		ArvoreFamiliaId pk = new ArvoreFamiliaId(arvoreDecisao.getId(), arvoreFamiliaDTO.getCntdDomiId());
		arvoreFamilia.setPk(pk);
		
		arvoreDecisao.addArvoreFamilia(arvoreFamilia);
	}

    /**
     *  Get all the arvoreDecisaos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ArvoreDecisaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ArvoreDecisaos");
        Page<ArvoreDecisao> result = arvoreDecisaoRepository.findAll(pageable);
        return result.map(arvoreDecisao -> arvoreDecisaoMapper.arvoreDecisaoToArvoreDecisaoDTO(arvoreDecisao));
    }

    /**
     *  Get one arvoreDecisao by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ArvoreDecisaoDTO findOne(Long id) {
        log.debug("Request to get ArvoreDecisao : {}", id);
        ArvoreDecisao arvoreDecisao = arvoreDecisaoRepository.findOne(id);
        ArvoreDecisaoDTO arvoreDecisaoDTO = arvoreDecisaoMapper.arvoreDecisaoToArvoreDecisaoDTO(arvoreDecisao);
        return arvoreDecisaoDTO;
    }

    /**
     *  Delete the  arvoreDecisao by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ArvoreDecisao : {}", id);
        arvoreDecisaoRepository.delete(id);
    }
    
    @Transactional(readOnly = true) 
    public ArvoreDecisao existsArvoreDecisaoDuplicadaByDescricao(Long id, String descricao) {
        log.debug("Request to existsArvoreDecisaoDuplicadaByDescricao: {}", descricao);
        ArvoreDecisao objeto = new ArvoreDecisao();
        objeto.setDsArvoreDecisao(StringUtils.trim(descricao));
        
        Example<ArvoreDecisao> example = Example.of(objeto);
        List<ArvoreDecisao> list = arvoreDecisaoRepository.findAll(example);

        // caso encontre o objeto pela descricao
        for (ArvoreDecisao found : list) {
        	// (valida se NAO é atualização do mesmo objeto) 
			if (!found.getId().equals(id)) {
				return found;
			}
        }
        return null;
    }
}
