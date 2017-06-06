package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.CnalProdSegd;
import br.com.jumplabel.simulador.repository.CnalProdSegdRepository;
import br.com.jumplabel.simulador.service.dto.CnalProdSegdDTO;
import br.com.jumplabel.simulador.service.mapper.CnalProdSegdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing CnalProdSegd.
 */
@Service
@Transactional
public class CnalProdSegdService {

    private final Logger log = LoggerFactory.getLogger(CnalProdSegdService.class);
    
    private final CnalProdSegdRepository cnalProdSegdRepository;

    private final CnalProdSegdMapper cnalProdSegdMapper;

    public CnalProdSegdService(CnalProdSegdRepository cnalProdSegdRepository, CnalProdSegdMapper cnalProdSegdMapper) {
        this.cnalProdSegdRepository = cnalProdSegdRepository;
        this.cnalProdSegdMapper = cnalProdSegdMapper;
    }

    /**
     * Save a cnalProdSegd.
     *
     * @param cnalProdSegdDTO the entity to save
     * @return the persisted entity
     */
    public CnalProdSegdDTO save(CnalProdSegdDTO cnalProdSegdDTO) {
        log.debug("Request to save CnalProdSegd : {}", cnalProdSegdDTO);
        CnalProdSegd cnalProdSegd = cnalProdSegdMapper.cnalProdSegdDTOToCnalProdSegd(cnalProdSegdDTO);
        cnalProdSegd = cnalProdSegdRepository.save(cnalProdSegd);
        CnalProdSegdDTO result = cnalProdSegdMapper.cnalProdSegdToCnalProdSegdDTO(cnalProdSegd);
        return result;
    }

    /**
     *  Get all the cnalProdSegds.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<CnalProdSegdDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CnalProdSegds");
        Page<CnalProdSegd> result = cnalProdSegdRepository.findAll(pageable);
        return result.map(cnalProdSegd -> cnalProdSegdMapper.cnalProdSegdToCnalProdSegdDTO(cnalProdSegd));
    }

    /**
     *  Get one cnalProdSegd by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CnalProdSegdDTO findOne(Long id) {
        log.debug("Request to get CnalProdSegd : {}", id);
        CnalProdSegd cnalProdSegd = cnalProdSegdRepository.findOne(id);
        CnalProdSegdDTO cnalProdSegdDTO = cnalProdSegdMapper.cnalProdSegdToCnalProdSegdDTO(cnalProdSegd);
        return cnalProdSegdDTO;
    }

    /**
     *  Delete the  cnalProdSegd by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CnalProdSegd : {}", id);
        cnalProdSegdRepository.delete(id);
    }
}
