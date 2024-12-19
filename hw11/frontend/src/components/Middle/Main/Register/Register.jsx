import React, {useCallback, useRef, useState} from 'react';
import {useNavigate} from "react-router-dom";
import axios from "axios";

const Register = ({users, setLogin}) => {

    const loginInputRef = useRef(null)
    const passwordInputRef = useRef(null)
    const [error, setError] = useState('')
    const route = useNavigate()

    const onRegister = useCallback(() => {
        const login = loginInputRef.current.value.trim()
        const password = passwordInputRef.current.value.trim()

        axios.post("/api/users", {
            login: login,
            password: password
        }).then(()=>{
            return axios.post("/api/jwt", { login, password });
        }).then((response) => {
            const jwt = response.data;
            localStorage.setItem("jwt", jwt);
            return axios.get("/api/jwt", { params: { jwt } });
        }).then((response) => {
            setLogin(response.data.login);
            route("/");
        }).catch((error)=>{
            console.log("AXIOS REGISTER ERROR: " + error);
            setError(error.response?.data || "Unknown error");
        })
    }, [users, route, setError, setLogin])

    return (
        <div className="register form-box">
            <div className="header">Register</div>
            <div className="body">
                <form method="" action="" onSubmit={event => {
                    event.preventDefault()
                    onRegister()
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
                            <label htmlFor="name">Password</label>
                        </div>
                        <div className="value">
                            <input
                                name="password"
                                ref={passwordInputRef}
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