package br.com.jumplabel.simulador.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.service.RespCorporativaService;
import br.com.jumplabel.simulador.service.dto.RespCorporativaDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO.StatusType;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing RespCorporativa.
 */
@RestController
@RequestMapping("/api")
public class RespCorporativaResource {

    private final Logger log = LoggerFactory.getLogger(RespCorporativaResource.class);
        
    private final RespCorporativaService respCorporativaService;

    public RespCorporativaResource(RespCorporativaService respCorporativaService) {
        this.respCorporativaService = respCorporativaService;
    }

    /**
     * POST  /resp-corporativas : Create a new respCorporativa.
     *
     * @param respCorporativaDTO the respCorporativaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new respCorporativaDTO, or with status 400 (Bad Request) if the respCorporativa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resp-corporativas")
    @Timed
    public ResponseEntity<RespCorporativaDTO> createRespCorporativa(@Valid @RequestBody RespCorporativaDTO respCorporativaDTO) throws URISyntaxException {
        log.debug("REST request to save RespCorporativa : {}", respCorporativaDTO);
        if (respCorporativaDTO.getRespostaId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("respCorporativa", "idexists", "A new respCorporativa cannot already have an ID")).body(null);
        }
        
        ResultDTO resultDTO = respCorporativaService.validate(respCorporativaDTO);
        RespCorporativaDTO entityResult = null;
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
            entityResult  = respCorporativaService.save(respCorporativaDTO);

            // caso seja valido
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.created(new URI("/api/resp-corporativas/" + entityResult.getRespostaId()))
            		.body(entityResult);
        } else {
        	entityResult = new RespCorporativaDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }

    /**
     * PUT  /resp-corporativas : Updates an existing respCorporativa.
     *
     * @param respCorporativaDTO the respCorporativaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respCorporativaDTO,
     * or with status 400 (Bad Request) if the respCorporativaDTO is not valid,
     * or with status 500 (Internal Server Error) if the respCorporativaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resp-corporativas")
    @Timed
    public ResponseEntity<RespCorporativaDTO> updateRespCorporativa(@Valid @RequestBody RespCorporativaDTO respCorporativaDTO) throws URISyntaxException {
        log.debug("REST request to update RespCorporativa : {}", respCorporativaDTO);
        if (respCorporativaDTO.getRespostaId() == null) {
            return createRespCorporativa(respCorporativaDTO);
        }
        
        ResultDTO resultDTO = respCorporativaService.validate(respCorporativaDTO);
        RespCorporativaDTO entityResult = null;
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
            entityResult = respCorporativaService.save(respCorporativaDTO);
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.ok((entityResult));
        } else {
        	entityResult = new RespCorporativaDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }

    /**
     * GET  /resp-corporativas : get all the respCorporativas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of respCorporativas in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/resp-corporativas")
    @Timed
    public ResponseEntity<List<RespCorporativaDTO>> getAllRespCorporativas(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RespCorporativas");
        Page<RespCorporativaDTO> page = respCorporativaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resp-corporativas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

//    /**
//     * GET  /resp-corporativas/:id : get the "id" respCorporativa.
//     *
//     * @param id the id of the respCorporativaDTO to retrieve
//     * @return the ResponseEntity with status 200 (OK) and with body the respCorporativaDTO, or with status 404 (Not Found)
//     */
//    @GetMapping("/resp-corporativas/{id}")
//    @Timed
//    public ResponseEntity<RespCorporativaDTO> getRespCorporativa(@PathVariable Long id) {
//        log.debug("REST request to get RespCorporativa : {}", id);
//        RespCorporativaDTO respCorporativaDTO = respCorporativaService.findOne(id);
//        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(respCorporativaDTO));
//    }
//
//    /**
//     * DELETE  /resp-corporativas/:id : delete the "id" respCorporativa.
//     *
//     * @param id the id of the respCorporativaDTO to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/resp-corporativas/{id}")
//    @Timed
//    public ResponseEntity<RespCorporativaDTO> deleteRespCorporativa(@PathVariable Long id) {
//        log.debug("REST request to delete RespCorporativa : {}", id);
//        
//        RespCorporativaDTO respCorporativaDTO = new RespCorporativaDTO();
//        respCorporativaDTO.setId(id);
//        
//        ResultDTO resultDTO = respCorporativaService.validateDelete(respCorporativaDTO);
//        RespCorporativaDTO entityResult = new RespCorporativaDTO();
//        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
//        	respCorporativaService.delete(respCorporativaDTO.getId());
//            entityResult.setResult(ResultDTO.getSuccessResult());
//            return ResponseEntity.ok((entityResult));
//        } else {
//        	entityResult.setResult(resultDTO);
//        	return ResponseEntity.ok(entityResult);
//        }
//    }

}
