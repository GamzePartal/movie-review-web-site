import React from "react";
import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();
  const token = localStorage.getItem("token"); // giriş kontrolü

  const handleLogout = () => {
    localStorage.removeItem("token"); // token sil
    navigate("/login"); // login sayfasına yönlendir
  };

  return (
    <nav className="navbar">
      {token ? (
        // Kullanıcı giriş yaptıysa
        <>
          <Link to="/">Anasayfa</Link> |{" "}
          <Link to="/movies">Filmler</Link> |{" "}
          <Link to="/series">Diziler</Link> |{" "}
          <Link to="/profile">Profil</Link> |{" "}
          <button onClick={handleLogout} style={{ background: "none", border: "none", cursor: "pointer", color: "white" , fontSize: "16", fontWeight:"bold" }}>
            Çıkış Yap
          </button>
        </>
      ) : (
        // Giriş yapmadıysa
        <>
          <Link to="/">Anasayfa</Link> |{" "}
          <Link to="/login">Giriş</Link> |{" "}
          <Link to="/register">Kayıt Ol</Link>
        </>
      )}
    </nav>
  );
}
