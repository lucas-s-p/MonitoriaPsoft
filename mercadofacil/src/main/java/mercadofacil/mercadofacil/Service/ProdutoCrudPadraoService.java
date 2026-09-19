package mercadofacil.mercadofacil.Service;

import org.modelmapper.ModelMapper;
import mercadofacil.mercadofacil.Dto.ProdutoPostDto;
import mercadofacil.mercadofacil.Model.Produto;
import mercadofacil.mercadofacil.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoCrudPadraoService implements ProdutoCrudService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public ProdutoPostDto criarProduto(ProdutoPostDto produtoPostDto) {
        Produto produto = modelMapper.map(produtoPostDto, Produto.class);
        return modelMapper.map(produtoRepository.save(produto), ProdutoPostDto.class);
    }

    @Override
    public List<Produto> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }
}
