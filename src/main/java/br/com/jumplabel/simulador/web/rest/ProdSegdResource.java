package br.com.jumplabel.simulador.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jumplabel.simulador.service.ProdSegdService;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import br.com.jumplabel.simulador.service.dto.ProdSegdDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProdSegd.
 */
@RestController
@RequestMapping("/api")
public class ProdSegdResource {

    private final Logger log = LoggerFactory.getLogger(ProdSegdResource.class);
        
    private final ProdSegdService prodSegdService;

    public ProdSegdResource(ProdSegdService prodSegdService) {
        this.prodSegdService = prodSegdService;
    }

    /**
     * GET  /prod-segds : get all the prodSegds.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prodSegds in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/prod-segds")
    @Timed
    public ResponseEntity<List<ProdSegdDTO>> getAllProdSegds(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ProdSegds");
        Page<ProdSegdDTO> page = prodSegdService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/prod-segds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /prod-segds/:id : get the "id" prodSegd.
     *
     * @param id the id of the prodSegdDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prodSegdDTO, or with status 404 (Not Found)
     */
    @GetMapping("/prod-segds/{id}")
    @Timed
    public ResponseEntity<ProdSegdDTO> getProdSegd(@PathVariable Long id) {
        log.debug("REST request to get ProdSegd : {}", id);
        ProdSegdDTO prodSegdDTO = prodSegdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prodSegdDTO));
    }
}