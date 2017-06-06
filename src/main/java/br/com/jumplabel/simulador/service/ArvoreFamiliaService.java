package br.com.jumplabel.simulador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.ArvoreFamilia;
import br.com.jumplabel.simulador.domain.ArvoreFamiliaId;
import br.com.jumplabel.simulador.repository.ArvoreFamiliaRepository;
import br.com.jumplabel.simulador.service.dto.ArvoreFamiliaDTO;
import br.com.jumplabel.simulador.service.mapper.ArvoreFamiliaMapper;

/**
 * Service Implementation for managing ArvoreFamilia.
 */
@Service
@Transactional
public class ArvoreFamiliaService {

    private final Logger log = LoggerFactory.getLogger(ArvoreFamiliaService.class);
    
    private final ArvoreFamiliaRepository arvoreFamiliaRepository;

    private final ArvoreFamiliaMapper arvoreFamiliaMapper;

    public ArvoreFamiliaService(ArvoreFamiliaRepository arvoreFamiliaRepository, ArvoreFamiliaMapper arvoreFamiliaMapper) {
        this.arvoreFamiliaRepository = arvoreFamiliaRepository;
        this.arvoreFamiliaMapper = arvoreFamiliaMapper;
    }

    /**
     * Save a arvoreFamilia.
     *
     * @param arvoreFamiliaDTO the entity to save
     * @return the persisted entity
     */
    public ArvoreFamiliaDTO save(ArvoreFamiliaDTO arvoreFamiliaDTO) {
        log.debug("Request to save ArvoreFamilia : {}", arvoreFamiliaDTO);
        ArvoreFamilia arvoreFamilia = arvoreFamiliaMapper.arvoreFamiliaDTOToArvoreFamilia(arvoreFamiliaDTO);
        arvoreFamilia = arvoreFamiliaRepository.save(arvoreFamilia);
        ArvoreFamiliaDTO result = arvoreFamiliaMapper.arvoreFamiliaToArvoreFamiliaDTO(arvoreFamilia);
        return result;
    }

    /**
     *  Get all the arvoreFamilias.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ArvoreFamiliaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ArvoreFamilias");
        Page<ArvoreFamilia> result = arvoreFamiliaRepository.findAll(pageable);
        return result.map(arvoreFamilia -> arvoreFamiliaMapper.arvoreFamiliaToArvoreFamiliaDTO(arvoreFamilia));
    }

    /**
     *  Get one arvoreFamilia by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ArvoreFamiliaDTO findOne(Long arvoreDecisaoId, Long cntdDomiId) {
    	ArvoreFamiliaId id = new ArvoreFamiliaId(arvoreDecisaoId, cntdDomiId);
        ArvoreFamilia arvoreFamilia = arvoreFamiliaRepository.findOne(id);
        ArvoreFamiliaDTO arvoreFamiliaDTO = arvoreFamiliaMapper.arvoreFamiliaToArvoreFamiliaDTO(arvoreFamilia);
        return arvoreFamiliaDTO;
    }

    /**
     *  Delete the  arvoreFamilia by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long arvoreDecisaoId, Long cntdDomiId) {
    	
        ArvoreFamiliaId id = new ArvoreFamiliaId(arvoreDecisaoId, cntdDomiId);
        arvoreFamiliaRepository.delete(id);
    }
}
