import { useEffect, useRef, useState, type FormEvent } from 'react'
import type { CategoriaInput } from '../api'

interface Props {
  onSalvar: (dados: CategoriaInput) => void
  onCancelar: () => void
}

export default function CategoriaModal({ onSalvar, onCancelar }: Props) {
  const dialogRef = useRef<HTMLDialogElement>(null)
  const [nomeCategoria, setNomeCategoria] = useState('')

  useEffect(() => {
    dialogRef.current?.showModal()
  }, [])

  function submit(evento: FormEvent) {
    evento.preventDefault()
    onSalvar({ nomeCategoria: nomeCategoria.trim() })
  }

  return (
    <dialog ref={dialogRef} onClose={onCancelar}>
      <form onSubmit={submit}>
        <h2>Nova categoria</h2>
        <label>
          Nome
          <input
            type="text"
            required
            value={nomeCategoria}
            onChange={(e) => setNomeCategoria(e.target.value)}
          />
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