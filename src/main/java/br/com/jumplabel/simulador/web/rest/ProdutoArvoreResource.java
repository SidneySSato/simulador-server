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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jumplabel.simulador.domain.ProdId;
import br.com.jumplabel.simulador.service.ProdutoArvoreService;
import br.com.jumplabel.simulador.service.dto.ProdutoArvoreDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO.StatusType;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing ProdutoArvore.
 */
@RestController
@RequestMapping("/api")
public class ProdutoArvoreResource {

    private final Logger log = LoggerFactory.getLogger(ProdutoArvoreResource.class);
        
    private final ProdutoArvoreService produtoArvoreService;

    public ProdutoArvoreResource(ProdutoArvoreService produtoArvoreService) {
        this.produtoArvoreService = produtoArvoreService;
    }

    /**
     * PUT  /produto-arvores : Updates an existing produtoArvore.
     *
     * @param produtoArvoreDTO the produtoArvoreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated produtoArvoreDTO,
     * or with status 400 (Bad Request) if the produtoArvoreDTO is not valid,
     * or with status 500 (Internal Server Error) if the produtoArvoreDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/produto-arvores")
    @Timed
    public ResponseEntity<ProdutoArvoreDTO> updateProdutoArvore(@Valid @RequestBody ProdutoArvoreDTO produtoArvoreDTO) throws URISyntaxException {
        log.debug("REST request to update ProdutoArvore : {}", produtoArvoreDTO);
        
        ResultDTO resultDTO = produtoArvoreService.validateCreateProdutoArvore(produtoArvoreDTO);
        ProdutoArvoreDTO entityResult = null;
        
        // caso seja  valido
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
        	ProdId prodId = produtoArvoreService.save(produtoArvoreDTO);
        	
            entityResult = produtoArvoreService.findOne(prodId.getProdId(), prodId.getSubpId());
        	entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.created(new URI("/api/arvore-decisao/" + entityResult.getProdId() + "/" + entityResult.getSubpId()))
            		.body(entityResult);
        } else {
        	entityResult = new ProdutoArvoreDTO();
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }   
    }

    /**
     * GET  /produto-arvores : get all the produtoArvores.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of produtoArvores in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/produto-arvores")
    @Timed
    public ResponseEntity<List<ProdutoArvoreDTO>> getAllProdutoArvores(@ApiParam Pageable pageable, @RequestParam(required = false) String filter)
        throws URISyntaxException {
        if ("prod-is-null".equals(filter)) {
            log.debug("REST request to get all ProdutoArvores where prod is null");
            return new ResponseEntity<>(produtoArvoreService.findAllWhereProdIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ProdutoArvores");
        Page<ProdutoArvoreDTO> page = produtoArvoreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/produto-arvores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /produto-arvores/:id : get the "id" produtoArvore.
     *
     * @param id the id of the produtoArvoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the produtoArvoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/produto-arvores/{prodId}/{subpId}")
    @Timed
    public ResponseEntity<ProdutoArvoreDTO> getProdutoArvore(@PathVariable String prodId, String subpId) {
        log.debug("REST request to get ProdutoArvore : {}", prodId + " e subpId=" + subpId);
        ProdutoArvoreDTO produtoArvoreDTO = produtoArvoreService.findOne(prodId, subpId);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(produtoArvoreDTO));
    }

    /**
     * DELETE  /produto-arvores/:id : delete the "id" produtoArvore.
     *
     * @param id the id of the produtoArvoreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/produto-arvores/{prodId}/{subpId}")
    @Timed
    public ResponseEntity<ProdutoArvoreDTO> deleteProdutoArvore(@PathVariable String prodId, String subpId) {
        log.debug("REST request to delete ProdutoArvore : {}", "prodId=" + prodId + " e subpId=" + subpId);
        
        ResultDTO resultDTO = produtoArvoreService.validateDelete(prodId, subpId);
        ProdutoArvoreDTO entityResult = new ProdutoArvoreDTO();
        if (!StatusType.ERROR.equals(resultDTO.getStatus())) {
        	produtoArvoreService.delete(prodId, subpId);
            entityResult.setResult(ResultDTO.getSuccessResult());
            return ResponseEntity.ok((entityResult));
        } else {
        	entityResult.setResult(resultDTO);
        	return ResponseEntity.ok(entityResult);
        }
    }
   
    /**
     * GET  /produto-arvores/canalIds/{canalIds}" : get all the Produtos by CanalIds
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cntdDomis in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/produto-arvores/canalIds/{canalIds}")
    @Timed
    public ResponseEntity<List<ProdutoArvoreDTO>> getAllProdutoArvoreByCanalIds(@PathVariable List<Long> canalIds)
        throws URISyntaxException {
    	
        log.debug("REST request to get List of ProdutoArvore : {canalIds}", canalIds);
        
        List<ProdutoArvoreDTO> findAllArvoreFamilias = produtoArvoreService.findAllProdutoArvoreByListCanal(canalIds);
        return new ResponseEntity<>(findAllArvoreFamilias, HttpStatus.OK);
    }  
}
