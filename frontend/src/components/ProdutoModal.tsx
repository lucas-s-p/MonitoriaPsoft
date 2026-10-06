import { useEffect, useRef, useState, type FormEvent } from 'react'
import type { Categoria, Produto, ProdutoInput } from '../api'

interface Props {
  produto?: Produto // undefined = criando um novo
  categorias: Categoria[]
  onSalvar: (dados: ProdutoInput) => void
  onCancelar: () => void
}

export default function ProdutoModal({ produto, categorias, onSalvar, onCancelar }: Props) {
  const dialogRef = useRef<HTMLDialogElement>(null)
  const [nomeProduto, setNomeProduto] = useState(produto?.nomeProduto ?? '')
  const [valorProduto, setValorProduto] = useState(produto ? String(produto.valorProduto) : '')
  const [codigoBarras, setCodigoBarras] = useState(produto?.codigoBarras ?? '')
  const [idCategoria, setIdCategoria] = useState(
    produto?.categoria ? String(produto.categoria.id) : ''
  )

  useEffect(() => {
    dialogRef.current?.showModal()
  }, [])

  function submit(evento: FormEvent) {
    evento.preventDefault()
    onSalvar({
      nomeProduto: nomeProduto.trim(),
      valorProduto: Number(valorProduto),
      codigoBarras: codigoBarras.trim(),
      idCategoria: idCategoria ? Number(idCategoria) : null,
    })
  }

  return (
    <dialog ref={dialogRef} onClose={onCancelar}>
      <form onSubmit={submit}>
        <h2>{produto ? 'Editar produto' : 'Novo produto'}</h2>
        <label>
          Nome
          <input
            type="text"
            required
            value={nomeProduto}
            onChange={(e) => setNomeProduto(e.target.value)}
          />
        </label>
        <label>
          Valor (R$)
          <input
            type="number"
            step="0.01"
            min="0.01"
            required
            value={valorProduto}
            onChange={(e) => setValorProduto(e.target.value)}
          />
        </label>
        <label>
          Código de barras
          <input
            type="text"
            required
            value={codigoBarras}
            onChange={(e) => setCodigoBarras(e.target.value)}
          />
        </label>
        <label>
          Categoria
          <select value={idCategoria} onChange={(e) => setIdCategoria(e.target.value)}>
            <option value="">Sem categoria</option>
            {categorias.map((categoria) => (
              <option key={categoria.id} value={categoria.id}>
                {categoria.nomeCategoria}
              </option>
            ))}
          </select>
        </label>
        <div className="acoes">
          <button type="button" className="btn-secundario" onClick={onCancelar}>
            Cancelar
          </button>
          <button type="submit" className="btn-primario">
            Salvar
          </button>
        </div>
      </form>
    </dialog>
  )
}