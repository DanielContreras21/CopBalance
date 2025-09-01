import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./home.css";


export default function Home() {
  const [message, setMessage] = useState("");
  const [token, setToken] = useState(localStorage.getItem("token"));
  const navigate = useNavigate();

  const logout = () => {
       try {
         localStorage.clear;
         setToken(null);
         navigate("/login");
       } catch (error) {
         setMessage(error.message);
       }
     };

  return (
    <div>
      <h1>
        Página Principal
        {token}
        <button onClick={logout}>Cerrar sesión</button>
      </h1>
    </div>
  );
}
