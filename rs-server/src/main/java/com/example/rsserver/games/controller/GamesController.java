package com.example.rsserver.games.controller;

import static com.example.rsserver.utils.ApiConstants.GAMES_API;
import static com.example.rsserver.utils.ApiConstants.GET_ALL_API;

import com.example.rsserver.common.controller.AbstractController;
import com.example.rsserver.games.entity.Game;
import com.example.rsserver.games.service.GamesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(GAMES_API)
public class GamesController extends AbstractController<Game, GamesService> {

    public GamesController(GamesService service) {
        super(service);
    }

    @GetMapping(GET_ALL_API)
    public Page<Game> getAllPageable(@RequestParam(value = "filter", required = false) String filter,
                                     Pageable pageable) {
        return service.getAllPageable(filter, pageable);
    }
}
