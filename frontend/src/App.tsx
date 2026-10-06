import { useState } from 'react'
import { useProdutos } from './hooks/useProdutos'
import type { CategoriaInput, Produto, ProdutoInput } from './api'
import Mensagem from './components/Mensagem'
import ListaProdutos from './components/ListaProdutos'
import ProdutoModal from './components/ProdutoModal'
import CategoriaModal from './components/CategoriaModal'

export default function App() {
  const { produtos, categorias, filtro, setFiltro, mensagem, criar, editar, remover, criarCategoria } =
    useProdutos()

  // null = modal fechado; undefined = criando; Produto = editando
  const [editando, setEditando] = useState<Produto | null | undefined>(null)
  const [criandoCategoria, setCriandoCategoria] = useState(false)

  function salvar(dados: ProdutoInput) {
    if (editando) {
      editar(editando.id, dados)
    } else {
      criar(dados)
    }
    setEditando(null)
  }

  function salvarCategoria(dados: CategoriaInput) {
    criarCategoria(dados)
    setCriandoCategoria(false)
  }

  return (
    <>
      <header>
        <h1>Mercado Fácil</h1>
        <div className="botoes">
          <button className="btn-secundario" onClick={() => setCriandoCategoria(true)}>
            Nova categoria
          </button>
          <button className="btn-mais" title="Criar produto" onClick={() => setEditando(undefined)}>
            +
          </button>
        </div>
      </header>

      <main>
        {mensagem && <Mensagem tipo={mensagem.tipo} texto={mensagem.texto} />}
        <label className="filtro">
          Filtrar por categoria:{' '}
          <select value={filtro} onChange={(e) => setFiltro(e.target.value)}>
            <option value="">Todas</option>
            {categorias.map((categoria) => (
              <option key={categoria.id} value={categoria.nomeCategoria}>
                {categoria.nomeCategoria}
              </option>
            ))}
          </select>
        </label>
        <ListaProdutos produtos={produtos} onEditar={setEditando} onRemover={remover} />
      </main>

      {editando !== null && (
        <ProdutoModal
          produto={editando}
          categorias={categorias}
          onSalvar={salvar}
          onCancelar={() => setEditando(null)}
        />
      )}

      {criandoCategoria && (
        <CategoriaModal onSalvar={salvarCategoria} onCancelar={() => setCriandoCategoria(false)} />
      )}
    </>
  )
}