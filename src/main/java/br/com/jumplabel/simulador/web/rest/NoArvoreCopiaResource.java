package br.com.jumplabel.simulador.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jumplabel.simulador.service.NoArvoreCopiaService;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import br.com.jumplabel.simulador.service.dto.NoArvoreCopiaDTO;

import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NoArvoreCopia.
 */
@RestController
@RequestMapping("/api")
public class NoArvoreCopiaResource {

    private final Logger log = LoggerFactory.getLogger(NoArvoreCopiaResource.class);
        
    private final NoArvoreCopiaService noArvoreCopiaService;

    public NoArvoreCopiaResource(NoArvoreCopiaService noArvoreCopiaService) {
        this.noArvoreCopiaService = noArvoreCopiaService;
    }

    /**
     * POST  /no-arvore-copias : Create a new noArvoreCopia.
     *
     * @param noArvoreCopiaDTO the noArvoreCopiaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new noArvoreCopiaDTO, or with status 400 (Bad Request) if the noArvoreCopia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/no-arvore-copias")
    @Timed
    public ResponseEntity<NoArvoreCopiaDTO> createNoArvoreCopia(@Valid @RequestBody NoArvoreCopiaDTO noArvoreCopiaDTO) throws URISyntaxException {
        log.debug("REST request to save NoArvoreCopia : {}", noArvoreCopiaDTO);
        if (noArvoreCopiaDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("noArvoreCopia", "idexists", "A new noArvoreCopia cannot already have an ID")).body(null);
        }
        NoArvoreCopiaDTO result = noArvoreCopiaService.save(noArvoreCopiaDTO);
        return ResponseEntity.created(new URI("/api/no-arvore-copias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("noArvoreCopia", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /no-arvore-copias : Updates an existing noArvoreCopia.
     *
     * @param noArvoreCopiaDTO the noArvoreCopiaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated noArvoreCopiaDTO,
     * or with status 400 (Bad Request) if the noArvoreCopiaDTO is not valid,
     * or with status 500 (Internal Server Error) if the noArvoreCopiaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/no-arvore-copias")
    @Timed
    public ResponseEntity<NoArvoreCopiaDTO> updateNoArvoreCopia(@Valid @RequestBody NoArvoreCopiaDTO noArvoreCopiaDTO) throws URISyntaxException {
        log.debug("REST request to update NoArvoreCopia : {}", noArvoreCopiaDTO);
        if (noArvoreCopiaDTO.getId() == null) {
            return createNoArvoreCopia(noArvoreCopiaDTO);
        }
        NoArvoreCopiaDTO result = noArvoreCopiaService.save(noArvoreCopiaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("noArvoreCopia", noArvoreCopiaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /no-arvore-copias : get all the noArvoreCopias.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of noArvoreCopias in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/no-arvore-copias")
    @Timed
    public ResponseEntity<List<NoArvoreCopiaDTO>> getAllNoArvoreCopias(@ApiParam Pageable pageable, @RequestParam(required = false) String filter)
        throws URISyntaxException {
        if ("noarvore-is-null".equals(filter)) {
            log.debug("REST request to get all NoArvoreCopias where noArvore is null");
            return new ResponseEntity<>(noArvoreCopiaService.findAllWhereNoArvoreIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of NoArvoreCopias");
        Page<NoArvoreCopiaDTO> page = noArvoreCopiaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/no-arvore-copias");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /no-arvore-copias/:id : get the "id" noArvoreCopia.
     *
     * @param id the id of the noArvoreCopiaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noArvoreCopiaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/no-arvore-copias/{id}")
    @Timed
    public ResponseEntity<NoArvoreCopiaDTO> getNoArvoreCopia(@PathVariable Long id) {
        log.debug("REST request to get NoArvoreCopia : {}", id);
        NoArvoreCopiaDTO noArvoreCopiaDTO = noArvoreCopiaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(noArvoreCopiaDTO));
    }

    /**
     * DELETE  /no-arvore-copias/:id : delete the "id" noArvoreCopia.
     *
     * @param id the id of the noArvoreCopiaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/no-arvore-copias/{id}")
    @Timed
    public ResponseEntity<Void> deleteNoArvoreCopia(@PathVariable Long id) {
        log.debug("REST request to delete NoArvoreCopia : {}", id);
        noArvoreCopiaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("noArvoreCopia", id.toString())).build();
    }

}
