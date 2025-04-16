import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from './components/Login';  // Componente de Login
import MainPage from './components/MainPage';  // Página principal após login

function App() {
  return (
    <Router>
      <Switch>
        {/* Rota para o Login (página inicial) */}
        <Route path="/" exact component={Login} />

        {/* Rota para a página principal do usuário comum */}
        <Route path="/user" component={MainPage} />

        {/* Rota para a página principal do admin */}
        <Route path="/admin" component={MainPage} />
      </Switch>
    </Router>
  );
}

export default App;

