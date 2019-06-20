import React from 'react';
import { connect } from 'react-redux';
import { fetchGames } from '../../actions';

class GameList extends React.Component {

    componentDidMount() {
       this.props.fetchGames();
    }

    render () {
        console.log(this.props.games);
        return (
            <div> Here is a list</div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        games: Object.values(state.games)
    }
}

export default connect (mapStateToProps, { fetchGames }) (GameList);