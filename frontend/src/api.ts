// Comunicação com o back-end (API REST) do sistema de cadastro de produtos.
const API = '/v1/produtos'
const API_CATEGORIAS = '/v1/categorias'

export interface Categoria {
  id: number
  nomeCategoria: string
}

// Corpo enviado no POST de categoria (mesmo formato do CategoriaPostPutDto do back).
export type CategoriaInput = Omit<Categoria, 'id'>

export interface Produto {
  id: number
  nomeProduto: string
  valorProduto: number
  codigoBarras: string
  categoria?: Categoria | null // o back devolve a categoria do produto (ou null)
}

// idCategoria = null quando o produto fica sem categoria.
export type ProdutoInput = Omit<Produto, 'id' | 'categoria'> & { idCategoria: number | null }

// Lançar erro
export class ApiError extends Error {
  constructor(
    public readonly status: number,
    public readonly rota: string,
    message: string
  ) {
    super(message)
  }
}

async function tratarResposta<T>(resposta: Response): Promise<T> {
  if (resposta.ok) {
    // 204 No Content não tem corpo
    if (resposta.status === 204) return undefined as T
    return resposta.json()
  }

  let detalhe = resposta.statusText
  try {
    const corpo = await resposta.json()
    detalhe = corpo.message || corpo.error || JSON.stringify(corpo)
  } catch {
    // corpo vazio ou não-JSON: mantém o statusText
  }
  const rota = new URL(resposta.url).pathname
  throw new ApiError(resposta.status, rota, detalhe)
}

// GET /v1/produtos?categoria=Bebidas
export function listarProdutos(categoria?: string): Promise<Produto[]> {
  const url = categoria ? `${API}?categoria=${encodeURIComponent(categoria)}` : API
  return fetch(url).then((r) => tratarResposta<Produto[]>(r))
}

export function criarProduto(dados: ProdutoInput): Promise<Produto> {
  return fetch(API, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(dados),
  }).then((r) => tratarResposta<Produto>(r))
}

// PUT /v1/produtos/{id} — rota a ser implementada no back-end
export function editarProduto(id: number, dados: ProdutoInput): Promise<Produto> {
  return fetch(`${API}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(dados),
  }).then((r) => tratarResposta<Produto>(r))
}

// DELETE /v1/produtos/{id} — rota a ser implementada no back-end
export function removerProduto(id: number): Promise<void> {
  return fetch(`${API}/${id}`, { method: 'DELETE' }).then((r) => tratarResposta<void>(r))
}

// GET /v1/categorias — rota a ser implementada no back-end
export function listarCategorias(): Promise<Categoria[]> {
  return fetch(API_CATEGORIAS).then((r) => tratarResposta<Categoria[]>(r))
}

// POST /v1/categorias — rota a ser implementada no back-end
export function criarCategoria(dados: CategoriaInput): Promise<Categoria> {
  return fetch(API_CATEGORIAS, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(dados),
  }).then((r) => tratarResposta<Categoria>(r))
}