import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// Toda requisição do front para /v1/... é redirecionada para o Spring Boot (porta 8080).
// Assim não precisamos configurar CORS no back-end.
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      '/v1': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
  },
})
