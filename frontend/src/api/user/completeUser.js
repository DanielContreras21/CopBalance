import axios from "axios";

const API_URL = "http://localhost:8080/users";

export async function completeUser(data) {
  try {
    const response = await axios.post(`${API_URL}/completeUserProfile`, data, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.data;
  } catch (error) {
    console.error("Error al completar los datos del usuario:", error);
    throw error.response?.data || error.message;
  }
}
