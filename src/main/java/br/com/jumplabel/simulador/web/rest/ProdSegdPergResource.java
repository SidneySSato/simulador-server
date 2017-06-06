package br.com.jumplabel.simulador.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.ProdSegdPergService;
import br.com.jumplabel.simulador.service.dto.ProdSegdPergDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ProdSegdPerg.
 */
@RestController
@RequestMapping("/api")
public class ProdSegdPergResource {

    private final Logger log = LoggerFactory.getLogger(ProdSegdPergResource.class);
        
    private final ProdSegdPergService prodSegdPergService;

    public ProdSegdPergResource(ProdSegdPergService prodSegdPergService) {
        this.prodSegdPergService = prodSegdPergService;
    }

    /**
     * GET  /prod-segd-pergs : get all the prodSegdPergs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prodSegdPergs in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/prod-segd-pergs")
    @Timed
    public ResponseEntity<List<ProdSegdPergDTO>> getAllProdSegdPergs(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ProdSegdPergs");
        Page<ProdSegdPergDTO> page = prodSegdPergService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/prod-segd-pergs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /prod-segd-pergs/:id : get the "id" prodSegdPerg.
     *
     * @param id the id of the prodSegdPergDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prodSegdPergDTO, or with status 404 (Not Found)
     */
    @GetMapping("/prod-segd-pergs/{id}")
    @Timed
    public ResponseEntity<ProdSegdPergDTO> getProdSegdPerg(@PathVariable Long id) {
        log.debug("REST request to get ProdSegdPerg : {}", id);
        ProdSegdPergDTO prodSegdPergDTO = prodSegdPergService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prodSegdPergDTO));
    }
}
