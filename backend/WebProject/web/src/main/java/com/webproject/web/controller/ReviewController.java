package com.webproject.web.controller;

import com.webproject.web.dto.DtoReview;
import com.webproject.web.dto.DtoReviewIU;
import com.webproject.web.service.IReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {


    private final IReviewService reviewService;

    @GetMapping("/movie/{movieId}")
    public List<DtoReview> getByMovie(@PathVariable Long movieId) {
        return reviewService.listByMovie(movieId);
    }

    @GetMapping("/user/{userId}")
    public List<DtoReview> getByUser(@PathVariable Long userId){
        return reviewService.listByUser(userId);
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<DtoReview> create(@Valid @RequestBody DtoReviewIU dto, Principal principal){
        Long userId = Long.valueOf(principal.getName());
        DtoReview created = reviewService.create(dto,userId);
        return ResponseEntity.created(URI.create("/api/reviews/"+ created.getId())).body(created);
    }


}
