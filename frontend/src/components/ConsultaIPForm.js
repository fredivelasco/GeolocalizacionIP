import React, { useState } from 'react';

function ConsultaIPForm({ onSubmit }) {
  const [direccionIP, setDireccionIP] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (direccionIP.trim()) {
      onSubmit(direccionIP);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Ingrese la direccion IP a consultar"
        value={direccionIP}
        onChange={(e) => setDireccionIP(e.target.value)}
      />
      <button type="submit">Buscar</button>
    </form>
  );
}

export default ConsultaIPForm;