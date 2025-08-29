import React, { useEffect, useState } from "react";
import axios from "axios";
import { Grid, Container } from "@mui/material";
import MovieCard from "../components/MovieCard";
import SearchSection from "../components/SearchSection"; // Üst kısım (slider + arama)

export default function Home() {
  const [movies, setMovies] = useState([]);
  const featuredMovies = movies.filter(movie => 
  [3,11,12,13,14,20,24,25,26].includes(movie.id)
);

  useEffect(() => {
    axios.get("http://localhost:8080/api/movies").then((res) => {
      setMovies(res.data);
    });
  }, []);

  return (
    <>
      {/* Üst kısım (arka plan slider + arama) */}
      <SearchSection  movies={featuredMovies} />

      {/* Film kartları */}
      <Container sx={{ mt: 4 }}>
        <Grid container spacing={3} justifyContent="center">
          {movies.map((movie) => (
            <Grid item key={movie.id} xs={12} sm={6} md={4} lg={3}>
              <MovieCard movie={movie} />
            </Grid>
          ))}
        </Grid>
      </Container>
    </>
  );
}
