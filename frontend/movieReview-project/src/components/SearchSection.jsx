import React from "react";
import Slider from "react-slick";
import { Box, Typography, TextField, Button } from "@mui/material";

export default function SearchSection({ movies }) {
  const settings = {
    infinite: true,
    autoplay: true,
    autoplaySpeed: 4000,
    arrows: false,
    fade: true,
  };

  return (
    <Box sx={{ position: "relative", height: "60vh", overflow: "hidden" }}>
      {/* Slider */}
      <Slider {...settings}>
        {movies.map((movie) => (
          <Box
            key={movie.id}
            sx={{
              height: "60vh",
              backgroundImage: `url(${movie.imageUrl})`,
              backgroundSize: "cover",
              backgroundPosition: "center",
              filter: "brightness(0.7)",
            }}
          />
        ))}
      </Slider>

      {/* arama çubuğu*/}
      <Box
        sx={{
          position: "absolute",
          top: 0,
          left: 0,
          width: "100%",
          height: "100%",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
          color: "white",
          textAlign: "center",
          px: 2,
        }}
      >
        <Typography variant="h3" fontWeight="bold">
          Hoş Geldiniz!
        </Typography>
        <Typography variant="h6" sx={{ mb: 3 }}>
          aradığın dizi/film için doğru yerdesin 
        </Typography>

        <Box sx={{ display: "flex", width: "60%", maxWidth: 600 }}>
          <TextField
            variant="outlined"
            placeholder="Film veya dizi ara..."
            fullWidth
            sx={{
              bgcolor: "white",
              borderRadius: "30px 0 0 30px",
              "& fieldset": { border: "none" },
            }}
          />
          <Button
            variant="contained"
            sx={{
              borderRadius: "0 30px 30px 0",
              px: 4,
              bgcolor: "#ea3a3aff",
              textTransform: "none",
            }}
          >
            Ara
          </Button>
        </Box>
      </Box>
    </Box>
  );
}
