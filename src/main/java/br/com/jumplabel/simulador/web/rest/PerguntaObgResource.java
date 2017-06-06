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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.PerguntaObgService;
import br.com.jumplabel.simulador.service.dto.PerguntaObgDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO.StatusType;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ProdutoArvore.
 */
@RestController
@RequestMapping("/api")
public class PerguntaObgResource {

	private final Logger log = LoggerFactory.getLogger(PerguntaObgResource.class);
	
	private final PerguntaObgService perguntaObgService;
	
	public PerguntaObgResource(PerguntaObgService perguntaObgService){
		this.perguntaObgService = perguntaObgService;
	}
	
	/**
     * PUT  /pergunta-obrigatoria : Updates an existing perguntaObg.
     *
     * @param perguntaObgDTO the perguntaObgDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated perguntaObgDTO,
     * or with status 400 (Bad Request) if the perguntaObgDTO is not valid,
     * or with status 500 (Internal Server Error) if the perguntaObgDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pergunta-obrigatoria")
    @Timed
    public ResponseEntity<PerguntaObgDTO> updatePerguntaObrigatoria(@Valid @RequestBody PerguntaObgDTO perguntaObgDTO) throws URISyntaxException {
    	log.debug("REST request to update PerguntaObg : {}", perguntaObgDTO);
        
        ResultDTO resultDTO = perguntaObgService.validateCreatePerguntaObg(perguntaObgDTO);
        PerguntaObgDTO entityResult = null;
        
     // caso seja  valido
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
        	PerguntaObgDTO perguntaObg = perguntaObgService.save(perguntaObgDTO);
        	entityResult = perguntaObgService.findOne(perguntaObg.getPerguntaId());
        	entityResult.setResult(ResultDTO.getSuccessResult());
        	return ResponseEntity.created(new URI("/api/pergunta-obrigatoria/" + entityResult.getPerguntaId()))
        			.body(entityResult);
        } else {
        	entityResult = new PerguntaObgDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }
    
    /**
     * GET  /pergunta-obrigatoria : get all the PerguntaObrigatoria.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of produtoArvores in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/pergunta-obrigatoria")
    @Timed
    public ResponseEntity<List<PerguntaObgDTO>> getAllPerguntaObrigatoria(@ApiParam Pageable pageable) throws URISyntaxException {
    	
    	log.debug("REST request to get a page of PerguntaObrigatoria");
        Page<PerguntaObgDTO> page = perguntaObgService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pergunta-obrigatoria");
    	return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /pergunta-obrigatoria/:id : get the "id" perguntaObrigatoria.
     *
     * @param id the id of the produtoArvoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the produtoArvoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pergunta-obrigatoria/{perguntaId}")
    @Timed
    public ResponseEntity<PerguntaObgDTO> getPerguntaObrigatoria(@PathVariable Long perguntaId) {
        log.debug("REST request to get PerguntaObrigatoria : {}", perguntaId);
        PerguntaObgDTO perguntaObgDTO = perguntaObgService.findOne(perguntaId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(perguntaObgDTO));
    }
}

