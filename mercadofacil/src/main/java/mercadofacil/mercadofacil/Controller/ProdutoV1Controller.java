package mercadofacil.mercadofacil.Controller;

import jakarta.validation.Valid;
import mercadofacil.mercadofacil.Dto.ProdutoPostDto;
import mercadofacil.mercadofacil.Service.ProdutoBuscaTodosService;
import mercadofacil.mercadofacil.Service.ProdutoCriarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoV1Controller {

    @Autowired
    ProdutoCriarService produtoCriarService;

    @Autowired
    ProdutoBuscaTodosService produtoBuscaTodosService;

    @PostMapping("")
    public ResponseEntity<?> criarProduto(
            @RequestBody @Valid ProdutoPostDto produtoPostDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoCriarService.criarProduto(produtoPostDto));
    }

    @GetMapping("")
    public  ResponseEntity<?> buscarTodosProdutos() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtoBuscaTodosService.buscaTodosProdutos());
    }
}
