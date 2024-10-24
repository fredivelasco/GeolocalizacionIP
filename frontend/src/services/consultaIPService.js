import axios from 'axios';

const API_URL = "http://localhost:8080/api/consulta/";

export const fetchConsultaIPData = async (direccionIP) => {

 
  const response = await axios.get(API_URL+direccionIP);

  const direccionIPInfo = response.data;
 
  return direccionIPInfo;
};