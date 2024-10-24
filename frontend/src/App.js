import './App.css';
import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import ConsultaIPForm from './components/ConsultaIPForm';
import ConsultaIPInfo from './components/ConsultaIPInfo';
import { fetchConsultaIPData } from './services/consultaIPService';
import Estadisticas from './components/Estadisticas';



function App() {
  const [direccionIPInfo, setDireccionIPInfo] = useState(null);
  const [error, setError] = useState(null);

  const handleSearch = async (direccionIP) => {
    try {
      const data = await fetchConsultaIPData(direccionIP);
      setDireccionIPInfo(data);
      setError(null);
    } catch (error) {
      setError('Información no disponible.');
      setDireccionIPInfo(null);
    }
  };


  return (
    <Router>
    <div className="App">
      <h1>IP Informacion</h1>
      <ConsultaIPForm onSubmit={handleSearch} />
      {error && <p>{error}</p>}
      {direccionIPInfo && <ConsultaIPInfo IPInformacion={direccionIPInfo} />}

      <Link to="/Estadisticas">Ver Estadisticas</Link>
      <Routes>
      <Route exact path="/Estadisticas" element={<Estadisticas/>} />
      
      </Routes>
    </div>
    </Router>
  );
}

export default App;
