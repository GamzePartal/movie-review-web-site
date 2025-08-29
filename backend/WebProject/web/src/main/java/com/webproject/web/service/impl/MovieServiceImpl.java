package com.webproject.web.service.impl;
import com.webproject.web.dto.DtoMovie;
import com.webproject.web.dto.DtoMovieIU;
import com.webproject.web.entity.Movie;
import com.webproject.web.excepiton.BaseException;
import com.webproject.web.excepiton.ErrorMessage;
import com.webproject.web.excepiton.MessageType;
import com.webproject.web.repository.IMovieRepository;
import com.webproject.web.service.IMovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements IMovieService {


    private final IMovieRepository movieRepository;
    private final org.modelmapper.ModelMapper modelMapper;


    @Override
    public List<DtoMovie> listAll() {
       return movieRepository.findAll()
               .stream()
               .map(movie -> modelMapper.map(movie, DtoMovie.class))
               .toList();
    }

    @Override
    public DtoMovie get(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "film bulunamdı")));
        return modelMapper.map(movie, DtoMovie.class);
    }

    //movie ekleme
    @Override
    public DtoMovie create(DtoMovieIU dtoMovieIU) {
        Movie movie = new Movie();
        movie.setMovieName(dtoMovieIU.getMovieName());
        movie.setMovieType(dtoMovieIU.getMovieType());
        movie.setMovieYear(dtoMovieIU.getMovieYear());
        movie.setDescription(dtoMovieIU.getDescription());
        movie.setImageUrl(dtoMovieIU.getImageUrl());

        Movie saved = movieRepository.save(movie);
        return modelMapper.map(saved, DtoMovie.class);
    }

    @Override
    public DtoMovie update(Long id, DtoMovieIU dtoMovieIU) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "film bulunamdı")));


        movie.setMovieName(dtoMovieIU.getMovieName());
        movie.setMovieType(dtoMovieIU.getMovieType());
        movie.setMovieYear(dtoMovieIU.getMovieYear());
        movie.setDescription(dtoMovieIU.getDescription());
        movie.setImageUrl(dtoMovieIU.getImageUrl());

        Movie updated = movieRepository.save(movie);
        return modelMapper.map(updated, DtoMovie.class);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }
}
