package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.ArvoreDecisao;
import br.com.jumplabel.simulador.repository.ArvoreDecisaoRepository;
import br.com.jumplabel.simulador.service.ArvoreDecisaoService;
import br.com.jumplabel.simulador.service.dto.ArvoreDecisaoDTO;
import br.com.jumplabel.simulador.service.mapper.ArvoreDecisaoMapper;

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
 * Test class for the ArvoreDecisaoResource REST controller.
 *
 * @see ArvoreDecisaoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class ArvoreDecisaoResourceIntTest {

    private static final String DEFAULT_DS_ARVORE_DECISAO = "AAAAAAAAAA";
    private static final String UPDATED_DS_ARVORE_DECISAO = "BBBBBBBBBB";

    private static final Long DEFAULT_QT_PRODUTOS = 1L;
    private static final Long UPDATED_QT_PRODUTOS = 2L;

    private static final Long DEFAULT_QT_PLANOS = 1L;
    private static final Long UPDATED_QT_PLANOS = 2L;

    private static final String DEFAULT_CD_SITU_ARVORE = "AAAA";
    private static final String UPDATED_CD_SITU_ARVORE = "BBBB";

    @Autowired
    private ArvoreDecisaoRepository arvoreDecisaoRepository;

    @Autowired
    private ArvoreDecisaoMapper arvoreDecisaoMapper;

    @Autowired
    private ArvoreDecisaoService arvoreDecisaoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restArvoreDecisaoMockMvc;

    private ArvoreDecisao arvoreDecisao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ArvoreDecisaoResource arvoreDecisaoResource = new ArvoreDecisaoResource(arvoreDecisaoService);
        this.restArvoreDecisaoMockMvc = MockMvcBuilders.standaloneSetup(arvoreDecisaoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ArvoreDecisao createEntity(EntityManager em) {
        ArvoreDecisao arvoreDecisao = new ArvoreDecisao()
                .dsArvoreDecisao(DEFAULT_DS_ARVORE_DECISAO)
                .qtProdutos(DEFAULT_QT_PRODUTOS)
                .qtPlanos(DEFAULT_QT_PLANOS)
                .cdSituArvore(DEFAULT_CD_SITU_ARVORE);
        return arvoreDecisao;
    }

    @Before
    public void initTest() {
        arvoreDecisao = createEntity(em);
    }

    @Test
    @Transactional
    public void createArvoreDecisao() throws Exception {
        int databaseSizeBeforeCreate = arvoreDecisaoRepository.findAll().size();

        // Create the ArvoreDecisao
        ArvoreDecisaoDTO arvoreDecisaoDTO = arvoreDecisaoMapper.arvoreDecisaoToArvoreDecisaoDTO(arvoreDecisao);

        restArvoreDecisaoMockMvc.perform(post("/api/arvore-decisaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(arvoreDecisaoDTO)))
            .andExpect(status().isCreated());

        // Validate the ArvoreDecisao in the database
        List<ArvoreDecisao> arvoreDecisaoList = arvoreDecisaoRepository.findAll();
        assertThat(arvoreDecisaoList).hasSize(databaseSizeBeforeCreate + 1);
        ArvoreDecisao testArvoreDecisao = arvoreDecisaoList.get(arvoreDecisaoList.size() - 1);
        assertThat(testArvoreDecisao.getDsArvoreDecisao()).isEqualTo(DEFAULT_DS_ARVORE_DECISAO);
        assertThat(testArvoreDecisao.getQtProdutos()).isEqualTo(DEFAULT_QT_PRODUTOS);
        assertThat(testArvoreDecisao.getQtPlanos()).isEqualTo(DEFAULT_QT_PLANOS);
        assertThat(testArvoreDecisao.getCdSituArvore()).isEqualTo(DEFAULT_CD_SITU_ARVORE);
    }

    @Test
    @Transactional
    public void createArvoreDecisaoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = arvoreDecisaoRepository.findAll().size();

        // Create the ArvoreDecisao with an existing ID
        ArvoreDecisao existingArvoreDecisao = new ArvoreDecisao();
        existingArvoreDecisao.setId(1L);
        ArvoreDecisaoDTO existingArvoreDecisaoDTO = arvoreDecisaoMapper.arvoreDecisaoToArvoreDecisaoDTO(existingArvoreDecisao);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArvoreDecisaoMockMvc.perform(post("/api/arvore-decisaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingArvoreDecisaoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<ArvoreDecisao> arvoreDecisaoList = arvoreDecisaoRepository.findAll();
        assertThat(arvoreDecisaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllArvoreDecisaos() throws Exception {
        // Initialize the database
        arvoreDecisaoRepository.saveAndFlush(arvoreDecisao);

        // Get all the arvoreDecisaoList
        restArvoreDecisaoMockMvc.perform(get("/api/arvore-decisaos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(arvoreDecisao.getId().intValue())))
            .andExpect(jsonPath("$.[*].dsArvoreDecisao").value(hasItem(DEFAULT_DS_ARVORE_DECISAO.toString())))
            .andExpect(jsonPath("$.[*].qtProdutos").value(hasItem(DEFAULT_QT_PRODUTOS.intValue())))
            .andExpect(jsonPath("$.[*].qtPlanos").value(hasItem(DEFAULT_QT_PLANOS.intValue())))
            .andExpect(jsonPath("$.[*].cdSituArvore").value(hasItem(DEFAULT_CD_SITU_ARVORE.toString())));
    }

    @Test
    @Transactional
    public void getArvoreDecisao() throws Exception {
        // Initialize the database
        arvoreDecisaoRepository.saveAndFlush(arvoreDecisao);

        // Get the arvoreDecisao
        restArvoreDecisaoMockMvc.perform(get("/api/arvore-decisaos/{id}", arvoreDecisao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(arvoreDecisao.getId().intValue()))
            .andExpect(jsonPath("$.dsArvoreDecisao").value(DEFAULT_DS_ARVORE_DECISAO.toString()))
            .andExpect(jsonPath("$.qtProdutos").value(DEFAULT_QT_PRODUTOS.intValue()))
            .andExpect(jsonPath("$.qtPlanos").value(DEFAULT_QT_PLANOS.intValue()))
            .andExpect(jsonPath("$.cdSituArvore").value(DEFAULT_CD_SITU_ARVORE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingArvoreDecisao() throws Exception {
        // Get the arvoreDecisao
        restArvoreDecisaoMockMvc.perform(get("/api/arvore-decisaos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArvoreDecisao() throws Exception {
        // Initialize the database
        arvoreDecisaoRepository.saveAndFlush(arvoreDecisao);
        int databaseSizeBeforeUpdate = arvoreDecisaoRepository.findAll().size();

        // Update the arvoreDecisao
        ArvoreDecisao updatedArvoreDecisao = arvoreDecisaoRepository.findOne(arvoreDecisao.getId());
        updatedArvoreDecisao
                .dsArvoreDecisao(UPDATED_DS_ARVORE_DECISAO)
                .qtProdutos(UPDATED_QT_PRODUTOS)
                .qtPlanos(UPDATED_QT_PLANOS)
                .cdSituArvore(UPDATED_CD_SITU_ARVORE);
        ArvoreDecisaoDTO arvoreDecisaoDTO = arvoreDecisaoMapper.arvoreDecisaoToArvoreDecisaoDTO(updatedArvoreDecisao);

        restArvoreDecisaoMockMvc.perform(put("/api/arvore-decisaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(arvoreDecisaoDTO)))
            .andExpect(status().isOk());

        // Validate the ArvoreDecisao in the database
        List<ArvoreDecisao> arvoreDecisaoList = arvoreDecisaoRepository.findAll();
        assertThat(arvoreDecisaoList).hasSize(databaseSizeBeforeUpdate);
        ArvoreDecisao testArvoreDecisao = arvoreDecisaoList.get(arvoreDecisaoList.size() - 1);
        assertThat(testArvoreDecisao.getDsArvoreDecisao()).isEqualTo(UPDATED_DS_ARVORE_DECISAO);
        assertThat(testArvoreDecisao.getQtProdutos()).isEqualTo(UPDATED_QT_PRODUTOS);
        assertThat(testArvoreDecisao.getQtPlanos()).isEqualTo(UPDATED_QT_PLANOS);
        assertThat(testArvoreDecisao.getCdSituArvore()).isEqualTo(UPDATED_CD_SITU_ARVORE);
    }

    @Test
    @Transactional
    public void updateNonExistingArvoreDecisao() throws Exception {
        int databaseSizeBeforeUpdate = arvoreDecisaoRepository.findAll().size();

        // Create the ArvoreDecisao
        ArvoreDecisaoDTO arvoreDecisaoDTO = arvoreDecisaoMapper.arvoreDecisaoToArvoreDecisaoDTO(arvoreDecisao);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restArvoreDecisaoMockMvc.perform(put("/api/arvore-decisaos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(arvoreDecisaoDTO)))
            .andExpect(status().isCreated());

        // Validate the ArvoreDecisao in the database
        List<ArvoreDecisao> arvoreDecisaoList = arvoreDecisaoRepository.findAll();
        assertThat(arvoreDecisaoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteArvoreDecisao() throws Exception {
        // Initialize the database
        arvoreDecisaoRepository.saveAndFlush(arvoreDecisao);
        int databaseSizeBeforeDelete = arvoreDecisaoRepository.findAll().size();

        // Get the arvoreDecisao
        restArvoreDecisaoMockMvc.perform(delete("/api/arvore-decisaos/{id}", arvoreDecisao.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ArvoreDecisao> arvoreDecisaoList = arvoreDecisaoRepository.findAll();
        assertThat(arvoreDecisaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
