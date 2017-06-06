package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.CompDomiSegd;
import br.com.jumplabel.simulador.repository.CompDomiSegdRepository;
import br.com.jumplabel.simulador.service.CompDomiSegdService;
import br.com.jumplabel.simulador.service.dto.CompDomiSegdDTO;
import br.com.jumplabel.simulador.service.mapper.CompDomiSegdMapper;

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
 * Test class for the CompDomiSegdResource REST controller.
 *
 * @see CompDomiSegdResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class CompDomiSegdResourceIntTest {

    private static final Long DEFAULT_NR_CNPJ_SEGD = 1L;
    private static final Long UPDATED_NR_CNPJ_SEGD = 2L;

    private static final String DEFAULT_CD_SUSEP_SEGD = "AAAAA";
    private static final String UPDATED_CD_SUSEP_SEGD = "BBBBB";

    @Autowired
    private CompDomiSegdRepository compDomiSegdRepository;

    @Autowired
    private CompDomiSegdMapper compDomiSegdMapper;

    @Autowired
    private CompDomiSegdService compDomiSegdService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restCompDomiSegdMockMvc;

    private CompDomiSegd compDomiSegd;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CompDomiSegdResource compDomiSegdResource = new CompDomiSegdResource(compDomiSegdService);
        this.restCompDomiSegdMockMvc = MockMvcBuilders.standaloneSetup(compDomiSegdResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompDomiSegd createEntity(EntityManager em) {
        CompDomiSegd compDomiSegd = new CompDomiSegd()
                .nrCnpjSegd(DEFAULT_NR_CNPJ_SEGD)
                .cdSusepSegd(DEFAULT_CD_SUSEP_SEGD);
        return compDomiSegd;
    }

    @Before
    public void initTest() {
        compDomiSegd = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompDomiSegd() throws Exception {
        int databaseSizeBeforeCreate = compDomiSegdRepository.findAll().size();

        // Create the CompDomiSegd
        CompDomiSegdDTO compDomiSegdDTO = compDomiSegdMapper.compDomiSegdToCompDomiSegdDTO(compDomiSegd);

        restCompDomiSegdMockMvc.perform(post("/api/comp-domi-segds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compDomiSegdDTO)))
            .andExpect(status().isCreated());

        // Validate the CompDomiSegd in the database
        List<CompDomiSegd> compDomiSegdList = compDomiSegdRepository.findAll();
        assertThat(compDomiSegdList).hasSize(databaseSizeBeforeCreate + 1);
        CompDomiSegd testCompDomiSegd = compDomiSegdList.get(compDomiSegdList.size() - 1);
        assertThat(testCompDomiSegd.getNrCnpjSegd()).isEqualTo(DEFAULT_NR_CNPJ_SEGD);
        assertThat(testCompDomiSegd.getCdSusepSegd()).isEqualTo(DEFAULT_CD_SUSEP_SEGD);
    }

    @Test
    @Transactional
    public void createCompDomiSegdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = compDomiSegdRepository.findAll().size();

        // Create the CompDomiSegd with an existing ID
        CompDomiSegd existingCompDomiSegd = new CompDomiSegd();
        existingCompDomiSegd.setId(1L);
        CompDomiSegdDTO existingCompDomiSegdDTO = compDomiSegdMapper.compDomiSegdToCompDomiSegdDTO(existingCompDomiSegd);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompDomiSegdMockMvc.perform(post("/api/comp-domi-segds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCompDomiSegdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CompDomiSegd> compDomiSegdList = compDomiSegdRepository.findAll();
        assertThat(compDomiSegdList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCompDomiSegds() throws Exception {
        // Initialize the database
        compDomiSegdRepository.saveAndFlush(compDomiSegd);

        // Get all the compDomiSegdList
        restCompDomiSegdMockMvc.perform(get("/api/comp-domi-segds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(compDomiSegd.getId().intValue())))
            .andExpect(jsonPath("$.[*].nrCnpjSegd").value(hasItem(DEFAULT_NR_CNPJ_SEGD.intValue())))
            .andExpect(jsonPath("$.[*].cdSusepSegd").value(hasItem(DEFAULT_CD_SUSEP_SEGD.toString())));
    }

    @Test
    @Transactional
    public void getCompDomiSegd() throws Exception {
        // Initialize the database
        compDomiSegdRepository.saveAndFlush(compDomiSegd);

        // Get the compDomiSegd
        restCompDomiSegdMockMvc.perform(get("/api/comp-domi-segds/{id}", compDomiSegd.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(compDomiSegd.getId().intValue()))
            .andExpect(jsonPath("$.nrCnpjSegd").value(DEFAULT_NR_CNPJ_SEGD.intValue()))
            .andExpect(jsonPath("$.cdSusepSegd").value(DEFAULT_CD_SUSEP_SEGD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCompDomiSegd() throws Exception {
        // Get the compDomiSegd
        restCompDomiSegdMockMvc.perform(get("/api/comp-domi-segds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompDomiSegd() throws Exception {
        // Initialize the database
        compDomiSegdRepository.saveAndFlush(compDomiSegd);
        int databaseSizeBeforeUpdate = compDomiSegdRepository.findAll().size();

        // Update the compDomiSegd
        CompDomiSegd updatedCompDomiSegd = compDomiSegdRepository.findOne(compDomiSegd.getId());
        updatedCompDomiSegd
                .nrCnpjSegd(UPDATED_NR_CNPJ_SEGD)
                .cdSusepSegd(UPDATED_CD_SUSEP_SEGD);
        CompDomiSegdDTO compDomiSegdDTO = compDomiSegdMapper.compDomiSegdToCompDomiSegdDTO(updatedCompDomiSegd);

        restCompDomiSegdMockMvc.perform(put("/api/comp-domi-segds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compDomiSegdDTO)))
            .andExpect(status().isOk());

        // Validate the CompDomiSegd in the database
        List<CompDomiSegd> compDomiSegdList = compDomiSegdRepository.findAll();
        assertThat(compDomiSegdList).hasSize(databaseSizeBeforeUpdate);
        CompDomiSegd testCompDomiSegd = compDomiSegdList.get(compDomiSegdList.size() - 1);
        assertThat(testCompDomiSegd.getNrCnpjSegd()).isEqualTo(UPDATED_NR_CNPJ_SEGD);
        assertThat(testCompDomiSegd.getCdSusepSegd()).isEqualTo(UPDATED_CD_SUSEP_SEGD);
    }

    @Test
    @Transactional
    public void updateNonExistingCompDomiSegd() throws Exception {
        int databaseSizeBeforeUpdate = compDomiSegdRepository.findAll().size();

        // Create the CompDomiSegd
        CompDomiSegdDTO compDomiSegdDTO = compDomiSegdMapper.compDomiSegdToCompDomiSegdDTO(compDomiSegd);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompDomiSegdMockMvc.perform(put("/api/comp-domi-segds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compDomiSegdDTO)))
            .andExpect(status().isCreated());

        // Validate the CompDomiSegd in the database
        List<CompDomiSegd> compDomiSegdList = compDomiSegdRepository.findAll();
        assertThat(compDomiSegdList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCompDomiSegd() throws Exception {
        // Initialize the database
        compDomiSegdRepository.saveAndFlush(compDomiSegd);
        int databaseSizeBeforeDelete = compDomiSegdRepository.findAll().size();

        // Get the compDomiSegd
        restCompDomiSegdMockMvc.perform(delete("/api/comp-domi-segds/{id}", compDomiSegd.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CompDomiSegd> compDomiSegdList = compDomiSegdRepository.findAll();
        assertThat(compDomiSegdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
