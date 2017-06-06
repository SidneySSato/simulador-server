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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.ArvoreDecisaoService;
import br.com.jumplabel.simulador.service.dto.ArvoreDecisaoDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO.StatusType;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ArvoreDecisao.
 */
@RestController
@RequestMapping("/api")
public class ArvoreDecisaoResource {

    private final Logger log = LoggerFactory.getLogger(ArvoreDecisaoResource.class);
        
    private final ArvoreDecisaoService arvoreDecisaoService;

    public ArvoreDecisaoResource(ArvoreDecisaoService arvoreDecisaoService) {
        this.arvoreDecisaoService = arvoreDecisaoService;
    }


    /**
     * PUT  /arvore-decisao : Updates an existing arvoreDecisao.
     *
     * @param arvoreDecisaoDTO the arvoreDecisaoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated arvoreDecisaoDTO,
     * or with status 400 (Bad Request) if the arvoreDecisaoDTO is not valid,
     * or with status 500 (Internal Server Error) if the arvoreDecisaoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/arvore-decisao")
    @Timed
    public ResponseEntity<ArvoreDecisaoDTO> updateArvoreDecisao(@Valid @RequestBody ArvoreDecisaoDTO arvoreDecisaoDTO) throws URISyntaxException {
        log.debug("REST request to update ArvoreDecisao : {}", arvoreDecisaoDTO);
        
        ResultDTO resultDTO = arvoreDecisaoService.validateCreateArvoreDecisao(arvoreDecisaoDTO);
        ArvoreDecisaoDTO entityResult = null;
        
        // caso seja  valido
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
        	Long arvoreDecisaoId = arvoreDecisaoService.save(arvoreDecisaoDTO);
        	
            entityResult = arvoreDecisaoService.findOne(arvoreDecisaoId);
        	entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.created(new URI("/api/arvore-decisao/" + entityResult.getId()))
            		.body(entityResult);
        } else {
        	entityResult = new ArvoreDecisaoDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }   
        
    }

    /**
     * GET  /arvore-decisao : get all the arvoreDecisaos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of arvoreDecisaos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping(value="/arvore-decisao", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Page<ArvoreDecisaoDTO>> getAllArvoreDecisaos(@ApiParam Pageable pageable, HttpServletRequest req)
        throws URISyntaxException {
        
        log.debug("REST request to get a page of ArvoreDecisaos");
        Page<ArvoreDecisaoDTO> page = arvoreDecisaoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/arvore-decisao");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * GET  /arvore-decisao/:id : get the "id" arvoreDecisao.
     *
     * @param id the id of the arvoreDecisaoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the arvoreDecisaoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/arvore-decisao/{id}")
    @Timed
    public ResponseEntity<ArvoreDecisaoDTO> getArvoreDecisao(@PathVariable Long id) {
        log.debug("REST request to get ArvoreDecisao : {}", id);
        ArvoreDecisaoDTO arvoreDecisaoDTO = arvoreDecisaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(arvoreDecisaoDTO));
    }

    /**
     * DELETE  /arvore-decisao/:id : delete the "id" arvoreDecisao.
     *
     * @param id the id of the arvoreDecisaoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/arvore-decisao/{id}")
    @Timed
    public ResponseEntity<ArvoreDecisaoDTO> deleteArvoreDecisao(@PathVariable Long id) {
        log.debug("REST request to delete ArvoreDecisao : {}", id);
        
        ResultDTO resultDTO = arvoreDecisaoService.validateDelete(id);
        ArvoreDecisaoDTO entityResult = new ArvoreDecisaoDTO();
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
        	arvoreDecisaoService.delete(id);
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.ok((entityResult));
        } else {
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }

}
