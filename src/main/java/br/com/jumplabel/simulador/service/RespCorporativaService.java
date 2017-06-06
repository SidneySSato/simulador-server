package br.com.jumplabel.simulador.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.RespCorporativa;
import br.com.jumplabel.simulador.domain.RespCorporativaId;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.repository.RespCorporativaRepository;
import br.com.jumplabel.simulador.repository.RespostaRepository;
import br.com.jumplabel.simulador.service.dto.RespCorporativaDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.mapper.RespCorporativaMapper;

/**
 * Service Implementation for managing RespCorporativa.
 */
@Service
@Transactional
public class RespCorporativaService {

    private final Logger log = LoggerFactory.getLogger(RespCorporativaService.class);
    
    private final RespCorporativaRepository respCorporativaRepository;

    private final RespCorporativaMapper respCorporativaMapper;
    
    private final RespostaRepository respostaRepository;

    public RespCorporativaService(RespCorporativaRepository respCorporativaRepository, RespCorporativaMapper respCorporativaMapper,
    		RespostaRepository respostaRepository) {
        this.respCorporativaRepository = respCorporativaRepository;
        this.respCorporativaMapper = respCorporativaMapper;
        this.respostaRepository = respostaRepository;
    }
    
    public ResultDTO validate(RespCorporativaDTO dto) {
        log.debug("Request to validate RangeRespostaDTO : {}", dto);
        ResultDTO message = new ResultDTO();
     
		// TODO realizar valicao de range
        return message;
    }
    
    public ResultDTO validateDelete(RespCorporativaDTO dto) {
        log.debug("Request to validate RespCorporativaDTO : {}", dto);
        ResultDTO message = new ResultDTO();
        return message;
    }
    
//    public ListaRespostaDTO findListaRespostaDTOByParameters(Long perguntaId) {
//        Resposta resposta = new Resposta();
//        resposta.setPergunta(new Pergunta(perguntaId));
//        
//        Example<Resposta> exampleResposta = Example.of(resposta);
//		List<Resposta> list = respostaRepository.findAll(exampleResposta);
//    }
    
    public Map<String, Resposta> findMapByDsResposta(Long perguntaId) {
    	
        Resposta resposta = new Resposta();
        resposta.setPergunta(new Pergunta(perguntaId));

        // busca as respostas pelo perguntaId
        Example<Resposta> exampleResposta = Example.of(resposta);
		List<Resposta> listRespostasByPerguntaId = respostaRepository.findAll(exampleResposta);
		
		Map<String, Resposta> map = listRespostasByPerguntaId.stream()
			    .collect(Collectors.toMap(Resposta::getDsResposta, Function.identity()));
		
		return map;
    }
    
    /**
     * Save a respCorporativa.
     *
     * @param respCorporativaDTO the entity to save
     * @return the persisted entity
     */
    public RespCorporativaDTO save(RespCorporativaDTO respCorporativaDTO) {
        log.debug("Request to save RespCorporativa : {}", respCorporativaDTO);
        RespCorporativa respCorporativa = respCorporativaMapper.respCorporativaDTOToRespCorporativa(respCorporativaDTO);
        respCorporativa = respCorporativaRepository.save(respCorporativa);
        RespCorporativaDTO result = respCorporativaMapper.respCorporativaToRespCorporativaDTO(respCorporativa);
        return result;
    }

    /**
     *  Get all the respCorporativas.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<RespCorporativaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RespCorporativas");
        Page<RespCorporativa> result = respCorporativaRepository.findAll(pageable);
        return result.map(respCorporativa -> respCorporativaMapper.respCorporativaToRespCorporativaDTO(respCorporativa));
    }

    /**
     *  Get one respCorporativa by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public RespCorporativaDTO findOne(RespCorporativaId id) {
        log.debug("Request to get RespCorporativa : {}", id);
        RespCorporativa respCorporativa = respCorporativaRepository.findOne(id);
        RespCorporativaDTO respCorporativaDTO = respCorporativaMapper.respCorporativaToRespCorporativaDTO(respCorporativa);
        return respCorporativaDTO;
    }

    /**
     *  Delete the  respCorporativa by id.
     *
     *  @param id the id of the entity
     */
    public void delete(RespCorporativaId id) {
        log.debug("Request to delete RespCorporativa : {}", id);
        respCorporativaRepository.delete(id);
    }
}
