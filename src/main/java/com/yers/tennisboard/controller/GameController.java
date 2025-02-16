package com.yers.tennisboard.controller;

import com.yers.tennisboard.dto.GameDto;
import com.yers.tennisboard.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<GameDto> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable int id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<GameDto> addGame(@RequestBody GameDto gameDto) {
        gameService.addGame(gameDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameDto);
    }
}
