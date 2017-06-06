package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.NoArvoreCopia;
import br.com.jumplabel.simulador.repository.NoArvoreCopiaRepository;
import br.com.jumplabel.simulador.service.dto.NoArvoreCopiaDTO;
import br.com.jumplabel.simulador.service.mapper.NoArvoreCopiaMapper;
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
 * Service Implementation for managing NoArvoreCopia.
 */
@Service
@Transactional
public class NoArvoreCopiaService {

    private final Logger log = LoggerFactory.getLogger(NoArvoreCopiaService.class);
    
    private final NoArvoreCopiaRepository noArvoreCopiaRepository;

    private final NoArvoreCopiaMapper noArvoreCopiaMapper;

    public NoArvoreCopiaService(NoArvoreCopiaRepository noArvoreCopiaRepository, NoArvoreCopiaMapper noArvoreCopiaMapper) {
        this.noArvoreCopiaRepository = noArvoreCopiaRepository;
        this.noArvoreCopiaMapper = noArvoreCopiaMapper;
    }

    /**
     * Save a noArvoreCopia.
     *
     * @param noArvoreCopiaDTO the entity to save
     * @return the persisted entity
     */
    public NoArvoreCopiaDTO save(NoArvoreCopiaDTO noArvoreCopiaDTO) {
        log.debug("Request to save NoArvoreCopia : {}", noArvoreCopiaDTO);
        NoArvoreCopia noArvoreCopia = noArvoreCopiaMapper.noArvoreCopiaDTOToNoArvoreCopia(noArvoreCopiaDTO);
        noArvoreCopia = noArvoreCopiaRepository.save(noArvoreCopia);
        NoArvoreCopiaDTO result = noArvoreCopiaMapper.noArvoreCopiaToNoArvoreCopiaDTO(noArvoreCopia);
        return result;
    }

    /**
     *  Get all the noArvoreCopias.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<NoArvoreCopiaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NoArvoreCopias");
        Page<NoArvoreCopia> result = noArvoreCopiaRepository.findAll(pageable);
        return result.map(noArvoreCopia -> noArvoreCopiaMapper.noArvoreCopiaToNoArvoreCopiaDTO(noArvoreCopia));
    }


    /**
     *  get all the noArvoreCopias where NoArvore is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<NoArvoreCopiaDTO> findAllWhereNoArvoreIsNull() {
        log.debug("Request to get all noArvoreCopias where NoArvore is null");
        return StreamSupport
            .stream(noArvoreCopiaRepository.findAll().spliterator(), false)
            .filter(noArvoreCopia -> noArvoreCopia.getNoArvore() == null)
            .map(noArvoreCopiaMapper::noArvoreCopiaToNoArvoreCopiaDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one noArvoreCopia by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public NoArvoreCopiaDTO findOne(Long id) {
        log.debug("Request to get NoArvoreCopia : {}", id);
        NoArvoreCopia noArvoreCopia = noArvoreCopiaRepository.findOne(id);
        NoArvoreCopiaDTO noArvoreCopiaDTO = noArvoreCopiaMapper.noArvoreCopiaToNoArvoreCopiaDTO(noArvoreCopia);
        return noArvoreCopiaDTO;
    }

    /**
     *  Delete the  noArvoreCopia by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete NoArvoreCopia : {}", id);
        noArvoreCopiaRepository.delete(id);
    }
}
