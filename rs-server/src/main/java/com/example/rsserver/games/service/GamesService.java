package com.example.rsserver.games.service;

import com.example.rsserver.common.service.AbstractService;
import com.example.rsserver.games.entity.Game;
import com.example.rsserver.games.entity.GameSpecification;
import com.example.rsserver.games.repository.GamesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GamesService extends AbstractService <Game, GamesRepository> {

    public GamesService(GamesRepository repository) {
        super(repository);
    }

    public Page<Game> getAllPageable(String filterCriterion, Pageable pageable) {
        Specification<Game> spec = Specification.where(new GameSpecification(filterCriterion));
        return repository.findAll(spec, pageable);
    }
}
