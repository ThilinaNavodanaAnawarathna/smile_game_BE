package com.sliit.smile.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private User user;
    private String difficultyLevel;
    private Boolean isCorrect;
}
