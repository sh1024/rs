package com.example.rsserver.controller;

import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_DELETE;
import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_EDIT;
import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_GET_ALL;
import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_GET_SINGLE;
import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_NEW;
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
import com.example.rsserver.games.repository.GamesRepository;
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
    private GamesRepository gamesRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldAcceptNewGameSuccessfully() throws Exception {
        Game game = new Game();
        game.setName("aaa");

        Game gameSaved = new Game();
        gameSaved.setName("aaa");
        gameSaved.setId(1L);

        when(gamesRepository.save(game)).thenReturn(gameSaved);

        mockMvc.perform(post(GAMES_NEW)
                .content(objectMapper.writeValueAsString(game))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(gameSaved)));
    }

    @Test
    public void shouldReplaceExistingGameSuccessfully() throws Exception {
        Game game = new Game();
        game.setId(1L);
        game.setName("aaa");

        when(gamesRepository.save(game)).thenReturn(game);

        mockMvc.perform(put(GAMES_EDIT, 1L)
                .content(objectMapper.writeValueAsString(game))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    public void shouldReplaceExistingGamePartSuccessfully() throws Exception {
        Game game = new Game();
        game.setId(1L);
        game.setDescription("Description");

        Game gameToReturn = new Game();
        gameToReturn.setId(1L);
        gameToReturn.setName("aaa");
        gameToReturn.setDescription("Description");

        when(gamesRepository.save(game)).thenReturn(gameToReturn);

        mockMvc.perform(patch(GAMES_EDIT, 1L)
                .content(objectMapper.writeValueAsString(game))
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(gameToReturn)));
    }

    @Test
    public void shouldDeleteExistingGameSuccessfully () throws Exception {
        mockMvc.perform(delete(GAMES_DELETE, 1L))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldReturnSingleGame() throws Exception {
        Game game = new Game();
        game.setId(1L);
        game.setDescription("Description");

        when(gamesRepository.findById(1L)).thenReturn(Optional.of(game));

        mockMvc.perform(get(GAMES_GET_SINGLE, 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    public void shouldReturnAllGamesSuccessfully() throws Exception {
        Game game1 = new Game();
        game1.setId(1L);
        game1.setDescription("Desc1");

        Game game2 = new Game();
        game2.setId(1L);
        game2.setDescription("Desc2");

        List<Game> listOfGames = asList(game1,game2);

        when(gamesRepository.findAll()).thenReturn(listOfGames);

        mockMvc.perform(get(GAMES_GET_ALL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listOfGames)));
    }
}
