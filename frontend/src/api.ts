// Comunicação com o back-end (API REST) do sistema de cadastro de produtos.
const API = '/v1/produtos'

export interface Produto {
  id: number
  nomeProduto: string
  valorProduto: number
  codigoBarras: string
}

// Corpo enviado no POST e no PUT (mesmo formato do ProdutoPostDto do back).
export type ProdutoInput = Omit<Produto, 'id'>

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

export function listarProdutos(): Promise<Produto[]> {
  return fetch(API).then((r) => tratarResposta<Produto[]>(r))
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
