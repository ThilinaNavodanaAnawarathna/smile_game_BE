package com.sliit.smile.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopPlayer {
    private String name;
    private int score;
}
