package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.RespostaIs;
import br.com.jumplabel.simulador.repository.RespostaIsRepository;
import br.com.jumplabel.simulador.service.RespostaIsService;
import br.com.jumplabel.simulador.service.dto.RespostaIsDTO;
import br.com.jumplabel.simulador.service.mapper.RespostaIsMapper;

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
 * Test class for the RespostaIsResource REST controller.
 *
 * @see RespostaIsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class RespostaIsResourceIntTest {

    private static final String DEFAULT_CD_TIPO_IS = "AAAA";
    private static final String UPDATED_CD_TIPO_IS = "BBBB";

    @Autowired
    private RespostaIsRepository respostaIsRepository;

    @Autowired
    private RespostaIsMapper respostaIsMapper;

    @Autowired
    private RespostaIsService respostaIsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restRespostaIsMockMvc;

    private RespostaIs respostaIs;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RespostaIsResource respostaIsResource = new RespostaIsResource(respostaIsService);
        this.restRespostaIsMockMvc = MockMvcBuilders.standaloneSetup(respostaIsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RespostaIs createEntity(EntityManager em) {
        RespostaIs respostaIs = new RespostaIs()
                .cdTipoIs(DEFAULT_CD_TIPO_IS);
        return respostaIs;
    }

    @Before
    public void initTest() {
        respostaIs = createEntity(em);
    }

    @Test
    @Transactional
    public void createRespostaIs() throws Exception {
        int databaseSizeBeforeCreate = respostaIsRepository.findAll().size();

        // Create the RespostaIs
        RespostaIsDTO respostaIsDTO = respostaIsMapper.respostaIsToRespostaIsDTO(respostaIs);

        restRespostaIsMockMvc.perform(post("/api/resposta-is")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaIsDTO)))
            .andExpect(status().isCreated());

        // Validate the RespostaIs in the database
        List<RespostaIs> respostaIsList = respostaIsRepository.findAll();
        assertThat(respostaIsList).hasSize(databaseSizeBeforeCreate + 1);
        RespostaIs testRespostaIs = respostaIsList.get(respostaIsList.size() - 1);
        assertThat(testRespostaIs.getCdTipoIs()).isEqualTo(DEFAULT_CD_TIPO_IS);
    }

    @Test
    @Transactional
    public void createRespostaIsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = respostaIsRepository.findAll().size();

        // Create the RespostaIs with an existing ID
        RespostaIs existingRespostaIs = new RespostaIs();
        existingRespostaIs.setId(1L);
        RespostaIsDTO existingRespostaIsDTO = respostaIsMapper.respostaIsToRespostaIsDTO(existingRespostaIs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRespostaIsMockMvc.perform(post("/api/resposta-is")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingRespostaIsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RespostaIs> respostaIsList = respostaIsRepository.findAll();
        assertThat(respostaIsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRespostaIs() throws Exception {
        // Initialize the database
        respostaIsRepository.saveAndFlush(respostaIs);

        // Get all the respostaIsList
        restRespostaIsMockMvc.perform(get("/api/resposta-is?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(respostaIs.getId().intValue())))
            .andExpect(jsonPath("$.[*].cdTipoIs").value(hasItem(DEFAULT_CD_TIPO_IS.toString())));
    }

    @Test
    @Transactional
    public void getRespostaIs() throws Exception {
        // Initialize the database
        respostaIsRepository.saveAndFlush(respostaIs);

        // Get the respostaIs
        restRespostaIsMockMvc.perform(get("/api/resposta-is/{id}", respostaIs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(respostaIs.getId().intValue()))
            .andExpect(jsonPath("$.cdTipoIs").value(DEFAULT_CD_TIPO_IS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRespostaIs() throws Exception {
        // Get the respostaIs
        restRespostaIsMockMvc.perform(get("/api/resposta-is/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRespostaIs() throws Exception {
        // Initialize the database
        respostaIsRepository.saveAndFlush(respostaIs);
        int databaseSizeBeforeUpdate = respostaIsRepository.findAll().size();

        // Update the respostaIs
        RespostaIs updatedRespostaIs = respostaIsRepository.findOne(respostaIs.getId());
        updatedRespostaIs
                .cdTipoIs(UPDATED_CD_TIPO_IS);
        RespostaIsDTO respostaIsDTO = respostaIsMapper.respostaIsToRespostaIsDTO(updatedRespostaIs);

        restRespostaIsMockMvc.perform(put("/api/resposta-is")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaIsDTO)))
            .andExpect(status().isOk());

        // Validate the RespostaIs in the database
        List<RespostaIs> respostaIsList = respostaIsRepository.findAll();
        assertThat(respostaIsList).hasSize(databaseSizeBeforeUpdate);
        RespostaIs testRespostaIs = respostaIsList.get(respostaIsList.size() - 1);
        assertThat(testRespostaIs.getCdTipoIs()).isEqualTo(UPDATED_CD_TIPO_IS);
    }

    @Test
    @Transactional
    public void updateNonExistingRespostaIs() throws Exception {
        int databaseSizeBeforeUpdate = respostaIsRepository.findAll().size();

        // Create the RespostaIs
        RespostaIsDTO respostaIsDTO = respostaIsMapper.respostaIsToRespostaIsDTO(respostaIs);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRespostaIsMockMvc.perform(put("/api/resposta-is")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(respostaIsDTO)))
            .andExpect(status().isCreated());

        // Validate the RespostaIs in the database
        List<RespostaIs> respostaIsList = respostaIsRepository.findAll();
        assertThat(respostaIsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRespostaIs() throws Exception {
        // Initialize the database
        respostaIsRepository.saveAndFlush(respostaIs);
        int databaseSizeBeforeDelete = respostaIsRepository.findAll().size();

        // Get the respostaIs
        restRespostaIsMockMvc.perform(delete("/api/resposta-is/{id}", respostaIs.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RespostaIs> respostaIsList = respostaIsRepository.findAll();
        assertThat(respostaIsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
