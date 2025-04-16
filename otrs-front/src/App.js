import React from 'react';
import ListaChamado from './pages/ListaChamado';
import Header from './components/Header';

function App() {

  const userType = 'admin'; // ou 'comum'

  return (
    <>
      <Header userType={use} />
      <div style={{ padding: '20px' }}>
        <ListaChamado/>
      </div>
    </>
  );
}

export default App;
