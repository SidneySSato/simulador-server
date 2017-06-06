package br.com.jumplabel.simulador.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jumplabel.simulador.service.MensagemProdutoService;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import br.com.jumplabel.simulador.service.dto.MensagemProdutoDTO;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MensagemProduto.
 */
@RestController
@RequestMapping("/api")
public class MensagemProdutoResource {

    private final Logger log = LoggerFactory.getLogger(MensagemProdutoResource.class);
        
    private final MensagemProdutoService mensagemProdutoService;

    public MensagemProdutoResource(MensagemProdutoService mensagemProdutoService) {
        this.mensagemProdutoService = mensagemProdutoService;
    }

    /**
     * POST  /mensagem-produtos : Create a new mensagemProduto.
     *
     * @param mensagemProdutoDTO the mensagemProdutoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mensagemProdutoDTO, or with status 400 (Bad Request) if the mensagemProduto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mensagem-produtos")
    @Timed
    public ResponseEntity<MensagemProdutoDTO> createMensagemProduto(@Valid @RequestBody MensagemProdutoDTO mensagemProdutoDTO) throws URISyntaxException {
        log.debug("REST request to save MensagemProduto : {}", mensagemProdutoDTO);
        if (mensagemProdutoDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("mensagemProduto", "idexists", "A new mensagemProduto cannot already have an ID")).body(null);
        }
        MensagemProdutoDTO result = mensagemProdutoService.save(mensagemProdutoDTO);
        return ResponseEntity.created(new URI("/api/mensagem-produtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("mensagemProduto", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mensagem-produtos : Updates an existing mensagemProduto.
     *
     * @param mensagemProdutoDTO the mensagemProdutoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mensagemProdutoDTO,
     * or with status 400 (Bad Request) if the mensagemProdutoDTO is not valid,
     * or with status 500 (Internal Server Error) if the mensagemProdutoDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mensagem-produtos")
    @Timed
    public ResponseEntity<MensagemProdutoDTO> updateMensagemProduto(@Valid @RequestBody MensagemProdutoDTO mensagemProdutoDTO) throws URISyntaxException {
        log.debug("REST request to update MensagemProduto : {}", mensagemProdutoDTO);
        if (mensagemProdutoDTO.getId() == null) {
            return createMensagemProduto(mensagemProdutoDTO);
        }
        MensagemProdutoDTO result = mensagemProdutoService.save(mensagemProdutoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("mensagemProduto", mensagemProdutoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mensagem-produtos : get all the mensagemProdutos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mensagemProdutos in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/mensagem-produtos")
    @Timed
    public ResponseEntity<List<MensagemProdutoDTO>> getAllMensagemProdutos(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MensagemProdutos");
        Page<MensagemProdutoDTO> page = mensagemProdutoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mensagem-produtos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /mensagem-produtos/:id : get the "id" mensagemProduto.
     *
     * @param id the id of the mensagemProdutoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mensagemProdutoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mensagem-produtos/{id}")
    @Timed
    public ResponseEntity<MensagemProdutoDTO> getMensagemProduto(@PathVariable Long id) {
        log.debug("REST request to get MensagemProduto : {}", id);
        MensagemProdutoDTO mensagemProdutoDTO = mensagemProdutoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(mensagemProdutoDTO));
    }

    /**
     * DELETE  /mensagem-produtos/:id : delete the "id" mensagemProduto.
     *
     * @param id the id of the mensagemProdutoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mensagem-produtos/{id}")
    @Timed
    public ResponseEntity<Void> deleteMensagemProduto(@PathVariable Long id) {
        log.debug("REST request to delete MensagemProduto : {}", id);
        mensagemProdutoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("mensagemProduto", id.toString())).build();
    }

}
