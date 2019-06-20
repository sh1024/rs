import _ from 'lodash';

import {
    FETCH_GAMES,
    FETCH_GAME,
    CREATE_GAME,
    EDIT_GAME,
    DELETE_GAME
} from '../actions/gameTypes';

export default (state = {}, action) => {
    switch(action.type) {
        case FETCH_GAMES: 
            return { ...state, ..._.mapKeys(action.payload, 'id')};
        // case FETCH_GAME: 
        // case CREATE_GAME:
        // case EDIT_GAME:
        //         return {...state, [action.payload.id]: action.payload};
        // case DELETE_GAME:
        //         return _.omit(state, action.payload);
        default: 
            return state;
    }
}