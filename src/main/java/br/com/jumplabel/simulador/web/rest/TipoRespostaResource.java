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

import br.com.jumplabel.simulador.service.TipoRespostaService;
import br.com.jumplabel.simulador.service.dto.TipoRespostaDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing TipoResposta.
 */
@RestController
@RequestMapping("/api")
public class TipoRespostaResource {

    private final Logger log = LoggerFactory.getLogger(TipoRespostaResource.class);
        
    private final TipoRespostaService tipoRespostaService;

    public TipoRespostaResource(TipoRespostaService tipoRespostaService) {
        this.tipoRespostaService = tipoRespostaService;
    }

    /**
     * GET  /tipo-respostas : get all the tipoRespostas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tipoRespostas in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/tipo-respostas")
    @Timed
    public ResponseEntity<List<TipoRespostaDTO>> getAllTipoRespostas(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of TipoRespostas");
        Page<TipoRespostaDTO> page = tipoRespostaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tipo-respostas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tipo-respostas/:id : get the "id" tipoResposta.
     *
     * @param id the id of the tipoRespostaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoRespostaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-respostas/{id}")
    @Timed
    public ResponseEntity<TipoRespostaDTO> getTipoResposta(@PathVariable Long id) {
        log.debug("REST request to get TipoResposta : {}", id);
        TipoRespostaDTO tipoRespostaDTO = tipoRespostaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tipoRespostaDTO));
    }
}