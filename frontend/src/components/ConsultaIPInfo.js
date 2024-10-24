import React from 'react';

function ConsultaIPInfo({ IPInformacion }) {
  return (
    <div>
      <h2>  {IPInformacion.direccionIp}</h2>
      <p>Fecha Actual: {IPInformacion.fechaActual}</p>
      <p>Nombre País: {IPInformacion.nombrePais}</p>
      <p>Código ISO: {IPInformacion.codigoISOPais}</p>
      <p>Idiomas: {IPInformacion.idiomaPais}</p>
      <p>Moneda: {IPInformacion.monedaPais}</p>
      <p>Hora: {IPInformacion.horaPais}</p>
      <p>Distancia Estimada: {IPInformacion.distanciaPais}</p>
      
    </div>
  );
}

export default ConsultaIPInfo;