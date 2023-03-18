package com.sliit.smile.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDto {
    private User user;
    private int score;
}
