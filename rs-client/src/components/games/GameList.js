import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { fetchGames } from '../../actions';

class GameList extends React.Component {

    componentDidMount() {
       this.props.fetchGames();
    }

    render () {
        return (
            <div>
                <h2>Games List</h2>
                <div className="ui celled list">
                    {this.renderList()}
                    {this.renderCreate()}
                </div>
            </div>
        );
    }

    renderEdit(game) {
        return (
            <div className="right floated content">
                <Link to={`/games/edit/${game.id}`} className="ui button primary">
                    EDIT
                </Link>
                <Link to={`/games/delete/${game.id}`} className="ui button negative">
                    DELETE
                </Link>
            </div>
        );
    }

    renderCreate() {
        return (
            <div style={{textAlign: 'right'}}>
                <Link to="/games/new" className="ui button primary">
                    Add game
                </Link>
            </div>
        );
    }

    renderList() {
        return this.props.games.map(game => 
            <div className="item" key={game.id}>
                {this.renderEdit(game)}
                <i className="larger middle aligned icon camera">
                    <div className="connect">
                        <Link to={`/games/${game.id}`}>
                            {game.name}
                        </Link>
                        <div className="description">{game.description}</div>
                    </div>
                </i>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        games: Object.values(state.games)
    }
}

export default connect (mapStateToProps, { fetchGames }) (GameList);