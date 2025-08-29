package com.webproject.web.controller;

import com.webproject.web.dto.DtoFavorite;
import com.webproject.web.dto.DtoFavoriteIU;
import com.webproject.web.service.IFavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final IFavoriteService favoriteService;

    @GetMapping("/user/{userId2}")
    public ResponseEntity<List<DtoFavorite>> list(@PathVariable Long userId2){
        List<DtoFavorite> result = favoriteService.listAll(userId2);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<DtoFavorite> get(@PathVariable Long userId) {
        DtoFavorite dto = favoriteService.get(userId);
        return  ResponseEntity.ok(dto);
    }

    @PostMapping(path = "/user/{userId}")
    public ResponseEntity<DtoFavorite> create(@Valid @RequestBody DtoFavoriteIU dto, @PathVariable Long userId){
        DtoFavorite created = favoriteService.create(dto, userId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}/user/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @PathVariable Long userId){
        favoriteService.delete(id, userId);
        return  ResponseEntity.noContent().build();
    }




}
