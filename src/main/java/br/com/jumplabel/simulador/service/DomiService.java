package br.com.jumplabel.simulador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.Domi;
import br.com.jumplabel.simulador.repository.DomiRepository;
import br.com.jumplabel.simulador.service.dto.DomiDTO;
import br.com.jumplabel.simulador.service.mapper.DomiMapper;

/**
 * Service Implementation for managing Domi.
 */
@Service
@Transactional
public class DomiService {

    private final Logger log = LoggerFactory.getLogger(DomiService.class);
    
    private final DomiRepository domiRepository;

    private final DomiMapper domiMapper;

    public DomiService(DomiRepository domiRepository, DomiMapper domiMapper) {
        this.domiRepository = domiRepository;
        this.domiMapper = domiMapper;
    }

    /**
     * Save a domi.
     *
     * @param domiDTO the entity to save
     * @return the persisted entity
     */
    public DomiDTO save(DomiDTO domiDTO) {
        log.debug("Request to save Domi : {}", domiDTO);
        Domi domi = domiMapper.domiDTOToDomi(domiDTO);
        domi = domiRepository.save(domi);
        DomiDTO result = domiMapper.domiToDomiDTO(domi);
        return result;
    }

    /**
     *  Get all the domis.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<DomiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Domis");
        Page<Domi> result = domiRepository.findAll(pageable);
        return result.map(domi -> domiMapper.domiToDomiDTO(domi));
    }

    /**
     *  Get one domi by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public DomiDTO findOne(Long id) {
        log.debug("Request to get Domi : {}", id);
        Domi domi = domiRepository.findOne(id);
        DomiDTO domiDTO = domiMapper.domiToDomiDTO(domi);
        return domiDTO;
    }

    /**
     *  Delete the  domi by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Domi : {}", id);
        domiRepository.delete(id);
    }
}
