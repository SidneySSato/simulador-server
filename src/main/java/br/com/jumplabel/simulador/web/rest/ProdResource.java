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

import br.com.jumplabel.simulador.service.ProdService;
import br.com.jumplabel.simulador.service.dto.ProdDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Prod.
 */
@RestController
@RequestMapping("/api")
public class ProdResource {

    private final Logger log = LoggerFactory.getLogger(ProdResource.class);
        
    private final ProdService prodService;

    public ProdResource(ProdService prodService) {
        this.prodService = prodService;
    }

    /**
     * GET  /prods : get all the prods.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prods in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/prods")
    @Timed
    public ResponseEntity<List<ProdDTO>> getAllProds(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Prods");
        Page<ProdDTO> page = prodService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/prods");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /prods/:id : get the "id" prod.
     *
     * @param id the id of the prodDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prodDTO, or with status 404 (Not Found)
     */
    @GetMapping("/prods/{id}")
    @Timed
    public ResponseEntity<ProdDTO> getProd(@PathVariable String prodId, String subpId) {
    	log.debug("REST request to get Prod : {}", prodId + " e subpId=" + subpId);
        ProdDTO prodDTO = prodService.findOne(prodId, subpId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prodDTO));
    }
}