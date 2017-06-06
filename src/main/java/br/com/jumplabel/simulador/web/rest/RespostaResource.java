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

import br.com.jumplabel.simulador.service.PerguntaService;
import br.com.jumplabel.simulador.service.RespostaService;
import br.com.jumplabel.simulador.service.dto.ListaRespostaDTO;
import br.com.jumplabel.simulador.service.dto.PerguntaDTO;
import br.com.jumplabel.simulador.service.dto.RespostaDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO.StatusType;
import br.com.jumplabel.simulador.web.rest.util.HeaderUtil;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Resposta.
 */
@RestController
@RequestMapping("/api")
public class RespostaResource {

    private final Logger log = LoggerFactory.getLogger(RespostaResource.class);
        
    private final RespostaService respostaService;
    
    private final PerguntaService perguntaService;

    public RespostaResource(RespostaService respostaService, 
    		PerguntaService perguntaService) {
        this.respostaService = respostaService;
        this.perguntaService = perguntaService;
    }

    /**
     * PUT  /respostas : Updates an existing resposta.
     *
     * @param respostaDTO the respostaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated respostaDTO,
     * or with status 400 (Bad Request) if the respostaDTO is not valid,
     * or with status 500 (Internal Server Error) if the respostaDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/respostas")
    @Timed
    public ResponseEntity<PerguntaDTO> updateResposta(@Valid @RequestBody ListaRespostaDTO listaRespostaDTO) throws URISyntaxException {
        log.debug("REST request to save ListaRespostaDTO : {}", listaRespostaDTO);
        if (listaRespostaDTO.getPerguntaId() == null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("listaRespostaDTO", "", "A listaRespostaDTO precisa ter perguntaId ")).body(null);
        }

        ResultDTO resultDTO = respostaService.validateResposta(listaRespostaDTO);
        PerguntaDTO entityResult = null;
        
        // caso seja  valido
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
        	Long perguntaId = respostaService.saveResposta(listaRespostaDTO);
        	
            entityResult = perguntaService.findOne(perguntaId);
        	entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.created(new URI("/api/respostas" + entityResult.getId()))
            		.body(entityResult);
        } else {
        	entityResult = new PerguntaDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }   
    }

    /**
     * GET  /respostas : get all the respostas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of respostas in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/respostas")
    @Timed
    public ResponseEntity<List<RespostaDTO>> getAllRespostas(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Respostas");
        Page<RespostaDTO> page = respostaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/respostas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

     /**
     * GET  /respostas/:id : get the "id" resposta.
     *
     * @param id the id of the respostaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the respostaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/respostas/{id}")
    @Timed
    public ResponseEntity<RespostaDTO> getResposta(@PathVariable Long id) {
        log.debug("REST request to get Resposta : {}", id);
        RespostaDTO respostaDTO = respostaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(respostaDTO));
    }

    /**
     * DELETE  /respostas/:id : delete the "id" resposta.
     *
     * @param id the id of the respostaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/respostas/{id}")
    @Timed
    public ResponseEntity<RespostaDTO> deleteResposta(@PathVariable Long id) {
        log.debug("REST request to delete Resposta : {}", id);
        
        ResultDTO resultDTO = respostaService.validateDelete(id);
        RespostaDTO entityResult = new RespostaDTO();
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
            respostaService.delete(id);
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.ok((entityResult));
        } else {
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }
}