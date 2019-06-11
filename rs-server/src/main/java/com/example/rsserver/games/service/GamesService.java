package com.example.rsserver.games.service;

import com.example.rsserver.common.service.AbstractService;
import com.example.rsserver.games.entity.Game;
import com.example.rsserver.games.repository.GamesRepository;

public class GamesService extends AbstractService<Game, GamesRepository> {

    public GamesService(GamesRepository repository) {
        super(repository);
    }
}
