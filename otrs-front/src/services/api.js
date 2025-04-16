import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api/chamados', // ajuste se sua API estiver em outro caminho
});

export default api;
