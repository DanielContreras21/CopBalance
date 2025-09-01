import React, { useState, useEffect } from "react";
import { register } from "../../api/auth/register";
import { useNavigate, Link } from "react-router-dom";
import "./register.css";

export default function Register() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: "",
    confirmEmail: "",
    password: "",
    confirmPassword: "",
    name: "",
    lastName: "",
    phoneNumber: "",
  });

  const [isSubmitted, setIsSubmitted] = useState(false);
  const [fieldErrors, setFieldErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false); // Estado para el indicador de carga

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const phoneRegex = /^3\d{9}$/;

  useEffect(() => {
    document.title = "Registro";
  }, []);

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData({ ...formData, [id]: value });
  };

  const handlePhoneChange = (e) => {
    const { id, value } = e.target;
    const numericValue = value.replace(/\D/g, '');
    setFormData({ ...formData, [id]: numericValue });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitted(true);
    setFieldErrors({});

    let hasError = false;
    const errors = {};

    // Validación de email
    if (!formData.email) {
      errors.email = "Este campo es obligatorio.";
      hasError = true;
    } else if (!emailRegex.test(formData.email)) {
      errors.email = "Ingresa un correo válido.";
      hasError = true;
    }
    
    // Validación de confirmación de email
    if (!formData.confirmEmail) {
      errors.confirmEmail = "Este campo es obligatorio.";
      hasError = true;
    } else if (formData.email !== formData.confirmEmail) {
      errors.confirmEmail = "Los correos no coinciden.";
      hasError = true;
    }

    // Validación de contraseña
    if (!formData.password) {
      errors.password = "Este campo es obligatorio.";
      hasError = true;
    } else if (formData.password.length < 6) {
      errors.password = "La contraseña debe tener al menos 6 caracteres.";
      hasError = true;
    }

    // Validación de confirmación de contraseña
    if (!formData.confirmPassword) {
      errors.confirmPassword = "Este campo es obligatorio.";
      hasError = true;
    } else if (formData.password !== formData.confirmPassword) {
      errors.confirmPassword = "Las contraseñas no coinciden.";
      hasError = true;
    }

    // Validación de campos restantes
    if (!formData.name) {
      errors.name = "Este campo es obligatorio.";
      hasError = true;
    }
    if (!formData.lastName) {
      errors.lastName = "Este campo es obligatorio.";
      hasError = true;
    }

    // Validación del número de teléfono
    if (!formData.phoneNumber) {
      errors.phoneNumber = "Este campo es obligatorio.";
      hasError = true;
    } else if (!phoneRegex.test(formData.phoneNumber)) {
      errors.phoneNumber = "Ingresa un número de teléfono válido (ej. 3XXXXXXXXX).";
      hasError = true;
    }

    setFieldErrors(errors);
    
    if (hasError) {
      return;
    }

    // Mostrar el spinner de carga antes de la petición
    setIsLoading(true);

    // Petición al API después de la validación del cliente
    try {
      await register(formData);
      navigate("/login");
    } catch (error) {
      if (error.status === 409) {
        if (error.message.includes("correo electrónico")) {
          setFieldErrors({ ...errors, email: "El correo electrónico ya se encuentra registrado." });
        } else if (error.message.includes("teléfono")) {
          setFieldErrors({ ...errors, phoneNumber: "El teléfono ya se encuentra registrado." });
        }
      }
    } finally {
      // Ocultar el spinner de carga al finalizar la petición
      setIsLoading(false);
    }
  };

  return (
    <div className="register-container">
      {isLoading && (
        <div className="loading-overlay">
          <div className="loading-spinner"></div>
        </div>
      )}
      <div className="register-form-wrapper">
        <h2>Crea tu cuenta en Copbalance</h2>
        <form className="register-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <div className="input-wrapper">
              <input
                type="text"
                id="email"
                className="input-field"
                value={formData.email}
                onChange={handleChange}
                placeholder="Correo electrónico"
              />
              {isSubmitted && fieldErrors.email && <p className="error-message">{fieldErrors.email}</p>}
            </div>
            <div className="input-wrapper">
              <input
                type="text"
                id="confirmEmail"
                className="input-field"
                value={formData.confirmEmail}
                onChange={handleChange}
                placeholder="Confirmar correo"
              />
              {isSubmitted && fieldErrors.confirmEmail && <p className="error-message">{fieldErrors.confirmEmail}</p>}
            </div>
          </div>
          <div className="form-group">
            <div className="input-wrapper">
              <input
                type="password"
                id="password"
                className="input-field"
                value={formData.password}
                onChange={handleChange}
                placeholder="Contraseña"
              />
              {isSubmitted && fieldErrors.password && <p className="error-message">{fieldErrors.password}</p>}
            </div>
            <div className="input-wrapper">
              <input
                type="password"
                id="confirmPassword"
                className="input-field"
                value={formData.confirmPassword}
                onChange={handleChange}
                placeholder="Confirmar contraseña"
              />
              {isSubmitted && fieldErrors.confirmPassword && <p className="error-message">{fieldErrors.confirmPassword}</p>}
            </div>
          </div>
          <div className="form-group">
            <div className="input-wrapper">
              <input
                type="text"
                id="name"
                className="input-field"
                value={formData.name}
                onChange={handleChange}
                placeholder="Nombre"
              />
              {isSubmitted && fieldErrors.name && <p className="error-message">{fieldErrors.name}</p>}
            </div>
            <div className="input-wrapper">
              <input
                type="text"
                id="lastName"
                className="input-field"
                value={formData.lastName}
                onChange={handleChange}
                placeholder="Apellido"
              />
              {isSubmitted && fieldErrors.lastName && <p className="error-message">{fieldErrors.lastName}</p>}
            </div>
          </div>
          <div className="input-wrapper">
            <input
              type="tel"
              id="phoneNumber"
              className="input-field"
              value={formData.phoneNumber}
              onChange={handlePhoneChange}
              placeholder="Número de teléfono"
            />
            {isSubmitted && fieldErrors.phoneNumber && <p className="error-message">{fieldErrors.phoneNumber}</p>}
          </div>

          <button type="submit" className="btn-submit">
            Registrarse
          </button>
        </form>
        <p className="login-link">
          ¿Ya tienes cuenta? <Link to="/login">Inicia sesión aquí</Link>
        </p>
      </div>
    </div>
  );
}