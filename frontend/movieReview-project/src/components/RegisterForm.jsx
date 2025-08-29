import React from "react";
import { useFormik } from "formik";
import * as Yup from "yup";
import {TextField,Button,Checkbox,FormControlLabel,Box,Typography,IconButton,InputAdornment,Card,CardContent,Link} from "@mui/material";
import {Visibility,VisibilityOff,Email,Lock,Person} from "@mui/icons-material";
import { useNavigate } from "react-router-dom";
import axios from 'axios';

export default function RegisterForm() {
  const navigate = useNavigate();
  const [showPassword, setShowPassword] = React.useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = React.useState(false);

  const formik = useFormik({
    initialValues: {
      name: "",
      surname: "",
      email: "",
      password: "",
      confirmPassword: "",
      term: false
    },


    validationSchema: Yup.object({
      name: Yup.string().required("İsim zorunludur"),
      surname: Yup.string().required("Soyad zorunludur"),
      email: Yup.string()
        .email("Geçerli email giriniz")
        .required("Email zorunludur"),
      password: Yup.string()
        .min(6, "Şifre en az 6 karakter olmalı")
        .required("Şifre zorunludur"),
      confirmPassword: Yup.string()
        .oneOf([Yup.ref("password"), null], "Şifreler eşleşmiyor")
        .required("Şifre tekrar zorunludur"),
      term: Yup.boolean().oneOf(
        [true],
        "Kullanıcı sözleşmesini onaylamalısınız"
      )
    }),

    onSubmit: async (values) => {
      try {
        
        const requestBody = {
          username: values.name,   
          password: values.password,
          email: values.email
        };

        const response = await axios.post(
          "http://localhost:8080/api/auth/register", requestBody,{ withCredentials: false }
        );

        console.log("Kayıt başarılı:", response.data);
        alert("Kayıt başarılı şimdi login olman lazım");
        navigate("/login");
      } catch (error) {
        console.error("Kayıt hatası mlsf:", error);
        alert("Kayıt başarısızzz");
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
        bgcolor: "#f5f5f5"
      }}
    >
      <Card sx={{ maxWidth: 400, boxShadow: 5, borderRadius: 3 }}>
        <CardContent sx={{ p: 4 }}>
          {/*form başlığ*/}
          <Typography variant="h5" align="center" gutterBottom fontWeight="bold">
            Kayıt Ol
          </Typography>

          <form onSubmit={formik.handleSubmit}>
            {/* isim kısmı */}
            <TextField
              fullWidth
              margin="normal"
              label="İsim"
              name="name"
              value={formik.values.name} 
              onChange={formik.handleChange}
              error={formik.touched.name && Boolean(formik.errors.name)}
              helperText={formik.touched.name && formik.errors.name}
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <Person />
                  </InputAdornment>
                )
              }}
            />

            {/* Soyad kısmı */}
            <TextField
              fullWidth
              margin="normal"
              label="Soyad"
              name="surname"
              value={formik.values.surname}
              onChange={formik.handleChange}
              error={formik.touched.surname && Boolean(formik.errors.surname)}
              helperText={formik.touched.surname && formik.errors.surname}
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <Person />
                  </InputAdornment>
                )
              }}
            />

            {/* email için */}
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
                )
              }}
            />

            {/* şifre için */}
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
                    <IconButton
                      onClick={() => setShowPassword(!showPassword)}
                    >
                      {showPassword ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                  </InputAdornment>
                )
              }}
            />

            {/* şifre tekrarı için */}
            <TextField
              fullWidth
              margin="normal"
              type={showConfirmPassword ? "text" : "password"}
              label="Şifre Tekrarı"
              name="confirmPassword"
              value={formik.values.confirmPassword}
              onChange={formik.handleChange}
              error={
                formik.touched.confirmPassword &&
                Boolean(formik.errors.confirmPassword)
              }
              helperText={
                formik.touched.confirmPassword && formik.errors.confirmPassword
              }
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <Lock />
                  </InputAdornment>
                ),
                endAdornment: (
                  <InputAdornment position="end">
                    <IconButton
                      onClick={() =>
                        setShowConfirmPassword(!showConfirmPassword)
                      }
                    >
                      {showConfirmPassword ? (
                        <VisibilityOff />
                      ) : (
                        <Visibility />
                      )}
                    </IconButton>
                  </InputAdornment>
                )
              }}
            />

            {/* sözleşme olayı */}
            <FormControlLabel
              control={
                <Checkbox
                  name="term"
                  checked={formik.values.term}
                  onChange={formik.handleChange}
                />
              }
              label="Kullanıcı sözleşmesini onaylıyorum"
            />
            {formik.touched.term && formik.errors.term && (
              <Typography variant="body2" color="error">
                {formik.errors.term}
              </Typography>
            )}

            {/* kayıt ol butonu */}
            <Button
              fullWidth
              variant="outlined"
              sx={{ mt: 2, fontWeight: "bold" }}
              type="submit"
              color= "#ea3a3aff"
            >
              KAYIT OL
            </Button>
          </form>

          {/* üye misin diye sor */}
          <Typography align="center" sx={{ mt: 2 }}>
            <Link
              component="button"
              onClick={() => navigate("/login")}
              underline="hover"
            >
              Zaten üye misin? Giriş yap
            </Link>
          </Typography>
        </CardContent>
      </Card>
    </Box>
  );
}
