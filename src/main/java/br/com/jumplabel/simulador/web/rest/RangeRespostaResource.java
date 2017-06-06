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

import br.com.jumplabel.simulador.service.RangeRespostaService;
import br.com.jumplabel.simulador.service.dto.RangeRespostaDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing RangeResposta.
 */
@RestController
@RequestMapping("/api")
public class RangeRespostaResource {

    private final Logger log = LoggerFactory.getLogger(RangeRespostaResource.class);
        
    private final RangeRespostaService rangeRespostaService;

    public RangeRespostaResource(RangeRespostaService rangeRespostaService) {
        this.rangeRespostaService = rangeRespostaService;
    }
//
//    /**
//     * POST  /range-respostas : Create a new rangeResposta.
//     *
//     * @param rangeRespostaDTO the rangeRespostaDTO to create
//     * @return the ResponseEntity with status 201 (Created) and with body the new rangeRespostaDTO, or with status 400 (Bad Request) if the rangeResposta has already an ID
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PostMapping("/range-respostas")
//    @Timed
//    public ResponseEntity<RangeRespostaDTO> createRangeResposta(@Valid @RequestBody RangeRespostaDTO rangeRespostaDTO) throws URISyntaxException {
//        log.debug("REST request to save RangeResposta : {}", rangeRespostaDTO);
//        if (rangeRespostaDTO.getId() != null) {
//            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("rangeResposta", "idexists", "A new rangeResposta cannot already have an ID")).body(null);
//        }
//        
//        List<RangeRespostaDTO> list = new ArrayList<RangeRespostaDTO>();
//        list.add(rangeRespostaDTO);
//        ResultDTO resultDTO = rangeRespostaService.validate(list);
//        RangeRespostaDTO entityResult = null;
//        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
//            entityResult  = rangeRespostaService.save(rangeRespostaDTO);
//
//            // caso seja valido
//            entityResult.setResult(ResultDTO.getSuccessResult());
//            return ResponseEntity.created(new URI("/api/range-respostas/" + entityResult.getRespostaId()))
//            		.body(entityResult);
//        } else {
//        	entityResult = new RangeRespostaDTO();
//        	entityResult.setResult(resultDTO);
//        	return ResponseEntity.ok(entityResult);
//        }
//    }
//
//    /**
//     * PUT  /range-respostas : Updates an existing rangeResposta.
//     *
//     * @param rangeRespostaDTO the rangeRespostaDTO to update
//     * @return the ResponseEntity with status 200 (OK) and with body the updated rangeRespostaDTO,
//     * or with status 400 (Bad Request) if the rangeRespostaDTO is not valid,
//     * or with status 500 (Internal Server Error) if the rangeRespostaDTO couldnt be updated
//     * @throws URISyntaxException if the Location URI syntax is incorrect
//     */
//    @PutMapping("/range-respostas")
//    @Timed
//    public ResponseEntity<RangeRespostaDTO> updateRangeResposta(@Valid @RequestBody RangeRespostaDTO rangeRespostaDTO) throws URISyntaxException {
//        log.debug("REST request to update RangeResposta : {}", rangeRespostaDTO);
//        if (rangeRespostaDTO.getId() == null) {
//            return createRangeResposta(rangeRespostaDTO);
//        }
//        
//        List<RangeRespostaDTO> list = new ArrayList<RangeRespostaDTO>();
//        list.add(rangeRespostaDTO);
//        ResultDTO resultDTO = rangeRespostaService.validate(list);
//        RangeRespostaDTO entityResult = null;
//        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
//            entityResult = rangeRespostaService.save(rangeRespostaDTO);
//            entityResult.setResult(ResultDTO.getSuccessResult());
//            return ResponseEntity.ok((entityResult));
//        } else {
//        	entityResult = new RangeRespostaDTO();
//        	entityResult.setResult(resultDTO);
//        	return ResponseEntity.ok(entityResult);
//        }
//    }

    /**
     * GET  /range-respostas : get all the rangeRespostas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rangeRespostas in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/range-respostas")
    @Timed
    public ResponseEntity<List<RangeRespostaDTO>> getAllRangeRespostas(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RangeRespostas");
        Page<RangeRespostaDTO> page = rangeRespostaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/range-respostas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /range-respostas/:id : get the "id" rangeResposta.
     *
     * @param id the id of the rangeRespostaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rangeRespostaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/range-respostas/{id}")
    @Timed
    public ResponseEntity<RangeRespostaDTO> getRangeResposta(@PathVariable Long id) {
        log.debug("REST request to get RangeResposta : {}", id);
        RangeRespostaDTO rangeRespostaDTO = rangeRespostaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rangeRespostaDTO));
    }

//    /**
//     * DELETE  /range-respostas/:id : delete the "id" rangeResposta.
//     *
//     * @param id the id of the rangeRespostaDTO to delete
//     * @return the ResponseEntity with status 200 (OK)
//     */
//    @DeleteMapping("/range-respostas/{id}")
//    @Timed
//    public ResponseEntity<RangeRespostaDTO> deleteRangeResposta(@PathVariable Long id) {
//        log.debug("REST request to delete RangeResposta : {}", id);
//        RangeRespostaDTO rangeRespostaDTO = new RangeRespostaDTO();
//        rangeRespostaDTO.setId(id);
//        
//        ResultDTO resultDTO = rangeRespostaService.validateDelete(rangeRespostaDTO);
//        RangeRespostaDTO entityResult = new RangeRespostaDTO();
//        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
//        	rangeRespostaService.delete(rangeRespostaDTO.getId());
//            entityResult.setResult(ResultDTO.getSuccessResult());
//            return ResponseEntity.ok((entityResult));
//        } else {
//        	entityResult.setResult(resultDTO);
//        	return ResponseEntity.ok(entityResult);
//        }
//    }

}
