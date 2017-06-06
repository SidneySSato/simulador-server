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

import br.com.jumplabel.simulador.service.CntdDomiService;
import br.com.jumplabel.simulador.service.dto.CntdDomiDTO;
import br.com.jumplabel.simulador.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing CntdDomi.
 */
@RestController
@RequestMapping("/api")
public class CntdDomiResource {

    private final Logger log = LoggerFactory.getLogger(CntdDomiResource.class);
        
    private final CntdDomiService cntdDomiService;

    public CntdDomiResource(CntdDomiService cntdDomiService) {
        this.cntdDomiService = cntdDomiService;
    }

    /**
     * GET  /cntd-domis : get all the cntdDomis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cntdDomis in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/cntd-domis")
    @Timed
    public ResponseEntity<List<CntdDomiDTO>> getAllCntdDomis(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CntdDomis");
        Page<CntdDomiDTO> page = cntdDomiService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cntd-domis");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cntd-domis/:id : get the "id" cntdDomi.
     *
     * @param id the id of the cntdDomiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cntdDomiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/cntd-domis/{id}")
    @Timed
    public ResponseEntity<CntdDomiDTO> getCntdDomi(@PathVariable Long id) {
        log.debug("REST request to get CntdDomi : {}", id);
        CntdDomiDTO cntdDomiDTO = cntdDomiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(cntdDomiDTO));
    }
    
    /**
     * GET  /cntd-domis : get all the familias.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cntdDomis in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/cntd-domis/familia")
    @Timed
    public ResponseEntity<List<CntdDomiDTO>> getAllFamiliasCntdDomis()
        throws URISyntaxException {
        log.debug("REST request to get a page of Familias CntdDomis");
        List<CntdDomiDTO> findAllArvoreFamilias = cntdDomiService.findAllArvoreFamilias();
        return new ResponseEntity<>(findAllArvoreFamilias, HttpStatus.OK);
    }    
    
    /**
     * GET  /cntd-domis/canal/familiaIds/{familiaIds} : get all the Canais by familiaIds
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cntdDomis in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/cntd-domis/canal/familiaIds/{familiaIds}")
    @Timed
    public ResponseEntity<List<CntdDomiDTO>> getAllCanaisCntdDomisByFamiliaIds(@PathVariable List<Long> familiaIds)
        throws URISyntaxException {
    	
        log.debug("REST request to get a page of Canais CntdDomis");
        List<CntdDomiDTO> findAllArvoreFamilias = cntdDomiService.findAllArvoreCanal(familiaIds);
        return new ResponseEntity<>(findAllArvoreFamilias, HttpStatus.OK);
    }   
    
    /**
     * GET  /cntd-domis/canal: get all the Canais
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of cntdDomis in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/cntd-domis/canal")
    @Timed
    public ResponseEntity<List<CntdDomiDTO>> getAllCanaisCntdDomis()
        throws URISyntaxException {
    	
        log.debug("REST request to get a page of Canais CntdDomis");
        List<CntdDomiDTO> findAllArvoreFamilias = cntdDomiService.findAllArvoreCanal(null);
        return new ResponseEntity<>(findAllArvoreFamilias, HttpStatus.OK);
    }   

}
