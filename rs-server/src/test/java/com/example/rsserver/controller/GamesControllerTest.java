package com.example.rsserver.controller;

import static com.example.rsserver.utils.ApiConstants.DELETE_API;
import static com.example.rsserver.utils.ApiConstants.EDIT_API;
import static com.example.rsserver.utils.ApiConstants.GET_ALL_API;
import static com.example.rsserver.utils.ApiConstants.GET_API;
import static com.example.rsserver.utils.ApiConstants.NEW_API;
import static com.example.rsserver.utils.ApiConstants.GAMES_API;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.rsserver.games.controller.GamesController;
import com.example.rsserver.games.entity.Game;
import com.example.rsserver.games.service.GamesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(GamesController.class)
@WithUserDetails("user")
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

    @Test
    public void shouldCorrectlyWorkWithPageable() throws Exception {
        Game game1 = createGame("aa");
        Game game2 = createGame("vv");
        Game game3 = createGame("bb");
        Game game4 = createGame("cc");
        Game game5 = createGame("dd");

        List<Game> listOfGames = asList(game1, game2, game3, game4, game5);
        Page<Game> pages = new PageImpl<>(listOfGames);
        Pageable pageable = PageRequest.of(0, 20, Sort.unsorted());
        when(gamesService.getAllPageable("", pageable)).thenReturn(pages);

        mockMvc.perform(get(GAMES_API + GET_ALL_API).param("filter", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.content.length()", is(5)))
                .andExpect(jsonPath("$.content[0].name", is("aa")))
                .andExpect(jsonPath("$.content[1].name", is("vv")))
                .andExpect(jsonPath("$.content[2].name", is("bb")))
                .andExpect(jsonPath("$.content[3].name", is("cc")))
                .andExpect(jsonPath("$.content[4].name", is("dd")));


        pages = new PageImpl<>(singletonList(game2));
        when(gamesService.getAllPageable("vv", pageable)).thenReturn(pages);
        mockMvc.perform(get(GAMES_API + GET_ALL_API).param("filter", "vv"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.content.length()", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("vv")));
    }

    private Game createGame(String name) {
        Game game = new Game();
        game.setName(name);
        return game;
    }

}
