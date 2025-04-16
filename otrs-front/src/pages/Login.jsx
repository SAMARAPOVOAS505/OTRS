import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

const Login = () => {
  const [username, setUsername] = useState('');
  const history = useHistory();

  const handleLogin = () => {
    // Simula autenticação
    localStorage.setItem('authToken', 'fake-token');
    localStorage.setItem('userType', username === 'admin' ? 'admin' : 'comum');

    history.push('/main');
  };

  return (
    <div style={{ padding: '2rem', textAlign: 'center' }}>
      <h1>Bem-vinda ao Sistema de Chamados</h1>
      <p>Sua solução de TI eficiente e descomplicada!</p>
      <div style={{ marginTop: '2rem' }}>
        <input
          type="text"
          placeholder="Digite seu nome de usuário"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <br /><br />
        <button onClick={handleLogin}>Entrar</button>
      </div>
    </div>
  );
};

export default Login;
