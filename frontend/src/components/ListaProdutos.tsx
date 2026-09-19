import type { Produto } from '../api'

interface Props {
  produtos: Produto[]
  onEditar: (produto: Produto) => void
  onRemover: (produto: Produto) => void
}

export default function ListaProdutos({ produtos, onEditar, onRemover }: Props) {
  if (produtos.length === 0) {
    return <p className="vazio">Nenhum produto cadastrado. Clique em + para criar.</p>
  }

  return (
    <ul className="lista">
      {produtos.map((produto) => (
        <li key={produto.id}>
          <div className="info">
            <strong>{produto.nomeProduto}</strong>
            <span>R$ {Number(produto.valorProduto).toFixed(2)}</span>
            <small>
              Código: {produto.codigoBarras} · id: {produto.id}
            </small>
          </div>
          <div className="botoes">
            <button className="btn-editar" onClick={() => onEditar(produto)}>
              Editar
            </button>
            <button className="btn-remover" onClick={() => onRemover(produto)}>
              Remover
            </button>
          </div>
        </li>
      ))}
    </ul>
  )
}
