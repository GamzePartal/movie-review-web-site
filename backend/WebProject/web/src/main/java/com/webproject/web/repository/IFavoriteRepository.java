package com.webproject.web.repository;

import com.webproject.web.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFavoriteRepository extends JpaRepository<Favorite, Long > {

    List<Favorite> findByUserId(Long userId); //kullanıcının tüm favları
    Optional<Favorite> findByIdAndUserId(Long id, Long userId);



}
