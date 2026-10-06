## O Mercado Fácil precisa de categorias

Dona Marta é a dona do Mercado Fácil. O cadastro de produtos já funciona, mas a lista cresceu e está uma bagunça: café, detergente e refrigerante aparecem todos misturados. Ela quer organizar os produtos em categorias, como Bebidas, Limpeza e Mercearia. A tela e a parte de produtos do back-end já estão prontas e esperam as categorias, mas o back-end ainda não sabe o que é uma categoria. Sua missão é ensiná-lo, em três etapas.

## US-01: 
o cliente deseja cadastrar categorias. Crie a entidade **Categoria**, com identificador gerado automaticamente e um nome obrigatório, guardada na tabela **tb_categoria**. Crie também o **repository**, os **DTOs** de entrada e de saída, o **service** (interface e implementação) e o **controller**. O cadastro deve ser feito em **POST /v1/categorias**, receber o nome da categoria e responder 201 com os dados da categoria criada. Um nome vazio deve ser recusado com erro 400. Siga o mesmo padrão que o time usou em produto.

## US-02:
o cliente deseja listar as categorias. Crie a rota **GET /v1/categorias**, que devolve todas as categorias cadastradas. É essa lista que a tela usa para preencher a escolha de categoria no cadastro de produto e o filtro da listagem. Depois desta etapa, cadastre três categorias pelo botão "Nova categoria" e confira que elas aparecem na tela e no console do H2.

## US-03:
o cliente deseja associar cada produto a uma categoria. Na entidade Produto, no ponto marcado com o comentário **"Adicione a relação aqui"**, declare o relacionamento com Categoria. Pense na cardinalidade: muitos produtos para uma categoria. Quem guarda a chave estrangeira? Dê a ela o nome **id_categoria** e confira no H2 que a tabela **tb_produto** ganhou essa coluna. O service de produto já busca a categoria e a devolve na resposta, então, quando a relação estiver correta, a tela passa a mostrar a categoria de cada produto. Teste também o filtro por categoria e o erro 404 ao usar uma categoria que não existe.

## Atenção aos nomes:
o código de produto já espera Categoria, CategoriaRepository, CategoriaResponseDto, o campo nomeCategoria e, em Produto, um atributo chamado categoria. Use exatamente esses nomes, ou o projeto não compila.