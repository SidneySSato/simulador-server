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

import br.com.jumplabel.simulador.service.PerguntaService;
import br.com.jumplabel.simulador.service.dto.PerguntaDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO.StatusType;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Pergunta.
 */
@RestController
@RequestMapping("/api")
public class PerguntaResource {

    private final Logger log = LoggerFactory.getLogger(PerguntaResource.class);
        
    private final PerguntaService perguntaService;

    public PerguntaResource(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    /**
     * POST  /perguntas : Create a new pergunta.
     *
     * @param perguntaDTO the perguntaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new perguntaDTO, or with status 400 (Bad Request) if the pergunta has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value="/perguntas", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PerguntaDTO> createPergunta(@Valid @RequestBody PerguntaDTO perguntaDTO) throws URISyntaxException {
        log.debug("REST request to save Pergunta : {}", perguntaDTO);
        if (perguntaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("pergunta", "idexists", "A new pergunta cannot already have an ID")).body(null);
        }

        ResultDTO resultDTO = perguntaService.validate(perguntaDTO);
        PerguntaDTO entityResult = null;
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
            Long perguntaId = perguntaService.save(perguntaDTO);
            entityResult = perguntaService.findOne(perguntaId);

            // caso seja valido
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.created(new URI("/api/perguntas/" + entityResult.getId()))
            		.body(entityResult);
        } else {
        	entityResult = new PerguntaDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }

    
    
    /**
     * PUT  /perguntas : Updates an existing pergunta.
     *
     * @param perguntaDTO the perguntaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated perguntaDTO,
     * or with status 400 (Bad Request) if the perguntaDTO is not valid,
     * or with status 500 (Internal Server Error) if the perguntaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value="/perguntas", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed    
    public ResponseEntity<PerguntaDTO> updatePergunta(@Valid @RequestBody PerguntaDTO perguntaDTO) throws URISyntaxException {
        log.debug("REST request to update Pergunta : {}", perguntaDTO);
        if (perguntaDTO.getId() == null) {
            return createPergunta(perguntaDTO);
        }
        
        ResultDTO resultDTO = perguntaService.validate(perguntaDTO);
        PerguntaDTO entityResult = null;
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
            Long perguntaId = perguntaService.save(perguntaDTO);
            entityResult = perguntaService.findOne(perguntaId);
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.ok((entityResult));
        } else {
        	entityResult = new PerguntaDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }

    /**
     * GET  /perguntas : get all the perguntas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of perguntas in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping(value="/perguntas", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Page<PerguntaDTO>> getAllPerguntas(@ApiParam Pageable pageable, HttpServletRequest req)
        throws URISyntaxException {
        log.debug("REST request to get a page of Perguntas");
        Page<PerguntaDTO> page = perguntaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/perguntas");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * GET  /perguntas/:id : get the "id" pergunta.
     *
     * @param id the id of the perguntaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the perguntaDTO, or with status 404 (Not Found)
     */
    @GetMapping(value="/perguntas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PerguntaDTO> getPergunta(@PathVariable Long id) {
        log.debug("REST request to get Pergunta : {}", id);
        PerguntaDTO perguntaDTO = perguntaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(perguntaDTO));
    }

    /**
     * DELETE  /perguntas/:id : delete the "id" pergunta.
     *
     * @param id the id of the perguntaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/perguntas/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PerguntaDTO> deletePergunta(@PathVariable Long id) {
        log.debug("REST request to delete Pergunta : {}", id);

        PerguntaDTO entityResult = new PerguntaDTO();
        if (perguntaService.delete(id)) {
        	entityResult.setResult(ResultDTO.getSuccessResult());
		} else {
			entityResult.setResult(ResultDTO.getValorNaoEncontradoResultError("id", "PerguntaDTO"));
		}
        return ResponseEntity.ok(entityResult);
    }
}