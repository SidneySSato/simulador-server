package br.com.jumplabel.simulador.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.repository.CntdDomiRepository;
import br.com.jumplabel.simulador.service.dto.CntdDomiDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.mapper.CntdDomiMapper;

/**
 * Service Implementation for managing CntdDomi.
 */
@Service
@Transactional
public class CntdDomiService {

    private final Logger log = LoggerFactory.getLogger(CntdDomiService.class);
    
    private final CntdDomiRepository cntdDomiRepository;

    private final CntdDomiMapper cntdDomiMapper;

    public CntdDomiService(CntdDomiRepository cntdDomiRepository, CntdDomiMapper cntdDomiMapper) {
        this.cntdDomiRepository = cntdDomiRepository;
        this.cntdDomiMapper = cntdDomiMapper;
    }

    /**
     *  Get all the cntdDomis.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<CntdDomiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CntdDomis");
        Page<CntdDomi> result = cntdDomiRepository.findAll(pageable);
        return result.map(cntdDomi -> cntdDomiMapper.cntdDomiToCntdDomiDTO(cntdDomi));
    }

    /**
     *  Get one cntdDomi by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public CntdDomiDTO findOne(Long id) {
        log.debug("Request to get CntdDomi : {}", id);
        CntdDomi cntdDomi = cntdDomiRepository.findOne(id);
        CntdDomiDTO cntdDomiDTO = cntdDomiMapper.cntdDomiToCntdDomiDTO(cntdDomi);
        return cntdDomiDTO;
    }
   
    /**
     *  Consulta todas as familias disponiveis associadas a algum produto cadastrado na tabela TB_PRODUTO_ARVORE
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public List<CntdDomiDTO> findAllArvoreFamilias() {
        log.debug("Request to get all familias CntdDomi : {}");
        List<CntdDomi> listCntdDomi = cntdDomiRepository.findAllArvoreFamilia(ResultDTO.DTO_CONSTANTE_ATIVO);
        List<CntdDomiDTO> listCntdDomiDTO = cntdDomiMapper.cntdDomisToCntdDomiDTOs(listCntdDomi);
        Collections.sort(listCntdDomiDTO);
        return listCntdDomiDTO;
    }    
    
    /**
     *  Consulta todas as familias disponiveis associadas a algum produto cadastrado na tabela TB_PRODUTO_ARVORE
     *
     *  @param familiaIds Lista dos Ids das familias para filtrar os canais
     *  @return Lista com os canais conforme o filtro de familia e produto ativo
     */
    @Transactional(readOnly = true) 
    public List<CntdDomiDTO> findAllArvoreCanal(List<Long> familiaIds) {
        log.debug("Request to get all canal CntdDomi : {}");
        Set<CntdDomi> cntdDomisResult = new HashSet<CntdDomi>();
        if (familiaIds != null) {
        	for (Long familiaId : familiaIds) {
        		List<CntdDomi> cntdDomis = 
        				cntdDomiRepository.findAllArvoreCanal(familiaId, ResultDTO.DTO_CONSTANTE_ATIVO);
        		
        	     // caso a lista seja vazia, a interseccao sera vazia e nao precisa mais buscar os itens
        		if (cntdDomis == null || cntdDomis.isEmpty()) {
        			cntdDomisResult.clear();
        			break;
        		} else {
            		cntdDomisResult.addAll(cntdDomis);
            		cntdDomisResult.retainAll(cntdDomis);
        		}
			}
		} 
        List<CntdDomiDTO> listCntdDomiDTO = cntdDomiMapper.cntdDomisToCntdDomiDTOs( new ArrayList<CntdDomi>(cntdDomisResult));
        Collections.sort(listCntdDomiDTO);
        return listCntdDomiDTO;
    }
    
    /**
     *  Consulta todas as familias disponiveis associadas a algum produto cadastrado na tabela TB_PRODUTO_ARVORE
     *  @return Lista com os canais conforme o produto ativo
     */
    @Transactional(readOnly = true) 
    public List<CntdDomiDTO> findAllArvoreCanal() {
        log.debug("Request to get all canal CntdDomi : {}");
        List<CntdDomi> listCntdDomi = new ArrayList<>();
		listCntdDomi = cntdDomiRepository.findAllArvoreCanal(ResultDTO.DTO_CONSTANTE_ATIVO);
        List<CntdDomiDTO> listCntdDomiDTO = cntdDomiMapper.cntdDomisToCntdDomiDTOs(listCntdDomi);
        Collections.sort(listCntdDomiDTO);
        return listCntdDomiDTO;
    }   
    
}