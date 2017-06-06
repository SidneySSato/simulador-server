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

import br.com.jumplabel.simulador.service.CompDomiSegdService;
import br.com.jumplabel.simulador.service.dto.CompDomiSegdDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing CompDomiSegd.
 */
@RestController
@RequestMapping("/api")
public class CompDomiSegdResource {

    private final Logger log = LoggerFactory.getLogger(CompDomiSegdResource.class);
        
    private final CompDomiSegdService compDomiSegdService;

    public CompDomiSegdResource(CompDomiSegdService compDomiSegdService) {
        this.compDomiSegdService = compDomiSegdService;
    }

    /**
     * GET  /comp-domi-segds : get all the compDomiSegds.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of compDomiSegds in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/comp-domi-segds")
    @Timed
    public ResponseEntity<List<CompDomiSegdDTO>> getAllCompDomiSegds(@ApiParam Pageable pageable, @RequestParam(required = false) String filter)
        throws URISyntaxException {
        if ("cntddomi-is-null".equals(filter)) {
            log.debug("REST request to get all CompDomiSegds where cntdDomi is null");
            return new ResponseEntity<>(compDomiSegdService.findAllWhereCntdDomiIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of CompDomiSegds");
        Page<CompDomiSegdDTO> page = compDomiSegdService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/comp-domi-segds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /comp-domi-segds/:id : get the "id" compDomiSegd.
     *
     * @param id the id of the compDomiSegdDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the compDomiSegdDTO, or with status 404 (Not Found)
     */
    @GetMapping("/comp-domi-segds/{id}")
    @Timed
    public ResponseEntity<CompDomiSegdDTO> getCompDomiSegd(@PathVariable Long id) {
        log.debug("REST request to get CompDomiSegd : {}", id);
        CompDomiSegdDTO compDomiSegdDTO = compDomiSegdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(compDomiSegdDTO));
    }
}
