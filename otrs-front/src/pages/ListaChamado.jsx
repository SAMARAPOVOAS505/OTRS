import React, { useEffect, useState } from "react";

import axios from "axios";

function ListaChamado() {
  const [chamados, setChamados] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/chamados")
      .then(response => {
        console.log(response.data);
        setChamados(response.data);
      })
      .catch(error => {
        console.error("Erro ao buscar chamados:", error);
      });
  }, []);

  const [hoveredCard, setHoveredCard] = useState(null);

  return (
    <div>
      <h1>Lista de Chamados</h1>
      {chamados.length === 0 ? (
        <p>Nenhum chamado encontrado.</p>
      ) : (
        <ul>
          {chamados.map((chamado) => (
  <div
    key={chamado.id}
    onMouseEnter={() => setHoveredCard(chamado.id)}
    onMouseLeave={() => setHoveredCard(null)}
    style={{
      backgroundColor:
        chamado.status === 'ABERTO'
          ? '#cce5ff'
          : chamado.status === 'EM ANDAMENTO'
          ? '#fff3cd'
          : '#d4edda',
      padding: '16px',
      marginBottom: '12px',
      borderRadius: '8px',
      boxShadow:
        hoveredCard === chamado.id
          ? '0 4px 12px rgba(0, 0, 0, 0.2)'
          : '0 2px 4px rgba(0, 0, 0, 0.1)',
      transform: hoveredCard === chamado.id ? 'translateY(-4px)' : 'none',
      transition: 'all 0.3s ease-in-out',
      cursor: 'pointer',
    }}
  >
    <h3 style={{ margin: 0 }}>
      {chamado.categoria} - {chamado.subcategoria || 'Sem subcategoria'}
    </h3>
    <p style={{ margin: '8px 0' }}>{chamado.descricao}</p>
    <strong>Status: {chamado.status}</strong>
  </div>
))}

        </ul>
      )}
    </div>
  );
}

export default ListaChamado;
