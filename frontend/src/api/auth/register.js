import axios from "axios";

const API_URL = "http://localhost:8080/auth";

export async function register(data) {
  try {
    const response = await axios.post(`${API_URL}/register`, data, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.data;
  } catch (error) {
    console.error("Error en registerUser:", error);
    throw error.response?.data || error.message;
  }
}
