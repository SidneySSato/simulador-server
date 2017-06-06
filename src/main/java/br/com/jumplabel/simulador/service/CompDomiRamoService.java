package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.CompDomiRamo;
import br.com.jumplabel.simulador.repository.CompDomiRamoRepository;
import br.com.jumplabel.simulador.service.dto.CompDomiRamoDTO;
import br.com.jumplabel.simulador.service.mapper.CompDomiRamoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing CompDomiRamo.
 */
@Service
@Transactional
public class CompDomiRamoService {

    private final Logger log = LoggerFactory.getLogger(CompDomiRamoService.class);
    
    private final CompDomiRamoRepository compDomiRamoRepository;

    private final CompDomiRamoMapper compDomiRamoMapper;

    public CompDomiRamoService(CompDomiRamoRepository compDomiRamoRepository, CompDomiRamoMapper compDomiRamoMapper) {
        this.compDomiRamoRepository = compDomiRamoRepository;
        this.compDomiRamoMapper = compDomiRamoMapper;
    }

    /**
     * Save a compDomiRamo.
     *
     * @param compDomiRamoDTO the entity to save
     * @return the persisted entity
     */
    public CompDomiRamoDTO save(CompDomiRamoDTO compDomiRamoDTO) {
        log.debug("Request to save CompDomiRamo : {}", compDomiRamoDTO);
        CompDomiRamo compDomiRamo = compDomiRamoMapper.compDomiRamoDTOToCompDomiRamo(compDomiRamoDTO);
        compDomiRamo = compDomiRamoRepository.save(compDomiRamo);
        CompDomiRamoDTO result = compDomiRamoMapper.compDomiRamoToCompDomiRamoDTO(compDomiRamo);
        return result;
    }

    /**
     *  Get all the compDomiRamos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<CompDomiRamoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CompDomiRamos");
        Page<CompDomiRamo> result = compDomiRamoRepository.findAll(pageable);
        return result.map(compDomiRamo -> compDomiRamoMapper.compDomiRamoToCompDomiRamoDTO(compDomiRamo));
    }


    /**
     *  get all the compDomiRamos where CntdDomi is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<CompDomiRamoDTO> findAllWhereCntdDomiIsNull() {
        log.debug("Request to get all compDomiRamos where CntdDomi is null");
        return StreamSupport
            .stream(compDomiRamoRepository.findAll().spliterator(), false)
            .filter(compDomiRamo -> compDomiRamo.getCntdDomi() == null)
            .map(compDomiRamoMapper::compDomiRamoToCompDomiRamoDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one compDomiRamo by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CompDomiRamoDTO findOne(Long id) {
        log.debug("Request to get CompDomiRamo : {}", id);
        CompDomiRamo compDomiRamo = compDomiRamoRepository.findOne(id);
        CompDomiRamoDTO compDomiRamoDTO = compDomiRamoMapper.compDomiRamoToCompDomiRamoDTO(compDomiRamo);
        return compDomiRamoDTO;
    }

    /**
     *  Delete the  compDomiRamo by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CompDomiRamo : {}", id);
        compDomiRamoRepository.delete(id);
    }
}
