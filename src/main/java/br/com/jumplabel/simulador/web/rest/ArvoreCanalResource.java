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

import br.com.jumplabel.simulador.service.ArvoreCanalService;
import br.com.jumplabel.simulador.service.dto.ArvoreCanalDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ArvoreCanal.
 */
@RestController
@RequestMapping("/api")
public class ArvoreCanalResource {

    private final Logger log = LoggerFactory.getLogger(ArvoreCanalResource.class);
        
    private final ArvoreCanalService arvoreCanalService;

    public ArvoreCanalResource(ArvoreCanalService arvoreCanalService) {
        this.arvoreCanalService = arvoreCanalService;
    }

   
    /**
     * GET  /arvore-canais : get all the arvoreCanais.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of arvoreCanais in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/arvore-canais")
    @Timed
    public ResponseEntity<List<ArvoreCanalDTO>> getAllArvoreCanais(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ArvoreCanais");
        Page<ArvoreCanalDTO> page = arvoreCanalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/arvore-canais");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
