import React from 'react';
import { connect } from 'react-redux';
import GameForm from './GameForm';
import { addGame } from '../../actions';


class GameAdd extends React.Component {
    render () {
        return (
            <div>
                <GameForm onSubmit={this.addGame}/>
            </div>
        );
    }

    addGame = (formValues) => {
        this.props.addGame(formValues);
    }
}

export default connect(null, {addGame}) (GameAdd)
