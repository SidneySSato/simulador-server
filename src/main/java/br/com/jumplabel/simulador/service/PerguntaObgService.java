package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.PerguntaObg;
import br.com.jumplabel.simulador.repository.PerguntaObgRepository;
import br.com.jumplabel.simulador.service.dto.PerguntaObgDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.mapper.PerguntaObgMapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing PerguntaObg.
 */
@Service
@Transactional
public class PerguntaObgService {

    private final Logger log = LoggerFactory.getLogger(PerguntaObgService.class);
    
    private final PerguntaObgRepository perguntaObgRepository;

    private final PerguntaObgMapper perguntaObgMapper;

    public PerguntaObgService(PerguntaObgRepository perguntaObgRepository, PerguntaObgMapper perguntaObgMapper) {
        this.perguntaObgRepository = perguntaObgRepository;
        this.perguntaObgMapper = perguntaObgMapper;
    }

    /**
     * Save a perguntaObg.
     *
     * @param perguntaObgDTO the entity to save
     * @return the persisted entity
     */
    public PerguntaObgDTO save(PerguntaObgDTO perguntaObgDTO) {
        log.debug("Request to save PerguntaObg : {}", perguntaObgDTO);
        PerguntaObg perguntaObg = perguntaObgMapper.perguntaObgDTOToPerguntaObg(perguntaObgDTO);
        perguntaObg = perguntaObgRepository.save(perguntaObg);
        PerguntaObgDTO result = perguntaObgMapper.perguntaObgToPerguntaObgDTO(perguntaObg);
        return result;
    }

    /**
     *  Get all the perguntaObgs.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<PerguntaObgDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PerguntaObgs");
        Page<PerguntaObg> result = perguntaObgRepository.findAll(pageable);
        return result.map(perguntaObg -> perguntaObgMapper.perguntaObgToPerguntaObgDTO(perguntaObg));
    }

    /**
     *  Get one perguntaObg by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PerguntaObgDTO findOne(Long id) {
        log.debug("Request to get PerguntaObg : {}", id);
        PerguntaObg perguntaObg = perguntaObgRepository.findOne(id);
        PerguntaObgDTO perguntaObgDTO = perguntaObgMapper.perguntaObgToPerguntaObgDTO(perguntaObg);
        return perguntaObgDTO;
    }

    /**
     *  Delete the  perguntaObg by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PerguntaObg : {}", id);
        perguntaObgRepository.delete(id);
    }
    
    /**
     * Valida os dados para create/update da perguntaObg
     * @param listaRespostaDTO objeto a ser validado
     * @return ResultDTO com os erros encontrados ou instancia ResultDTO sem nenhum erro
     */
    @Transactional(readOnly = true)    
    public ResultDTO validateCreatePerguntaObg(PerguntaObgDTO perguntaObgDTO) {
    	log.debug("Request to validate perguntaObgDTO");
        ResultDTO message = new ResultDTO();
        
		if (StringUtils.isEmpty(perguntaObgDTO.getPerguntaId().toString())) {
			return ResultDTO.getValidacaoCampoObrigatorioResultError("perguntaId", "");
		}

        return message;
    }
}
