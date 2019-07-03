import React from 'react';
import { Field, reduxForm} from 'redux-form';

class GameForm extends React.Component {
    render() {
        return (
            <div> 
                <form className="ui form" onSubmit={this.props.handleSubmit(this.onSubmit)}>
                    <Field name="name" component={this.renderInput} label="Enter name" />
                    <Field name="description" component={this.renderInput} label="Enter description" />
                    <button className="ui button">Submit</button>
                </form>
            </div>
        );
    }

    renderInput = ({input, label, meta}) => {
        return (
            <div className="field">
                <label>{label}</label>
                <input {...input} />
                {/* meta */}
            </div>
        );
    }

    onSubmit = (formValues) => {
        this.props.onSubmit(formValues);
    }
}

export default reduxForm ({form: 'gameForm'})(GameForm);
