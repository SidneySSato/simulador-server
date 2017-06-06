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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.RespostaIsService;
import br.com.jumplabel.simulador.service.dto.RespostaIsDTO;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing RespostaIs.
 */
@RestController
@RequestMapping("/api")
public class RespostaIsResource {

    private final Logger log = LoggerFactory.getLogger(RespostaIsResource.class);
        
    private final RespostaIsService respostaIsService;

    public RespostaIsResource(RespostaIsService respostaIsService) {
        this.respostaIsService = respostaIsService;
    }

    /**
     * POST  /resposta-is : Create a new respostaIs.
     *
     * @param respostaIsDTO the respostaIsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respostaIsDTO, or with status 400 (Bad Request) if the respostaIs has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resposta-is")
    @Timed
    public ResponseEntity<RespostaIsDTO> createRespostaIs(@Valid @RequestBody RespostaIsDTO respostaIsDTO) throws URISyntaxException {
        log.debug("REST request to save RespostaIs : {}", respostaIsDTO);
        if (respostaIsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("respostaIs", "idexists", "A new respostaIs cannot already have an ID")).body(null);
        }
        RespostaIsDTO result = respostaIsService.save(respostaIsDTO);
        return ResponseEntity.created(new URI("/api/resposta-is/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("respostaIs", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resposta-is : Updates an existing respostaIs.
     *
     * @param respostaIsDTO the respostaIsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respostaIsDTO,
     * or with status 400 (Bad Request) if the respostaIsDTO is not valid,
     * or with status 500 (Internal Server Error) if the respostaIsDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resposta-is")
    @Timed
    public ResponseEntity<RespostaIsDTO> updateRespostaIs(@Valid @RequestBody RespostaIsDTO respostaIsDTO) throws URISyntaxException {
        log.debug("REST request to update RespostaIs : {}", respostaIsDTO);
        if (respostaIsDTO.getId() == null) {
            return createRespostaIs(respostaIsDTO);
        }
        RespostaIsDTO result = respostaIsService.save(respostaIsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("respostaIs", respostaIsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resposta-is : get all the respostaIs.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of respostaIs in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/resposta-is")
    @Timed
    public ResponseEntity<List<RespostaIsDTO>> getAllRespostaIs(@ApiParam Pageable pageable, @RequestParam(required = false) String filter)
        throws URISyntaxException {
        if ("resposta-is-null".equals(filter)) {
            log.debug("REST request to get all RespostaIss where resposta is null");
            return new ResponseEntity<>(respostaIsService.findAllWhereRespostaIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of RespostaIs");
        Page<RespostaIsDTO> page = respostaIsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resposta-is");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /resposta-is/:id : get the "id" respostaIs.
     *
     * @param id the id of the respostaIsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respostaIsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resposta-is/{id}")
    @Timed
    public ResponseEntity<RespostaIsDTO> getRespostaIs(@PathVariable Long id) {
        log.debug("REST request to get RespostaIs : {}", id);
        RespostaIsDTO respostaIsDTO = respostaIsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(respostaIsDTO));
    }

    /**
     * DELETE  /resposta-is/:id : delete the "id" respostaIs.
     *
     * @param id the id of the respostaIsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resposta-is/{id}")
    @Timed
    public ResponseEntity<Void> deleteRespostaIs(@PathVariable Long id) {
        log.debug("REST request to delete RespostaIs : {}", id);
        respostaIsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("respostaIs", id.toString())).build();
    }

}
