package com.yers.tennisboard.service;

import com.yers.tennisboard.dto.GameDto;
import com.yers.tennisboard.dto.converter.GameConverter;
import com.yers.tennisboard.entity.Game;
import com.yers.tennisboard.exception.GameNotFoundException;
import com.yers.tennisboard.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class GameService {

    private final GameConverter gameConverter;
    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameDto> getAllGames() {
        List<Game> games = gameRepository.findAll();
        List<GameDto> gameDtos = new ArrayList<>();
        for (Game game : games) {
            gameDtos.add(gameConverter.toDto(game));
        }
        return gameDtos;
    }


    @Transactional(readOnly = true)
    public GameDto getGameById(int id) {
        if (!gameRepository.existsById(id)) {
            throw new GameNotFoundException("This game not found");
        }
        return gameConverter.toDto(Objects.requireNonNull(gameRepository.findById(id).orElse(null)));
    }

    @Transactional
    public void addGame(GameDto gameDto) {
        gameRepository.save(gameConverter.toEntity(gameDto));
    }

}
