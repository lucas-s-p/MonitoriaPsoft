package mercadofacil.mercadofacil.Controller;

import jakarta.validation.Valid;
import mercadofacil.mercadofacil.Dto.ProdutoPostPutDto;
import mercadofacil.mercadofacil.Dto.ProdutoResponseDto;
import mercadofacil.mercadofacil.Service.ProdutoCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoV1Controller {

    @Autowired
    ProdutoCrudService produtoCrudService;

    @PostMapping("")
    public ResponseEntity<ProdutoResponseDto> criarProduto(
            @RequestBody @Valid ProdutoPostPutDto produtoPostPutDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(produtoCrudService.criarProduto(produtoPostPutDto));
    }

    // GET /v1/produtos            -> todos os produtos
    // GET /v1/produtos?categoria=Bebidas -> só os da categoria
    @GetMapping("")
    public ResponseEntity<List<ProdutoResponseDto>> buscarTodosProdutos(
            @RequestParam(required = false) String categoria) {
        List<ProdutoResponseDto> produtos = (categoria == null || categoria.isBlank())
                ? produtoCrudService.buscarTodosProdutos()
                : produtoCrudService.buscarProdutosPorCategoria(categoria);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(produtos);
    }
}