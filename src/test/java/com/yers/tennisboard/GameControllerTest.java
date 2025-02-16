package com.yers.tennisboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yers.tennisboard.controller.GameController;
import com.yers.tennisboard.dto.GameDto;
import com.yers.tennisboard.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private GameService gameService;

    @Autowired
    private ObjectMapper objectMapper; // Для сериализации JSON

    @Test
    void testGetAllGames() throws Exception {
        GameDto game = new GameDto("Player A", "Player B", 15, 30, 1, 0, 2, 3);
        List<GameDto> games = Collections.singletonList(game);

        when(gameService.getAllGames()).thenReturn(games);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].player1Name").value("Player A"))
                .andExpect(jsonPath("$[0].player2Name").value("Player B"))
                .andExpect(jsonPath("$[0].player1Score").value(15))
                .andExpect(jsonPath("$[0].player2Score").value(30))
                .andExpect(jsonPath("$[0].player1Sets").value(1))
                .andExpect(jsonPath("$[0].player2Sets").value(0))
                .andExpect(jsonPath("$[0].player1Games").value(2))
                .andExpect(jsonPath("$[0].player2Games").value(3));
    }

    @Test
    void testGetGameById() throws Exception {
        GameDto game = new GameDto("Player X", "Player Y", 40, 40, 2, 2, 5, 5);

        when(gameService.getGameById(1)).thenReturn(game);

        mockMvc.perform(get("/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.player1Name").value("Player X"))
                .andExpect(jsonPath("$.player2Name").value("Player Y"))
                .andExpect(jsonPath("$.player1Score").value(40))
                .andExpect(jsonPath("$.player2Score").value(40))
                .andExpect(jsonPath("$.player1Sets").value(2))
                .andExpect(jsonPath("$.player2Sets").value(2))
                .andExpect(jsonPath("$.player1Games").value(5))
                .andExpect(jsonPath("$.player2Games").value(5));
    }
    @Test
    void testAddGame() throws Exception {
        GameDto game = new GameDto("Player M", "Player N", 0, 0, 0, 0, 0, 0);

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(game)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.player1Name").value("Player M"))
                .andExpect(jsonPath("$.player2Name").value("Player N"))
                .andExpect(jsonPath("$.player1Score").value(0))
                .andExpect(jsonPath("$.player2Score").value(0))
                .andExpect(jsonPath("$.player1Sets").value(0))
                .andExpect(jsonPath("$.player2Sets").value(0))
                .andExpect(jsonPath("$.player1Games").value(0))
                .andExpect(jsonPath("$.player2Games").value(0));
    }
}
