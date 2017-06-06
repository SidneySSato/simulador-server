package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.ProdSegd;
import br.com.jumplabel.simulador.repository.ProdSegdRepository;
import br.com.jumplabel.simulador.service.dto.ProdSegdDTO;
import br.com.jumplabel.simulador.service.mapper.ProdSegdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing ProdSegd.
 */
@Service
@Transactional
public class ProdSegdService {

    private final Logger log = LoggerFactory.getLogger(ProdSegdService.class);
    
    private final ProdSegdRepository prodSegdRepository;

    private final ProdSegdMapper prodSegdMapper;

    public ProdSegdService(ProdSegdRepository prodSegdRepository, ProdSegdMapper prodSegdMapper) {
        this.prodSegdRepository = prodSegdRepository;
        this.prodSegdMapper = prodSegdMapper;
    }

    /**
     * Save a prodSegd.
     *
     * @param prodSegdDTO the entity to save
     * @return the persisted entity
     */
    public ProdSegdDTO save(ProdSegdDTO prodSegdDTO) {
        log.debug("Request to save ProdSegd : {}", prodSegdDTO);
        ProdSegd prodSegd = prodSegdMapper.prodSegdDTOToProdSegd(prodSegdDTO);
        prodSegd = prodSegdRepository.save(prodSegd);
        ProdSegdDTO result = prodSegdMapper.prodSegdToProdSegdDTO(prodSegd);
        return result;
    }

    /**
     *  Get all the prodSegds.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ProdSegdDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProdSegds");
        Page<ProdSegd> result = prodSegdRepository.findAll(pageable);
        return result.map(prodSegd -> prodSegdMapper.prodSegdToProdSegdDTO(prodSegd));
    }

    /**
     *  Get one prodSegd by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProdSegdDTO findOne(Long id) {
        log.debug("Request to get ProdSegd : {}", id);
        ProdSegd prodSegd = prodSegdRepository.findOne(id);
        ProdSegdDTO prodSegdDTO = prodSegdMapper.prodSegdToProdSegdDTO(prodSegd);
        return prodSegdDTO;
    }

    /**
     *  Delete the  prodSegd by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ProdSegd : {}", id);
        prodSegdRepository.delete(id);
    }
}
