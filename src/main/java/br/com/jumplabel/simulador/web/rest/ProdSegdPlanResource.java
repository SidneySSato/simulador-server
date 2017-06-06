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

import br.com.jumplabel.simulador.service.ProdSegdPlanService;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ProdSegdPlan.
 */
@RestController
@RequestMapping("/api")
public class ProdSegdPlanResource {

    private final Logger log = LoggerFactory.getLogger(ProdSegdPlanResource.class);
        
    private final ProdSegdPlanService prodSegdPlanService;

    public ProdSegdPlanResource(ProdSegdPlanService prodSegdPlanService) {
        this.prodSegdPlanService = prodSegdPlanService;
    }

    /**
     * GET  /prod-segd-plans : get all the prodSegdPlans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prodSegdPlans in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/prod-segd-plans")
    @Timed
    public ResponseEntity<List<ProdSegdPlanDTO>> getAllProdSegdPlans(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ProdSegdPlans");
        Page<ProdSegdPlanDTO> page = prodSegdPlanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/prod-segd-plans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /prod-segd-plans/:id : get the "id" prodSegdPlan.
     *
     * @param id the id of the prodSegdPlanDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prodSegdPlanDTO, or with status 404 (Not Found)
     */
    @GetMapping("/prod-segd-plans/{id}")
    @Timed
    public ResponseEntity<ProdSegdPlanDTO> getProdSegdPlan(@PathVariable Long id) {
        log.debug("REST request to get ProdSegdPlan : {}", id);
        ProdSegdPlanDTO prodSegdPlanDTO = prodSegdPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prodSegdPlanDTO));
    }
}
