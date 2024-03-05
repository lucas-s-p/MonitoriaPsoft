package mercadofacil.mercadofacil.Service;

import mercadofacil.mercadofacil.Model.Produto;

import java.util.List;

@FunctionalInterface
public interface ProdutoBuscaTodosService {
    List<Produto> buscaTodosProdutos();
}
