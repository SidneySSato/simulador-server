package br.com.jumplabel.simulador.service;

import br.com.jumplabel.simulador.domain.MensagemProduto;
import br.com.jumplabel.simulador.repository.MensagemProdutoRepository;
import br.com.jumplabel.simulador.service.dto.MensagemProdutoDTO;
import br.com.jumplabel.simulador.service.mapper.MensagemProdutoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing MensagemProduto.
 */
@Service
@Transactional
public class MensagemProdutoService {

    private final Logger log = LoggerFactory.getLogger(MensagemProdutoService.class);
    
    private final MensagemProdutoRepository mensagemProdutoRepository;

    private final MensagemProdutoMapper mensagemProdutoMapper;

    public MensagemProdutoService(MensagemProdutoRepository mensagemProdutoRepository, MensagemProdutoMapper mensagemProdutoMapper) {
        this.mensagemProdutoRepository = mensagemProdutoRepository;
        this.mensagemProdutoMapper = mensagemProdutoMapper;
    }

    /**
     * Save a mensagemProduto.
     *
     * @param mensagemProdutoDTO the entity to save
     * @return the persisted entity
     */
    public MensagemProdutoDTO save(MensagemProdutoDTO mensagemProdutoDTO) {
        log.debug("Request to save MensagemProduto : {}", mensagemProdutoDTO);
        MensagemProduto mensagemProduto = mensagemProdutoMapper.mensagemProdutoDTOToMensagemProduto(mensagemProdutoDTO);
        mensagemProduto = mensagemProdutoRepository.save(mensagemProduto);
        MensagemProdutoDTO result = mensagemProdutoMapper.mensagemProdutoToMensagemProdutoDTO(mensagemProduto);
        return result;
    }

    /**
     *  Get all the mensagemProdutos.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<MensagemProdutoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MensagemProdutos");
        Page<MensagemProduto> result = mensagemProdutoRepository.findAll(pageable);
        return result.map(mensagemProduto -> mensagemProdutoMapper.mensagemProdutoToMensagemProdutoDTO(mensagemProduto));
    }

    /**
     *  Get one mensagemProduto by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public MensagemProdutoDTO findOne(Long id) {
        log.debug("Request to get MensagemProduto : {}", id);
        MensagemProduto mensagemProduto = mensagemProdutoRepository.findOne(id);
        MensagemProdutoDTO mensagemProdutoDTO = mensagemProdutoMapper.mensagemProdutoToMensagemProdutoDTO(mensagemProduto);
        return mensagemProdutoDTO;
    }

    /**
     *  Delete the  mensagemProduto by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete MensagemProduto : {}", id);
        mensagemProdutoRepository.delete(id);
    }
}
