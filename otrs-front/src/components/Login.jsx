// src/components/Login.jsx
import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const history = useHistory();

  const handleLogin = () => {
    if (username === 'admin' && password === 'admin123') {
      history.push('/admin');  // Redireciona para a página do admin
    } else if (username === 'user' && password === 'user123') {
      history.push('/user');  // Redireciona para a página do usuário comum
    } else {
      alert('Credenciais inválidas!');
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <input
        type="text"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        placeholder="Nome de usuário"
      />
      <input
        type="password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        placeholder="Senha"
      />
      <button onClick={handleLogin}>Entrar</button>
    </div>
  );
};

export default Login;
