import { useCallback, useEffect, useState } from 'react'
import * as api from '../api'
import type { Produto, ProdutoInput } from '../api'

export interface Mensagem {
  tipo: 'sucesso' | 'erro'
  texto: string
}

// Mensagem de Erro
function mensagemDeErro(erro: unknown, acao: string): string {
  if (erro instanceof api.ApiError) {
    if (erro.status === 404) {
      return (
        `Erro 404: a rota para ${acao} ainda não existe no back-end.\n` +
        `Implemente-a no Spring Boot (${erro.rota}) e tente novamente.`
      )
    }
    return `Erro ${erro.status} ao ${acao}: ${erro.message}`
  }
  return 'Não foi possível conectar ao back-end. O Spring Boot está rodando na porta 8080?'
}

// Hook que concentra o estado e as ações do CRUD de produtos.
export function useProdutos() {
  const [produtos, setProdutos] = useState<Produto[]>([])
  const [mensagem, setMensagem] = useState<Mensagem | null>(null)

  const carregar = useCallback(async () => {
    try {
      setProdutos(await api.listarProdutos())
    } catch (erro) {
      setMensagem({ tipo: 'erro', texto: mensagemDeErro(erro, 'listar produtos') })
    }
  }, [])

  useEffect(() => {
    carregar()
  }, [carregar])

  // Executa uma ação da API, mostra a mensagem de sucesso/erro e recarrega a lista.
  async function executar(acao: string, sucesso: string, fn: () => Promise<unknown>) {
    setMensagem(null)
    try {
      await fn()
      setMensagem({ tipo: 'sucesso', texto: sucesso })
      await carregar()
    } catch (erro) {
      setMensagem({ tipo: 'erro', texto: mensagemDeErro(erro, acao) })
    }
  }

  const criar = (dados: ProdutoInput) =>
    executar('criar produto', 'Produto criado com sucesso!', () => api.criarProduto(dados))

  const editar = (id: number, dados: ProdutoInput) =>
    executar('editar produto', 'Produto editado com sucesso!', () => api.editarProduto(id, dados))

  const remover = (produto: Produto) => {
    if (!confirm(`Remover o produto "${produto.nomeProduto}"?`)) return
    return executar('remover produto', 'Produto removido com sucesso!', () =>
      api.removerProduto(produto.id)
    )
  }

  return { produtos, mensagem, criar, editar, remover }
}
