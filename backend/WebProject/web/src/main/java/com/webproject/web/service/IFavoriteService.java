package com.webproject.web.service;

import com.webproject.web.dto.DtoFavorite;
import com.webproject.web.dto.DtoFavoriteIU;
import com.webproject.web.dto.DtoMovieIU;

import java.util.List;

public interface IFavoriteService {

    List<DtoFavorite> listAll(Long userId);
    DtoFavorite create(DtoFavoriteIU dto, Long userId);
    DtoFavorite get(Long id);  //fav id
    void delete(Long id, Long userId);
}
