package com.example.rsserver.games.repository;

import com.example.rsserver.games.entity.Game;
import org.springframework.data.repository.CrudRepository;

public interface GamesRepository extends CrudRepository<Game, Long> {
}
