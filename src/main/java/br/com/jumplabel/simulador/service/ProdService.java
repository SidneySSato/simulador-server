package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.Prod;
import br.com.jumplabel.simulador.domain.ProdId;
import br.com.jumplabel.simulador.repository.ProdRepository;
import br.com.jumplabel.simulador.service.dto.ProdDTO;
import br.com.jumplabel.simulador.service.mapper.ProdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing Prod.
 */
@Service
@Transactional
public class ProdService {

    private final Logger log = LoggerFactory.getLogger(ProdService.class);
    
    private final ProdRepository prodRepository;

    private final ProdMapper prodMapper;

    public ProdService(ProdRepository prodRepository, ProdMapper prodMapper) {
        this.prodRepository = prodRepository;
        this.prodMapper = prodMapper;
    }

    /**
     * Save a prod.
     *
     * @param prodDTO the entity to save
     * @return the persisted entity
     */
    public ProdDTO save(ProdDTO prodDTO) {
        log.debug("Request to save Prod : {}", prodDTO);
        Prod prod = prodMapper.prodDTOToProd(prodDTO);
        prod = prodRepository.save(prod);
        ProdDTO result = prodMapper.prodToProdDTO(prod);
        return result;
    }

    /**
     *  Get all the prods.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ProdDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Prods");
        Page<Prod> result = prodRepository.findAll(pageable);
        return result.map(prod -> prodMapper.prodToProdDTO(prod));
    }

    /**
     *  Get one prod by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ProdDTO findOne(String prodId, String subpId) {
        Prod prod = prodRepository.findOne(new ProdId(prodId, subpId));
        ProdDTO prodDTO = prodMapper.prodToProdDTO(prod);
        return prodDTO;
    }


}
