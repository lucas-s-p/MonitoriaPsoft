package mercadofacil.mercadofacil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;
import mercadofacil.mercadofacil.Dto.ProdutoPostDto;
import mercadofacil.mercadofacil.Model.Produto;
import mercadofacil.mercadofacil.Repository.ProdutoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@DisplayName("Classe de testes de produto")
public class ProdutoV1ControllerTests {
    final String URI_PRODUTO = "/v1/produtos";
    @Autowired
    MockMvc driver;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    ProdutoRepository produtoRepository;
    @BeforeEach
    void setup() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @AfterEach
    void tearDown() {
        produtoRepository.deleteAll();
    }

    @Test
    @DisplayName("Teste para criação de um produto")
    void testQuandoCriamosUmProduto()  throws Exception{
        ProdutoPostDto produtoPostDto = ProdutoPostDto.builder()
                .nomeProduto("Pipoca")
                .valorProduto(21.00)
                .codigoBarras("8302184870420")
                .build();


        String responseJSONString = driver.perform(post(URI_PRODUTO)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(produtoPostDto)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Produto resultado = objectMapper.readValue(responseJSONString, Produto.ProdutoBuilder.class).build();
        assertEquals(1, produtoRepository.findAll().size());

        //System.out.println("Produto adicionado: " + produtoRepository.findById(1L).get());
    }

    @Test
    @DisplayName("Teste para buscar todos produtos")
    void testQuandoBuscamosTodosProduto()  throws Exception{
        Produto produto = produtoRepository.save(
                Produto.builder()
                        .nomeProduto("arroz")
                        .valorProduto(5.00)
                        .codigoBarras("7891232143")
                        .build()
        );

        Produto produto1 = produtoRepository.save(
                Produto.builder()
                        .nomeProduto("feijao")
                        .valorProduto(8.00)
                        .codigoBarras("7891232545")
                        .build()
        );

        String responseJSONString = driver.perform(get(URI_PRODUTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        List<Produto> resultado = objectMapper.readValue(responseJSONString, new TypeReference<List<Produto>>(){});
        assertEquals(2, resultado.size());

        //System.out.println("Produtos do meu banco de dados: " + produtoRepository.findAll());
    }
}
