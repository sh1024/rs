import games from '../api/games';
import history from '../history';

import {
    ADD_GAME, 
    FETCH_GAMES, 
    FETCH_GAME ,
    DELETE_GAME ,
    EDIT_GAME 
} from './gameTypes';

export const fetchGames = () => async dispatch => {
    const response = await games.get('/v1/games');
    dispatch({ type: FETCH_GAMES, payload: response.data});
}

export const addGame = (game) => async dispatch => {
    const response = await games.post('/v1/games/new', game);
    console.log(response);
    if (response.status === 200 || response.status === 201) {
        dispatch({ type: ADD_GAME, payload: response.data });
        history.push('/games');
    }
}

export const editGame = (id, game) => async dispatch => {
    const response = await games.patch(`/v1/games/edit/${id}`, game);
    if (response.status === 200) {
        dispatch({ type: EDIT_GAME, payload: response.data});
        history.push('/games');
    }
}

export const fetchGame = (id) => async dispatch => {
    const response = await games.get(`/v1/games/${id}`);
    dispatch({type: FETCH_GAME, payload: response.data});
}

export const deleteGame = (id) => async dispatch => {
    const response = await games.delete(`/v1/games/delete/${id}`);
    if (response.status === 200 || response.status === 204)  {
        dispatch({type: DELETE_GAME, payload: id});
        history.push('/games');
    }
}