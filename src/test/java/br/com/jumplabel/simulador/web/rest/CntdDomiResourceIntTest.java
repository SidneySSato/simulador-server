package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.CntdDomi;
import br.com.jumplabel.simulador.repository.CntdDomiRepository;
import br.com.jumplabel.simulador.service.CntdDomiService;
import br.com.jumplabel.simulador.service.dto.CntdDomiDTO;
import br.com.jumplabel.simulador.service.mapper.CntdDomiMapper;

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
 * Test class for the CntdDomiResource REST controller.
 *
 * @see CntdDomiResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class CntdDomiResourceIntTest {

    private static final String DEFAULT_CD_CNTD_DOMI_LGDO = "AAAAAAAAAA";
    private static final String UPDATED_CD_CNTD_DOMI_LGDO = "BBBBBBBBBB";

    private static final String DEFAULT_DS_CNTD_DOMI_LGDO = "AAAAAAAAAA";
    private static final String UPDATED_DS_CNTD_DOMI_LGDO = "BBBBBBBBBB";

    @Autowired
    private CntdDomiRepository cntdDomiRepository;

    @Autowired
    private CntdDomiMapper cntdDomiMapper;

    @Autowired
    private CntdDomiService cntdDomiService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restCntdDomiMockMvc;

    private CntdDomi cntdDomi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CntdDomiResource cntdDomiResource = new CntdDomiResource(cntdDomiService);
        this.restCntdDomiMockMvc = MockMvcBuilders.standaloneSetup(cntdDomiResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CntdDomi createEntity(EntityManager em) {
        CntdDomi cntdDomi = new CntdDomi()
                .cdCntdDomiLgdo(DEFAULT_CD_CNTD_DOMI_LGDO)
                .dsCntdDomiLgdo(DEFAULT_DS_CNTD_DOMI_LGDO);
        return cntdDomi;
    }

    @Before
    public void initTest() {
        cntdDomi = createEntity(em);
    }

    @Test
    @Transactional
    public void createCntdDomi() throws Exception {
        int databaseSizeBeforeCreate = cntdDomiRepository.findAll().size();

        // Create the CntdDomi
        CntdDomiDTO cntdDomiDTO = cntdDomiMapper.cntdDomiToCntdDomiDTO(cntdDomi);

        restCntdDomiMockMvc.perform(post("/api/cntd-domis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cntdDomiDTO)))
            .andExpect(status().isCreated());

        // Validate the CntdDomi in the database
        List<CntdDomi> cntdDomiList = cntdDomiRepository.findAll();
        assertThat(cntdDomiList).hasSize(databaseSizeBeforeCreate + 1);
        CntdDomi testCntdDomi = cntdDomiList.get(cntdDomiList.size() - 1);
        assertThat(testCntdDomi.getCdCntdDomiLgdo()).isEqualTo(DEFAULT_CD_CNTD_DOMI_LGDO);
        assertThat(testCntdDomi.getDsCntdDomiLgdo()).isEqualTo(DEFAULT_DS_CNTD_DOMI_LGDO);
    }

    @Test
    @Transactional
    public void createCntdDomiWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cntdDomiRepository.findAll().size();

        // Create the CntdDomi with an existing ID
        CntdDomi existingCntdDomi = new CntdDomi();
        existingCntdDomi.setId(1L);
        CntdDomiDTO existingCntdDomiDTO = cntdDomiMapper.cntdDomiToCntdDomiDTO(existingCntdDomi);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCntdDomiMockMvc.perform(post("/api/cntd-domis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingCntdDomiDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CntdDomi> cntdDomiList = cntdDomiRepository.findAll();
        assertThat(cntdDomiList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCntdDomis() throws Exception {
        // Initialize the database
        cntdDomiRepository.saveAndFlush(cntdDomi);

        // Get all the cntdDomiList
        restCntdDomiMockMvc.perform(get("/api/cntd-domis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cntdDomi.getId().intValue())))
            .andExpect(jsonPath("$.[*].cdCntdDomiLgdo").value(hasItem(DEFAULT_CD_CNTD_DOMI_LGDO.toString())))
            .andExpect(jsonPath("$.[*].dsCntdDomiLgdo").value(hasItem(DEFAULT_DS_CNTD_DOMI_LGDO.toString())));
    }

    @Test
    @Transactional
    public void getCntdDomi() throws Exception {
        // Initialize the database
        cntdDomiRepository.saveAndFlush(cntdDomi);

        // Get the cntdDomi
        restCntdDomiMockMvc.perform(get("/api/cntd-domis/{id}", cntdDomi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cntdDomi.getId().intValue()))
            .andExpect(jsonPath("$.cdCntdDomiLgdo").value(DEFAULT_CD_CNTD_DOMI_LGDO.toString()))
            .andExpect(jsonPath("$.dsCntdDomiLgdo").value(DEFAULT_DS_CNTD_DOMI_LGDO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCntdDomi() throws Exception {
        // Get the cntdDomi
        restCntdDomiMockMvc.perform(get("/api/cntd-domis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCntdDomi() throws Exception {
        // Initialize the database
        cntdDomiRepository.saveAndFlush(cntdDomi);
        int databaseSizeBeforeUpdate = cntdDomiRepository.findAll().size();

        // Update the cntdDomi
        CntdDomi updatedCntdDomi = cntdDomiRepository.findOne(cntdDomi.getId());
        updatedCntdDomi
                .cdCntdDomiLgdo(UPDATED_CD_CNTD_DOMI_LGDO)
                .dsCntdDomiLgdo(UPDATED_DS_CNTD_DOMI_LGDO);
        CntdDomiDTO cntdDomiDTO = cntdDomiMapper.cntdDomiToCntdDomiDTO(updatedCntdDomi);

        restCntdDomiMockMvc.perform(put("/api/cntd-domis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cntdDomiDTO)))
            .andExpect(status().isOk());

        // Validate the CntdDomi in the database
        List<CntdDomi> cntdDomiList = cntdDomiRepository.findAll();
        assertThat(cntdDomiList).hasSize(databaseSizeBeforeUpdate);
        CntdDomi testCntdDomi = cntdDomiList.get(cntdDomiList.size() - 1);
        assertThat(testCntdDomi.getCdCntdDomiLgdo()).isEqualTo(UPDATED_CD_CNTD_DOMI_LGDO);
        assertThat(testCntdDomi.getDsCntdDomiLgdo()).isEqualTo(UPDATED_DS_CNTD_DOMI_LGDO);
    }

    @Test
    @Transactional
    public void updateNonExistingCntdDomi() throws Exception {
        int databaseSizeBeforeUpdate = cntdDomiRepository.findAll().size();

        // Create the CntdDomi
        CntdDomiDTO cntdDomiDTO = cntdDomiMapper.cntdDomiToCntdDomiDTO(cntdDomi);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCntdDomiMockMvc.perform(put("/api/cntd-domis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cntdDomiDTO)))
            .andExpect(status().isCreated());

        // Validate the CntdDomi in the database
        List<CntdDomi> cntdDomiList = cntdDomiRepository.findAll();
        assertThat(cntdDomiList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCntdDomi() throws Exception {
        // Initialize the database
        cntdDomiRepository.saveAndFlush(cntdDomi);
        int databaseSizeBeforeDelete = cntdDomiRepository.findAll().size();

        // Get the cntdDomi
        restCntdDomiMockMvc.perform(delete("/api/cntd-domis/{id}", cntdDomi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CntdDomi> cntdDomiList = cntdDomiRepository.findAll();
        assertThat(cntdDomiList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
