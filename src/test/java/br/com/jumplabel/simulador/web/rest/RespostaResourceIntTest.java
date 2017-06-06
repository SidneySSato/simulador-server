package br.com.jumplabel.simulador.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import br.com.jumplabel.simulador.SimuladorServerApp;
import br.com.jumplabel.simulador.domain.Resposta;
import br.com.jumplabel.simulador.repository.RespostaRepository;
import br.com.jumplabel.simulador.service.PerguntaService;
import br.com.jumplabel.simulador.service.RespostaService;
import br.com.jumplabel.simulador.service.dto.RespostaDTO;
import br.com.jumplabel.simulador.service.mapper.RespostaMapper;

/**
 * Test class for the RespostaResource REST controller.
 *
 * @see RespostaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class RespostaResourceIntTest {

    private static final String DEFAULT_DS_RESPOSTA = "AAAAAAAAAA";
    private static final String UPDATED_DS_RESPOSTA = "BBBBBBBBBB";

    private static final String DEFAULT_DS_CATEGORIA = "AAAAAAAAAA";
    private static final String UPDATED_DS_CATEGORIA = "BBBBBBBBBB";

    private static final String DEFAULT_FLAG_UTILIZACAO = "N";

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private RespostaMapper respostaMapper;

    @Autowired
    private RespostaService respostaService;

    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restRespostaMockMvc;

    private Resposta resposta;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RespostaResource respostaResource = new RespostaResource(respostaService, perguntaService);
        this.restRespostaMockMvc = MockMvcBuilders.standaloneSetup(respostaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Resposta createEntity(EntityManager em) {
        Resposta resposta = new Resposta()
                .dsResposta(DEFAULT_DS_RESPOSTA)
                .dsCategoria(DEFAULT_DS_CATEGORIA);
        return resposta;
    }

    @Before
    public void initTest() {
        resposta = createEntity(em);
    }

    @Test
    @Transactional
    public void createResposta() throws Exception {
        int databaseSizeBeforeCreate = respostaRepository.findAll().size();

        // Create the Resposta
        RespostaDTO respostaDTO = respostaMapper.respostaToRespostaDTO(resposta);

        restRespostaMockMvc.perform(post("/api/respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaDTO)))
            .andExpect(status().isCreated());

        // Validate the Resposta in the database
        List<Resposta> respostaList = respostaRepository.findAll();
        assertThat(respostaList).hasSize(databaseSizeBeforeCreate + 1);
        Resposta testResposta = respostaList.get(respostaList.size() - 1);
        assertThat(testResposta.getDsResposta()).isEqualTo(DEFAULT_DS_RESPOSTA);
        assertThat(testResposta.getDsCategoria()).isEqualTo(DEFAULT_DS_CATEGORIA);
    }

    @Test
    @Transactional
    public void createRespostaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respostaRepository.findAll().size();

        // Create the Resposta with an existing ID
        Resposta existingResposta = new Resposta();
        existingResposta.setId(1L);
        RespostaDTO existingRespostaDTO = respostaMapper.respostaToRespostaDTO(existingResposta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespostaMockMvc.perform(post("/api/respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRespostaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Resposta> respostaList = respostaRepository.findAll();
        assertThat(respostaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRespostas() throws Exception {
        // Initialize the database
        respostaRepository.saveAndFlush(resposta);

        // Get all the respostaList
        restRespostaMockMvc.perform(get("/api/respostas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resposta.getId().intValue())))
            .andExpect(jsonPath("$.[*].dsResposta").value(hasItem(DEFAULT_DS_RESPOSTA.toString())))
            .andExpect(jsonPath("$.[*].dsCategoria").value(hasItem(DEFAULT_DS_CATEGORIA.toString())))
            .andExpect(jsonPath("$.[*].inUtilizacao").value(hasItem(DEFAULT_FLAG_UTILIZACAO.toString())));
    }

    @Test
    @Transactional
    public void getResposta() throws Exception {
        // Initialize the database
        respostaRepository.saveAndFlush(resposta);

        // Get the resposta
        restRespostaMockMvc.perform(get("/api/respostas/{id}", resposta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resposta.getId().intValue()))
            .andExpect(jsonPath("$.dsResposta").value(DEFAULT_DS_RESPOSTA.toString()))
            .andExpect(jsonPath("$.dsCategoria").value(DEFAULT_DS_CATEGORIA.toString()))
            .andExpect(jsonPath("$.inUtilizacao").value(DEFAULT_FLAG_UTILIZACAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingResposta() throws Exception {
        // Get the resposta
        restRespostaMockMvc.perform(get("/api/respostas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResposta() throws Exception {
        // Initialize the database
        respostaRepository.saveAndFlush(resposta);
        int databaseSizeBeforeUpdate = respostaRepository.findAll().size();

        // Update the resposta
        Resposta updatedResposta = respostaRepository.findOne(resposta.getId());
        updatedResposta
                .dsResposta(UPDATED_DS_RESPOSTA)
                .dsCategoria(UPDATED_DS_CATEGORIA);
        RespostaDTO respostaDTO = respostaMapper.respostaToRespostaDTO(updatedResposta);

        restRespostaMockMvc.perform(put("/api/respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaDTO)))
            .andExpect(status().isOk());

        // Validate the Resposta in the database
        List<Resposta> respostaList = respostaRepository.findAll();
        assertThat(respostaList).hasSize(databaseSizeBeforeUpdate);
        Resposta testResposta = respostaList.get(respostaList.size() - 1);
        assertThat(testResposta.getDsResposta()).isEqualTo(UPDATED_DS_RESPOSTA);
        assertThat(testResposta.getDsCategoria()).isEqualTo(UPDATED_DS_CATEGORIA);
    }

    @Test
    @Transactional
    public void updateNonExistingResposta() throws Exception {
        int databaseSizeBeforeUpdate = respostaRepository.findAll().size();

        // Create the Resposta
        RespostaDTO respostaDTO = respostaMapper.respostaToRespostaDTO(resposta);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRespostaMockMvc.perform(put("/api/respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaDTO)))
            .andExpect(status().isCreated());

        // Validate the Resposta in the database
        List<Resposta> respostaList = respostaRepository.findAll();
        assertThat(respostaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteResposta() throws Exception {
        // Initialize the database
        respostaRepository.saveAndFlush(resposta);
        int databaseSizeBeforeDelete = respostaRepository.findAll().size();

        // Get the resposta
        restRespostaMockMvc.perform(delete("/api/respostas/{id}", resposta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Resposta> respostaList = respostaRepository.findAll();
        assertThat(respostaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
