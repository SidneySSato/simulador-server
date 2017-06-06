package br.com.jumplabel.simulador.service;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.domain.PerguntaTag;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.domain.Tag;
import br.com.jumplabel.simulador.repository.PerguntaRepository;
import br.com.jumplabel.simulador.repository.RespostaRepository;
import br.com.jumplabel.simulador.repository.TagRepository;
import br.com.jumplabel.simulador.service.dto.PerguntaDTO;
import br.com.jumplabel.simulador.service.dto.ResultDTO;
import br.com.jumplabel.simulador.service.dto.TagDTO;
import br.com.jumplabel.simulador.service.mapper.PerguntaMapper;

/**
 * Service Implementation for managing Pergunta.
 */
@Service
@Transactional
public class PerguntaService {

    private final Logger log = LoggerFactory.getLogger(PerguntaService.class);
    
    private final PerguntaRepository perguntaRepository;
    
    private final RespostaRepository respostaRepository;
    
    private final TagRepository tagRepository;
    
    private final PerguntaMapper perguntaMapper;

    public PerguntaService(PerguntaRepository perguntaRepository, PerguntaMapper perguntaMapper,
    		RespostaRepository respostaRepository, TagRepository tagRepository) {
        this.perguntaRepository = perguntaRepository;
        this.perguntaMapper = perguntaMapper;
        this.respostaRepository = respostaRepository;
        this.tagRepository = tagRepository;
    }

    public ResultDTO validate(PerguntaDTO dto) {
        log.debug("Request to validate Pergunta : {}", dto);
        ResultDTO message = new ResultDTO();
     
        Pergunta perguntaFound = existsPerguntaDuplicadaByDescricao(dto.getId(), dto.getDsPergunta());
		if (perguntaFound != null) {
			return ResultDTO.getValorRepetidoResultError("dsPergunta", "PerguntaDTO", "Já existe uma Pergunta com id=" + perguntaFound.getId() + " sem Tag com o dsPergunta repetido.");
		}
        return message;
    }
        
    
    /**
     * Save a pergunta.
     *
     * @param perguntaDTO the entity to save
     * @return the persisted entity
     */
	public Long save(PerguntaDTO perguntaDTO) {
		log.debug("Request to save Pergunta : {}", perguntaDTO);

		Pergunta pergunta = perguntaMapper.perguntaDTOToPergunta(perguntaDTO);

		// realiza o trim do campo descricao
		pergunta.setDsPergunta(StringUtils.trim(pergunta.getDsPergunta()));

		// se for inclusao
		if (pergunta.getId() == null) {
			// adiciona as tags
			if (perguntaDTO.getTags() != null) {
				for (TagDTO tagDTO : perguntaDTO.getTags()) {
					Tag tag = tagRepository.getOne(tagDTO.getId());
					PerguntaTag perguntaTag = new PerguntaTag();
					perguntaTag.setPergunta(pergunta);
					perguntaTag.setTag(tag);
					pergunta.addPerguntaTag(perguntaTag);
				}
			}
		
			// save da pergunta
			pergunta = perguntaRepository.save(pergunta);

			return pergunta.getId();
	
			
//		// se for update
			// TODO nao esta retirando as tags que nao foram enviadas, 
			// vai ter q fazer uma operacao para retirar as tags ou adicionar novas
		} else {
			Pergunta perguntaUpdate = perguntaRepository.getOne(pergunta.getId());
			perguntaUpdate.setDsPergunta(pergunta.getDsPergunta());
			perguntaUpdate.setDomi(pergunta.getDomi());
			perguntaUpdate.setTipoResposta(pergunta.getTipoResposta());
			
			// save da pergunta
			perguntaUpdate = perguntaRepository.save(perguntaUpdate);

			return perguntaUpdate.getId();

		}
			
	}

    /**
     *  Get all the perguntas.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<PerguntaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Perguntas");
        Page<Pergunta> result = perguntaRepository.findAll(pageable);
        return result.map(pergunta -> perguntaMapper.perguntaToPerguntaDTO(pergunta));
    }

    /**
     *  Get one pergunta by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PerguntaDTO findOne(Long id) {
        log.debug("Request to get Pergunta : {}", id);
        Pergunta pergunta = perguntaRepository.findOne(id);
        PerguntaDTO perguntaDTO = perguntaMapper.perguntaToPerguntaDTO(pergunta);
        return perguntaDTO;
    }

    /**
     * Retorna true caso ja exista a descricao duplicada conforme regra de negocio e false caso contrario
     * @param perguntaId
     * @param descricao
     * @return
     */
    @Transactional(readOnly = true) 
    public Pergunta existsPerguntaDuplicadaByDescricao(Long perguntaId, String descricao) {
        log.debug("Request to existsPerguntaByDescricao: {}", descricao);
        Pergunta pergunta = new Pergunta();
        pergunta.setDsPergunta(StringUtils.trim(descricao));
        
        Example<Pergunta> example = Example.of(pergunta);
        List<Pergunta> list = perguntaRepository.findAll(example);

        // caso encontre a pergunta pela descricao
        // percorre a lista e verifica se existe alguma pergunta que NAO tem tags relacionadas
        for (Pergunta perguntaFound : list) {
        	// (valida se NAO é atualização da mesma pergunta) E (NAO tem tags relacionadas)
			if (!perguntaFound.getId().equals(perguntaId) && (perguntaFound.getPerguntaTags() != null && perguntaFound.getPerguntaTags().isEmpty())) {
				return perguntaFound;
			}
        }
        return null;
    }
    
    /**
     *  Delete the  pergunta by id.
     *  Caso ocorra com sucesso, retorna true
     *  Caso nao encontre pelo ID, retorna false
     *  @param id the id of the entity
     */
    public boolean delete(Long id) {
        log.debug("Request to delete Pergunta : {}", id);
        
        Pergunta entity = perguntaRepository.findOne(id);
        if (entity != null) {
        	Set<Resposta> respostas = entity.getRespostas();
	        if (respostas != null) {
	        	for (Resposta resposta : respostas) {
	        		respostaRepository.delete(resposta);
	        	}
			}
	        
//        	Set<PerguntaTag> perguntaTags = entity.getPerguntaTags();
//	        if (perguntaTags != null) {
//	        	for (PerguntaTag perguntaTag : perguntaTags) {
//	        		perguntaTagRepository.delete(perguntaTag.getPk().get);
//	        	}
//			}

	        
            perguntaRepository.delete(id);
            return true;
		} 
        return false;
    }
}
