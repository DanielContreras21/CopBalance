import React, { useState, useEffect } from "react";
import { register } from "../../api/auth/register";
import { completeUser } from "../../api/user/completeUser";
import { accountExist } from "../../api/auth/accountExist";
import { useNavigate, Link } from "react-router-dom";
import "./register.css";

export default function Register() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: "",
    confirmEmail: "",
    password: "",
    confirmPassword: "",
    phoneNumber: "",
  });

  const [userFormData, setUserFormData] = useState({
    identificationType: "CC",
    identification: "",
    name: "",
    address: "",
  });

  const [step, setStep] = useState(1);
  const [isSubmitted, setIsSubmitted] = useState(false);
  const [fieldErrors, setFieldErrors] = useState({});
  const [isLoading, setIsLoading] = useState(false);

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const phoneRegex = /^3\d{9}$/;

  useEffect(() => {
    document.title = "Registro";
  }, []);

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData({ ...formData, [id]: value });
  };

  const handleUserChange = (e) => {
    const { id, value } = e.target;
    setUserFormData({ ...userFormData, [id]: value });
  };

  const handlePhoneChange = (e) => {
    const { id, value } = e.target;
    const numericValue = value.replace(/\D/g, "");
    setFormData({ ...formData, [id]: numericValue });
  };

  const handleNextStep = async (e) => {
    e.preventDefault();
    setIsSubmitted(true);
    setFieldErrors({});

    let hasError = false;
    const errors = {};

    // Client-side validation for Step 1
    if (!formData.email) {
      errors.email = "Este campo es obligatorio.";
      hasError = true;
    } else if (!emailRegex.test(formData.email)) {
      errors.email = "Ingresa un correo válido.";
      hasError = true;
    }

    if (!formData.confirmEmail) {
      errors.confirmEmail = "Este campo es obligatorio.";
      hasError = true;
    } else if (formData.email !== formData.confirmEmail) {
      errors.confirmEmail = "Los correos no coinciden.";
      hasError = true;
    }

    if (!formData.password) {
      errors.password = "Este campo es obligatorio.";
      hasError = true;
    } else if (formData.password.length < 6) {
      errors.password = "La contraseña debe tener al menos 6 caracteres.";
      hasError = true;
    }

    if (!formData.confirmPassword) {
      errors.confirmPassword = "Este campo es obligatorio.";
      hasError = true;
    } else if (formData.password !== formData.confirmPassword) {
      errors.confirmPassword = "Las contraseñas no coinciden.";
      hasError = true;
    }

    if (!formData.phoneNumber) {
      errors.phoneNumber = "Este campo es obligatorio.";
      hasError = true;
    } else if (!phoneRegex.test(formData.phoneNumber)) {
      errors.phoneNumber = "Ingresa un número de teléfono válido (ej. 3XXXXXXXXX).";
      hasError = true;
    }

    if (hasError) {
      setFieldErrors(errors);
      return;
    }

    try {
      const validateAccount = {
        email: formData.email,
        phoneNumber: formData.phoneNumber,
      };
      await accountExist(validateAccount);
      setStep(2);
    } catch (error) {
      const newErrors = { ...errors };
      if (error.status === 409) {
        if (error.message.includes("correo electrónico")) {
          newErrors.email = "El correo electrónico ya se encuentra registrado.";
        } else if (error.message.includes("teléfono")) {
          newErrors.phoneNumber = "El teléfono ya se encuentra registrado.";
        }
        setFieldErrors(newErrors);
      } else {
        // Handle other general errors
        setFieldErrors({ general: "Hubo un problema al registrar el usuario. Por favor, inténtalo de nuevo." });
      }
    } finally {
      setIsLoading(false);
    }
  };

  const handleUserSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitted(true);
    setFieldErrors({});

    let hasError = false;
    const errors = {};

    // Client-side validation for Step 2
    if (!userFormData.name) {
      errors.name = "El nombre es obligatorio.";
      hasError = true;
    }
    if (!userFormData.identification) {
      errors.identification = "La identificación es obligatoria.";
      hasError = true;
    }
    if (!userFormData.address) {
      errors.address = "La dirección es obligatoria.";
      hasError = true;
    }

    if (hasError) {
      setFieldErrors(errors);
      return;
    }

    setIsLoading(true);

    try {
      const registerResponse = await register(formData);
      await new Promise(resolve => setTimeout(resolve, 500));
      await completeUser({
        ...userFormData,
        id: registerResponse.id,
      });
      navigate("/login");
    } catch (error) {
      console.error("Error en el registro o al completar el perfil:", error);
      const newErrors = { ...errors };
      
      // Check if the error is a 409 Conflict from the server
      if (error.status === 409) {
        setStep(1); // Go back to the registration form

        if (error.message.includes("correo electrónico")) {
          newErrors.email = "El correo electrónico ya se encuentra registrado.";
        } else if (error.message.includes("teléfono")) {
          newErrors.phoneNumber = "El teléfono ya se encuentra registrado.";
        }
        setFieldErrors(newErrors);
      } else {
        // Handle other general errors
        setFieldErrors({ general: "Hubo un problema al registrar el usuario. Por favor, inténtalo de nuevo." });
      }
    } finally {
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

        {step === 1 && (
          <form className="register-form" onSubmit={handleNextStep}>
            {fieldErrors.general && <p className="error-message general-error">{fieldErrors.general}</p>}
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
              Siguiente
            </button>
          </form>
        )}

        {step === 2 && (
          <form className="register-form" onSubmit={handleUserSubmit}>
            <button onClick={() => setStep(1)} className="btn-back">
              &lt; Volver
            </button>
            {fieldErrors.general && <p className="error-message general-error">{fieldErrors.general}</p>}
            <div className="form-group">
              <div className="input-wrapper">
                <label htmlFor="identificationType">Tipo de identificación</label>
                <select
                  id="identificationType"
                  className="input-field"
                  value={userFormData.identificationType}
                  onChange={handleUserChange}
                >
                  <option value="CC">Cédula de Ciudadanía</option>
                  <option value="NIT">NIT</option>
                </select>
              </div>
              <div className="input-wrapper">
                <label htmlFor="identification">Número de identificación</label>
                <input
                  type="text"
                  id="identification"
                  className="input-field"
                  value={userFormData.identification}
                  onChange={handleUserChange}
                  placeholder="Número de identificación"
                />
                {isSubmitted && fieldErrors.identification && <p className="error-message">{fieldErrors.identification}</p>}
              </div>
            </div>

            <div className="input-wrapper">
              <label htmlFor="name">Nombre completo</label>
              <input
                type="text"
                id="name"
                className="input-field"
                value={userFormData.name}
                onChange={handleUserChange}
                placeholder="Nombre completo"
              />
              {isSubmitted && fieldErrors.name && <p className="error-message">{fieldErrors.name}</p>}
            </div>

            <div className="input-wrapper">
              <label htmlFor="address">Dirección de residencia</label>
              <input
                type="text"
                id="address"
                className="input-field"
                value={userFormData.address}
                onChange={handleUserChange}
                placeholder="Dirección"
              />
              {isSubmitted && fieldErrors.address && <p className="error-message">{fieldErrors.address}</p>}
            </div>
            <button type="submit" className="btn-submit">
              Registrarse
            </button>
          </form>
        )}

        <p className="login-link">
          ¿Ya tienes cuenta? <Link to="/login">Inicia sesión aquí</Link>
        </p>
      </div>
    </div>
  );
}