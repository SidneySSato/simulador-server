package br.com.jumplabel.simulador.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.PerguntaTagService;
import br.com.jumplabel.simulador.service.dto.PerguntaTagDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO.StatusType;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing PerguntaTag.
 */
@RestController
@RequestMapping("/api")
public class PerguntaTagResource {

    private final Logger log = LoggerFactory.getLogger(PerguntaTagResource.class);
        
    private final PerguntaTagService perguntaTagService;

    public PerguntaTagResource(PerguntaTagService perguntaTagService) {
        this.perguntaTagService = perguntaTagService;
    }

    /**
     * POST  /pergunta-tags : Create a new perguntaTag.
     *
     * @param perguntaTagDTO the perguntaTagDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new perguntaTagDTO, or with status 400 (Bad Request) if the perguntaTag has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pergunta-tags")
    @Timed
    public ResponseEntity<PerguntaTagDTO> createPerguntaTag(@Valid @RequestBody PerguntaTagDTO perguntaTagDTO) throws URISyntaxException {
        log.debug("REST request to save PerguntaTag : {}", perguntaTagDTO);
        
        if (perguntaTagDTO.getPerguntaId() == null || perguntaTagDTO.getTagId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("perguntaTag", "idNotexists", "A new perguntaTag needs perguntaId and tagId")).body(null);
        }
        
        ResultDTO resultDTO = perguntaTagService.validate(perguntaTagDTO);
        PerguntaTagDTO entityResult = null;
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
            entityResult  = perguntaTagService.save(perguntaTagDTO);

            // caso seja valido
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.created(new URI("/api/pergunta-tags/" + entityResult.getPerguntaId() + "/" + entityResult.getTagId()))
            		.body(entityResult);
        } else {
        	entityResult = new PerguntaTagDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }

//    /**
//     * PUT  /pergunta-tags : Updates an existing perguntaTag.
//     *
//     * @param perguntaTagDTO the perguntaTagDTO to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated perguntaTagDTO,
//     * or with status 400 (Bad Request) if the perguntaTagDTO is not valid,
//     * or with status 500 (Internal Server Error) if the perguntaTagDTO couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/pergunta-tags")
//    @Timed
//    public ResponseEntity<PerguntaTagDTO> updatePerguntaTag(@Valid @RequestBody PerguntaTagDTO perguntaTagDTO) throws URISyntaxException {
//        log.debug("REST request to update PerguntaTag : {}", perguntaTagDTO);
//        if (perguntaTagDTO.getId() == null) {
//            return createPerguntaTag(perguntaTagDTO);
//        }
//        PerguntaTagDTO result = perguntaTagService.save(perguntaTagDTO);
//        return ResponseEntity.ok()
//            .headers(HeaderUtil.createEntityUpdateAlert("perguntaTag", perguntaTagDTO.getId().toString()))
//            .body(result);
//    }

    /**
     * GET  /pergunta-tags : get all the perguntaTags.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of perguntaTags in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/pergunta-tags")
    @Timed
    public ResponseEntity<List<PerguntaTagDTO>> getAllPerguntaTags(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PerguntaTags");
        Page<PerguntaTagDTO> page = perguntaTagService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pergunta-tags");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pergunta-tags/:id : get the "id" perguntaTag.
     *
     * @param id the id of the perguntaTagDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the perguntaTagDTO, or with status 404 (Not Found)
     */
    	         
    @GetMapping("/pergunta-tags/{perguntaId}/{tagId}")
    @Timed
    public ResponseEntity<PerguntaTagDTO> getPerguntaTag(@PathVariable Long perguntaId, @PathVariable Long tagId) {
    	log.debug("REST request to get PerguntaTag : {}", "perguntaId=" + perguntaId + " tagId=" + tagId);
        PerguntaTagDTO perguntaTagDTO = perguntaTagService.findOne(perguntaId, tagId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(perguntaTagDTO));
    }

    /**
     * DELETE  /pergunta-tags/:id : delete the "id" perguntaTag.
     *
     * @param id the id of the perguntaTagDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pergunta-tags/{perguntaId}/{tagId}")
    @Timed
    public ResponseEntity<PerguntaTagDTO> deletePerguntaTag(@PathVariable Long perguntaId, @PathVariable Long tagId) {
        log.debug("REST request to delete PerguntaTag : {}", "perguntaId=" + perguntaId + " tagId=" + tagId);

        PerguntaTagDTO perguntaTagDTO = new PerguntaTagDTO();
        perguntaTagDTO.setPerguntaId(perguntaId);
        perguntaTagDTO.setTagId(tagId);
        
        ResultDTO resultDTO = perguntaTagService.validateDelete(perguntaTagDTO);
        PerguntaTagDTO entityResult = new PerguntaTagDTO();
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
        	perguntaTagService.delete(perguntaTagDTO.getPerguntaId(), perguntaTagDTO.getTagId());
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.ok((entityResult));
        } else {
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
        
    }

}
