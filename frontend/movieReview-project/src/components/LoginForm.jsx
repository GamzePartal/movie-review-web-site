import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import {TextField,Button,Checkbox,FormControlLabel,Box,Typography,Link,IconButton,InputAdornment,Card,CardContent,} from "@mui/material";
import { Visibility, VisibilityOff, Email, Lock } from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function LoginForm() {
  const navigate = useNavigate();
  const [showPassword, setShowPassword] = React.useState(false);

  const formik = useFormik({
    initialValues: { email: "", password: "", remember: false },
    validationSchema: Yup.object({
      email: Yup.string().email("Geçerli bir e-posta giriniz").required("E-posta zorunludur"),
      password: Yup.string().required("Şifre zorunludur"),
    }),

    onSubmit: async (values) => {
      try {
        const response = await axios.post("http://localhost:8080/api/auth/login", {
          email: values.email,
          password: values.password,
        });

        console.log("Giriş tamamdır:", response.data);

        // Tokenı buraya kaydet
        localStorage.setItem("token", response.data.token);

        alert("giriş yaptınn h.o");
        navigate("/"); //giriş yapınca anasayfa gider
      } catch (error) {
        console.error("Giriş hatası:", error);
        alert("Giriş yapamıyorsun eksik bişiler var demekki");
      }
    },
  });

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        bgcolor: "#f5f5f5",
      }}
    >
      <Card sx={{ maxWidth: 400, boxShadow: 5, borderRadius: 3 }}>
        <CardContent sx={{ p: 4 }}>
          {/* başlık kısmı */}
          <Typography variant="h5" align="center" gutterBottom fontWeight="bold">
            Giriş Yap
          </Typography>

          <form onSubmit={formik.handleSubmit}>
            {/* e-posta kısmı */}
            <TextField
              fullWidth
              margin="normal"
              label="E-posta"
              name="email"
              value={formik.values.email}
              onChange={formik.handleChange}
              error={formik.touched.email && Boolean(formik.errors.email)}
              helperText={formik.touched.email && formik.errors.email}
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <Email />
                  </InputAdornment>
                ),
              }}
            />

            {/* şifre kısmııı */}
            <TextField
              fullWidth
              margin="normal"
              type={showPassword ? "text" : "password"}
              label="Şifre"
              name="password"
              value={formik.values.password}
              onChange={formik.handleChange}
              error={formik.touched.password && Boolean(formik.errors.password)}
              helperText={formik.touched.password && formik.errors.password}
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <Lock />
                  </InputAdornment>
                ),
                endAdornment: (
                  <InputAdornment position="end">
                    <IconButton onClick={() => setShowPassword(!showPassword)}>
                      {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                ),
              }}
            />

            {/* beni hatırla + şifre unutma  */}
            <Box
              sx={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
              }}
            >
              <FormControlLabel
                control={
                  <Checkbox
                    name="remember"
                    checked={formik.values.remember}
                    onChange={formik.handleChange}
                  />
                }
                label="Beni Hatırla"
              />
              <Link href="#" underline="hover">
                Şifremi unuttum
              </Link>
            </Box>

            {/* giriş butonu için */}
            <Button
              fullWidth
              variant="contained"
              sx={{ mt: 2, fontWeight: "bold", backgroundColor: "#ea3a3aff" }}
              type="submit"
            >
              Giriş Yap
            </Button>
          </form>

          {/* registere gönderme kısmı */}
          <Typography align="center" sx={{ mt: 2 }}>
            Hesabınız yok mu?{" "}
            <Link component="button" onClick={() => navigate("/register")} underline="hover">
              Kayıt Ol
            </Link>
          </Typography>
        </CardContent>
      </Card>
    </Box>
  );
}
