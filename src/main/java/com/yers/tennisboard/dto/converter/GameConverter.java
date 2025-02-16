package com.yers.tennisboard.dto.converter;

import com.yers.tennisboard.dto.GameDto;
import com.yers.tennisboard.entity.Game;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameConverter {
    private final ModelMapper modelMapper;
    public GameDto toDto(Game game) {
        return modelMapper.map(game, GameDto.class);
    }
    public Game toEntity(GameDto gameDto) {
        return modelMapper.map(gameDto, Game.class);
    }
}
