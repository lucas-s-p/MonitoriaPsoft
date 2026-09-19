# Mercado Fácil — Front-end
## Como rodar

1. Suba o back-end (na pasta `mercadofacil`):
   ```bash
   ./gradlew bootRun
   ```
2. Em outro terminal, nesta pasta:
   ```bash
   npm install
   npm run dev
   ```
3. Abra http://localhost:5173

## Endpoints usados

| Ação             | Método | Rota                 | Status            |
|------------------|--------|----------------------|-------------------|
| Listar produtos  | GET    | `/v1/produtos`       | implementado      |
| Criar produto    | POST   | `/v1/produtos`       | implementado      |
| Editar produto   | PUT    | `/v1/produtos/{id}`  | **a implementar** |
| Remover produto  | DELETE | `/v1/produtos/{id}`  | **a implementar** |

Enquanto as rotas de editar e remover não existirem no back-end, o Spring responde
`404` e a tela mostra uma mensagem avisando que a rota ainda não foi implementada.

