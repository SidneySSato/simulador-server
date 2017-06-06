package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.ProdSegdPlanSegm;
import br.com.jumplabel.simulador.domain.ProdSegdPlanSegmId;
import br.com.jumplabel.simulador.repository.ProdSegdPlanSegmRepository;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanSegmDTO;
import br.com.jumplabel.simulador.service.mapper.ProdSegdPlanSegmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing ProdSegdPlanSegm.
 */
@Service
@Transactional
public class ProdSegdPlanSegmService {

    private final Logger log = LoggerFactory.getLogger(ProdSegdPlanSegmService.class);
    
    private final ProdSegdPlanSegmRepository prodSegdPlanSegmRepository;

    private final ProdSegdPlanSegmMapper prodSegdPlanSegmMapper;

    public ProdSegdPlanSegmService(ProdSegdPlanSegmRepository prodSegdPlanSegmRepository, ProdSegdPlanSegmMapper prodSegdPlanSegmMapper) {
        this.prodSegdPlanSegmRepository = prodSegdPlanSegmRepository;
        this.prodSegdPlanSegmMapper = prodSegdPlanSegmMapper;
    }

    /**
     * Save a prodSegdPlanSegm.
     *
     * @param prodSegdPlanSegmDTO the entity to save
     * @return the persisted entity
     */
    public ProdSegdPlanSegmDTO save(ProdSegdPlanSegmDTO prodSegdPlanSegmDTO) {
        log.debug("Request to save ProdSegdPlanSegm : {}", prodSegdPlanSegmDTO);
        ProdSegdPlanSegm prodSegdPlanSegm = prodSegdPlanSegmMapper.prodSegdPlanSegmDTOToProdSegdPlanSegm(prodSegdPlanSegmDTO);
        prodSegdPlanSegm = prodSegdPlanSegmRepository.save(prodSegdPlanSegm);
        ProdSegdPlanSegmDTO result = prodSegdPlanSegmMapper.prodSegdPlanSegmToProdSegdPlanSegmDTO(prodSegdPlanSegm);
        return result;
    }

    /**
     *  Get all the prodSegdPlanSegms.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ProdSegdPlanSegmDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProdSegdPlanSegms");
        Page<ProdSegdPlanSegm> result = prodSegdPlanSegmRepository.findAll(pageable);
        return result.map(prodSegdPlanSegm -> prodSegdPlanSegmMapper.prodSegdPlanSegmToProdSegdPlanSegmDTO(prodSegdPlanSegm));
    }

    /**
     *  Get one prodSegdPlanSegm by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProdSegdPlanSegmDTO findOne(ProdSegdPlanSegmId id) {
        log.debug("Request to get ProdSegdPlanSegm : {}", id);
        ProdSegdPlanSegm prodSegdPlanSegm = prodSegdPlanSegmRepository.findOne(id);
        ProdSegdPlanSegmDTO prodSegdPlanSegmDTO = prodSegdPlanSegmMapper.prodSegdPlanSegmToProdSegdPlanSegmDTO(prodSegdPlanSegm);
        return prodSegdPlanSegmDTO;
    }
}
