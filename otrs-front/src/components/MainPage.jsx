import React from 'react';
import { useHistory } from 'react-router-dom';

const MainPage = () => {
  const history = useHistory();

  const handleLogout = () => {
    // Simula um logout, por exemplo, removendo um token de autenticação
    localStorage.removeItem('authToken'); // Exemplo de remoção de token

    // Redireciona para a página de login
    history.push('/');
  };

  return (
    <div>
      <h1>Página Principal</h1>
      <button onClick={handleLogout}>Sair</button>
    </div>
  );
};

export default MainPage;
