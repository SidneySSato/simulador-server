package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.NoArvoreCopia;
import br.com.jumplabel.simulador.repository.NoArvoreCopiaRepository;
import br.com.jumplabel.simulador.service.NoArvoreCopiaService;
import br.com.jumplabel.simulador.service.dto.NoArvoreCopiaDTO;
import br.com.jumplabel.simulador.service.mapper.NoArvoreCopiaMapper;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static br.com.jumplabel.simulador.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the NoArvoreCopiaResource REST controller.
 *
 * @see NoArvoreCopiaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class NoArvoreCopiaResourceIntTest {

    private static final String DEFAULT_DS_NO_ARVORE_COPIA = "AAAAAAAAAA";
    private static final String UPDATED_DS_NO_ARVORE_COPIA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DT_COPIA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DT_COPIA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private NoArvoreCopiaRepository noArvoreCopiaRepository;

    @Autowired
    private NoArvoreCopiaMapper noArvoreCopiaMapper;

    @Autowired
    private NoArvoreCopiaService noArvoreCopiaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restNoArvoreCopiaMockMvc;

    private NoArvoreCopia noArvoreCopia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        NoArvoreCopiaResource noArvoreCopiaResource = new NoArvoreCopiaResource(noArvoreCopiaService);
        this.restNoArvoreCopiaMockMvc = MockMvcBuilders.standaloneSetup(noArvoreCopiaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NoArvoreCopia createEntity(EntityManager em) {
        NoArvoreCopia noArvoreCopia = new NoArvoreCopia()
                .dsNoArvoreCopia(DEFAULT_DS_NO_ARVORE_COPIA)
                .dtCopia(DEFAULT_DT_COPIA);
        return noArvoreCopia;
    }

    @Before
    public void initTest() {
        noArvoreCopia = createEntity(em);
    }

    @Test
    @Transactional
    public void createNoArvoreCopia() throws Exception {
        int databaseSizeBeforeCreate = noArvoreCopiaRepository.findAll().size();

        // Create the NoArvoreCopia
        NoArvoreCopiaDTO noArvoreCopiaDTO = noArvoreCopiaMapper.noArvoreCopiaToNoArvoreCopiaDTO(noArvoreCopia);

        restNoArvoreCopiaMockMvc.perform(post("/api/no-arvore-copias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noArvoreCopiaDTO)))
            .andExpect(status().isCreated());

        // Validate the NoArvoreCopia in the database
        List<NoArvoreCopia> noArvoreCopiaList = noArvoreCopiaRepository.findAll();
        assertThat(noArvoreCopiaList).hasSize(databaseSizeBeforeCreate + 1);
        NoArvoreCopia testNoArvoreCopia = noArvoreCopiaList.get(noArvoreCopiaList.size() - 1);
        assertThat(testNoArvoreCopia.getDsNoArvoreCopia()).isEqualTo(DEFAULT_DS_NO_ARVORE_COPIA);
        assertThat(testNoArvoreCopia.getDtCopia()).isEqualTo(DEFAULT_DT_COPIA);
    }

    @Test
    @Transactional
    public void createNoArvoreCopiaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = noArvoreCopiaRepository.findAll().size();

        // Create the NoArvoreCopia with an existing ID
        NoArvoreCopia existingNoArvoreCopia = new NoArvoreCopia();
        existingNoArvoreCopia.setId(1L);
        NoArvoreCopiaDTO existingNoArvoreCopiaDTO = noArvoreCopiaMapper.noArvoreCopiaToNoArvoreCopiaDTO(existingNoArvoreCopia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNoArvoreCopiaMockMvc.perform(post("/api/no-arvore-copias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingNoArvoreCopiaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<NoArvoreCopia> noArvoreCopiaList = noArvoreCopiaRepository.findAll();
        assertThat(noArvoreCopiaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllNoArvoreCopias() throws Exception {
        // Initialize the database
        noArvoreCopiaRepository.saveAndFlush(noArvoreCopia);

        // Get all the noArvoreCopiaList
        restNoArvoreCopiaMockMvc.perform(get("/api/no-arvore-copias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(noArvoreCopia.getId().intValue())))
            .andExpect(jsonPath("$.[*].dsNoArvoreCopia").value(hasItem(DEFAULT_DS_NO_ARVORE_COPIA.toString())))
            .andExpect(jsonPath("$.[*].dtCopia").value(hasItem(sameInstant(DEFAULT_DT_COPIA))));
    }

    @Test
    @Transactional
    public void getNoArvoreCopia() throws Exception {
        // Initialize the database
        noArvoreCopiaRepository.saveAndFlush(noArvoreCopia);

        // Get the noArvoreCopia
        restNoArvoreCopiaMockMvc.perform(get("/api/no-arvore-copias/{id}", noArvoreCopia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(noArvoreCopia.getId().intValue()))
            .andExpect(jsonPath("$.dsNoArvoreCopia").value(DEFAULT_DS_NO_ARVORE_COPIA.toString()))
            .andExpect(jsonPath("$.dtCopia").value(sameInstant(DEFAULT_DT_COPIA)));
    }

    @Test
    @Transactional
    public void getNonExistingNoArvoreCopia() throws Exception {
        // Get the noArvoreCopia
        restNoArvoreCopiaMockMvc.perform(get("/api/no-arvore-copias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNoArvoreCopia() throws Exception {
        // Initialize the database
        noArvoreCopiaRepository.saveAndFlush(noArvoreCopia);
        int databaseSizeBeforeUpdate = noArvoreCopiaRepository.findAll().size();

        // Update the noArvoreCopia
        NoArvoreCopia updatedNoArvoreCopia = noArvoreCopiaRepository.findOne(noArvoreCopia.getId());
        updatedNoArvoreCopia
                .dsNoArvoreCopia(UPDATED_DS_NO_ARVORE_COPIA)
                .dtCopia(UPDATED_DT_COPIA);
        NoArvoreCopiaDTO noArvoreCopiaDTO = noArvoreCopiaMapper.noArvoreCopiaToNoArvoreCopiaDTO(updatedNoArvoreCopia);

        restNoArvoreCopiaMockMvc.perform(put("/api/no-arvore-copias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noArvoreCopiaDTO)))
            .andExpect(status().isOk());

        // Validate the NoArvoreCopia in the database
        List<NoArvoreCopia> noArvoreCopiaList = noArvoreCopiaRepository.findAll();
        assertThat(noArvoreCopiaList).hasSize(databaseSizeBeforeUpdate);
        NoArvoreCopia testNoArvoreCopia = noArvoreCopiaList.get(noArvoreCopiaList.size() - 1);
        assertThat(testNoArvoreCopia.getDsNoArvoreCopia()).isEqualTo(UPDATED_DS_NO_ARVORE_COPIA);
        assertThat(testNoArvoreCopia.getDtCopia()).isEqualTo(UPDATED_DT_COPIA);
    }

    @Test
    @Transactional
    public void updateNonExistingNoArvoreCopia() throws Exception {
        int databaseSizeBeforeUpdate = noArvoreCopiaRepository.findAll().size();

        // Create the NoArvoreCopia
        NoArvoreCopiaDTO noArvoreCopiaDTO = noArvoreCopiaMapper.noArvoreCopiaToNoArvoreCopiaDTO(noArvoreCopia);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNoArvoreCopiaMockMvc.perform(put("/api/no-arvore-copias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(noArvoreCopiaDTO)))
            .andExpect(status().isCreated());

        // Validate the NoArvoreCopia in the database
        List<NoArvoreCopia> noArvoreCopiaList = noArvoreCopiaRepository.findAll();
        assertThat(noArvoreCopiaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNoArvoreCopia() throws Exception {
        // Initialize the database
        noArvoreCopiaRepository.saveAndFlush(noArvoreCopia);
        int databaseSizeBeforeDelete = noArvoreCopiaRepository.findAll().size();

        // Get the noArvoreCopia
        restNoArvoreCopiaMockMvc.perform(delete("/api/no-arvore-copias/{id}", noArvoreCopia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NoArvoreCopia> noArvoreCopiaList = noArvoreCopiaRepository.findAll();
        assertThat(noArvoreCopiaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
