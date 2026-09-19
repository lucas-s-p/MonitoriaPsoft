package mercadofacil.mercadofacil.Service;

import mercadofacil.mercadofacil.Dto.ProdutoPostDto;
import mercadofacil.mercadofacil.Model.Produto;

import java.util.List;

public interface ProdutoCrudService {
    ProdutoPostDto criarProduto(ProdutoPostDto produtoPostDto);

    List<Produto> buscarTodosProdutos();
}
