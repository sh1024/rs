import React from 'react';
import { Router, Route, Switch } from 'react-router-dom';
import { connect } from 'react-redux';

import GameAdd from "./games/GameAdd";
import GameList from "./games/GameList";
import GameDelete from "./games/GameDelete";
import GameEdit from "./games/GameEdit";

import StartPage from './StartPage';
import AuthForm from './auth/AuthForm';

import history from '../history';

class App extends React.Component {
    render() {
        return (
            <div className="ui container">
                <Router history={history}>
                    <Switch>
                        <Route path="/" exact component={this.checkAuthComponent(StartPage)} />
                        <Route path="/games" exact component={this.checkAuthComponent(GameList)} />
                        <Route path="/games/new" exact component={this.checkAuthComponent (GameAdd)} />
                        <Route path="/games/edit/:id" exact component={this.checkAuthComponent (GameEdit)} />
                        <Route path="/games/delete/:id" exact component={this.checkAuthComponent(GameDelete)} />

                        <Route path="/login" exact component={AuthForm} />
                    </Switch>
                </Router>
            </div>
        );
    }

    checkAuthComponent = (component) => {
        return this.props.isSignedIn ? component : AuthForm;
    }
}

const mapStateToProps = (state) => {
    return { isSignedIn: state.auth.isSignedIn };
}

export default connect(mapStateToProps, null)(App);