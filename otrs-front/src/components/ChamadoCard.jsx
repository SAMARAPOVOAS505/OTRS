
import React from 'react';

const ChamadoCard = ({ chamado }) => {
  return (
    <div style={{
      border: '1px solid #ccc',
      borderRadius: '8px',
      padding: '1rem',
      marginBottom: '1rem',
      backgroundColor: '#f9f9f9'
    }}>
      <h3>{chamado.descricao}</h3>
      <p>Status: <strong>{chamado.status}</strong></p>
      <p>Usuário: {chamado.usuario}</p>
    </div>
  );
};

export default ChamadoCard;
