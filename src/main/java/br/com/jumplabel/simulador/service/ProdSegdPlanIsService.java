package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.ProdSegdPlanIs;
import br.com.jumplabel.simulador.repository.ProdSegdPlanIsRepository;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanIsDTO;
import br.com.jumplabel.simulador.service.mapper.ProdSegdPlanIsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing ProdSegdPlanIs.
 */
@Service
@Transactional
public class ProdSegdPlanIsService {

    private final Logger log = LoggerFactory.getLogger(ProdSegdPlanIsService.class);
    
    private final ProdSegdPlanIsRepository prodSegdPlanIsRepository;

    private final ProdSegdPlanIsMapper prodSegdPlanIsMapper;

    public ProdSegdPlanIsService(ProdSegdPlanIsRepository prodSegdPlanIsRepository, ProdSegdPlanIsMapper prodSegdPlanIsMapper) {
        this.prodSegdPlanIsRepository = prodSegdPlanIsRepository;
        this.prodSegdPlanIsMapper = prodSegdPlanIsMapper;
    }

    /**
     * Save a prodSegdPlanIs.
     *
     * @param prodSegdPlanIsDTO the entity to save
     * @return the persisted entity
     */
    public ProdSegdPlanIsDTO save(ProdSegdPlanIsDTO prodSegdPlanIsDTO) {
        log.debug("Request to save ProdSegdPlanIs : {}", prodSegdPlanIsDTO);
        ProdSegdPlanIs prodSegdPlanIs = prodSegdPlanIsMapper.prodSegdPlanIsDTOToProdSegdPlanIs(prodSegdPlanIsDTO);
        prodSegdPlanIs = prodSegdPlanIsRepository.save(prodSegdPlanIs);
        ProdSegdPlanIsDTO result = prodSegdPlanIsMapper.prodSegdPlanIsToProdSegdPlanIsDTO(prodSegdPlanIs);
        return result;
    }

    /**
     *  Get all the prodSegdPlanIs.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ProdSegdPlanIsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProdSegdPlanIs");
        Page<ProdSegdPlanIs> result = prodSegdPlanIsRepository.findAll(pageable);
        return result.map(prodSegdPlanIs -> prodSegdPlanIsMapper.prodSegdPlanIsToProdSegdPlanIsDTO(prodSegdPlanIs));
    }

    /**
     *  Get one prodSegdPlanIs by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProdSegdPlanIsDTO findOne(Long id) {
        log.debug("Request to get ProdSegdPlanIs : {}", id);
        ProdSegdPlanIs prodSegdPlanIs = prodSegdPlanIsRepository.findOne(id);
        ProdSegdPlanIsDTO prodSegdPlanIsDTO = prodSegdPlanIsMapper.prodSegdPlanIsToProdSegdPlanIsDTO(prodSegdPlanIs);
        return prodSegdPlanIsDTO;
    }

    /**
     *  Delete the  prodSegdPlanIs by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ProdSegdPlanIs : {}", id);
        prodSegdPlanIsRepository.delete(id);
    }
}
