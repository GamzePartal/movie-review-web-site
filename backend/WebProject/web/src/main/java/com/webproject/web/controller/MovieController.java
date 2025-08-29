package com.webproject.web.controller;

import com.webproject.web.dto.DtoMovie;
import com.webproject.web.dto.DtoMovieIU;
import com.webproject.web.service.IMovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {


    private final IMovieService movieService;

    @GetMapping()
    public List<DtoMovie> listAllMovies() {
        return movieService.listAll();
    }

    @GetMapping("/{id}")
    public DtoMovie getById(@PathVariable Long id) {
        return movieService.get(id);
    }


    @PostMapping
    public ResponseEntity<DtoMovie> create(@Valid @RequestBody DtoMovieIU dto) {
        DtoMovie created = movieService.create(dto);
        return ResponseEntity.created(URI.create("/api/movies/" + created.getId())).body(created);
    }



    @PutMapping("/{id}")
    public DtoMovie update(@PathVariable Long id, @Valid @RequestBody DtoMovieIU dto) {
        return movieService.update(id,dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
