package br.com.jumplabel.simulador.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.ArvoreDecisaoService;
import br.com.jumplabel.simulador.service.NoArvoreService;
import br.com.jumplabel.simulador.service.dto.NoArvoreCadastroDTO;
import br.com.jumplabel.simulador.service.dto.NoArvoreDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO.StatusType;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing NoArvore.
 */
@RestController
@RequestMapping("/api")
public class NoArvoreResource {

    private final Logger log = LoggerFactory.getLogger(NoArvoreResource.class);
        
    private final NoArvoreService noArvoreService;
    private final ArvoreDecisaoService arvoreDecisaoService;

    public NoArvoreResource(NoArvoreService noArvoreService,
    		ArvoreDecisaoService arvoreDecisaoService) {
        this.noArvoreService = noArvoreService;
        this.arvoreDecisaoService = arvoreDecisaoService;
        
    }

//    /**
//     * POST  /no-arvores : Create a new noArvore.
//     *
//     * @param noArvoreDTO the noArvoreDTO to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new noArvoreDTO, or with status 400 (Bad Request) if the noArvore has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/no-arvores")
//    @Timed
//    public ResponseEntity<NoArvoreDTO> createNoArvore(@Valid @RequestBody NoArvoreDTO noArvoreDTO) throws URISyntaxException {
//        log.debug("REST request to save NoArvore : {}", noArvoreDTO);
//        if (noArvoreDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("noArvore", "idexists", "A new noArvore cannot already have an ID")).body(null);
//        }
//        NoArvoreDTO result = noArvoreService.save(noArvoreDTO);
//        return ResponseEntity.created(new URI("/api/no-arvores/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert("noArvore", result.getId().toString()))
//            .body(result);
//    }

    /**
     * PUT  /no-arvores : Updates an existing noArvore.
     *
     * @param noArvoreDTO the noArvoreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated noArvoreDTO,
     * or with status 400 (Bad Request) if the noArvoreDTO is not valid,
     * or with status 500 (Internal Server Error) if the noArvoreDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/no-arvores")
    @Timed
    public ResponseEntity<NoArvoreDTO> updateNoArvore(@Valid @RequestBody NoArvoreCadastroDTO dto) throws URISyntaxException {
        log.debug("REST request to update NoArvoreCadastroDTO : {}", dto);

        if (dto.getArvoreDecisaoId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("listaNoArvoreDTO", "", "A listaRespostaDTO precisa ter arvoreDecisaoId ")).body(null);
        }

        ResultDTO resultDTO = noArvoreService.validate(dto);
        NoArvoreDTO entityResult = null;
        
        // caso seja  valido
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
        	Long arvoreDecisaoId = noArvoreService.save(dto);
        	// Verificar que tem q ser noarvore Id e nao arvoreDecisaoId
            entityResult = noArvoreService.findNoArvorePai(arvoreDecisaoId);
        	entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.created(new URI("/api/no-arvores" + entityResult.getArvoreDecisaoId()))
            		.body(entityResult);
        } else {
        	entityResult = new NoArvoreDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }   
    }

    /**
     * GET  /no-arvores : get all the noArvores.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of noArvores in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/no-arvores")
    @Timed
    public ResponseEntity<List<NoArvoreDTO>> getAllNoArvores(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of NoArvores");
        Page<NoArvoreDTO> page = noArvoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/no-arvores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /no-arvores/:id : get the "id" noArvore.
     *
     * @param id the id of the noArvoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noArvoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/no-arvores/{id}")
    @Timed
    public ResponseEntity<NoArvoreDTO> getNoArvore(@PathVariable Long id) {
        log.debug("REST request to get NoArvore : {}", id);
        NoArvoreDTO noArvoreDTO = noArvoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(noArvoreDTO));
    }
    
    /**
     * GET  /no-arvores/arvore-decisao-id/:arvoreDecisaoId : get the "arvoreDecisaoId" noArvore.
     *
     * @param id the id of the noArvoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the noArvoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/no-arvores/arvore-decisao-id/{arvoreDecisaoId}")
    @Timed
    public ResponseEntity<NoArvoreDTO> getNoArvoreByDecisaoId(@PathVariable Long arvoreDecisaoId) {
        log.debug("REST request to get NoArvore : {arvoreDecisaoId}", arvoreDecisaoId);
        NoArvoreDTO noArvoreDTO = noArvoreService.findNoArvorePai(arvoreDecisaoId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(noArvoreDTO));
    }

    /**
     * DELETE  /no-arvores/:id : delete the "id" noArvore.
     *
     * @param id the id of the noArvoreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/no-arvores/{id}")
    @Timed
    public ResponseEntity<Void> deleteNoArvore(@PathVariable Long id) {
        log.debug("REST request to delete NoArvore : {}", id);
        noArvoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("noArvore", id.toString())).build();
    }

}
