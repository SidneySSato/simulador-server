package br.com.jumplabel.simulador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.ArvoreCanal;
import br.com.jumplabel.simulador.domain.ArvoreCanalId;
import br.com.jumplabel.simulador.repository.ArvoreCanalRepository;
import br.com.jumplabel.simulador.service.dto.ArvoreCanalDTO;
import br.com.jumplabel.simulador.service.mapper.ArvoreCanalMapper;

/**
 * Service Implementation for managing ArvoreCanal.
 */
@Service
@Transactional
public class ArvoreCanalService {

    private final Logger log = LoggerFactory.getLogger(ArvoreCanalService.class);
    
    private final ArvoreCanalRepository arvoreCanalRepository;

    private final ArvoreCanalMapper arvoreCanalMapper;

    public ArvoreCanalService(ArvoreCanalRepository arvoreCanalRepository, ArvoreCanalMapper arvoreCanalMapper) {
        this.arvoreCanalRepository = arvoreCanalRepository;
        this.arvoreCanalMapper = arvoreCanalMapper;
    }

    /**
     * Save a arvoreCanal.
     *
     * @param arvoreCanalDTO the entity to save
     * @return the persisted entity
     */
    public ArvoreCanalDTO save(ArvoreCanalDTO arvoreCanalDTO) {
        log.debug("Request to save ArvoreCanal : {}", arvoreCanalDTO);
        ArvoreCanal arvoreCanal = arvoreCanalMapper.arvoreCanalDTOToArvoreCanal(arvoreCanalDTO);
        arvoreCanal = arvoreCanalRepository.save(arvoreCanal);
        ArvoreCanalDTO result = arvoreCanalMapper.arvoreCanalToArvoreCanalDTO(arvoreCanal);
        return result;
    }

    /**
     *  Get all the arvoreCanais.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<ArvoreCanalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ArvoreCanais");
        Page<ArvoreCanal> result = arvoreCanalRepository.findAll(pageable);
        return result.map(arvoreCanal -> arvoreCanalMapper.arvoreCanalToArvoreCanalDTO(arvoreCanal));
    }

    /**
     *  Get one arvoreCanal by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ArvoreCanalDTO findOne(Long arvoreDecisaoId, Long cntdDomiId) {
    	ArvoreCanalId id = new ArvoreCanalId(arvoreDecisaoId, cntdDomiId);
        ArvoreCanal arvoreCanal = arvoreCanalRepository.findOne(id);
        ArvoreCanalDTO arvoreCanalDTO = arvoreCanalMapper.arvoreCanalToArvoreCanalDTO(arvoreCanal);
        return arvoreCanalDTO;
    }

    /**
     *  Delete the  arvoreCanal by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long arvoreDecisaoId, Long cntdDomiId) {
        ArvoreCanalId id = new ArvoreCanalId(arvoreDecisaoId, cntdDomiId);
        arvoreCanalRepository.delete(id);
    }
}
