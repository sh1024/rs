import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import GameCreate from "./games/GameCreate";
import GameList from "./games/GameList";
import GameDelete from "./games/GameDelete";
import GameEdit from "./games/GameEdit";
import StartPage from './StartPage';

// import history from '../history';

class App extends React.Component {
    render () {
        return (
            <div className = "ui container">
                <BrowserRouter>
                    <Route path="/" exact component = {StartPage} />
                    <Route path="/games" exact component = {GameList}/>
                    <Route path="/games/new" exact component = {GameCreate}/>
                    <Route path="/games/edit/:id" exact component = {GameEdit}/>
                    <Route path="/games/delete/:id" exact component = {GameDelete}/>
                </BrowserRouter>
            </div>
        );
    }
}

export default App;