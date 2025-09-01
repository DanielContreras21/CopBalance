import { useState, useEffect } from "react";
import { login } from "../../api/auth/login";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import "./login.css";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [fieldErrors, setFieldErrors] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);
  const navigate = useNavigate();

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  useEffect(() => {
      document.title = "Iniciar Sesión";
    }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitted(true);
    setFieldErrors({});

    let hasError = false;
    const errors = {};

    if (!email) {
      errors.email = "Este campo es obligatorio.";
      hasError = true;
    } else if (!emailRegex.test(email)) {
      errors.email = "Correo electrónico o contraseña incorrectos";
      hasError = true;
    }

    if (!password) {
      errors.password = "Este campo es obligatorio.";
      hasError = true;
    }

    setFieldErrors(errors);

    if (hasError) {
      return;
    }

    try {
      const token = await login(email, password);
      localStorage.setItem("token", token);
      navigate("/home");
    } catch (error) {
      const genericErrorMessage = "Correo electrónico o contraseña incorrectos";
      setFieldErrors({
        email: genericErrorMessage,
        password: genericErrorMessage,
      });
    }
  };

  return (
    <div className="login-container">
      <div className="login-hero-section">
        <div className="hero-content">
          <h1 className="hero-title">CopBalance</h1>
          <p className="hero-subtitle">
            Tu solución inteligente para el control financiero.
          </p>
        </div>
      </div>
      <div className="login-form-section">
        <form className="login-form" onSubmit={handleSubmit}>
          <h2>Ingresa a tu cuenta</h2>
          <div className="input-wrapper">
            <input
              type="text"
              placeholder="Correo electrónico"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {isSubmitted && fieldErrors.email && <p className="error-message">{fieldErrors.email}</p>}
          </div>
          <div className="input-wrapper">
            <input
              type="password"
              placeholder="Contraseña"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {isSubmitted && fieldErrors.password && <p className="error-message">{fieldErrors.password}</p>}
          </div>
          <button type="submit">Ingresar</button>
          <p>
            ¿Aún no te has registrado?<br />
            <Link to="/register">Regístrate aquí</Link>
          </p>
        </form>
      </div>
    </div>
  );
}