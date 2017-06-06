package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.CompDomiSegd;
import br.com.jumplabel.simulador.repository.CompDomiSegdRepository;
import br.com.jumplabel.simulador.service.dto.CompDomiSegdDTO;
import br.com.jumplabel.simulador.service.mapper.CompDomiSegdMapper;
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
 * Service Implementation for managing CompDomiSegd.
 */
@Service
@Transactional
public class CompDomiSegdService {

    private final Logger log = LoggerFactory.getLogger(CompDomiSegdService.class);
    
    private final CompDomiSegdRepository compDomiSegdRepository;

    private final CompDomiSegdMapper compDomiSegdMapper;

    public CompDomiSegdService(CompDomiSegdRepository compDomiSegdRepository, CompDomiSegdMapper compDomiSegdMapper) {
        this.compDomiSegdRepository = compDomiSegdRepository;
        this.compDomiSegdMapper = compDomiSegdMapper;
    }

    /**
     * Save a compDomiSegd.
     *
     * @param compDomiSegdDTO the entity to save
     * @return the persisted entity
     */
    public CompDomiSegdDTO save(CompDomiSegdDTO compDomiSegdDTO) {
        log.debug("Request to save CompDomiSegd : {}", compDomiSegdDTO);
        CompDomiSegd compDomiSegd = compDomiSegdMapper.compDomiSegdDTOToCompDomiSegd(compDomiSegdDTO);
        compDomiSegd = compDomiSegdRepository.save(compDomiSegd);
        CompDomiSegdDTO result = compDomiSegdMapper.compDomiSegdToCompDomiSegdDTO(compDomiSegd);
        return result;
    }

    /**
     *  Get all the compDomiSegds.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<CompDomiSegdDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CompDomiSegds");
        Page<CompDomiSegd> result = compDomiSegdRepository.findAll(pageable);
        return result.map(compDomiSegd -> compDomiSegdMapper.compDomiSegdToCompDomiSegdDTO(compDomiSegd));
    }


    /**
     *  get all the compDomiSegds where CntdDomi is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<CompDomiSegdDTO> findAllWhereCntdDomiIsNull() {
        log.debug("Request to get all compDomiSegds where CntdDomi is null");
        return StreamSupport
            .stream(compDomiSegdRepository.findAll().spliterator(), false)
            .filter(compDomiSegd -> compDomiSegd.getCntdDomi() == null)
            .map(compDomiSegdMapper::compDomiSegdToCompDomiSegdDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one compDomiSegd by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CompDomiSegdDTO findOne(Long id) {
        log.debug("Request to get CompDomiSegd : {}", id);
        CompDomiSegd compDomiSegd = compDomiSegdRepository.findOne(id);
        CompDomiSegdDTO compDomiSegdDTO = compDomiSegdMapper.compDomiSegdToCompDomiSegdDTO(compDomiSegd);
        return compDomiSegdDTO;
    }

    /**
     *  Delete the  compDomiSegd by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete CompDomiSegd : {}", id);
        compDomiSegdRepository.delete(id);
    }
}
