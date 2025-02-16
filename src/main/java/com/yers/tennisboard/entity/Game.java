package com.yers.tennisboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Tenis_Count")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName1;
    private String fullName2;
    private Integer score1;
    private Integer score2;
    private Integer set1;
    private Integer set2;
    private Integer game1;
    private Integer game2;
}
