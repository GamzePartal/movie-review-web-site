package com.webproject.web.repository;

import com.webproject.web.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {


    List<Review> findByMovieId(Long movieId); //movieye göre review listesi
    List<Review> findByUserId(Long userId); //Usera göre review listesi
}
