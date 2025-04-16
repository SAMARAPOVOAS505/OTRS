import React from 'react';

const Header = ({ userType }) => {
  const renderUserInfo = () => {
    if (userType === 'admin') {
      return (
        <>
          <p style={{ marginTop: '5px' }}>Bem-vindo, Admin (TI)!</p>
          <p style={{ marginTop: '5px' }}>Você pode gerenciar todos os chamados.</p>
        </>
      );
    } else {
      return (
        <>
          <p style={{ marginTop: '5px' }}>Bem-vindo, Usuário!</p>
          <p style={{ marginTop: '5px' }}>Você pode criar e acompanhar seus chamados.</p>
        </>
      );
    }
  };

  return (
    <header
      style={{
        backgroundColor: '#0d6efd',
        color: 'white',
        padding: '20px',
        textAlign: 'center',
        boxShadow: '0 2px 6px rgba(0,0,0,0.1)',
      }}
    >
      <h1 style={{ margin: 0 }}>Sistema de Chamados</h1>
      {renderUserInfo()}
    </header>
  );
};

export default Header;

