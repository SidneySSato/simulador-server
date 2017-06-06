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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.CompDomiRamoService;
import br.com.jumplabel.simulador.service.dto.CompDomiRamoDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing CompDomiRamo.
 */
@RestController
@RequestMapping("/api")
public class CompDomiRamoResource {

    private final Logger log = LoggerFactory.getLogger(CompDomiRamoResource.class);
        
    private final CompDomiRamoService compDomiRamoService;

    public CompDomiRamoResource(CompDomiRamoService compDomiRamoService) {
        this.compDomiRamoService = compDomiRamoService;
    }

    /**
     * GET  /comp-domi-ramos : get all the compDomiRamos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of compDomiRamos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/comp-domi-ramos")
    @Timed
    public ResponseEntity<List<CompDomiRamoDTO>> getAllCompDomiRamos(@ApiParam Pageable pageable, @RequestParam(required = false) String filter)
        throws URISyntaxException {
        if ("cntddomi-is-null".equals(filter)) {
            log.debug("REST request to get all CompDomiRamos where cntdDomi is null");
            return new ResponseEntity<>(compDomiRamoService.findAllWhereCntdDomiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of CompDomiRamos");
        Page<CompDomiRamoDTO> page = compDomiRamoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comp-domi-ramos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /comp-domi-ramos/:id : get the "id" compDomiRamo.
     *
     * @param id the id of the compDomiRamoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compDomiRamoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/comp-domi-ramos/{id}")
    @Timed
    public ResponseEntity<CompDomiRamoDTO> getCompDomiRamo(@PathVariable Long id) {
        log.debug("REST request to get CompDomiRamo : {}", id);
        CompDomiRamoDTO compDomiRamoDTO = compDomiRamoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(compDomiRamoDTO));
    }
}
