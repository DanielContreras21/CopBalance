import React, { useState } from "react";
import { register } from "../../api/auth/register";
import { useNavigate } from "react-router-dom";
import "./register.css"

export default function Register() {
  const navigate = useNavigate();

  // Estado para guardar los valores del formulario
  const [formData, setFormData] = useState({
    email: "",
    confirmEmail: "",
    password: "",
    confirmPassword: "",
    name: "",
    lastName: "",
    phoneNumber: ""
  });

  // Estado para mostrar errores o éxito
  const [message, setMessage] = useState("");

  // Manejo de cambios en inputs
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
  };

  // Manejo de envío del formulario
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await register(formData);
      setMessage("✅ Registro exitoso!");
      // Redirigir al login
      setTimeout(() => navigate("/login"), 1500);
    } catch (error) {
      setMessage("❌ Error en el registro. Revisa tus datos.");
    }
  };

  return (
    <div className="register-container">
      <h2>Registro</h2>
      <form className="register-form" onSubmit={handleSubmit}>
        <label>Email:</label>
        <input
          type="email"
          id="email"
          className="input-field"
          value={formData.email}
          onChange={handleChange}
        />

        <label>Confirmar Email:</label>
        <input
          type="email"
          id="confirmEmail"
          className="input-field"
          value={formData.confirmEmail}
          onChange={handleChange}
        />

        <label>Password:</label>
        <input
          type="password"
          id="password"
          className="input-field"
          value={formData.password}
          onChange={handleChange}
        />

        <label>Confirmar Password:</label>
        <input
          type="password"
          id="confirmPassword"
          className="input-field"
          value={formData.confirmPassword}
          onChange={handleChange}
        />

        <label>Nombre:</label>
        <input
          type="text"
          id="name"
          className="input-field"
          value={formData.name}
          onChange={handleChange}
        />

        <label>Apellido:</label>
        <input
          type="text"
          id="lastName"
          className="input-field"
          value={formData.lastName}
          onChange={handleChange}
        />

        <label>Teléfono:</label>
        <input
          type="text"
          id="phoneNumber"
          className="input-field"
          value={formData.phoneNumber}
          onChange={handleChange}
        />

        <button type="submit" className="btn-submit">
          Registrarse
        </button>
      </form>
      {message && <p>{message}</p>}
      <p>
        ¿Ya tienes cuenta? <a href="/login">Inicia sesión aquí</a>
      </p>
    </div>
  );
}
