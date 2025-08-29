package com.webproject.web.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoReviewIU {

    private Long movieId;
    private String comment;
    private Integer rating;
}
