import { useState } from "react";
import { login } from "../../api/auth/login";
import { Link } from "react-router-dom"
import { useNavigate } from "react-router-dom";
import "./login.css"

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const token = await login(email, password);
      navigate("/home")
      localStorage.setItem("token", token);
    } catch (error) {
      setMessage(error.message);
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Iniciar sesión</h2>
        <input
          type="email"
          placeholder="Correo"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <br />
        <input
          type="password"
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <br />
        <button type="submit">Ingresar</button>
      </form>
      {/* 👇 Aquí agregamos el texto con el link */}
      <p>
        ¿Aún no te has registrado?<br />
        <Link to="/register">Regístrate aquí</Link>
      </p>
    </div>
  );
}
