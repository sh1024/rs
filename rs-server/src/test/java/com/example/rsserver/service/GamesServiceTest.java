package com.example.rsserver.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import com.example.rsserver.AbstractDatabaseTest;
import com.example.rsserver.games.entity.Game;
import com.example.rsserver.games.repository.GamesRepository;
import com.example.rsserver.games.service.GamesService;
import com.example.rsserver.utils.DomainObjectMerger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDateTime;

@Import({GamesService.class, DomainObjectMerger.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:/test-sql/CRUD.sql")
public class GamesServiceTest extends AbstractDatabaseTest {

    @Autowired
    private GamesService gamesService;

    @Autowired
    private GamesRepository gamesRepository;

    @Test
    public void shouldReturnGameInserted() {
        Game game = gamesService.getSingle(1L).orElse(null);
        assertThat(game, notNullValue());
        assertThat(game.getId(), equalTo(1L));
        assertThat(game.getName(), equalTo("tetris"));
        assertThat(game.getDescription(), equalTo("old school game"));
        assertThat(game.getCreatedAt(), notNullValue());
        assertThat(game.getVersion(), equalTo(1));
    }

    @Test
    public void shouldInsertGameCorrectly() {
        Game game = new Game();
        game.setName("Counter strike");
        game.setDescription("Shooter");

        Game saved = gamesService.create(game);
        assertThat(saved.getId(), equalTo(2L));
        assertThat(saved.getName(), equalTo(game.getName()));
        assertThat(saved.getDescription(), equalTo(game.getDescription()));
        assertThat(saved.getVersion(), equalTo(0));
        assertThat(saved.getCreatedAt(), notNullValue());
        assertThat(saved.getUpdatedAt(), notNullValue());
    }

    @Test
    public void shouldReplaceGameCorrectly() {
        Game game = gamesService.getSingle(1L).orElse(null);
        assertThat(game, notNullValue());
        game.setName("newName");
        game.setDescription(null);
        LocalDateTime timeToCheck = LocalDateTime.now();

        Game saved = gamesService.edit(1L, game);
        // triggers update version and date
        gamesRepository.flush();

        assertThat(saved.getName(), equalTo("newName"));
        assertThat(saved.getDescription(), nullValue());
        assertThat(saved.getVersion(), equalTo(2));
        assertThat(saved.getUpdatedAt(), greaterThan(timeToCheck));
    }

    @Test
    public void shouldReplaceGameWithoutNullCorrectly() {
        Game game = new Game();
        game.setDescription("New description");

        Game result = gamesService.partialEdit(1L, game);
        assertThat(result.getName(), notNullValue());
    }
}
