package mercadofacil.mercadofacil.Service;

import mercadofacil.mercadofacil.Dto.ProdutoPostDto;
import mercadofacil.mercadofacil.Model.Produto;
import mercadofacil.mercadofacil.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoBuscaTodosPadraoService implements ProdutoBuscaTodosService {

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public List<Produto> buscaTodosProdutos() {
        return produtoRepository.findAll();
    }
}