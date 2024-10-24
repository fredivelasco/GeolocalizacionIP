import axios from 'axios';

//const API_URL = "http://172.18.0.3:8080/api/consulta/";

const API_URL = "http://backend:8080/api/consulta/";

export const fetchConsultaIPData = async (direccionIP) => {

 
  const response = await axios.get(API_URL+direccionIP);

  const direccionIPInfo = response.data;
 
  return direccionIPInfo;
};