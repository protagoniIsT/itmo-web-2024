import React, {useRef, useState} from 'react';

const LOGIN_MIN_LENGTH = 3
const LOGIN_MAX_LEN = 16

const NAME_MIN_LENGTH = 1
const NAME_MAX_LENGTH = 32

const Register = ({setPage, registerUser, users}) => {

    const loginInputRef = useRef(null)
    const nameInputRef = useRef(null)
    const [error, setError] = useState('')

    const setErrorMessage = (message) => {
        setError('');
        setTimeout(() => setError(message), 0);
    };

    const validateLogin = (login) => {
        const trimmedLogin = login.trim();
        if (trimmedLogin.length < LOGIN_MIN_LENGTH || trimmedLogin.length > LOGIN_MAX_LEN) {
            return 'Login length should be between 3 and 16'
        }
        if (!/^[a-zA-Z]+$/.test(trimmedLogin)) {
            return 'Login should contain only latin letters'
        }
        if (users.some(user => user.login === trimmedLogin)) {
            return 'This login is already in use';
        }
        return null;
    }

    const validateName = (name) => {
        const trimmedName = name.trim();
        if (trimmedName.length < NAME_MIN_LENGTH || trimmedName.length > NAME_MAX_LENGTH) {
            return 'Name length should be between 1 and 32'
        }
        return null;
    }

    const handleSubmit = () => {
        const login = loginInputRef.current.value
        const name = nameInputRef.current.value

        const loginFieldError = validateLogin(login)
        const nameFieldError = validateName(name)

        if (loginFieldError || nameFieldError) {
            setErrorMessage(loginFieldError || nameFieldError);
            return
        }

        setError('');
        registerUser({
            login: login,
            name: name
        })
        setPage('enter')
    }

    return (
        <div className="register form-box">
            <div className="header">Register</div>
            <div className="body">
                <form method="" action="" onSubmit={event => {
                    event.preventDefault()
                    handleSubmit()
                }}>
                    <input type="hidden" name="action" value="register"/>
                    <div className="field">
                        <div className="name">
                            <label htmlFor="login">Login</label>
                        </div>
                        <div className="value">
                            <input
                                autoFocus
                                name="login"
                                ref={loginInputRef}
                                onChange={() => setError(null)}
                            />
                        </div>
                    </div>
                    <div className="field">
                        <div className="name">
                            <label htmlFor="name">Name</label>
                        </div>
                        <div className="value">
                            <input
                                name="name"
                                ref={nameInputRef}
                                onChange={() => setError(null)}
                            />
                        </div>
                    </div>
                    {error
                        ? <div className={'error'}>{error}</div>
                        : null
                    }
                    <div className="button-field">
                        <input type="submit" value="Register"/>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Register;