package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.CompDomiRamo;
import br.com.jumplabel.simulador.repository.CompDomiRamoRepository;
import br.com.jumplabel.simulador.service.CompDomiRamoService;
import br.com.jumplabel.simulador.service.dto.CompDomiRamoDTO;
import br.com.jumplabel.simulador.service.mapper.CompDomiRamoMapper;

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
 * Test class for the CompDomiRamoResource REST controller.
 *
 * @see CompDomiRamoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class CompDomiRamoResourceIntTest {

    private static final String DEFAULT_IN_RAMO_GENE = "A";
    private static final String UPDATED_IN_RAMO_GENE = "B";

    private static final String DEFAULT_CD_GRUP_SUSEP = "AAAA";
    private static final String UPDATED_CD_GRUP_SUSEP = "BBBB";

    @Autowired
    private CompDomiRamoRepository compDomiRamoRepository;

    @Autowired
    private CompDomiRamoMapper compDomiRamoMapper;

    @Autowired
    private CompDomiRamoService compDomiRamoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restCompDomiRamoMockMvc;

    private CompDomiRamo compDomiRamo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CompDomiRamoResource compDomiRamoResource = new CompDomiRamoResource(compDomiRamoService);
        this.restCompDomiRamoMockMvc = MockMvcBuilders.standaloneSetup(compDomiRamoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompDomiRamo createEntity(EntityManager em) {
        CompDomiRamo compDomiRamo = new CompDomiRamo()
                .inRamoGene(DEFAULT_IN_RAMO_GENE)
                .cdGrupSusep(DEFAULT_CD_GRUP_SUSEP);
        return compDomiRamo;
    }

    @Before
    public void initTest() {
        compDomiRamo = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompDomiRamo() throws Exception {
        int databaseSizeBeforeCreate = compDomiRamoRepository.findAll().size();

        // Create the CompDomiRamo
        CompDomiRamoDTO compDomiRamoDTO = compDomiRamoMapper.compDomiRamoToCompDomiRamoDTO(compDomiRamo);

        restCompDomiRamoMockMvc.perform(post("/api/comp-domi-ramos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compDomiRamoDTO)))
            .andExpect(status().isCreated());

        // Validate the CompDomiRamo in the database
        List<CompDomiRamo> compDomiRamoList = compDomiRamoRepository.findAll();
        assertThat(compDomiRamoList).hasSize(databaseSizeBeforeCreate + 1);
        CompDomiRamo testCompDomiRamo = compDomiRamoList.get(compDomiRamoList.size() - 1);
        assertThat(testCompDomiRamo.getInRamoGene()).isEqualTo(DEFAULT_IN_RAMO_GENE);
        assertThat(testCompDomiRamo.getCdGrupSusep()).isEqualTo(DEFAULT_CD_GRUP_SUSEP);
    }

    @Test
    @Transactional
    public void createCompDomiRamoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = compDomiRamoRepository.findAll().size();

        // Create the CompDomiRamo with an existing ID
        CompDomiRamo existingCompDomiRamo = new CompDomiRamo();
        existingCompDomiRamo.setId(1L);
        CompDomiRamoDTO existingCompDomiRamoDTO = compDomiRamoMapper.compDomiRamoToCompDomiRamoDTO(existingCompDomiRamo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompDomiRamoMockMvc.perform(post("/api/comp-domi-ramos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCompDomiRamoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CompDomiRamo> compDomiRamoList = compDomiRamoRepository.findAll();
        assertThat(compDomiRamoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCompDomiRamos() throws Exception {
        // Initialize the database
        compDomiRamoRepository.saveAndFlush(compDomiRamo);

        // Get all the compDomiRamoList
        restCompDomiRamoMockMvc.perform(get("/api/comp-domi-ramos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(compDomiRamo.getId().intValue())))
            .andExpect(jsonPath("$.[*].inRamoGene").value(hasItem(DEFAULT_IN_RAMO_GENE.toString())))
            .andExpect(jsonPath("$.[*].cdGrupSusep").value(hasItem(DEFAULT_CD_GRUP_SUSEP.toString())));
    }

    @Test
    @Transactional
    public void getCompDomiRamo() throws Exception {
        // Initialize the database
        compDomiRamoRepository.saveAndFlush(compDomiRamo);

        // Get the compDomiRamo
        restCompDomiRamoMockMvc.perform(get("/api/comp-domi-ramos/{id}", compDomiRamo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(compDomiRamo.getId().intValue()))
            .andExpect(jsonPath("$.inRamoGene").value(DEFAULT_IN_RAMO_GENE.toString()))
            .andExpect(jsonPath("$.cdGrupSusep").value(DEFAULT_CD_GRUP_SUSEP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCompDomiRamo() throws Exception {
        // Get the compDomiRamo
        restCompDomiRamoMockMvc.perform(get("/api/comp-domi-ramos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompDomiRamo() throws Exception {
        // Initialize the database
        compDomiRamoRepository.saveAndFlush(compDomiRamo);
        int databaseSizeBeforeUpdate = compDomiRamoRepository.findAll().size();

        // Update the compDomiRamo
        CompDomiRamo updatedCompDomiRamo = compDomiRamoRepository.findOne(compDomiRamo.getId());
        updatedCompDomiRamo
                .inRamoGene(UPDATED_IN_RAMO_GENE)
                .cdGrupSusep(UPDATED_CD_GRUP_SUSEP);
        CompDomiRamoDTO compDomiRamoDTO = compDomiRamoMapper.compDomiRamoToCompDomiRamoDTO(updatedCompDomiRamo);

        restCompDomiRamoMockMvc.perform(put("/api/comp-domi-ramos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compDomiRamoDTO)))
            .andExpect(status().isOk());

        // Validate the CompDomiRamo in the database
        List<CompDomiRamo> compDomiRamoList = compDomiRamoRepository.findAll();
        assertThat(compDomiRamoList).hasSize(databaseSizeBeforeUpdate);
        CompDomiRamo testCompDomiRamo = compDomiRamoList.get(compDomiRamoList.size() - 1);
        assertThat(testCompDomiRamo.getInRamoGene()).isEqualTo(UPDATED_IN_RAMO_GENE);
        assertThat(testCompDomiRamo.getCdGrupSusep()).isEqualTo(UPDATED_CD_GRUP_SUSEP);
    }

    @Test
    @Transactional
    public void updateNonExistingCompDomiRamo() throws Exception {
        int databaseSizeBeforeUpdate = compDomiRamoRepository.findAll().size();

        // Create the CompDomiRamo
        CompDomiRamoDTO compDomiRamoDTO = compDomiRamoMapper.compDomiRamoToCompDomiRamoDTO(compDomiRamo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCompDomiRamoMockMvc.perform(put("/api/comp-domi-ramos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(compDomiRamoDTO)))
            .andExpect(status().isCreated());

        // Validate the CompDomiRamo in the database
        List<CompDomiRamo> compDomiRamoList = compDomiRamoRepository.findAll();
        assertThat(compDomiRamoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCompDomiRamo() throws Exception {
        // Initialize the database
        compDomiRamoRepository.saveAndFlush(compDomiRamo);
        int databaseSizeBeforeDelete = compDomiRamoRepository.findAll().size();

        // Get the compDomiRamo
        restCompDomiRamoMockMvc.perform(delete("/api/comp-domi-ramos/{id}", compDomiRamo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CompDomiRamo> compDomiRamoList = compDomiRamoRepository.findAll();
        assertThat(compDomiRamoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
