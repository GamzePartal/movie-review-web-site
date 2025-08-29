package com.webproject.web.service;

import com.webproject.web.dto.DtoMovieIU;
import com.webproject.web.dto.DtoReview;
import com.webproject.web.dto.DtoReviewIU;

import java.util.List;

public interface IReviewService {

    List<DtoReview> listByMovie(Long movieId);
    List<DtoReview> listByUser(Long userId);
    DtoReview create(DtoReviewIU dto, Long userId);
}
