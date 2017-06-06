package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.Domi;
import br.com.jumplabel.simulador.repository.DomiRepository;
import br.com.jumplabel.simulador.service.DomiService;
import br.com.jumplabel.simulador.service.dto.DomiDTO;
import br.com.jumplabel.simulador.service.mapper.DomiMapper;

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
 * Test class for the DomiResource REST controller.
 *
 * @see DomiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class DomiResourceIntTest {

    private static final String DEFAULT_NM_DOMI = "AAAAAAAAAA";
    private static final String UPDATED_NM_DOMI = "BBBBBBBBBB";

    @Autowired
    private DomiRepository domiRepository;

    @Autowired
    private DomiMapper domiMapper;

    @Autowired
    private DomiService domiService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restDomiMockMvc;

    private Domi domi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DomiResource domiResource = new DomiResource(domiService);
        this.restDomiMockMvc = MockMvcBuilders.standaloneSetup(domiResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Domi createEntity(EntityManager em) {
        Domi domi = new Domi()
                .nmDomi(DEFAULT_NM_DOMI);
        return domi;
    }

    @Before
    public void initTest() {
        domi = createEntity(em);
    }

    @Test
    @Transactional
    public void createDomi() throws Exception {
        int databaseSizeBeforeCreate = domiRepository.findAll().size();

        // Create the Domi
        DomiDTO domiDTO = domiMapper.domiToDomiDTO(domi);

        restDomiMockMvc.perform(post("/api/domis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domiDTO)))
            .andExpect(status().isCreated());

        // Validate the Domi in the database
        List<Domi> domiList = domiRepository.findAll();
        assertThat(domiList).hasSize(databaseSizeBeforeCreate + 1);
        Domi testDomi = domiList.get(domiList.size() - 1);
        assertThat(testDomi.getNmDomi()).isEqualTo(DEFAULT_NM_DOMI);
    }

    @Test
    @Transactional
    public void createDomiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = domiRepository.findAll().size();

        // Create the Domi with an existing ID
        Domi existingDomi = new Domi();
        existingDomi.setId(1L);
        DomiDTO existingDomiDTO = domiMapper.domiToDomiDTO(existingDomi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDomiMockMvc.perform(post("/api/domis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingDomiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Domi> domiList = domiRepository.findAll();
        assertThat(domiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDomis() throws Exception {
        // Initialize the database
        domiRepository.saveAndFlush(domi);

        // Get all the domiList
        restDomiMockMvc.perform(get("/api/domis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(domi.getId().intValue())))
            .andExpect(jsonPath("$.[*].nmDomi").value(hasItem(DEFAULT_NM_DOMI.toString())));
    }

    @Test
    @Transactional
    public void getDomi() throws Exception {
        // Initialize the database
        domiRepository.saveAndFlush(domi);

        // Get the domi
        restDomiMockMvc.perform(get("/api/domis/{id}", domi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(domi.getId().intValue()))
            .andExpect(jsonPath("$.nmDomi").value(DEFAULT_NM_DOMI.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDomi() throws Exception {
        // Get the domi
        restDomiMockMvc.perform(get("/api/domis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDomi() throws Exception {
        // Initialize the database
        domiRepository.saveAndFlush(domi);
        int databaseSizeBeforeUpdate = domiRepository.findAll().size();

        // Update the domi
        Domi updatedDomi = domiRepository.findOne(domi.getId());
        updatedDomi
                .nmDomi(UPDATED_NM_DOMI);
        DomiDTO domiDTO = domiMapper.domiToDomiDTO(updatedDomi);

        restDomiMockMvc.perform(put("/api/domis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domiDTO)))
            .andExpect(status().isOk());

        // Validate the Domi in the database
        List<Domi> domiList = domiRepository.findAll();
        assertThat(domiList).hasSize(databaseSizeBeforeUpdate);
        Domi testDomi = domiList.get(domiList.size() - 1);
        assertThat(testDomi.getNmDomi()).isEqualTo(UPDATED_NM_DOMI);
    }

    @Test
    @Transactional
    public void updateNonExistingDomi() throws Exception {
        int databaseSizeBeforeUpdate = domiRepository.findAll().size();

        // Create the Domi
        DomiDTO domiDTO = domiMapper.domiToDomiDTO(domi);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDomiMockMvc.perform(put("/api/domis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(domiDTO)))
            .andExpect(status().isCreated());

        // Validate the Domi in the database
        List<Domi> domiList = domiRepository.findAll();
        assertThat(domiList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDomi() throws Exception {
        // Initialize the database
        domiRepository.saveAndFlush(domi);
        int databaseSizeBeforeDelete = domiRepository.findAll().size();

        // Get the domi
        restDomiMockMvc.perform(delete("/api/domis/{id}", domi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Domi> domiList = domiRepository.findAll();
        assertThat(domiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
