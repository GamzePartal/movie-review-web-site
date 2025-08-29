package com.webproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoReview {

    private Integer id;
    private String comment;
    private Integer rating;
    private String movieName;
    private String username;
    private LocalDateTime createdAt;
}
