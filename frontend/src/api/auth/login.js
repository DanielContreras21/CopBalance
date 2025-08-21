import axios from "axios";

const API_URL = "http://localhost:8080/auth";

export const login = async (email, password) => {
  try {
    const response = await axios.post(`${API_URL}/login`, {
      email,
      password,
    });
    return response.data; 
  } catch (error) {
    if (error.response) {
      throw new Error(error.response.data || "Error en el login");
    } else {
      throw new Error("No se pudo conectar con el servidor");
    }
  }
};
