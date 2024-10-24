import React, { useEffect, useState } from 'react';
import { fetchEstadisticas } from '../services/estadisticasService'; 

const Estadisticas = () => {
  const [stats, setStats] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Llama a la API para obtener las estadísticas
    const getStatistics = async () => {
      try {
        const data = await fetchEstadisticas();
        setStats(data);
        setError(null);
      } catch (err) {
        setError('Error fetching statistics.');
      }
    };

    getStatistics();
  }, []);

  if (error) return <p>{error}</p>;
  if (!stats) return <p>Cargando estadisticas...</p>;

  return (
    <div >
      <h2>Estadisticas de Uso</h2>
     
 

      <table>
        <thead>
          <tr>
            
            <th>País</th>
            <th>Distancia (km)</th>
            <th>Invocaciones</th>
          </tr>
        </thead>
        <tbody>
          {stats.estadisticas.map((stat, index) => (
            <tr key={index}>
             
              <td>{stat.pais.trim()}</td>
              <td>{stat.distancia.toFixed(2)}</td>
              <td>{stat.invocaciones}</td>
            </tr>
          ))}
        </tbody>
      </table>



      <p><strong>Distancia promedio:</strong> {stats.promedio.toFixed(2)} km</p>
      
    </div>
  );
};

export default Estadisticas;