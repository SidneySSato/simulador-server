package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.TipoResposta;
import br.com.jumplabel.simulador.repository.TipoRespostaRepository;
import br.com.jumplabel.simulador.service.TipoRespostaService;
import br.com.jumplabel.simulador.service.dto.TipoRespostaDTO;
import br.com.jumplabel.simulador.service.mapper.TipoRespostaMapper;

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

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TipoRespostaResource REST controller.
 *
 * @see TipoRespostaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class TipoRespostaResourceIntTest {

    private static final String DEFAULT_DS_TIPO_RESPOSTA = "AAAAAAAAAA";
    private static final String UPDATED_DS_TIPO_RESPOSTA = "BBBBBBBBBB";

    @Autowired
    private TipoRespostaRepository tipoRespostaRepository;

    @Autowired
    private TipoRespostaMapper tipoRespostaMapper;

    @Autowired
    private TipoRespostaService tipoRespostaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoRespostaMockMvc;

    private TipoResposta tipoResposta;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TipoRespostaResource tipoRespostaResource = new TipoRespostaResource(tipoRespostaService);
        this.restTipoRespostaMockMvc = MockMvcBuilders.standaloneSetup(tipoRespostaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoResposta createEntity(EntityManager em) {
        TipoResposta tipoResposta = new TipoResposta()
                .dsTipoResposta(DEFAULT_DS_TIPO_RESPOSTA);
        return tipoResposta;
    }

    @Before
    public void initTest() {
        tipoResposta = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoResposta() throws Exception {
        int databaseSizeBeforeCreate = tipoRespostaRepository.findAll().size();

        // Create the TipoResposta
        TipoRespostaDTO tipoRespostaDTO = tipoRespostaMapper.tipoRespostaToTipoRespostaDTO(tipoResposta);

        restTipoRespostaMockMvc.perform(post("/api/tipo-respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoRespostaDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoResposta in the database
        List<TipoResposta> tipoRespostaList = tipoRespostaRepository.findAll();
        assertThat(tipoRespostaList).hasSize(databaseSizeBeforeCreate + 1);
        TipoResposta testTipoResposta = tipoRespostaList.get(tipoRespostaList.size() - 1);
        assertThat(testTipoResposta.getDsTipoResposta()).isEqualTo(DEFAULT_DS_TIPO_RESPOSTA);
    }

    @Test
    @Transactional
    public void createTipoRespostaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoRespostaRepository.findAll().size();

        // Create the TipoResposta with an existing ID
        TipoResposta existingTipoResposta = new TipoResposta();
        existingTipoResposta.setId(1L);
        TipoRespostaDTO existingTipoRespostaDTO = tipoRespostaMapper.tipoRespostaToTipoRespostaDTO(existingTipoResposta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoRespostaMockMvc.perform(post("/api/tipo-respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingTipoRespostaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TipoResposta> tipoRespostaList = tipoRespostaRepository.findAll();
        assertThat(tipoRespostaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDsTipoRespostaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoRespostaRepository.findAll().size();
        // set the field null
        tipoResposta.setDsTipoResposta(null);

        // Create the TipoResposta, which fails.
        TipoRespostaDTO tipoRespostaDTO = tipoRespostaMapper.tipoRespostaToTipoRespostaDTO(tipoResposta);

        restTipoRespostaMockMvc.perform(post("/api/tipo-respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoRespostaDTO)))
            .andExpect(status().isBadRequest());

        List<TipoResposta> tipoRespostaList = tipoRespostaRepository.findAll();
        assertThat(tipoRespostaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoRespostas() throws Exception {
        // Initialize the database
        tipoRespostaRepository.saveAndFlush(tipoResposta);

        // Get all the tipoRespostaList
        restTipoRespostaMockMvc.perform(get("/api/tipo-respostas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoResposta.getId().intValue())))
            .andExpect(jsonPath("$.[*].dsTipoResposta").value(hasItem(DEFAULT_DS_TIPO_RESPOSTA.toString())));
    }

    @Test
    @Transactional
    public void getTipoResposta() throws Exception {
        // Initialize the database
        tipoRespostaRepository.saveAndFlush(tipoResposta);

        // Get the tipoResposta
        restTipoRespostaMockMvc.perform(get("/api/tipo-respostas/{id}", tipoResposta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoResposta.getId().intValue()))
            .andExpect(jsonPath("$.dsTipoResposta").value(DEFAULT_DS_TIPO_RESPOSTA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoResposta() throws Exception {
        // Get the tipoResposta
        restTipoRespostaMockMvc.perform(get("/api/tipo-respostas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoResposta() throws Exception {
        // Initialize the database
        tipoRespostaRepository.saveAndFlush(tipoResposta);
        int databaseSizeBeforeUpdate = tipoRespostaRepository.findAll().size();

        // Update the tipoResposta
        TipoResposta updatedTipoResposta = tipoRespostaRepository.findOne(tipoResposta.getId());
        updatedTipoResposta
                .dsTipoResposta(UPDATED_DS_TIPO_RESPOSTA);
        TipoRespostaDTO tipoRespostaDTO = tipoRespostaMapper.tipoRespostaToTipoRespostaDTO(updatedTipoResposta);

        restTipoRespostaMockMvc.perform(put("/api/tipo-respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoRespostaDTO)))
            .andExpect(status().isOk());

        // Validate the TipoResposta in the database
        List<TipoResposta> tipoRespostaList = tipoRespostaRepository.findAll();
        assertThat(tipoRespostaList).hasSize(databaseSizeBeforeUpdate);
        TipoResposta testTipoResposta = tipoRespostaList.get(tipoRespostaList.size() - 1);
        assertThat(testTipoResposta.getDsTipoResposta()).isEqualTo(UPDATED_DS_TIPO_RESPOSTA);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoResposta() throws Exception {
        int databaseSizeBeforeUpdate = tipoRespostaRepository.findAll().size();

        // Create the TipoResposta
        TipoRespostaDTO tipoRespostaDTO = tipoRespostaMapper.tipoRespostaToTipoRespostaDTO(tipoResposta);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTipoRespostaMockMvc.perform(put("/api/tipo-respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoRespostaDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoResposta in the database
        List<TipoResposta> tipoRespostaList = tipoRespostaRepository.findAll();
        assertThat(tipoRespostaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTipoResposta() throws Exception {
        // Initialize the database
        tipoRespostaRepository.saveAndFlush(tipoResposta);
        int databaseSizeBeforeDelete = tipoRespostaRepository.findAll().size();

        // Get the tipoResposta
        restTipoRespostaMockMvc.perform(delete("/api/tipo-respostas/{id}", tipoResposta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoResposta> tipoRespostaList = tipoRespostaRepository.findAll();
        assertThat(tipoRespostaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
