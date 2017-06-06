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

import br.com.jumplabel.simulador.service.ProdSegdPlanSegmService;
import br.com.jumplabel.simulador.service.dto.ProdSegdPlanSegmDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ProdSegdPlanSegm.
 */
@RestController
@RequestMapping("/api")
public class ProdSegdPlanSegmResource {

    private final Logger log = LoggerFactory.getLogger(ProdSegdPlanSegmResource.class);
        
    private final ProdSegdPlanSegmService prodSegdPlanSegmService;

    public ProdSegdPlanSegmResource(ProdSegdPlanSegmService prodSegdPlanSegmService) {
        this.prodSegdPlanSegmService = prodSegdPlanSegmService;
    }

    /**
     * GET  /prod-segd-plan-segms : get all the prodSegdPlanSegms.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of prodSegdPlanSegms in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/prod-segd-plan-segms")
    @Timed
    public ResponseEntity<List<ProdSegdPlanSegmDTO>> getAllProdSegdPlanSegms(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ProdSegdPlanSegms");
        Page<ProdSegdPlanSegmDTO> page = prodSegdPlanSegmService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/prod-segd-plan-segms");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

//    /**
//     * GET  /prod-segd-plan-segms/:id : get the "id" prodSegdPlanSegm.
//     *
//     * @param id the id of the prodSegdPlanSegmDTO to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the prodSegdPlanSegmDTO, or with status 404 (Not Found)
//     */
//    @GetMapping("/prod-segd-plan-segms/{id}")
//    @Timed
//    public ResponseEntity<ProdSegdPlanSegmDTO> getProdSegdPlanSegm(@PathVariable Long id) {
//        log.debug("REST request to get ProdSegdPlanSegm : {}", id);
//        ProdSegdPlanSegmDTO prodSegdPlanSegmDTO = prodSegdPlanSegmService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(prodSegdPlanSegmDTO));
//    }
}