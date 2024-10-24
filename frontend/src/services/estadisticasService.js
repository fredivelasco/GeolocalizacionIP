import axios from 'axios';

const API_URL = "http://172.18.0.3:8080/api/consulta/estadisticas";

export const fetchEstadisticas = async () => {


  
  const response = await axios.get(API_URL);

  const estadisticasInfo = response.data;
 
  return estadisticasInfo;
};