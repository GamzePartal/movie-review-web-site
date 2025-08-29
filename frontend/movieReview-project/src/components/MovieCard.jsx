import React from "react";
import { Card, CardContent, Typography, Box } from "@mui/material";

export default function MovieCard({ movie }) {
  const width = 200;
  const height = 300;

  // fotoların çözünürlüğünü arttırmak için
  const highResUrl = movie.imageUrl?.includes("/upload/")
                    ? movie.imageUrl.replace("/upload/", `/upload/w_${width * 2},h_${height * 2},q_auto,f_auto/`): movie.imageUrl;

  return (
    <Card
      sx={{
        width: width,
        borderRadius: 3,
        boxShadow: 3,
        overflow: "hidden",
        m: 2,
      }}
    >
      <Box sx={{ position: "relative" }}>
        <img
          src={movie.imageUrl}
          srcSet={`${movie.imageUrl} 1x, ${highResUrl} 2x`}
          alt={movie.movieName}
          style={{
            width: "100%",
            height: height,
            objectFit: "cover",
            display: "block",
          }}
        />
        {/* yüzdelik etiketler */}
        <Box
          sx={{
            position: "absolute",
            bottom: 10,
            left: 10,
            bgcolor: "white",
            borderRadius: "50%",
            px: 1.2,
            py: 0.8,
            boxShadow: 2,
            fontWeight: "bold",
            fontSize: 14,
            color: "blue",
          }}
        >
          76%
        </Box>
      </Box>

      <CardContent>
        <Typography variant="h6" noWrap>
          {movie.movieName}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {movie.movieYear}
        </Typography>
      </CardContent>
    </Card>
  );
}
