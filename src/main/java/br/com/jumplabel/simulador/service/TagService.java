package br.com.jumplabel.simulador.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.Tag;
import br.com.jumplabel.simulador.repository.TagRepository;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.TagDTO;
import br.com.jumplabel.simulador.service.mapper.TagMapper;

/**
 * Service Implementation for managing Tag.
 */
@Service
@Transactional
public class TagService {

    private final Logger log = LoggerFactory.getLogger(TagService.class);
    
    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public ResultDTO validate(TagDTO dto) {
        log.debug("Request to validate Tag : {}", dto);
        ResultDTO message = new ResultDTO();
     
        // update
        if (dto.getId() != null) {
        	if ( findOne(dto.getId()) == null) {
        		return ResultDTO.getValorNaoEncontradoResultError("id", "TagDTO");
        	}
		} 
        
        Tag tagFound = existsTagByDescricao(dto.getId(), dto.getDsTag());
        
		if (tagFound != null) {
			return ResultDTO.getValorRepetidoResultError("dsTag", "TagDTO", "Já existe uma Tag com id= " + tagFound.getId() + " com o dsTag repetido.");
		}
        return message;
    }

    public ResultDTO validateDelete(Long id) {
        log.debug("Request to validate delete Tag : {}", id);
        ResultDTO message = new ResultDTO();
     
        Tag entity = tagRepository.findOne(id);

        // tem que existir a entidade e nao ter perguntas associadas para poder apagar
        if (entity == null) {
        	return ResultDTO.getValorNaoEncontradoResultError("id", "TagDTO");
        } else if (!entity.getPerguntaTags().isEmpty()){
        	return ResultDTO.getValidacaoFKResultError("tag.id","pergunta_tag.tags_id", "TagDTO");
        }
        
        return message;
    }

    
    /**
     * Save a tag.
     *
     * @param tagDTO the entity to save
     * @return the persisted entity
     */
    public TagDTO save(TagDTO tagDTO) {
        log.debug("Request to save Tag : {}", tagDTO);
        Tag tag = tagMapper.tagDTOToTag(tagDTO);
        
        tag.setDsTag(StringUtils.trim(tag.getDsTag()));
        tag = tagRepository.save(tag);
        TagDTO result = tagMapper.tagToTagDTO(tag);
        return result;
    }

    /**
     *  Get all the tags.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<TagDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tags");
        Page<Tag> result = tagRepository.findAll(pageable);
        return result.map(tag -> tagMapper.tagToTagDTO(tag));
    }

    /**
     * Retorna a Tag caso ja exista a descricao no banco para id diferente e null caso contrario
     * @param tagId id da Tag
     * @param descricao descricao da tag
     * @return Retorna a Tag caso ja exista a descricao no banco para id diferente e null caso contrario
     */
    @Transactional(readOnly = true) 
    public Tag existsTagByDescricao(Long tagId, String descricao) {
        log.debug("Request to existsPerguntaByDescricao: {}", descricao);
        Tag tag = new Tag();
        tag.setDsTag(StringUtils.trim(descricao));
        
        Example<Tag> example = Example.of(tag);
        List<Tag> list = tagRepository.findAll(example);
        
        if (!list.isEmpty()) {
			if (tagId == null || (!tagId.equals(list.get(0).getId()))) {
				return list.get(0);
			}
		}
        
        return null;
    } 
    
    /**
     *  Get one tag by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public TagDTO findOne(Long id) {
        log.debug("Request to get Tag : {}", id);
        Tag tag = tagRepository.findOne(id);
        TagDTO tagDTO = tagMapper.tagToTagDTO(tag);
        return tagDTO;
    }

    /**
     *  Caso ocorra com sucesso, retorna true
     *  Caso nao encontre pelo ID, retorna false
     *  Delete the  tag by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Tag : {}", id);
        tagRepository.delete(id);
    }
}
