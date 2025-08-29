package com.webproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoMovie {

    private Long id;
    private String movieName;
    private String movieType;
    private Integer movieYear;
    private String description;
    private String imageUrl;
}
