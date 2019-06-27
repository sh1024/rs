import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { fetchGame, deleteGame } from '../../actions';
import Modal from '../Modal';
import history from '../../history';

class GameDelete extends React.Component {

    componentDidMount() {
        this.props.fetchGame(this.props.match.params.id);
    }

    renderContent() {
        if (!this.props.game) {
            return 'Are you sure?'
        }
        return `Are you sure want to delete ${this.props.game.name}`
    }

    renderActions() {
        const id = this.props.match.params.id;
        return (
            <React.Fragment>
                <button className="ui button negative" onClick={() => this.props.deleteGame(id)}>
                    DELETE
                </button>
                <Link to="/games" className="ui button">Cancel</Link>
            </React.Fragment>
        );
    }

    render() {
        return (
            <Modal title="Delete Game"
                content={this.renderContent()}
                actions={this.renderActions()}
                onDismiss={() => history.push('/games')}
            />
        );
    }
}

const mapStateToProps = (state, ownProps) => {
    return {
        game: state.games[ownProps.match.params.id]
    }
}

export default connect(mapStateToProps, { fetchGame, deleteGame })(GameDelete);