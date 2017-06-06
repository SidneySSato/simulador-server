package br.com.jumplabel.simulador.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.TagService;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO.StatusType;
import br.com.jumplabel.simulador.service.dto.TagDTO;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Tag.
 */
@RestController
@RequestMapping("/api")
public class TagResource {

    private final Logger log = LoggerFactory.getLogger(TagResource.class);
        
    private final TagService tagService;

    public TagResource(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * POST  /tags : Create a new tag.
     *
     * @param tagDTO the tagDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tagDTO, or with status 400 (Bad Request) if the tag has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value="/tags", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TagDTO> createTag(@Valid @RequestBody TagDTO tagDTO) throws URISyntaxException {
        log.debug("REST request to save Tag : {}", tagDTO);
        if (tagDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("tag", "idexists", "A new tag cannot already have an ID")).body(null);
        }
        
        ResultDTO resultDTO = tagService.validate(tagDTO);
        TagDTO entityResult = null;
        
        // caso seja  valido
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
        	entityResult = tagService.save(tagDTO);
        	entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.created(new URI("/api/tags/" + entityResult.getId()))
            		.body(entityResult);
        } else {
        	entityResult = new TagDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }   
    }

    /**
     * PUT  /tags : Updates an existing tag.
     *
     * @param tagDTO the tagDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tagDTO,
     * or with status 400 (Bad Request) if the tagDTO is not valid,
     * or with status 500 (Internal Server Error) if the tagDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value="/tags", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TagDTO> updateTag(@RequestBody TagDTO tagDTO) throws URISyntaxException {
    	
        log.debug("REST request to update Tag : {}", tagDTO);
        if (tagDTO.getId() == null) {
            return createTag(tagDTO);
        }
        // TODO validar se a tag existe antes de atualizar
        // TODO Corrigir dtCriacao e dtAtualizacao, usuario tbm
        
        
        ResultDTO resultDTO = tagService.validate(tagDTO);
        TagDTO entityResult = null;
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
            entityResult = tagService.save(tagDTO);
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.ok((entityResult));
        } else {
        	entityResult = new TagDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
        
    }

    /**
     * GET  /tags : get all the tags.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tags in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping(value="/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Page<TagDTO>> getAllTags(@ApiParam Pageable pageable, HttpServletRequest req)
    		throws URISyntaxException {
        log.debug("REST request to get a page of Tags");
        Page<TagDTO> page = tagService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tags");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * GET  /tags/:id : get the "id" tag.
     *
     * @param id the id of the tagDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tagDTO, or with status 404 (Not Found)
     */
    @GetMapping(value="/tags/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TagDTO> getTag(@PathVariable Long id) {
        log.debug("REST request to get Tag : {}", id);
        TagDTO tagDTO = tagService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tagDTO));
    }

    /**
     * DELETE  /tags/:id : delete the "id" tag.
     *
     * @param id the id of the tagDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
 
    @RequestMapping(value = "/tags/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TagDTO> deleteTag(@PathVariable Long id) {
        log.debug("REST request to delete Tag : {}", id);
        
        ResultDTO resultDTO = tagService.validateDelete(id);
        TagDTO entityResult = new TagDTO();
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
            tagService.delete(id);
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.ok((entityResult));
        } else {
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }

}
