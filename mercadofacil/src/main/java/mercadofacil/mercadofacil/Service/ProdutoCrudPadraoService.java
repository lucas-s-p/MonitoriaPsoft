package mercadofacil.mercadofacil.Service;

import mercadofacil.mercadofacil.Dto.CategoriaResponseDto;
import mercadofacil.mercadofacil.Dto.ProdutoPostPutDto;
import mercadofacil.mercadofacil.Dto.ProdutoResponseDto;
import mercadofacil.mercadofacil.Model.Categoria;
import mercadofacil.mercadofacil.Model.Produto;
import mercadofacil.mercadofacil.Repository.CategoriaRepository;
import mercadofacil.mercadofacil.Repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoCrudPadraoService implements ProdutoCrudService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public ProdutoResponseDto criarProduto(ProdutoPostPutDto produtoPostPutDto) {
        // Monta a entidade "na mão": o ModelMapper pode confundir idCategoria com o id do produto
        Produto produto = Produto.builder()
                .nomeProduto(produtoPostPutDto.getNomeProduto())
                .valorProduto(produtoPostPutDto.getValorProduto())
                .codigoBarras(produtoPostPutDto.getCodigoBarras())
                .categoria(buscarCategoria(produtoPostPutDto.getIdCategoria()))
                .build();
        return converter(produtoRepository.save(produto));
    }

    @Override
    public List<ProdutoResponseDto> buscarTodosProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::converter)
                .toList();
    }

    @Override
    public List<ProdutoResponseDto> buscarProdutosPorCategoria(String nomeCategoria) {
        return produtoRepository.findByCategoriaNomeCategoria(nomeCategoria)
                .stream()
                .map(this::converter)
                .toList();
    }

    // idCategoria null = produto sem categoria; id inexistente = 404
    private Categoria buscarCategoria(Long idCategoria) {
        if (idCategoria == null) {
            return null;
        }
        return categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }

    private ProdutoResponseDto converter(Produto produto) {
        return ProdutoResponseDto.builder()
                .id(produto.getId())
                .nomeProduto(produto.getNomeProduto())
                .valorProduto(produto.getValorProduto())
                .codigoBarras(produto.getCodigoBarras())
                .categoria(produto.getCategoria() == null
                        ? null
                        : modelMapper.map(produto.getCategoria(), CategoriaResponseDto.class))
                .build();
    }
}