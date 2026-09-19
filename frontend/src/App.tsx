import { useState } from 'react'
import { useProdutos } from './hooks/useProdutos'
import type { Produto, ProdutoInput } from './api'
import Mensagem from './components/Mensagem'
import ListaProdutos from './components/ListaProdutos'
import ProdutoModal from './components/ProdutoModal'

export default function App() {
  const { produtos, mensagem, criar, editar, remover } = useProdutos()

  // null = modal fechado; undefined = criando; Produto = editando
  const [editando, setEditando] = useState<Produto | null | undefined>(null)

  function salvar(dados: ProdutoInput) {
    if (editando) {
      editar(editando.id, dados)
    } else {
      criar(dados)
    }
    setEditando(null)
  }

  return (
    <>
      <header>
        <h1>Mercado Fácil</h1>
        <button className="btn-mais" title="Criar produto" onClick={() => setEditando(undefined)}>
          +
        </button>
      </header>

      <main>
        {mensagem && <Mensagem tipo={mensagem.tipo} texto={mensagem.texto} />}
        <ListaProdutos produtos={produtos} onEditar={setEditando} onRemover={remover} />
      </main>

      {editando !== null && (
        <ProdutoModal produto={editando} onSalvar={salvar} onCancelar={() => setEditando(null)} />
      )}
    </>
  )
}
