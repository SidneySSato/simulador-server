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
import br.com.jumplabel.simulador.domain.RangeResposta;
import br.com.jumplabel.simulador.repository.RangeRespostaRepository;
import br.com.jumplabel.simulador.service.RangeRespostaService;
import br.com.jumplabel.simulador.service.dto.RangeRespostaDTO;
import br.com.jumplabel.simulador.service.mapper.RangeRespostaMapper;

/**
 * Test class for the RangeRespostaResource REST controller.
 *
 * @see RangeRespostaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class RangeRespostaResourceIntTest {

    private static final Long DEFAULT_RANGE_INICIO = 1L;
    private static final Long UPDATED_RANGE_INICIO = 2L;

    private static final Long DEFAULT_RANGE_FINAL = 1L;
    private static final Long UPDATED_RANGE_FINAL = 2L;

    private static final String DEFAULT_OPERADOR = "AAA";

    @Autowired
    private RangeRespostaRepository rangeRespostaRepository;

    @Autowired
    private RangeRespostaMapper rangeRespostaMapper;

    @Autowired
    private RangeRespostaService rangeRespostaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restRangeRespostaMockMvc;

    private RangeResposta rangeResposta;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RangeRespostaResource rangeRespostaResource = new RangeRespostaResource(rangeRespostaService);
        this.restRangeRespostaMockMvc = MockMvcBuilders.standaloneSetup(rangeRespostaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RangeResposta createEntity(EntityManager em) {
        RangeResposta rangeResposta = new RangeResposta()
                .rangeInicio(DEFAULT_RANGE_INICIO)
                .rangeFinal(DEFAULT_RANGE_FINAL);
        return rangeResposta;
    }

    @Before
    public void initTest() {
        rangeResposta = createEntity(em);
    }

    @Test
    @Transactional
    public void createRangeResposta() throws Exception {
        int databaseSizeBeforeCreate = rangeRespostaRepository.findAll().size();

        // Create the RangeResposta
        RangeRespostaDTO rangeRespostaDTO = rangeRespostaMapper.rangeRespostaToRangeRespostaDTO(rangeResposta);

        restRangeRespostaMockMvc.perform(post("/api/range-respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeRespostaDTO)))
            .andExpect(status().isCreated());

        // Validate the RangeResposta in the database
        List<RangeResposta> rangeRespostaList = rangeRespostaRepository.findAll();
        assertThat(rangeRespostaList).hasSize(databaseSizeBeforeCreate + 1);
        RangeResposta testRangeResposta = rangeRespostaList.get(rangeRespostaList.size() - 1);
        assertThat(testRangeResposta.getRangeInicio()).isEqualTo(DEFAULT_RANGE_INICIO);
        assertThat(testRangeResposta.getRangeFinal()).isEqualTo(DEFAULT_RANGE_FINAL);
    }

    @Test
    @Transactional
    public void createRangeRespostaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rangeRespostaRepository.findAll().size();

        // Create the RangeResposta with an existing ID
        RangeResposta existingRangeResposta = new RangeResposta();
        existingRangeResposta.setId(1L);
        RangeRespostaDTO existingRangeRespostaDTO = rangeRespostaMapper.rangeRespostaToRangeRespostaDTO(existingRangeResposta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRangeRespostaMockMvc.perform(post("/api/range-respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRangeRespostaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RangeResposta> rangeRespostaList = rangeRespostaRepository.findAll();
        assertThat(rangeRespostaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRangeRespostas() throws Exception {
        // Initialize the database
        rangeRespostaRepository.saveAndFlush(rangeResposta);

        // Get all the rangeRespostaList
        restRangeRespostaMockMvc.perform(get("/api/range-respostas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rangeResposta.getId().intValue())))
            .andExpect(jsonPath("$.[*].rangeInicio").value(hasItem(DEFAULT_RANGE_INICIO.intValue())))
            .andExpect(jsonPath("$.[*].rangeFinal").value(hasItem(DEFAULT_RANGE_FINAL.intValue())))
            .andExpect(jsonPath("$.[*].operador").value(hasItem(DEFAULT_OPERADOR.toString())));
    }

    @Test
    @Transactional
    public void getRangeResposta() throws Exception {
        // Initialize the database
        rangeRespostaRepository.saveAndFlush(rangeResposta);

        // Get the rangeResposta
        restRangeRespostaMockMvc.perform(get("/api/range-respostas/{id}", rangeResposta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rangeResposta.getId().intValue()))
            .andExpect(jsonPath("$.rangeInicio").value(DEFAULT_RANGE_INICIO.intValue()))
            .andExpect(jsonPath("$.rangeFinal").value(DEFAULT_RANGE_FINAL.intValue()))
            .andExpect(jsonPath("$.operador").value(DEFAULT_OPERADOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRangeResposta() throws Exception {
        // Get the rangeResposta
        restRangeRespostaMockMvc.perform(get("/api/range-respostas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRangeResposta() throws Exception {
        // Initialize the database
        rangeRespostaRepository.saveAndFlush(rangeResposta);
        int databaseSizeBeforeUpdate = rangeRespostaRepository.findAll().size();

        // Update the rangeResposta
        RangeResposta updatedRangeResposta = rangeRespostaRepository.findOne(rangeResposta.getId());
        updatedRangeResposta
                .rangeInicio(UPDATED_RANGE_INICIO)
                .rangeFinal(UPDATED_RANGE_FINAL);
        RangeRespostaDTO rangeRespostaDTO = rangeRespostaMapper.rangeRespostaToRangeRespostaDTO(updatedRangeResposta);

        restRangeRespostaMockMvc.perform(put("/api/range-respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeRespostaDTO)))
            .andExpect(status().isOk());

        // Validate the RangeResposta in the database
        List<RangeResposta> rangeRespostaList = rangeRespostaRepository.findAll();
        assertThat(rangeRespostaList).hasSize(databaseSizeBeforeUpdate);
        RangeResposta testRangeResposta = rangeRespostaList.get(rangeRespostaList.size() - 1);
        assertThat(testRangeResposta.getRangeInicio()).isEqualTo(UPDATED_RANGE_INICIO);
        assertThat(testRangeResposta.getRangeFinal()).isEqualTo(UPDATED_RANGE_FINAL);
    }

    @Test
    @Transactional
    public void updateNonExistingRangeResposta() throws Exception {
        int databaseSizeBeforeUpdate = rangeRespostaRepository.findAll().size();

        // Create the RangeResposta
        RangeRespostaDTO rangeRespostaDTO = rangeRespostaMapper.rangeRespostaToRangeRespostaDTO(rangeResposta);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRangeRespostaMockMvc.perform(put("/api/range-respostas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rangeRespostaDTO)))
            .andExpect(status().isCreated());

        // Validate the RangeResposta in the database
        List<RangeResposta> rangeRespostaList = rangeRespostaRepository.findAll();
        assertThat(rangeRespostaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRangeResposta() throws Exception {
        // Initialize the database
        rangeRespostaRepository.saveAndFlush(rangeResposta);
        int databaseSizeBeforeDelete = rangeRespostaRepository.findAll().size();

        // Get the rangeResposta
        restRangeRespostaMockMvc.perform(delete("/api/range-respostas/{id}", rangeResposta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RangeResposta> rangeRespostaList = rangeRespostaRepository.findAll();
        assertThat(rangeRespostaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
