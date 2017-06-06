package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.NoArvore;
import br.com.jumplabel.simulador.repository.NoArvoreRepository;
import br.com.jumplabel.simulador.service.ArvoreDecisaoService;
import br.com.jumplabel.simulador.service.NoArvoreService;
import br.com.jumplabel.simulador.service.dto.NoArvoreDTO;
import br.com.jumplabel.simulador.service.mapper.NoArvoreMapper;

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
 * Test class for the NoArvoreResource REST controller.
 *
 * @see NoArvoreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class NoArvoreResourceIntTest {

    private static final String DEFAULT_CD_TIPO_NO = "AAAA";
    private static final String UPDATED_CD_TIPO_NO = "BBBB";

    @Autowired
    private NoArvoreRepository noArvoreRepository;

    @Autowired
    private NoArvoreMapper noArvoreMapper;

    @Autowired
    private NoArvoreService noArvoreService;

    @Autowired
    private ArvoreDecisaoService arvoreDecisaoService;
    
    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restNoArvoreMockMvc;

    private NoArvore noArvore;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NoArvoreResource noArvoreResource = new NoArvoreResource(noArvoreService, arvoreDecisaoService);
        this.restNoArvoreMockMvc = MockMvcBuilders.standaloneSetup(noArvoreResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoArvore createEntity(EntityManager em) {
        NoArvore noArvore = new NoArvore()
                .cdTipoNo(DEFAULT_CD_TIPO_NO);
        return noArvore;
    }

    @Before
    public void initTest() {
        noArvore = createEntity(em);
    }

    @Test
    @Transactional
    public void createNoArvore() throws Exception {
        int databaseSizeBeforeCreate = noArvoreRepository.findAll().size();

        // Create the NoArvore
        NoArvoreDTO noArvoreDTO = noArvoreMapper.noArvoreToNoArvoreDTO(noArvore);

        restNoArvoreMockMvc.perform(post("/api/no-arvores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noArvoreDTO)))
            .andExpect(status().isCreated());

        // Validate the NoArvore in the database
        List<NoArvore> noArvoreList = noArvoreRepository.findAll();
        assertThat(noArvoreList).hasSize(databaseSizeBeforeCreate + 1);
        NoArvore testNoArvore = noArvoreList.get(noArvoreList.size() - 1);
        assertThat(testNoArvore.getCdTipoNo()).isEqualTo(DEFAULT_CD_TIPO_NO);
    }

    @Test
    @Transactional
    public void createNoArvoreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noArvoreRepository.findAll().size();

        // Create the NoArvore with an existing ID
        NoArvore existingNoArvore = new NoArvore();
        existingNoArvore.setId(1L);
        NoArvoreDTO existingNoArvoreDTO = noArvoreMapper.noArvoreToNoArvoreDTO(existingNoArvore);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoArvoreMockMvc.perform(post("/api/no-arvores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingNoArvoreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<NoArvore> noArvoreList = noArvoreRepository.findAll();
        assertThat(noArvoreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNoArvores() throws Exception {
        // Initialize the database
        noArvoreRepository.saveAndFlush(noArvore);

        // Get all the noArvoreList
        restNoArvoreMockMvc.perform(get("/api/no-arvores?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noArvore.getId().intValue())))
            .andExpect(jsonPath("$.[*].cdTipoNo").value(hasItem(DEFAULT_CD_TIPO_NO.toString())));
    }

    @Test
    @Transactional
    public void getNoArvore() throws Exception {
        // Initialize the database
        noArvoreRepository.saveAndFlush(noArvore);

        // Get the noArvore
        restNoArvoreMockMvc.perform(get("/api/no-arvores/{id}", noArvore.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(noArvore.getId().intValue()))
            .andExpect(jsonPath("$.cdTipoNo").value(DEFAULT_CD_TIPO_NO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNoArvore() throws Exception {
        // Get the noArvore
        restNoArvoreMockMvc.perform(get("/api/no-arvores/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNoArvore() throws Exception {
        // Initialize the database
        noArvoreRepository.saveAndFlush(noArvore);
        int databaseSizeBeforeUpdate = noArvoreRepository.findAll().size();

        // Update the noArvore
        NoArvore updatedNoArvore = noArvoreRepository.findOne(noArvore.getId());
        updatedNoArvore
                .cdTipoNo(UPDATED_CD_TIPO_NO);
        NoArvoreDTO noArvoreDTO = noArvoreMapper.noArvoreToNoArvoreDTO(updatedNoArvore);

        restNoArvoreMockMvc.perform(put("/api/no-arvores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noArvoreDTO)))
            .andExpect(status().isOk());

        // Validate the NoArvore in the database
        List<NoArvore> noArvoreList = noArvoreRepository.findAll();
        assertThat(noArvoreList).hasSize(databaseSizeBeforeUpdate);
        NoArvore testNoArvore = noArvoreList.get(noArvoreList.size() - 1);
        assertThat(testNoArvore.getCdTipoNo()).isEqualTo(UPDATED_CD_TIPO_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingNoArvore() throws Exception {
        int databaseSizeBeforeUpdate = noArvoreRepository.findAll().size();

        // Create the NoArvore
        NoArvoreDTO noArvoreDTO = noArvoreMapper.noArvoreToNoArvoreDTO(noArvore);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNoArvoreMockMvc.perform(put("/api/no-arvores")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noArvoreDTO)))
            .andExpect(status().isCreated());

        // Validate the NoArvore in the database
        List<NoArvore> noArvoreList = noArvoreRepository.findAll();
        assertThat(noArvoreList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNoArvore() throws Exception {
        // Initialize the database
        noArvoreRepository.saveAndFlush(noArvore);
        int databaseSizeBeforeDelete = noArvoreRepository.findAll().size();

        // Get the noArvore
        restNoArvoreMockMvc.perform(delete("/api/no-arvores/{id}", noArvore.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NoArvore> noArvoreList = noArvoreRepository.findAll();
        assertThat(noArvoreList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
