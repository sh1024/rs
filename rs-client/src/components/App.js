import React from 'react';
import { Router, Route, Switch } from 'react-router-dom';

import GameAdd from "./games/GameAdd";
import GameList from "./games/GameList";
import GameDelete from "./games/GameDelete";
import GameEdit from "./games/GameEdit";
import StartPage from './StartPage';

import history from '../history';

class App extends React.Component {
    render () {
        return (
            <div className = "ui container">
                <Router history={history}>
                    <Switch>
                        <Route path="/" exact component = {StartPage} />
                        <Route path="/games" exact component = {GameList}/>
                        <Route path="/games/new" exact component = {GameAdd}/>
                        <Route path="/games/edit/:id" exact component = {GameEdit}/>
                        <Route path="/games/delete/:id" exact component = {GameDelete}/>
                    </Switch>
                </Router>
            </div>
        );
    }
}

export default App;