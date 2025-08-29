package com.webproject.web.service;

import com.webproject.web.dto.DtoMovie;
import com.webproject.web.dto.DtoMovieIU;

import java.util.List;

public interface IMovieService {

    List<DtoMovie> listAll();
    DtoMovie get(Long id);
    DtoMovie create(DtoMovieIU dtoMovieIU);
    DtoMovie update(Long id, DtoMovieIU dtoMovieIU);
    void delete(Long id);


}
