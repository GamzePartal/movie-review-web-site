package com.webproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoFavorite {

    private Long id;
    private String movieName;
    private Long movieId;
}
