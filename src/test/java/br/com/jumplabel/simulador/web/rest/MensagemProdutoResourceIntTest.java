package br.com.jumplabel.simulador.web.rest;

import br.com.jumplabel.simulador.SimuladorServerApp;

import br.com.jumplabel.simulador.domain.MensagemProduto;
import br.com.jumplabel.simulador.repository.MensagemProdutoRepository;
import br.com.jumplabel.simulador.service.MensagemProdutoService;
import br.com.jumplabel.simulador.service.dto.MensagemProdutoDTO;
import br.com.jumplabel.simulador.service.mapper.MensagemProdutoMapper;

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
 * Test class for the MensagemProdutoResource REST controller.
 *
 * @see MensagemProdutoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimuladorServerApp.class)
public class MensagemProdutoResourceIntTest {

    private static final String DEFAULT_DS_MENSAGEM = "AAAAAAAAAA";
    private static final String UPDATED_DS_MENSAGEM = "BBBBBBBBBB";

    @Autowired
    private MensagemProdutoRepository mensagemProdutoRepository;

    @Autowired
    private MensagemProdutoMapper mensagemProdutoMapper;

    @Autowired
    private MensagemProdutoService mensagemProdutoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private EntityManager em;

    private MockMvc restMensagemProdutoMockMvc;

    private MensagemProduto mensagemProduto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MensagemProdutoResource mensagemProdutoResource = new MensagemProdutoResource(mensagemProdutoService);
        this.restMensagemProdutoMockMvc = MockMvcBuilders.standaloneSetup(mensagemProdutoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MensagemProduto createEntity(EntityManager em) {
        MensagemProduto mensagemProduto = new MensagemProduto()
                .dsMensagem(DEFAULT_DS_MENSAGEM);
        return mensagemProduto;
    }

    @Before
    public void initTest() {
        mensagemProduto = createEntity(em);
    }

    @Test
    @Transactional
    public void createMensagemProduto() throws Exception {
        int databaseSizeBeforeCreate = mensagemProdutoRepository.findAll().size();

        // Create the MensagemProduto
        MensagemProdutoDTO mensagemProdutoDTO = mensagemProdutoMapper.mensagemProdutoToMensagemProdutoDTO(mensagemProduto);

        restMensagemProdutoMockMvc.perform(post("/api/mensagem-produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mensagemProdutoDTO)))
            .andExpect(status().isCreated());

        // Validate the MensagemProduto in the database
        List<MensagemProduto> mensagemProdutoList = mensagemProdutoRepository.findAll();
        assertThat(mensagemProdutoList).hasSize(databaseSizeBeforeCreate + 1);
        MensagemProduto testMensagemProduto = mensagemProdutoList.get(mensagemProdutoList.size() - 1);
        assertThat(testMensagemProduto.getDsMensagem()).isEqualTo(DEFAULT_DS_MENSAGEM);
    }

    @Test
    @Transactional
    public void createMensagemProdutoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mensagemProdutoRepository.findAll().size();

        // Create the MensagemProduto with an existing ID
        MensagemProduto existingMensagemProduto = new MensagemProduto();
        existingMensagemProduto.setId(1L);
        MensagemProdutoDTO existingMensagemProdutoDTO = mensagemProdutoMapper.mensagemProdutoToMensagemProdutoDTO(existingMensagemProduto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMensagemProdutoMockMvc.perform(post("/api/mensagem-produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingMensagemProdutoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<MensagemProduto> mensagemProdutoList = mensagemProdutoRepository.findAll();
        assertThat(mensagemProdutoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDsMensagemIsRequired() throws Exception {
        int databaseSizeBeforeTest = mensagemProdutoRepository.findAll().size();
        // set the field null
        mensagemProduto.setDsMensagem(null);

        // Create the MensagemProduto, which fails.
        MensagemProdutoDTO mensagemProdutoDTO = mensagemProdutoMapper.mensagemProdutoToMensagemProdutoDTO(mensagemProduto);

        restMensagemProdutoMockMvc.perform(post("/api/mensagem-produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mensagemProdutoDTO)))
            .andExpect(status().isBadRequest());

        List<MensagemProduto> mensagemProdutoList = mensagemProdutoRepository.findAll();
        assertThat(mensagemProdutoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMensagemProdutos() throws Exception {
        // Initialize the database
        mensagemProdutoRepository.saveAndFlush(mensagemProduto);

        // Get all the mensagemProdutoList
        restMensagemProdutoMockMvc.perform(get("/api/mensagem-produtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mensagemProduto.getId().intValue())))
            .andExpect(jsonPath("$.[*].dsMensagem").value(hasItem(DEFAULT_DS_MENSAGEM.toString())));
    }

    @Test
    @Transactional
    public void getMensagemProduto() throws Exception {
        // Initialize the database
        mensagemProdutoRepository.saveAndFlush(mensagemProduto);

        // Get the mensagemProduto
        restMensagemProdutoMockMvc.perform(get("/api/mensagem-produtos/{id}", mensagemProduto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mensagemProduto.getId().intValue()))
            .andExpect(jsonPath("$.dsMensagem").value(DEFAULT_DS_MENSAGEM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMensagemProduto() throws Exception {
        // Get the mensagemProduto
        restMensagemProdutoMockMvc.perform(get("/api/mensagem-produtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMensagemProduto() throws Exception {
        // Initialize the database
        mensagemProdutoRepository.saveAndFlush(mensagemProduto);
        int databaseSizeBeforeUpdate = mensagemProdutoRepository.findAll().size();

        // Update the mensagemProduto
        MensagemProduto updatedMensagemProduto = mensagemProdutoRepository.findOne(mensagemProduto.getId());
        updatedMensagemProduto
                .dsMensagem(UPDATED_DS_MENSAGEM);
        MensagemProdutoDTO mensagemProdutoDTO = mensagemProdutoMapper.mensagemProdutoToMensagemProdutoDTO(updatedMensagemProduto);

        restMensagemProdutoMockMvc.perform(put("/api/mensagem-produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mensagemProdutoDTO)))
            .andExpect(status().isOk());

        // Validate the MensagemProduto in the database
        List<MensagemProduto> mensagemProdutoList = mensagemProdutoRepository.findAll();
        assertThat(mensagemProdutoList).hasSize(databaseSizeBeforeUpdate);
        MensagemProduto testMensagemProduto = mensagemProdutoList.get(mensagemProdutoList.size() - 1);
        assertThat(testMensagemProduto.getDsMensagem()).isEqualTo(UPDATED_DS_MENSAGEM);
    }

    @Test
    @Transactional
    public void updateNonExistingMensagemProduto() throws Exception {
        int databaseSizeBeforeUpdate = mensagemProdutoRepository.findAll().size();

        // Create the MensagemProduto
        MensagemProdutoDTO mensagemProdutoDTO = mensagemProdutoMapper.mensagemProdutoToMensagemProdutoDTO(mensagemProduto);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMensagemProdutoMockMvc.perform(put("/api/mensagem-produtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mensagemProdutoDTO)))
            .andExpect(status().isCreated());

        // Validate the MensagemProduto in the database
        List<MensagemProduto> mensagemProdutoList = mensagemProdutoRepository.findAll();
        assertThat(mensagemProdutoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMensagemProduto() throws Exception {
        // Initialize the database
        mensagemProdutoRepository.saveAndFlush(mensagemProduto);
        int databaseSizeBeforeDelete = mensagemProdutoRepository.findAll().size();

        // Get the mensagemProduto
        restMensagemProdutoMockMvc.perform(delete("/api/mensagem-produtos/{id}", mensagemProduto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MensagemProduto> mensagemProdutoList = mensagemProdutoRepository.findAll();
        assertThat(mensagemProdutoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
