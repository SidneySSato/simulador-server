package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.ProdSegdPlan;
import br.com.jumplabel.simulador.repository.ProdSegdPlanRepository;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanDTO;
import br.com.jumplabel.simulador.service.mapper.ProdSegdPlanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing ProdSegdPlan.
 */
@Service
@Transactional
public class ProdSegdPlanService {

    private final Logger log = LoggerFactory.getLogger(ProdSegdPlanService.class);
    
    private final ProdSegdPlanRepository prodSegdPlanRepository;

    private final ProdSegdPlanMapper prodSegdPlanMapper;

    public ProdSegdPlanService(ProdSegdPlanRepository prodSegdPlanRepository, ProdSegdPlanMapper prodSegdPlanMapper) {
        this.prodSegdPlanRepository = prodSegdPlanRepository;
        this.prodSegdPlanMapper = prodSegdPlanMapper;
    }

    /**
     * Save a prodSegdPlan.
     *
     * @param prodSegdPlanDTO the entity to save
     * @return the persisted entity
     */
    public ProdSegdPlanDTO save(ProdSegdPlanDTO prodSegdPlanDTO) {
        log.debug("Request to save ProdSegdPlan : {}", prodSegdPlanDTO);
        ProdSegdPlan prodSegdPlan = prodSegdPlanMapper.prodSegdPlanDTOToProdSegdPlan(prodSegdPlanDTO);
        prodSegdPlan = prodSegdPlanRepository.save(prodSegdPlan);
        ProdSegdPlanDTO result = prodSegdPlanMapper.prodSegdPlanToProdSegdPlanDTO(prodSegdPlan);
        return result;
    }

    /**
     *  Get all the prodSegdPlans.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ProdSegdPlanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProdSegdPlans");
        Page<ProdSegdPlan> result = prodSegdPlanRepository.findAll(pageable);
        return result.map(prodSegdPlan -> prodSegdPlanMapper.prodSegdPlanToProdSegdPlanDTO(prodSegdPlan));
    }

    /**
     *  Get one prodSegdPlan by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProdSegdPlanDTO findOne(Long id) {
        log.debug("Request to get ProdSegdPlan : {}", id);
        ProdSegdPlan prodSegdPlan = prodSegdPlanRepository.findOne(id);
        ProdSegdPlanDTO prodSegdPlanDTO = prodSegdPlanMapper.prodSegdPlanToProdSegdPlanDTO(prodSegdPlan);
        return prodSegdPlanDTO;
    }

    /**
     *  Delete the  prodSegdPlan by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ProdSegdPlan : {}", id);
        prodSegdPlanRepository.delete(id);
    }
}
