package mercadofacil.mercadofacil.Service;

import mercadofacil.mercadofacil.Dto.ProdutoPostDto;
import mercadofacil.mercadofacil.Model.Produto;

@FunctionalInterface
public interface ProdutoCriarService {
    Produto criarProduto(ProdutoPostDto produtoPostDto);
}
