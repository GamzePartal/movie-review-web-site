package com.webproject.web.service.impl;

import com.webproject.web.dto.DtoReview;
import com.webproject.web.dto.DtoReviewIU;
import com.webproject.web.entity.Movie;
import com.webproject.web.entity.Review;
import com.webproject.web.entity.User;
import com.webproject.web.excepiton.BaseException;
import com.webproject.web.excepiton.ErrorMessage;
import com.webproject.web.excepiton.MessageType;
import com.webproject.web.repository.IMovieRepository;
import com.webproject.web.repository.IReviewRepository;
import com.webproject.web.repository.IUserRepository;
import com.webproject.web.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements IReviewService {

    private final IReviewRepository reviewRepository;
    private final IMovieRepository movieRepository;
    private final IUserRepository userRepository;
    private final org.modelmapper.ModelMapper modelMapper;

    @Override
    public List<DtoReview> listByMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId)
                .stream()
                .map(review -> modelMapper.map(review, DtoReview.class))
                .toList();
    }

    @Override
    public List<DtoReview> listByUser(Long userId) {
        return reviewRepository.findByUserId(userId)
                .stream()
                .map(review -> modelMapper.map(review, DtoReview.class))
                .toList();
    }


    @Override
    public DtoReview create(DtoReviewIU dto, Long userId){

        Movie movie = movieRepository.findById(dto.getMovieId().longValue())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "film bulunamdı")));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "kullanıcı bulunamdı")));

        Review review = new Review();
        review.setMovie(movie);
        review.setUser(user);
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        return modelMapper.map(reviewRepository.save(review), DtoReview.class);

    }






}
