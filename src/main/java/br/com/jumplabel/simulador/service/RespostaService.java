package br.com.jumplabel.simulador.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.RangeResposta;
import br.com.jumplabel.simulador.domain.RespCorporativa;
import br.com.jumplabel.simulador.domain.RespCorporativaId;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.domain.TipoResposta;
import br.com.jumplabel.simulador.repository.CntdDomiRepository;
import br.com.jumplabel.simulador.repository.PerguntaRepository;
import br.com.jumplabel.simulador.repository.RespCorporativaRepository;
import br.com.jumplabel.simulador.repository.RespostaRepository;
import br.com.jumplabel.simulador.repository.TipoRespostaRepository;
import br.com.jumplabel.simulador.service.dto.ListaRespostaDTO;
import br.com.jumplabel.simulador.service.dto.PerguntaDTO;
import br.com.jumplabel.simulador.service.dto.RangeRespostaDTO;
import br.com.jumplabel.simulador.service.dto.RespCorporativaDTO;
import br.com.jumplabel.simulador.service.dto.RespostaDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.mapper.RangeRespostaMapper;
import br.com.jumplabel.simulador.service.mapper.RespostaMapper;
import br.com.jumplabel.simulador.web.rest.errors.ErrorConstants;

/**
 * Service Implementation for managing Resposta.
 */
@Service
@Transactional
public class RespostaService {

    private final Logger log = LoggerFactory.getLogger(RespostaService.class);
    
    private final RespostaRepository respostaRepository;
    
    private final PerguntaRepository perguntaRepository;
    
    
    private final RespCorporativaRepository respCorporativaRepository;
    
    
    private final CntdDomiRepository cntdDomiRepository;

    private final RespostaMapper respostaMapper;
    
    private final RangeRespostaMapper rangeRespostaMapper;
    
    private final TipoRespostaRepository tipoRespostaRepository;
    
    public RespostaService(RespostaRepository respostaRepository, 
    		RespostaMapper respostaMapper,
    		PerguntaRepository perguntaRepository,
    		TipoRespostaRepository tipoRespostaRepository,
    		CntdDomiRepository cntdDomiRepository, 
    		RespCorporativaRepository respCorporativaRepository,
    		RangeRespostaMapper rangeRespostaMapper) {
    	
        this.respostaRepository = respostaRepository;
        this.respostaMapper = respostaMapper;
        this.perguntaRepository = perguntaRepository;
        this.tipoRespostaRepository = tipoRespostaRepository;
        this.cntdDomiRepository = cntdDomiRepository;
        this.respCorporativaRepository = respCorporativaRepository;
        this.rangeRespostaMapper = rangeRespostaMapper;
    }

    public ResultDTO validateDelete(Long respostaId) {
        log.debug("Request to validate delete Resposta : {}", "respostaId=" + respostaId);
        ResultDTO message = new ResultDTO();
        Resposta result = respostaRepository.findOne(respostaId);
        // tem que existir a entidade
        if (result == null) {
        	return ResultDTO.getValorNaoEncontradoResultError("id", "RespostaDTO");
        }
        return message;
    }

    public ResultDTO validateResposta(ListaRespostaDTO listaRespostaDTO) {

    	if (listaRespostaDTO.getPerguntaTipoRespostaId() != null) {
			if (listaRespostaDTO.getPerguntaTipoRespostaId().equals(PerguntaDTO.TIPO_RESPOSTA_INPUT_DADOS)) {
				return validateRespostaTipoInput(listaRespostaDTO);
			} else if (listaRespostaDTO.getPerguntaTipoRespostaId().equals(PerguntaDTO.TIPO_RESPOSTA_DADOS_CORPORATIVOS)) {
				return validateRespostaTipoDadosCorporativos(listaRespostaDTO);
			} else if (listaRespostaDTO.getPerguntaTipoRespostaId().equals(PerguntaDTO.TIPO_RESPOSTA_LISTA)) {
				return validateRespostaTipoLista(listaRespostaDTO);
			} else {
				return ResultDTO.getValidacaoResultError(ErrorConstants.ERR_VALIDATION, "perguntaTipoRespostaId precisa ser 1(TIPO_RESPOSTA_LISTA), 2(TIPO_RESPOSTA_INPUT_DADOS) ou 3(TIPO_RESPOSTA_DADOS_CORPORATIVOS)");
			}
    	} else {
    		return ResultDTO.getValidacaoCampoObrigatorioResultError("perguntaTipoRespostaId", "");
    	}
    }
    
    /**
     * Valida os dados para PerguntaDTO.TIPO_RESPOSTA_INPUT_DADOS e TIPO_RESPOSTA_INPUT_CATEGORIA_NUMERICO
     * @param listaRespostaDTO objeto a ser validado
     * @return ResultDTO com os erros encontrados ou instancia ResultDTO sem nenhum erro
     */
    private ResultDTO validateRespostaTipoInput(ListaRespostaDTO listaRespostaDTO) {
        log.debug("Request to validate merge Resposta Dados Corporativos: {}", "perguntaId=" + listaRespostaDTO.getPerguntaId());
        ResultDTO message = new ResultDTO();
        
		if (listaRespostaDTO.getPerguntaId() == null) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("perguntaId", "");
		}
		
		if (listaRespostaDTO.getRespostas() == null || listaRespostaDTO.getRespostas().isEmpty() ) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("respostas", "");
		}

		List<RangeRespostaDTO> listValidacaoRespostaRange = new ArrayList<RangeRespostaDTO>();
		Pergunta pergunta = null;
		// realiza a busca das ranges cadastradas pela pergunta
		if (RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_NUMERICO.equals(listaRespostaDTO.getRespostas().get(0).getDsCategoria()))  {
			pergunta = perguntaRepository.getOne(listaRespostaDTO.getPerguntaId());
			for (Resposta respostaValidacao: pergunta.getRespostas()) {
				if (respostaValidacao.getRangeResposta() == null) {
					return ResultDTO.getValidacaoResultError(ErrorConstants.ERR_VALIDATION, "nao foi possivel obter o rangeResposta da "
							+ "resposta com id=" + respostaValidacao.getId() + ", verificar consistencia de banco de dados");
				}
        		listValidacaoRespostaRange.add(rangeRespostaMapper.rangeRespostaToRangeRespostaDTO(respostaValidacao.getRangeResposta()));
        	}
		} else if(RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_TODOS.
				equals(listaRespostaDTO.getRespostas().get(0).getDsCategoria())) {
			pergunta = perguntaRepository.getOne(listaRespostaDTO.getPerguntaId());
		}
		
		// realiza a validacao das respostas por dsCategoria
		int listaRespostaDTOSize = listaRespostaDTO.getRespostas().size();
		for (RespostaDTO respostaDTO : listaRespostaDTO.getRespostas()) {
			if (StringUtils.isEmpty(respostaDTO.getDsCategoria())) {
				return ResultDTO.getValidacaoCampoObrigatorioResultError("dsCategoria", "respostas");
			}

			if (RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_NUMERICO.equals(respostaDTO.getDsCategoria())) {
				if (respostaDTO.getInserir()) {
					
					if (respostaDTO.getRangeResposta() == null) {
						return ResultDTO.getValidacaoCampoObrigatorioResultError("rangeResposta", "respostas");
					}

					if (respostaDTO.getRangeResposta().getRangeInicio() == null) {
						return ResultDTO.getValidacaoCampoObrigatorioResultError("rangeInicio", "resposta.rangeResposta");
					}

					// se for uma nova resposta valida se nao tem overlap
					if (respostaDTO.getId() == null) {
						listValidacaoRespostaRange.add(respostaDTO.getRangeResposta());
		        		// true - caso nao exista overlap e false - caso exista overlap
		        		if (!RangeRespostaDTO.isNonoverlapping(listValidacaoRespostaRange)) {
		        			return ResultDTO.getValidacaoOverlapRangeError(respostaDTO.getRangeResposta().getRangeInicio(), 
		        					respostaDTO.getRangeResposta().getRangeFinal(), "RangeRespostaDTO");
		        		}
					}
				} else {
					// TODO validar quando for deletar contra o no da arvore
					
				}

			} else if (RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_ESPECIFICO.equals(respostaDTO.getDsCategoria())) {
				if (respostaDTO.getDsResposta() == null) {
					return ResultDTO.getValidacaoCampoObrigatorioResultError("dsResposta", "respostas");
				}
			} else if (RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_TODOS.equals(respostaDTO.getDsCategoria())) {
				
				if (respostaDTO.getInserir() && pergunta.getRespostas() != null && pergunta.getRespostas().size() > 0) {
					return ResultDTO.getValidacaoResultError(ErrorConstants.VAL_VALIDACAO_TAMANHO_LISTA, "Operacao nao executada, pois ja tem cadastrado na base 1 resposta para essa pergunta e resposta.dsCategoria=TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_TODOS");	
				}
				
				if (listaRespostaDTOSize > 1) {
					return ResultDTO.getValidacaoResultError(ErrorConstants.VAL_VALIDACAO_TAMANHO_LISTA, "Operacao nao executada, pois a lista de respostas deve ter somente 1 resposta");
				}
			} 
			else {
				return ResultDTO.getValidacaoResultError(ErrorConstants.ERR_VALIDATION, "dsCategoria precisa ser NUMERICO, ALPHANUMERICO_TODOS ou ALPHANUMERICO_ESPECIFICO");
			}
		}
        return message;
    }
    
    /**
     * Valida os dados para PerguntaDTO.TIPO_RESPOSTA_DADOS_CORPORATIVOS
     * @param listaRespostaDTO objeto a ser validado
     * @return ResultDTO com os erros encontrados ou instancia ResultDTO sem nenhum erro
     */
    private ResultDTO validateRespostaTipoDadosCorporativos(ListaRespostaDTO listaRespostaDTO) {
    	
        log.debug("Request to validate merge Resposta Dados Corporativos: {}", "perguntaId=" + listaRespostaDTO.getPerguntaId());
        ResultDTO message = new ResultDTO();
        
		if (listaRespostaDTO.getPerguntaId() == null) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("perguntaId", "");
		}

		if (StringUtils.isEmpty(listaRespostaDTO.getPerguntaInEditavel())) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("perguntaInEditavel", "");
		}
		
		if (listaRespostaDTO.getRespostas() == null || listaRespostaDTO.getRespostas().isEmpty() ) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("respostas", "");
		}
		
		
		ResultDTO dto = ResultDTO.getValidacaoTipoCharSimNao(StringUtils.upperCase(listaRespostaDTO.getPerguntaInEditavel()), "perguntaInEditavel");
		if (dto != null) {
			return dto;
		}

		for (RespostaDTO respostaDTO : listaRespostaDTO.getRespostas()) {
			
			if (respostaDTO.getInserir()) {
				if (StringUtils.isEmpty(respostaDTO.getDsResposta())) {
					return ResultDTO.getValidacaoCampoObrigatorioResultError("dsResposta", "respostas");
				}
				
				if (respostaDTO.getRespCorporativas() == null || respostaDTO.getRespCorporativas().isEmpty() ) {
					return ResultDTO.getValidacaoCampoObrigatorioResultError("respCorporativas", "respostas");
				} else {
				
					for (RespCorporativaDTO respCorporativa : respostaDTO.getRespCorporativas()) {
						if (respCorporativa.getCntdDomiId() == null) {
							return ResultDTO.getValidacaoCampoObrigatorioResultError("cntdDomiId", "respCorporativa");
						} else {
						
							if (!cntdDomiRepository.exists(respCorporativa.getCntdDomiId())) {
								return ResultDTO.getValidacaoFKResultError("cntdDomi.id", "respCorporativa.cntdDomiId=" + respCorporativa.getCntdDomiId(),"respCorporativa");
							}
						}
					}
				}
			} else {
				// TODO adicionar validacao para deletar
			}
		}
        return message;
    }
    
    private ResultDTO validateRespostaTipoLista(ListaRespostaDTO listaRespostaDTO) {
    	log.debug("Request to validate merge Resposta Lista: {}", "perguntaId=" + listaRespostaDTO.getPerguntaId());
        ResultDTO message = new ResultDTO();
        
		if (listaRespostaDTO.getPerguntaId() == null) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("perguntaId", "");
		}
		
		if (listaRespostaDTO.getRespostas() == null || listaRespostaDTO.getRespostas().isEmpty() ) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("respostas", "");
		}

		// realiza a validacao das respostas por dsCategoria
		for (RespostaDTO respostaDTO : listaRespostaDTO.getRespostas()) {
			
			if (respostaDTO.getInserir()) {
				if (StringUtils.isEmpty(respostaDTO.getDsResposta())) {
					return ResultDTO.getValidacaoCampoObrigatorioResultError("dsResposta", "respostas");
				}
	
				RespostaDTO respostaFound = findOne(listaRespostaDTO.getPerguntaId(), respostaDTO.getDsResposta());
		        
				if (respostaFound != null) {
					return ResultDTO.getValorRepetidoResultError("dsResposta", "resposta", 
							"Ja existe uma Resposta com dsResposta=" + respostaFound.getDsResposta() + " e com idResposta= " 
									+ respostaFound.getId() +" para a pergunta com perguntaId=" + listaRespostaDTO.getPerguntaId());
				}
			} else {
				// TODO adicionar validacao para deletar
			}
		}
        
        return message;
    }
    
    /**
     * Realiza o merge da Resposta
     * @param listaRespostaDTO
     * @return id da Pergunta pos merge
     */
    public Long saveResposta(ListaRespostaDTO listaRespostaDTO) {
    	if (listaRespostaDTO.getPerguntaTipoRespostaId() != null) {
    		
            // salva os dados basicos da pergunta
            Pergunta pergunta = perguntaRepository.getOne(listaRespostaDTO.getPerguntaId());
            pergunta.setInEditavel(listaRespostaDTO.getPerguntaInEditavel());
            
            TipoResposta tipoResposta = tipoRespostaRepository.getOne(listaRespostaDTO.getPerguntaTipoRespostaId());
            pergunta.setTipoResposta(tipoResposta);
            
            pergunta = perguntaRepository.saveAndFlush(pergunta);
    		
			if (listaRespostaDTO.getPerguntaTipoRespostaId().equals(PerguntaDTO.TIPO_RESPOSTA_INPUT_DADOS)) {
				return saveRespostaTipoInput(listaRespostaDTO, pergunta);
			} else if (listaRespostaDTO.getPerguntaTipoRespostaId().equals(PerguntaDTO.TIPO_RESPOSTA_DADOS_CORPORATIVOS)) {
				return saveRespostaTipoDadosCorporativos(listaRespostaDTO, pergunta);
			} else if (listaRespostaDTO.getPerguntaTipoRespostaId().equals(PerguntaDTO.TIPO_RESPOSTA_LISTA)) {
				return saveRespostaTipoLista(listaRespostaDTO, pergunta);
			} else {
				throw new RuntimeException("perguntaTipoRespostaId precisa ser 1(TIPO_RESPOSTA_LISTA), 2(TIPO_RESPOSTA_INPUT_DADOS) ou 3(TIPO_RESPOSTA_DADOS_CORPORATIVOS)");
			}
    	} else {
    		throw new RuntimeException("Operacao nao executada, pois ocorreu erro de validacao de campo obrigatorio, perguntaTipoRespostaId");
    	}
    }
    
    
    /**
     * Save a resposta.
     *
     * @param respostaDTO the entity to save
     * @return the persisted entity
     */
    private Long saveRespostaTipoLista(ListaRespostaDTO listaRespostaDTO, Pergunta pergunta) {
    	
        log.debug("Request to save Resposta Tipo Lista : {}", listaRespostaDTO);
        
        // map onde a chave eh idResposta e o value a resposta
        Map<Long, Resposta> mapRespostaByIdResposta = pergunta.getRespostas().stream()
				.collect(Collectors.toMap(Resposta::getId, resposta -> resposta));
        
       	Resposta resposta = null;
    	for (RespostaDTO respostaDTO : listaRespostaDTO.getRespostas()) {
    		
            // verifica se eh para inserir na base o registro
        	if (respostaDTO.getInserir()) {
        		// se nao existir na base, cria a resposta 
            	if (!(mapRespostaByIdResposta.get(respostaDTO.getId()) != null)) {
            		
					resposta = new Resposta();
					resposta.setDsResposta(StringUtils.trim(respostaDTO.getDsResposta()));
					resposta.setPergunta(pergunta);
					resposta = respostaRepository.saveAndFlush(resposta);
					
            	// quando encontrar a resposta, a resposta eh atualizado somente para TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_ESPECIFICO		
            	} else {
        			resposta = mapRespostaByIdResposta.get(respostaDTO.getId());
        			resposta.setDsResposta(StringUtils.trim(respostaDTO.getDsResposta()));
					resposta = respostaRepository.saveAndFlush(resposta);
            	}
        		
        	} else {
        		// se o id existir é pq ja existe na base e precisa ser deletado
        		if (respostaDTO.getId() != null) {
                	// apaga a resposta
        			respostaRepository.delete(respostaDTO.getId());
				}
        	}
		}
        return listaRespostaDTO.getPerguntaId();
    }

    /**
     * Realiza o merge dos dados de dados corporativos
     * @param listaRespostaDTO
     * @return
     */
    private Long saveRespostaTipoDadosCorporativos(ListaRespostaDTO listaRespostaDTO, Pergunta pergunta) {
    	log.debug("Request to save Resposta Tipo Dados Corporativos : {}", listaRespostaDTO);

        
     // map onde a chave eh dsResposta e o value resposta
        Map<String, Resposta> mapRespostaByDsResposta = pergunta.getRespostas().stream()
				.collect(Collectors.toMap(Resposta::getDsResposta, resposta -> resposta));
        
        // map onde a chave eh dsResposta e o value a lista de respostas relativas
		Map<String, List<RespostaDTO>> mapListRespostaDTOByDsResposta = listaRespostaDTO.getRespostas().stream()
				.collect(Collectors.groupingBy(RespostaDTO::getDsResposta));

    	// itera para obter as respostas agrupadas por dsResposta
    	for (Map.Entry<String, List<RespostaDTO>> entry : mapListRespostaDTOByDsResposta.entrySet()) {
        	
        	Resposta resposta = null;
        	List<RespCorporativa> listRespCorporativaInsert = new ArrayList<RespCorporativa>();
        	// itera para obter as respostas agrupadas por dsResposta
        	for (RespostaDTO respostaDTO : entry.getValue()) {
        		
	            // verifica se eh para inserir na base o registro
            	if (respostaDTO.getInserir()) {
	        		
	        		// verifica se ja existe na base a resposta, 
	        		// pre requisito: nao pode existir dsResposta  repetida para a mesma perguntaId
	            	if (mapRespostaByDsResposta.get(respostaDTO.getDsResposta()) != null) {
	            		
	            		resposta = mapRespostaByDsResposta.get(respostaDTO.getDsResposta());
	            		
	            		resposta.setDsResposta(StringUtils.trim(respostaDTO.getDsResposta()));
	            	// quando a resposta nao existe na base, sera criado a resposta
	    			} else {
	    				// se tem uma instancia de resposta, eh pq ja existe na base
	    				if (resposta == null) {
	    					resposta = new Resposta();
	    					resposta.setDsResposta(StringUtils.trim(respostaDTO.getDsResposta()));
	    				}
	    			}
	            	
	        		for (RespCorporativaDTO respCorporativaDTO : respostaDTO.getRespCorporativas()) {
	        			// adiciona na lista para inserir
	        			RespCorporativa resp = new RespCorporativa();
	        			
	        			CntdDomi cntdDomi = cntdDomiRepository.getOne(respCorporativaDTO.getCntdDomiId());
	        			resp.setCntdDomi(cntdDomi);
	        			
	        			RespCorporativaId pk = new RespCorporativaId();
	        			pk.setCntdDomiId(respCorporativaDTO.getCntdDomiId());
	        			resp.setPk(pk);
	        			
	        			listRespCorporativaInsert.add(resp);
					}
            	} else {
            		// se o id existir é pq ja existe na base e precisa ser deletado
            		if (respostaDTO.getId() != null) {
                    	// apaga o relacionamento respCorporativa ou nao insere a resposta	
                		for (RespCorporativaDTO respCorporativaDTO : respostaDTO.getRespCorporativas()) {
            			
                			RespCorporativaId pk = new RespCorporativaId(respostaDTO.getId(), 
                					respCorporativaDTO.getCntdDomiId());
                			respCorporativaRepository.delete(pk);
                		}
					}
            	}
			}
        	
    		// insere a resposta
    		if (resposta != null) {
    			resposta.setPergunta(pergunta);        		
    			resposta.getRespCorporativas().clear();
    			resposta = respostaRepository.saveAndFlush(resposta);
    			
    			for (RespCorporativa respCorporativa : listRespCorporativaInsert) {
    				respCorporativa.setResposta(resposta);
    				respCorporativa.getPk().setRespostaId(resposta.getId());
    				resposta.addRespCorporativa(respCorporativa);
    			}
    			resposta = respostaRepository.saveAndFlush(resposta);
    		}
    	}

    	// limpa as respostas que ficaram sem respCorporativas
    	pergunta = perguntaRepository.getOne(listaRespostaDTO.getPerguntaId());
    	for (Resposta resposta : pergunta.getRespostas()) {
    		if (resposta.getRespCorporativas().isEmpty()) {
				respostaRepository.delete(resposta.getId());
			}
		}
        return listaRespostaDTO.getPerguntaId();
    }
    
    
    /**
     * Realiza o merge dos range de respostas (tipo resposta Input - numerico)
     * @param listaRespostaDTO
     * @return
     */
    private Long saveRespostaTipoInput(ListaRespostaDTO listaRespostaDTO, Pergunta pergunta) {
    	log.debug("Request to save Resposta Tipo Input : {}", listaRespostaDTO);
        
        // map onde a chave eh idResposta e o value a resposta
        Map<Long, Resposta> mapRespostaByIdResposta = pergunta.getRespostas().stream()
				.collect(Collectors.toMap(Resposta::getId, resposta -> resposta));
        
       	Resposta resposta = null;
    	for (RespostaDTO respostaDTO : listaRespostaDTO.getRespostas()) {
    		
            // verifica se eh para inserir na base o registro
        	if (respostaDTO.getInserir()) {
        		// se nao existir na base, cria a resposta 
            	if (!(mapRespostaByIdResposta.get(respostaDTO.getId()) != null)) {
            		
					resposta = new Resposta();
					resposta.setDsResposta(StringUtils.trim(respostaDTO.getDsResposta()));
					resposta.setDsCategoria(StringUtils.trim(respostaDTO.getDsCategoria()));
					resposta.setPergunta(pergunta);
					
					// para range é criado os registros na tabela de range
					if (RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_NUMERICO.equals(respostaDTO.getDsCategoria())) {
						RangeResposta rangeResposta = new RangeResposta();
						rangeResposta.setRangeInicio(respostaDTO.getRangeResposta().getRangeInicio());
						rangeResposta.setRangeFinal(respostaDTO.getRangeResposta().getRangeFinal());
						rangeResposta.setResposta(resposta);					
						resposta.setRangeResposta(rangeResposta);
					} else if (RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_TODOS.equals(respostaDTO.getDsCategoria())) {
						
						// seta o valor default para quando for todos
						resposta.setDsResposta(RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_TODOS_VALOR_DEFAULT);
					}
					
					resposta = respostaRepository.saveAndFlush(resposta);
					
            	// quando encontrar a resposta, a resposta eh atualizado somente para TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_ESPECIFICO		
            	} else {

            		if (RespostaDTO.TIPO_RESPOSTA_INPUT_CATEGORIA_ALPHANUMERICO_ESPECIFICO.equals(respostaDTO.getDsCategoria())) {
            			resposta = mapRespostaByIdResposta.get(respostaDTO.getId());
            			resposta.setDsResposta(StringUtils.trim(respostaDTO.getDsResposta()));
            			resposta = respostaRepository.saveAndFlush(resposta);
            		}
            		
            	}
        		
        	} else {
        		// se o id existir é pq ja existe na base e precisa ser deletado
        		if (respostaDTO.getId() != null) {
                	// apaga a resposta
        			respostaRepository.delete(respostaDTO.getId());
				}
        	}
		}
        return listaRespostaDTO.getPerguntaId();
    }
    
    
    /**
     *  Get all the respostas.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<RespostaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Respostas");
        Page<Resposta> result = respostaRepository.findAll(pageable);
        return result.map(resposta -> respostaMapper.respostaToRespostaDTO(resposta));
    }

    /**
     *  Get one resposta by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public RespostaDTO findOne(Long id) {
        log.debug("Request to get Resposta : {}", id);
        Resposta resposta = respostaRepository.findOne(id);
        RespostaDTO respostaDTO = respostaMapper.respostaToRespostaDTO(resposta);
        return respostaDTO;
    }

    /**
     *  Get one resposta by idPergunta e dsResposta.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public RespostaDTO findOne(Long perguntaId, String dsResposta) {
        log.debug("Request to get Resposta : {}", "perguntaId=" + perguntaId + "dsResposta=" + dsResposta);
        List<Resposta> respostas = respostaRepository.findByPerguntaIdRespostaDs(perguntaId, dsResposta);
        
        RespostaDTO respostaDTO = null;
        if (!respostas.isEmpty()) {
        	respostaDTO = respostaMapper.respostaToRespostaDTO(respostas.get(0));
		}
        
        return respostaDTO;
    }

     /**
     *  Delete the  resposta by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Resposta : {}", id);
        respostaRepository.delete(id);
    }
    
	public RespostaDTO map(Resposta resposta, Pergunta pergunta) {
        if ( resposta == null || pergunta == null) {
            return null;
        }
        RespostaDTO respostaDTO = new RespostaDTO();
        respostaDTO.setId(resposta.getId());
        
        if (PerguntaDTO.TIPO_RESPOSTA_INPUT_DADOS.equals(pergunta.getTipoResposta())) {
        	respostaDTO.setDsResposta(resposta.getDsResposta());
		} else if (PerguntaDTO.TIPO_RESPOSTA_LISTA.equals(pergunta.getTipoResposta())) {
			respostaDTO.setDsResposta(resposta.getDsResposta());
		} else if (PerguntaDTO.TIPO_RESPOSTA_DADOS_CORPORATIVOS.equals(pergunta.getTipoResposta())) {
			respostaDTO.setDsResposta(resposta.getDsResposta());
		} else {
			throw new RuntimeException("PerguntaDTO.TIPO_RESPOSTA, valor invalido=" + pergunta.getTipoResposta());
		}
        
        
        
        return respostaDTO;
    }
}
