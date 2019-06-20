import games from '../api/games';

import {
    CREATE_GAME, 
    FETCH_GAMES, 
    FETCH_GAME ,
    DELETE_GAME ,
    EDIT_GAME 
} from './gameTypes';

export const fetchGames = () => async dispatch => {
    const response = await games.get('/v1/games');
    dispatch({ type: FETCH_GAMES, payload: response.data});
}