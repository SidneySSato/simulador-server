package br.com.jumplabel.simulador.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.PerguntaTag;
import br.com.jumplabel.simulador.domain.PerguntaTagId;
import br.com.jumplabel.simulador.domain.Tag;
import br.com.jumplabel.simulador.repository.PerguntaRepository;
import br.com.jumplabel.simulador.repository.PerguntaTagRepository;
import br.com.jumplabel.simulador.repository.TagRepository;
import br.com.jumplabel.simulador.service.dto.PerguntaTagDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.mapper.PerguntaTagMapper;

/**
 * Service Implementation for managing PerguntaTag.
 */
@Service
@Transactional
public class PerguntaTagService {

    private final Logger log = LoggerFactory.getLogger(PerguntaTagService.class);
    
    private final PerguntaTagRepository perguntaTagRepository;

    private final PerguntaTagMapper perguntaTagMapper;

    private final PerguntaRepository perguntaRepository;
    
    private final TagRepository tagRepository;
    
    public PerguntaTagService(PerguntaTagRepository perguntaTagRepository, PerguntaTagMapper perguntaTagMapper, 
    		PerguntaRepository perguntaRepository, TagRepository tagRepository) {
        this.perguntaTagRepository = perguntaTagRepository;
        this.perguntaTagMapper = perguntaTagMapper;
        this.perguntaRepository = perguntaRepository;
        this.tagRepository = tagRepository;
        
    }

    public ResultDTO validate(PerguntaTagDTO dto) {
        log.debug("Request to validate PerguntaTagDTO : {}", dto);
        ResultDTO message = new ResultDTO();
     
        // valida se existe pergunta cadastrada
        Pergunta perguntaFound = perguntaRepository.getOne(dto.getPerguntaId());       
		if (perguntaFound == null) {
			return ResultDTO.getValidacaoFKResultError("perguntaTag.perguntaId", "pergunta.id", "PerguntaTagDTO");
		}

		// valida se existe tag cadastrada
        Tag tagFound = tagRepository.getOne(dto.getTagId());
		if (tagFound == null) {
			return ResultDTO.getValidacaoFKResultError("perguntaTag.tagId", "tag.id", "PerguntaTagDTO");
		}

		// valida se ja existe o relacionamento cadastrado
		PerguntaTagDTO findOne = findOne(dto.getPerguntaId(), dto.getTagId());
		
		if (findOne != null) {
			return ResultDTO.getValorRepetidoResultError("perguntaId e tagId", "PerguntaTagDTO", "Já existe uma PerguntaTagDTO com perguntaId=" 
					+ dto.getPerguntaId() + "tagId=" + dto.getTagId() + " repetido na base.");
		}
		
        return message;
    }
    
    public ResultDTO validateDelete(PerguntaTagDTO dto) {
        log.debug("Request to validate PerguntaTagDTO : {}", dto);
        ResultDTO message = new ResultDTO();
     
		// valida se ja existe o relacionamento cadastrado
        PerguntaTagId pk = new PerguntaTagId(dto.getPerguntaId(), dto.getTagId());
		PerguntaTag findOne = perguntaTagRepository.findOne(pk);
		
		if (findOne == null) {
        	return ResultDTO.getValorNaoEncontradoResultError("perguntaId e tagId", "PerguntaTagDTO");
		}
		
        return message;
    }
    
    /**
     * Save a perguntaTag.
     *
     * @param perguntaTagDTO the entity to save
     * @return the persisted entity
     */
    public PerguntaTagDTO save(PerguntaTagDTO perguntaTagDTO) {
        log.debug("Request to save PerguntaTag : {}", perguntaTagDTO);
        PerguntaTag perguntaTag = perguntaTagMapper.perguntaTagDTOToPerguntaTag(perguntaTagDTO);
        perguntaTag = perguntaTagRepository.save(perguntaTag);
        PerguntaTagDTO result = perguntaTagMapper.perguntaTagToPerguntaTagDTO(perguntaTag);
        return result;
    }

    /**
     *  Get all the perguntaTags.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<PerguntaTagDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PerguntaTags");
        Page<PerguntaTag> result = perguntaTagRepository.findAll(pageable);
        return result.map(perguntaTag -> perguntaTagMapper.perguntaTagToPerguntaTagDTO(perguntaTag));
    }

    /**
     *  Get one perguntaTag by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PerguntaTagDTO findOne(Long perguntaId, Long tagId) {
        log.debug("Request to get PerguntaTag : {}", "perguntaId=" + perguntaId + " tagId=" + tagId);
        
        PerguntaTagId pk = new PerguntaTagId(perguntaId, tagId);        
        
        PerguntaTag perguntaTag = perguntaTagRepository.findOne(pk);
        PerguntaTagDTO perguntaTagDTO = perguntaTagMapper.perguntaTagToPerguntaTagDTO(perguntaTag);
        return perguntaTagDTO;
    }

    /**
     *  Delete the  perguntaTag by perguntaId e tagId.
     */
    public void delete(Long perguntaId, Long tagId) {
        log.debug("Request to delete PerguntaTag : {}", "perguntaId=" + perguntaId + " tagId=" + tagId);
        PerguntaTagId pk = new PerguntaTagId(perguntaId, tagId);
        perguntaTagRepository.delete(pk);
    }
}
