package com.example.rsserver.games.utils;

public class GamesApiConstants {
    private GamesApiConstants(){};

    public static final String GAMES_GET_SINGLE =  "/v1/games/{id}";
    public static final String GAMES_GET_ALL =  "/v1/games";
    public static final String GAMES_EDIT =  "/v1/games/edit/{id}";
    public static final String GAMES_NEW =  "/v1/games/new";
    public static final String GAMES_DELETE =  "/v1/games/delete/{id}";

}
