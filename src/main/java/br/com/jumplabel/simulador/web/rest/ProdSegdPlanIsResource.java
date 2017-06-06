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

import br.com.jumplabel.simulador.service.ProdSegdPlanIsService;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanIsDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ProdSegdPlanIs.
 */
@RestController
@RequestMapping("/api")
public class ProdSegdPlanIsResource {

    private final Logger log = LoggerFactory.getLogger(ProdSegdPlanIsResource.class);
        
    private final ProdSegdPlanIsService prodSegdPlanIsService;

    public ProdSegdPlanIsResource(ProdSegdPlanIsService prodSegdPlanIsService) {
        this.prodSegdPlanIsService = prodSegdPlanIsService;
    }

    /**
     * GET  /prod-segd-plan-is : get all the prodSegdPlanIs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prodSegdPlanIs in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/prod-segd-plan-is")
    @Timed
    public ResponseEntity<List<ProdSegdPlanIsDTO>> getAllProdSegdPlanIs(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ProdSegdPlanIs");
        Page<ProdSegdPlanIsDTO> page = prodSegdPlanIsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/prod-segd-plan-is");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /prod-segd-plan-is/:id : get the "id" prodSegdPlanIs.
     *
     * @param id the id of the prodSegdPlanIsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prodSegdPlanIsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/prod-segd-plan-is/{id}")
    @Timed
    public ResponseEntity<ProdSegdPlanIsDTO> getProdSegdPlanIs(@PathVariable Long id) {
        log.debug("REST request to get ProdSegdPlanIs : {}", id);
        ProdSegdPlanIsDTO prodSegdPlanIsDTO = prodSegdPlanIsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prodSegdPlanIsDTO));
    }
}