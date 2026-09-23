package mercadofacil.mercadofacil.Service;

import org.modelmapper.ModelMapper;
import mercadofacil.mercadofacil.Dto.ProdutoPostPutDto;
import mercadofacil.mercadofacil.Dto.ProdutoResponseDto;
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
    public ProdutoResponseDto criarProduto(ProdutoPostPutDto produtoPostPutDto) {
        Produto produto = modelMapper.map(produtoPostPutDto, Produto.class);
        return modelMapper.map(produtoRepository.save(produto), ProdutoResponseDto.class);
    }

    @Override
    public List<ProdutoResponseDto> buscarTodosProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(produto -> modelMapper.map(produto, ProdutoResponseDto.class))
                .toList();
    }
}
