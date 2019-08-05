import { combineReducers } from 'redux';
import { reducer as formReducer } from 'redux-form';

import gameReducer from './gameReducer';
import authReducer from  './authReducer';

export default combineReducers (
    { form: formReducer,
      games: gameReducer,
      auth: authReducer }
);