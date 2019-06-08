package com.example.rsserver.games.controller;

import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_DELETE;
import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_EDIT;
import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_GET_ALL;
import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_GET_SINGLE;
import static com.example.rsserver.games.utils.GamesApiConstants.GAMES_NEW;

import com.example.rsserver.games.entity.Game;
import com.example.rsserver.games.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class GamesController {

    private GamesRepository gamesRepository;

    @GetMapping(value = GAMES_GET_SINGLE)
    public Game getGame(@PathVariable long id) {
        return gamesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    }

    @GetMapping(value = GAMES_GET_ALL)
    public Iterable<Game> getAllGames() {
        return gamesRepository.findAll();
    }

    @PostMapping(value = GAMES_NEW)
    public @ResponseBody Game newGame(@RequestBody Game game)  {
        return gamesRepository.save(game);
    }

    @PutMapping(value = GAMES_EDIT)
    public @ResponseBody Game editGame(@RequestBody Game game) {
        return gamesRepository.save(game);
    }

    @PatchMapping(value = GAMES_EDIT)
    public @ResponseBody Game editGamePart(@RequestBody Game game) {
        return gamesRepository.save(game);
    }

    @DeleteMapping(value = GAMES_DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable long id) {
        gamesRepository.deleteById(id);
    }


    @Autowired
    public void setGamesRepository(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }
}
