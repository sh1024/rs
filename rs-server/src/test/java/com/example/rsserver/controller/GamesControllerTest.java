package com.example.rsserver.controller;

import static com.example.rsserver.utils.ApiConstants.DELETE_API;
import static com.example.rsserver.utils.ApiConstants.EDIT_API;
import static com.example.rsserver.utils.ApiConstants.GET_API;
import static com.example.rsserver.utils.ApiConstants.NEW_API;
import static com.example.rsserver.utils.ApiConstants.GAMES_API;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.rsserver.games.controller.GamesController;
import com.example.rsserver.games.entity.Game;
import com.example.rsserver.games.service.GamesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(GamesController.class)
public class GamesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GamesService gamesService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldPostNewGameSuccessfully() throws Exception {
        Game game = createGame("name");

        when(gamesService.create(game)).thenReturn(game);

        mockMvc.perform(post(GAMES_API + NEW_API)
                .content(objectMapper.writeValueAsString(game))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    public void shouldPutGameSuccessfully() throws Exception {
        Game game = createGame("name");

        when(gamesService.edit(1L, game)).thenReturn(game);

        mockMvc.perform(put(GAMES_API + EDIT_API, 1L)
                .content(objectMapper.writeValueAsString(game))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    public void shouldPatchGameSuccessfully() throws Exception {
        Game game = createGame("name");

        when(gamesService.partialEdit(1L, game)).thenReturn(game);

        mockMvc.perform(patch(GAMES_API + EDIT_API, 1L)
                .content(objectMapper.writeValueAsString(game))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    public void shouldDeleteExistingGameSuccessfully () throws Exception {
        mockMvc.perform(delete(GAMES_API + DELETE_API, 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldReturnSingleGame() throws Exception {
        Game game = createGame("name");

        when(gamesService.getSingle(1L)).thenReturn(Optional.of(game));

        mockMvc.perform(get(GAMES_API + GET_API, 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    public void shouldReturnAllGamesSuccessfully() throws Exception {
        Game game1 = createGame("name1");
        Game game2 = createGame("name2");

        List<Game> listOfGames = asList(game1,game2);

        when(gamesService.getAll()).thenReturn(listOfGames);

        mockMvc.perform(get(GAMES_API))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listOfGames)));
    }

    private Game createGame(String name) {
        Game game = new Game();
        game.setName(name);
        return game;
    }

}
