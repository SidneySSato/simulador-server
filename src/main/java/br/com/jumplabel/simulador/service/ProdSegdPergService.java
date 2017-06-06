package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.ProdSegdPerg;
import br.com.jumplabel.simulador.repository.ProdSegdPergRepository;
import br.com.jumplabel.simulador.service.dto.ProdSegdPergDTO;
import br.com.jumplabel.simulador.service.mapper.ProdSegdPergMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing ProdSegdPerg.
 */
@Service
@Transactional
public class ProdSegdPergService {

    private final Logger log = LoggerFactory.getLogger(ProdSegdPergService.class);
    
    private final ProdSegdPergRepository prodSegdPergRepository;

    private final ProdSegdPergMapper prodSegdPergMapper;

    public ProdSegdPergService(ProdSegdPergRepository prodSegdPergRepository, ProdSegdPergMapper prodSegdPergMapper) {
        this.prodSegdPergRepository = prodSegdPergRepository;
        this.prodSegdPergMapper = prodSegdPergMapper;
    }

    /**
     * Save a prodSegdPerg.
     *
     * @param prodSegdPergDTO the entity to save
     * @return the persisted entity
     */
    public ProdSegdPergDTO save(ProdSegdPergDTO prodSegdPergDTO) {
        log.debug("Request to save ProdSegdPerg : {}", prodSegdPergDTO);
        ProdSegdPerg prodSegdPerg = prodSegdPergMapper.prodSegdPergDTOToProdSegdPerg(prodSegdPergDTO);
        prodSegdPerg = prodSegdPergRepository.save(prodSegdPerg);
        ProdSegdPergDTO result = prodSegdPergMapper.prodSegdPergToProdSegdPergDTO(prodSegdPerg);
        return result;
    }

    /**
     *  Get all the prodSegdPergs.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ProdSegdPergDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProdSegdPergs");
        Page<ProdSegdPerg> result = prodSegdPergRepository.findAll(pageable);
        return result.map(prodSegdPerg -> prodSegdPergMapper.prodSegdPergToProdSegdPergDTO(prodSegdPerg));
    }

    /**
     *  Get one prodSegdPerg by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProdSegdPergDTO findOne(Long id) {
        log.debug("Request to get ProdSegdPerg : {}", id);
        ProdSegdPerg prodSegdPerg = prodSegdPergRepository.findOne(id);
        ProdSegdPergDTO prodSegdPergDTO = prodSegdPergMapper.prodSegdPergToProdSegdPergDTO(prodSegdPerg);
        return prodSegdPergDTO;
    }

    /**
     *  Delete the  prodSegdPerg by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ProdSegdPerg : {}", id);
        prodSegdPergRepository.delete(id);
    }
}
