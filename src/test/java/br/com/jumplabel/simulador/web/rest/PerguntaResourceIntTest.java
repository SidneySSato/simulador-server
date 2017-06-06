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
import br.com.jumplabel.simulador.domain.Pergunta;
import br.com.jumplabel.simulador.repository.PerguntaRepository;
import br.com.jumplabel.simulador.service.PerguntaService;
import br.com.jumplabel.simulador.service.dto.PerguntaDTO;
import br.com.jumplabel.simulador.service.mapper.PerguntaMapper;

/**
 * Test class for the PerguntaResource REST controller.
 *
 * @see PerguntaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class PerguntaResourceIntTest {

    private static final String DEFAULT_DS_PERGUNTA = "AAAAAAAAAA";
    private static final String UPDATED_DS_PERGUNTA = "BBBBBBBBBB";

    private static final String DEFAULT_FLAG_EDITAVEL = "N";
    private static final String UPDATED_FLAG_EDITAVEL = "S";

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private PerguntaMapper perguntaMapper;

    @Autowired
    private PerguntaService perguntaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restPerguntaMockMvc;

    private Pergunta pergunta;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PerguntaResource perguntaResource = new PerguntaResource(perguntaService);
        this.restPerguntaMockMvc = MockMvcBuilders.standaloneSetup(perguntaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pergunta createEntity(EntityManager em) {
        Pergunta pergunta = new Pergunta()
                .dsPergunta(DEFAULT_DS_PERGUNTA)
                .inEditavel(DEFAULT_FLAG_EDITAVEL);
        return pergunta;
    }

    @Before
    public void initTest() {
        pergunta = createEntity(em);
    }

    @Test
    @Transactional
    public void createPergunta() throws Exception {
        int databaseSizeBeforeCreate = perguntaRepository.findAll().size();

        // Create the Pergunta
        PerguntaDTO perguntaDTO = perguntaMapper.perguntaToPerguntaDTO(pergunta);

        restPerguntaMockMvc.perform(post("/api/perguntas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perguntaDTO)))
            .andExpect(status().isCreated());

        // Validate the Pergunta in the database
        List<Pergunta> perguntaList = perguntaRepository.findAll();
        assertThat(perguntaList).hasSize(databaseSizeBeforeCreate + 1);
        Pergunta testPergunta = perguntaList.get(perguntaList.size() - 1);
        assertThat(testPergunta.getDsPergunta()).isEqualTo(DEFAULT_DS_PERGUNTA);
        assertThat(testPergunta.getInEditavel()).isEqualTo(DEFAULT_FLAG_EDITAVEL);
    }

    @Test
    @Transactional
    public void createPerguntaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = perguntaRepository.findAll().size();

        // Create the Pergunta with an existing ID
        Pergunta existingPergunta = new Pergunta();
        existingPergunta.setId(1L);
        PerguntaDTO existingPerguntaDTO = perguntaMapper.perguntaToPerguntaDTO(existingPergunta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerguntaMockMvc.perform(post("/api/perguntas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingPerguntaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Pergunta> perguntaList = perguntaRepository.findAll();
        assertThat(perguntaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDsPerguntaIsRequired() throws Exception {
        int databaseSizeBeforeTest = perguntaRepository.findAll().size();
        // set the field null
        pergunta.setDsPergunta(null);

        // Create the Pergunta, which fails.
        PerguntaDTO perguntaDTO = perguntaMapper.perguntaToPerguntaDTO(pergunta);

        restPerguntaMockMvc.perform(post("/api/perguntas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perguntaDTO)))
            .andExpect(status().isBadRequest());

        List<Pergunta> perguntaList = perguntaRepository.findAll();
        assertThat(perguntaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPerguntas() throws Exception {
        // Initialize the database
        perguntaRepository.saveAndFlush(pergunta);

        // Get all the perguntaList
        restPerguntaMockMvc.perform(get("/api/perguntas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pergunta.getId().intValue())))
            .andExpect(jsonPath("$.[*].dsPergunta").value(hasItem(DEFAULT_DS_PERGUNTA.toString())))
            .andExpect(jsonPath("$.[*].inEditavel").value(hasItem(DEFAULT_FLAG_EDITAVEL.toString())));
    }

    @Test
    @Transactional
    public void getPergunta() throws Exception {
        // Initialize the database
        perguntaRepository.saveAndFlush(pergunta);

        // Get the pergunta
        restPerguntaMockMvc.perform(get("/api/perguntas/{id}", pergunta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pergunta.getId().intValue()))
            .andExpect(jsonPath("$.dsPergunta").value(DEFAULT_DS_PERGUNTA.toString()))
            .andExpect(jsonPath("$.inEditavel").value(DEFAULT_FLAG_EDITAVEL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPergunta() throws Exception {
        // Get the pergunta
        restPerguntaMockMvc.perform(get("/api/perguntas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePergunta() throws Exception {
        // Initialize the database
        perguntaRepository.saveAndFlush(pergunta);
        int databaseSizeBeforeUpdate = perguntaRepository.findAll().size();

        // Update the pergunta
        Pergunta updatedPergunta = perguntaRepository.findOne(pergunta.getId());
        updatedPergunta
                .dsPergunta(UPDATED_DS_PERGUNTA)
                .inEditavel(UPDATED_FLAG_EDITAVEL);
        PerguntaDTO perguntaDTO = perguntaMapper.perguntaToPerguntaDTO(updatedPergunta);

        restPerguntaMockMvc.perform(put("/api/perguntas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perguntaDTO)))
            .andExpect(status().isOk());

        // Validate the Pergunta in the database
        List<Pergunta> perguntaList = perguntaRepository.findAll();
        assertThat(perguntaList).hasSize(databaseSizeBeforeUpdate);
        Pergunta testPergunta = perguntaList.get(perguntaList.size() - 1);
        assertThat(testPergunta.getDsPergunta()).isEqualTo(UPDATED_DS_PERGUNTA);
        assertThat(testPergunta.getInEditavel()).isEqualTo(UPDATED_FLAG_EDITAVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingPergunta() throws Exception {
        int databaseSizeBeforeUpdate = perguntaRepository.findAll().size();

        // Create the Pergunta
        PerguntaDTO perguntaDTO = perguntaMapper.perguntaToPerguntaDTO(pergunta);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPerguntaMockMvc.perform(put("/api/perguntas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perguntaDTO)))
            .andExpect(status().isCreated());

        // Validate the Pergunta in the database
        List<Pergunta> perguntaList = perguntaRepository.findAll();
        assertThat(perguntaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePergunta() throws Exception {
        // Initialize the database
        perguntaRepository.saveAndFlush(pergunta);
        int databaseSizeBeforeDelete = perguntaRepository.findAll().size();

        // Get the pergunta
        restPerguntaMockMvc.perform(delete("/api/perguntas/{id}", pergunta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pergunta> perguntaList = perguntaRepository.findAll();
        assertThat(perguntaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
