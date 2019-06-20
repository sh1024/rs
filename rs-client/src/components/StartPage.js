import React from 'react';
import { Link } from 'react-router-dom';

class StartPage extends React.Component {
    render() {
        return (
            <div>
                <div>This is a start page!</div>
                <Link to="/games">
                    Open games list
                </Link>
            </div>
        );
    }
}

export default StartPage;