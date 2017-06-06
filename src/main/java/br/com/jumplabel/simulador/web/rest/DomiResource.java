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
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.DomiService;
import br.com.jumplabel.simulador.service.dto.DomiDTO;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Domi.
 */
@RestController
@RequestMapping("/api")
public class DomiResource {

    private final Logger log = LoggerFactory.getLogger(DomiResource.class);
        
    private final DomiService domiService;

    public DomiResource(DomiService domiService) {
        this.domiService = domiService;
    }

    /**
     * POST  /domis : Create a new domi.
     *
     * @param domiDTO the domiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new domiDTO, or with status 400 (Bad Request) if the domi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/domis")
    @Timed
    public ResponseEntity<DomiDTO> createDomi(@Valid @RequestBody DomiDTO domiDTO) throws URISyntaxException {
        log.debug("REST request to save Domi : {}", domiDTO);
        if (domiDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("domi", "idexists", "A new domi cannot already have an ID")).body(null);
        }
        DomiDTO result = domiService.save(domiDTO);
        return ResponseEntity.created(new URI("/api/domis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("domi", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /domis : Updates an existing domi.
     *
     * @param domiDTO the domiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated domiDTO,
     * or with status 400 (Bad Request) if the domiDTO is not valid,
     * or with status 500 (Internal Server Error) if the domiDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/domis")
    @Timed
    public ResponseEntity<DomiDTO> updateDomi(@Valid @RequestBody DomiDTO domiDTO) throws URISyntaxException {
        log.debug("REST request to update Domi : {}", domiDTO);
        if (domiDTO.getId() == null) {
            return createDomi(domiDTO);
        }
        DomiDTO result = domiService.save(domiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("domi", domiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /domis : get all the domis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of domis in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/domis")
    @Timed
    public ResponseEntity<Page<DomiDTO>> getAllDomis(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Domis");
        Page<DomiDTO> page = domiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/domis");
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * GET  /domis/:id : get the "id" domi.
     *
     * @param id the id of the domiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the domiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/domis/{id}")
    @Timed
    public ResponseEntity<DomiDTO> getDomi(@PathVariable Long id) {
        log.debug("REST request to get Domi : {}", id);
        DomiDTO domiDTO = domiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(domiDTO));
    }

    /**
     * DELETE  /domis/:id : delete the "id" domi.
     *
     * @param id the id of the domiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/domis/{id}")
    @Timed
    public ResponseEntity<Void> deleteDomi(@PathVariable Long id) {
        log.debug("REST request to delete Domi : {}", id);
        domiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("domi", id.toString())).build();
    }

}
