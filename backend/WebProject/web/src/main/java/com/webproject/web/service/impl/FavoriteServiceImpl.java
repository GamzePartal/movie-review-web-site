package com.webproject.web.service.impl;

import com.webproject.web.dto.DtoFavorite;
import com.webproject.web.dto.DtoFavoriteIU;
import com.webproject.web.entity.Favorite;
import com.webproject.web.entity.Movie;
import com.webproject.web.entity.User;
import com.webproject.web.excepiton.BaseException;
import com.webproject.web.excepiton.ErrorMessage;
import com.webproject.web.excepiton.MessageType;
import com.webproject.web.repository.IFavoriteRepository;
import com.webproject.web.repository.IMovieRepository;
import com.webproject.web.repository.IUserRepository;
import com.webproject.web.service.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements IFavoriteService {


    private final IFavoriteRepository favoriteRepository;
    private final IMovieRepository movieRepository;
    private final IUserRepository userRepository;
    private final org.modelmapper.ModelMapper modelMapper;


    @Override
    public List<DtoFavorite> listAll(Long userId) {
        return favoriteRepository.findByUserId(userId)
                .stream()
                .map(favorite -> modelMapper.map(favorite, DtoFavorite.class))
                .toList();

    }


    @Override
    public DtoFavorite create(DtoFavoriteIU dto, Long userId) {
        Movie movie = movieRepository.findById(dto.getMovieId().longValue())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "film bulunamdı")));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "kullanıcı bulunamdı")));

        Favorite favorite = new Favorite();
        favorite.setMovie(movie);
        favorite.setUser(user);

        return modelMapper.map(favoriteRepository.save(favorite), DtoFavorite.class);
    }

    @Override
    public DtoFavorite get(Long id){
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "fav yok knk")));
        return modelMapper.map(favorite,DtoFavorite.class);
    }



    @Override
    public void delete(Long id, Long userId) {
        Favorite fav = favoriteRepository.findByIdAndUserId(id,userId)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "fav yok cnm")));
        favoriteRepository.delete(fav);
    }
}
