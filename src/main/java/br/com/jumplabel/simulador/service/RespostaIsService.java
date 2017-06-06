package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.RespostaIs;
import br.com.jumplabel.simulador.repository.RespostaIsRepository;
import br.com.jumplabel.simulador.service.dto.RespostaIsDTO;
import br.com.jumplabel.simulador.service.mapper.RespostaIsMapper;
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
 * Service Implementation for managing RespostaIs.
 */
@Service
@Transactional
public class RespostaIsService {

    private final Logger log = LoggerFactory.getLogger(RespostaIsService.class);
    
    private final RespostaIsRepository respostaIsRepository;

    private final RespostaIsMapper respostaIsMapper;

    public RespostaIsService(RespostaIsRepository respostaIsRepository, RespostaIsMapper respostaIsMapper) {
        this.respostaIsRepository = respostaIsRepository;
        this.respostaIsMapper = respostaIsMapper;
    }

    /**
     * Save a respostaIs.
     *
     * @param respostaIsDTO the entity to save
     * @return the persisted entity
     */
    public RespostaIsDTO save(RespostaIsDTO respostaIsDTO) {
        log.debug("Request to save RespostaIs : {}", respostaIsDTO);
        RespostaIs respostaIs = respostaIsMapper.respostaIsDTOToRespostaIs(respostaIsDTO);
        respostaIs = respostaIsRepository.save(respostaIs);
        RespostaIsDTO result = respostaIsMapper.respostaIsToRespostaIsDTO(respostaIs);
        return result;
    }

    /**
     *  Get all the respostaIs.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<RespostaIsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RespostaIs");
        Page<RespostaIs> result = respostaIsRepository.findAll(pageable);
        return result.map(respostaIs -> respostaIsMapper.respostaIsToRespostaIsDTO(respostaIs));
    }


    /**
     *  get all the respostaIs where Resposta is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RespostaIsDTO> findAllWhereRespostaIsNull() {
        log.debug("Request to get all respostaIs where Resposta is null");
        return StreamSupport
            .stream(respostaIsRepository.findAll().spliterator(), false)
            .filter(respostaIs -> respostaIs.getResposta() == null)
            .map(respostaIsMapper::respostaIsToRespostaIsDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one respostaIs by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public RespostaIsDTO findOne(Long id) {
        log.debug("Request to get RespostaIs : {}", id);
        RespostaIs respostaIs = respostaIsRepository.findOne(id);
        RespostaIsDTO respostaIsDTO = respostaIsMapper.respostaIsToRespostaIsDTO(respostaIs);
        return respostaIsDTO;
    }

    /**
     *  Delete the  respostaIs by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete RespostaIs : {}", id);
        respostaIsRepository.delete(id);
    }
}
