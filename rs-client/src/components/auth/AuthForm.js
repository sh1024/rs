import React from 'react'
import { Field, reduxForm } from 'redux-form';
import { connect } from 'react-redux';
import { login } from '../../actions';

class AuthForm extends React.Component {
    render() {
        return (
            <div>
                <form className="ui form" onSubmit={this.props.handleSubmit(this.signIn)}>
                    <Field name="username" component={this.renderInput} type="text" label="Enter username" />
                    <Field name="password" component={this.renderInput} type="password" label="Enter password" />
                    <button className="ui button">Submit</button>
                </form>
            </div>
        );
    }

    renderInput = ({label, input, type}) => {
        return (
            <div className="field">
                <label>{label}</label>
                <input {...input} type={type} />
            </div>
        );
    }

    signIn = (formValues) => {
        this.props.login(formValues);
    }
}

const authForm = connect (null, { login }) (AuthForm);

export default reduxForm ({form: 'authForm'})(authForm);
