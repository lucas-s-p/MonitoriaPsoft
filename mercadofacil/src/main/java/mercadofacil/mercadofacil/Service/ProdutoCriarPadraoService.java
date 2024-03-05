package mercadofacil.mercadofacil.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import mercadofacil.mercadofacil.Dto.ProdutoPostDto;
import mercadofacil.mercadofacil.Model.Produto;
import mercadofacil.mercadofacil.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoCriarPadraoService implements ProdutoCriarService{
    @Autowired
    ObjectMapper modelMapper;

    @Autowired
    ProdutoRepository produtoRepository;
    @Override
    public Produto criarProduto(ProdutoPostDto produtoPostDto) {
        Produto produto = modelMapper.convertValue(produtoPostDto, Produto.class);
        return produtoRepository.save(produto);
    }
}
