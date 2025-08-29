package com.webproject.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoMovieIU {


    private String movieName;
    private String movieType;
    private Integer movieYear;
    private String description;
    private String imageUrl;
}
