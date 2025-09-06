import axios from "axios";

const API_URL = "http://localhost:8080/auth";

export const accountExist = async (data) => {
  try {
    const response = await axios.post(`${API_URL}/accountExist`, data, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.data; 
  } catch (error) {
    console.error("Error en accountExist:", error);
    throw error.response?.data || error.message;
  }
};
