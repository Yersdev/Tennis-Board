package com.yers.tennisboard.repository;

import com.yers.tennisboard.entity.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GameRepositoryTests {

    @Autowired
    private GameRepository gameRepository;

    @Test
    @DisplayName("Сохранение и поиск игры")
    void saveAndFindGame() {
        Game game = new Game(null, "Player One", "Player Two", 6, 4, 2, 1, 3, 2);

        Game savedGame = gameRepository.save(game);
        List<Game> games = gameRepository.findAll();

        assertThat(savedGame.getId()).isNotNull();
        assertThat(games).hasSize(1);
        assertThat(games.get(0).getFullName1()).isEqualTo("Player One");
    }

    @Test
    @DisplayName("Удаление всех игр")
    void deleteAllGames() {
        Game game1 = new Game(null, "Player One", "Player Two", 6, 4, 2, 1, 3, 2);
        Game game2 = new Game(null, "Player Three", "Player Four", 7, 5, 1, 0, 4, 3);

        gameRepository.saveAll(List.of(game1, game2));
        gameRepository.deleteAll();

        List<Game> games = gameRepository.findAll();
        assertThat(games).isEmpty();
    }
}
