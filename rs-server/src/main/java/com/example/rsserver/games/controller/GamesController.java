package com.example.rsserver.games.controller;

import com.example.rsserver.common.controller.AbstractController;
import com.example.rsserver.games.entity.Game;
import com.example.rsserver.games.service.GamesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/games")
public class GamesController extends AbstractController<Game, GamesService> {

    public GamesController(GamesService service) {
        super(service);
    }
}
