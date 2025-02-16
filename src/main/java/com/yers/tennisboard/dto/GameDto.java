package com.yers.tennisboard.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameDto {
    private String fullName1;
    private String fullName2;
    private Integer score1;
    private Integer score2;
    private Integer set1;
    private Integer set2;
    private Integer game1;
    private Integer game2;
}
