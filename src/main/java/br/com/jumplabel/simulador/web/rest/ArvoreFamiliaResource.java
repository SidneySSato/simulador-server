package br.com.jumplabel.simulador.web.rest;

import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.ArvoreFamiliaService;
import br.com.jumplabel.simulador.service.dto.ArvoreFamiliaDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ArvoreFamilia.
 */
@RestController
@RequestMapping("/api")
public class ArvoreFamiliaResource {

    private final Logger log = LoggerFactory.getLogger(ArvoreFamiliaResource.class);
        
    private final ArvoreFamiliaService arvoreFamiliaService;

    public ArvoreFamiliaResource(ArvoreFamiliaService arvoreFamiliaService) {
        this.arvoreFamiliaService = arvoreFamiliaService;
    }

    /**
     * GET  /arvore-familias : get all the arvoreFamilias.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of arvoreFamilias in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/arvore-familias")
    @Timed
    public ResponseEntity<List<ArvoreFamiliaDTO>> getAllArvoreFamilias(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ArvoreFamilias");
        Page<ArvoreFamiliaDTO> page = arvoreFamiliaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/arvore-familias");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
