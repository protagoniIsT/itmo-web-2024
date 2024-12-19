import React, {useCallback, useRef, useState} from 'react';
import {useNavigate} from "react-router-dom";
import axios from "axios";

const WritePost = ({setPosts, login}) => {

    const titleInputRef = useRef(null)
    const textInputRef = useRef(null)
    const [error, setError] = useState('')

    const route = useNavigate();

    const userByLogin = useCallback((login) => {
        axios.get(`/api/users/${login}`)
        .then((response) => {
            console.log(response.data);
            return response.data;
        }).catch((error) => {
            console.error("Error fetching user ID:", error);
            setError(error.response.data);
        })
    }, []);

    const onWrite = useCallback( async () => {
        const title = titleInputRef.current.value.trim()
        const text = textInputRef.current.value.trim()
        const user = userByLogin(login)

        try {
            await axios.post(`/api/posts/${login}`, {
                title: title,
                text: text,
                user: user
            });
            const response = await axios.get("/api/posts");
            setPosts(response.data);
            route("/");
        } catch (error) {
            console.error("AXIOS WRITE POST ERROR: ", error);
            setError(error.response?.data?.message || "Unknown error");
        }
    }, [setPosts, login])

    return (
        <div className="form">
            <div className="header">Write Post</div>
            <div className="body">
                <form method="post" action="" onSubmit={event => {
                    event.preventDefault()
                    onWrite()
                }}>
                    <input type="hidden" name="action" value="writePost"/>
                    <div className="field">
                        <div className="name">
                            <label htmlFor="title">Title</label>
                        </div>
                        <div className="value">
                            <input
                                autoFocus
                                id="title"
                                name="title"
                                ref={titleInputRef}
                                onChange={() => setError(null)}
                            />
                        </div>
                    </div>
                    <div className="field">
                        <div className="name">
                            <label htmlFor="text">Text</label>
                        </div>
                        <div className="value">
                            <textarea
                                id="text"
                                name="text"
                                ref={textInputRef}
                                onChange={() => setError(null)}
                            />
                        </div>
                    </div>
                    <div className="button-field">
                        <input type="submit" value="Create"/>
                    </div>
                    {error
                        ? <div className={'error'}>{error}</div>
                        : null
                    }
                </form>
            </div>
        </div>
    );
};

export default WritePost;