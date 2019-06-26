import React from 'react';
import {connect} from 'react-redux';
import _ from 'lodash';
import GameForm from './GameForm';
import { fetchGame, editGame } from '../../actions';

class GameEdit extends React.Component {

    editGame = (game) => {
        this.props.editGame(this.props.match.params.id, game);
    }

    componentDidMount(){
        this.props.fetchGame(this.props.match.params.id);
    }

    render () {
        if (!this.props.game) {
            return <div>Loading...</div>
        }
        return (
            <GameForm onSubmit={this.editGame} 
                initialValues={_.pick(this.props.game, 'name', 'description')}/>
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        game: state.games[ownProps.match.params.id]
    }
}

export default connect(mapStateToProps, {fetchGame, editGame})(GameEdit);