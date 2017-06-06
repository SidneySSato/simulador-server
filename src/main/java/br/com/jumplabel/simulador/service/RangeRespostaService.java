package br.com.jumplabel.simulador.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.RangeResposta;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.repository.RangeRespostaRepository;
import br.com.jumplabel.simulador.repository.RespostaRepository;
import br.com.jumplabel.simulador.service.dto.RangeRespostaDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.mapper.RangeRespostaMapper;

/**
 * Service Implementation for managing RangeResposta.
 */
@Service
@Transactional
public class RangeRespostaService {

    private final Logger log = LoggerFactory.getLogger(RangeRespostaService.class);
    
    private final RangeRespostaRepository rangeRespostaRepository;
    
    private final RespostaRepository respostaRepository;

    private final RangeRespostaMapper rangeRespostaMapper;

    public RangeRespostaService(RangeRespostaRepository rangeRespostaRepository, RangeRespostaMapper rangeRespostaMapper,
    		RespostaRepository respostaRepository) {
        this.rangeRespostaRepository = rangeRespostaRepository;
        this.rangeRespostaMapper = rangeRespostaMapper;
        this.respostaRepository = respostaRepository;
    }

    @Transactional(readOnly = true) 
    public ResultDTO validate(List<RangeRespostaDTO> listDto) {
        log.debug("Request to validate RangeRespostaDTO : {}", listDto);
        ResultDTO message = new ResultDTO();
     
    	Long respostaId = null;
        if (listDto != null && !listDto.isEmpty()) {
        	respostaId = listDto.get(0).getRespostaId();
		}
        
        List<RangeRespostaDTO> listValidacao = new ArrayList<RangeRespostaDTO>();		

        // valida os ranges que ja estao cadastrados no banco
        if (respostaId != null) {
        	// valida se existe resposta cadastrada para o primeiro range passado na lista
        	Resposta respostaFound = respostaRepository.getOne(respostaId);       
        	if (respostaFound == null) {
        		return ResultDTO.getValidacaoFKResultError("rangeResposta.respostaId", "resposta.id", "RangeRespostaDTO");
        	}
        	List<RangeRespostaDTO> rangeRespostaDTOs = 
        			rangeRespostaMapper.rangeRespostasToRangeRespostaDTOs(findAllByRespostaId(respostaId));
			
        	for (RangeRespostaDTO rangeRespostaDTO : rangeRespostaDTOs) {
        		listValidacao.add(rangeRespostaDTO);
        		// true - caso nao exista overlap e false - caso exista overlap
        		if (!RangeRespostaDTO.isNonoverlapping(listValidacao)) {
        			return ResultDTO.getValidacaoOverlapRangeError(rangeRespostaDTO.getRangeInicio(), 
        					rangeRespostaDTO.getRangeFinal(), "RangeRespostaDTO");
        		}
        	}
		}
		
		// valida os ranges recebidos para cadastro
        int size = listDto.size() - 1;
        
			
		for (int i = 0; i < listDto.size(); i++) {
				RangeRespostaDTO rangeRespostaDTO = listDto.get(i);
			
			// se for o ultimo elemento, o rangeFinal pode ser null
			if (i == size) {
				if (rangeRespostaDTO.getRangeInicio() == null) {
					return ResultDTO.getValidacaoCampoObrigatorioResultError("rangeInicio", "RangeRespostaDTO");
				}
			} else {
				if (rangeRespostaDTO.getRangeInicio() == null) {
					return ResultDTO.getValidacaoCampoObrigatorioResultError("rangeInicio", "RangeRespostaDTO");
				}
				if (rangeRespostaDTO.getRangeFinal() == null) {
					return ResultDTO.getValidacaoCampoObrigatorioResultError("rangeFinal", "RangeRespostaDTO");
				}
			}
				
			listValidacao.add(rangeRespostaDTO);
			// true - caso nao exista overlap e false - caso exista overlap
			if (!RangeRespostaDTO.isNonoverlapping(listValidacao)) {
				return ResultDTO.getValidacaoOverlapRangeError(rangeRespostaDTO.getRangeInicio(), 
						rangeRespostaDTO.getRangeFinal(), "RangeRespostaDTO");
			}
		}
		
        return message;
    }
    
    @Transactional(readOnly = true) 
    public List<RangeResposta> findAllByRespostaId(Long respostaId) {
    	RangeResposta rangeResposta = new RangeResposta();
    	Resposta resposta = new Resposta(respostaId);
        rangeResposta.setResposta(resposta);
        
        Example<RangeResposta> example = Example.of(rangeResposta);
		
		return rangeRespostaRepository.findAll(example);   	
    }
    
    public ResultDTO validateDelete(RangeRespostaDTO dto) {
        log.debug("Request to validate RangeRespostaDTO : {}", dto);
        ResultDTO message = new ResultDTO();
        return message;
    }
    
    
    /**
     * Save a rangeResposta.
     *
     * @param rangeRespostaDTO the entity to save
     * @return the persisted entity
     */
    public RangeRespostaDTO save(RangeRespostaDTO rangeRespostaDTO) {
        log.debug("Request to save RangeResposta : {}", rangeRespostaDTO);
        RangeResposta rangeResposta = rangeRespostaMapper.rangeRespostaDTOToRangeResposta(rangeRespostaDTO);
        rangeResposta = rangeRespostaRepository.save(rangeResposta);
        RangeRespostaDTO result = rangeRespostaMapper.rangeRespostaToRangeRespostaDTO(rangeResposta);
        return result;
    }

    /**
     *  Get all the rangeRespostas.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<RangeRespostaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RangeRespostas");
        Page<RangeResposta> result = rangeRespostaRepository.findAll(pageable);
        return result.map(rangeResposta -> rangeRespostaMapper.rangeRespostaToRangeRespostaDTO(rangeResposta));
    }

    /**
     *  Get one rangeResposta by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public RangeRespostaDTO findOne(Long id) {
        log.debug("Request to get RangeResposta : {}", id);
        RangeResposta rangeResposta = rangeRespostaRepository.findOne(id);
        RangeRespostaDTO rangeRespostaDTO = rangeRespostaMapper.rangeRespostaToRangeRespostaDTO(rangeResposta);
        return rangeRespostaDTO;
    }

    /**
     *  Delete the  rangeResposta by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete RangeResposta : {}", id);
        rangeRespostaRepository.delete(id);
    }
}
