package br.com.jumplabel.simulador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.TipoResposta;
import br.com.jumplabel.simulador.repository.TipoRespostaRepository;
import br.com.jumplabel.simulador.service.dto.TipoRespostaDTO;
import br.com.jumplabel.simulador.service.mapper.TipoRespostaMapper;

/**
 * Service Implementation for managing TipoResposta.
 */
@Service
@Transactional
public class TipoRespostaService {

    private final Logger log = LoggerFactory.getLogger(TipoRespostaService.class);
    
    private final TipoRespostaRepository tipoRespostaRepository;

    private final TipoRespostaMapper tipoRespostaMapper;

    public TipoRespostaService(TipoRespostaRepository tipoRespostaRepository, TipoRespostaMapper tipoRespostaMapper) {
        this.tipoRespostaRepository = tipoRespostaRepository;
        this.tipoRespostaMapper = tipoRespostaMapper;
    }

    /**
     * Save a tipoResposta.
     *
     * @param tipoRespostaDTO the entity to save
     * @return the persisted entity
     */
    public TipoRespostaDTO save(TipoRespostaDTO tipoRespostaDTO) {
        log.debug("Request to save TipoResposta : {}", tipoRespostaDTO);
        TipoResposta tipoResposta = tipoRespostaMapper.tipoRespostaDTOToTipoResposta(tipoRespostaDTO);
        tipoResposta = tipoRespostaRepository.save(tipoResposta);
        TipoRespostaDTO result = tipoRespostaMapper.tipoRespostaToTipoRespostaDTO(tipoResposta);
        return result;
    }

    /**
     *  Get all the tipoRespostas.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<TipoRespostaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TipoRespostas");
        Page<TipoResposta> result = tipoRespostaRepository.findAll(pageable);
        return result.map(tipoResposta -> tipoRespostaMapper.tipoRespostaToTipoRespostaDTO(tipoResposta));
    }

    /**
     *  Get one tipoResposta by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public TipoRespostaDTO findOne(Long id) {
        log.debug("Request to get TipoResposta : {}", id);
        TipoResposta tipoResposta = tipoRespostaRepository.findOne(id);
        TipoRespostaDTO tipoRespostaDTO = tipoRespostaMapper.tipoRespostaToTipoRespostaDTO(tipoResposta);
        return tipoRespostaDTO;
    }

    /**
     *  Delete the  tipoResposta by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TipoResposta : {}", id);
        tipoRespostaRepository.delete(id);
    }
}
