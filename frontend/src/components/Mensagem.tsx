interface Props {
  tipo: 'sucesso' | 'erro'
  texto: string
}

export default function Mensagem({ tipo, texto }: Props) {
  return <p className={`mensagem ${tipo}`}>{texto}</p>
}
