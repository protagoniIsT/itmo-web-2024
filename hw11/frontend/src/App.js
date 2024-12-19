import './App.css';
import Enter from "./components/Middle/Main/Enter/Enter";
import Index from "./components/Middle/Main/Index/Index";
import React, {useEffect, useState} from "react";
import {BrowserRouter, Navigate, Route, Routes, useParams} from "react-router-dom";
import Application from "./Application";
import axios from "axios";
import Users from "./components/Middle/Main/Users/Users";
import NotFoundPage from "./components/Middle/Main/NotFoundPage/NotFoundPage";
import RequireAuth from "./components/RequireAuth/RequireAuth";
import Register from "./components/Middle/Main/Register/Register";
import WritePost from "./components/Middle/Main/WritePost/WritePost";
import PostPage from "./components/Middle/Main/PostPage/PostPage";

function App() {

    const [login, setLogin] = useState(null);
    const [users, setUsers] = useState(null);
    const [posts, setPosts] = useState([]);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        const jwt = localStorage.getItem("jwt");

        if (jwt) {
            axios.get("/api/jwt", {
                params: {
                    jwt
                }
            }).then((response) => {
                localStorage.setItem("login", response.data.login);
                setLogin(response.data.login);
            }).catch((error) => {
                console.log(error);
                setLogin(null);
            }).finally(() => {
                setLoading(false);
            });
        } else {
            setLogin(null);
            setLoading(false);
        }
    }, []);

    const fetchUsers = async () => {
        axios.get("/api/users").then((response)=>{
            //console.log("Users fetched successfully:", response.data);
            setUsers(response.data)
        }).catch((error)=>{
            //console.error("Error fetching users:", error.response?.data || error.message);
            console.log(error)
        })
    };

    useEffect(() => {
        fetchUsers();
    }, [users]);


    const fetchPosts = async () => {
        axios.get("/api/posts").then((response)=>{
            //console.log("Posts fetched successfully:", response.data);
            setPosts(response.data)
        }).catch((error)=> {
            //console.error("Error fetching posts:", error.response?.data || error.message);
            console.log(error)
        })
    };

    useEffect(() => {
        fetchPosts();
    }, [posts]);

    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route
                        index={true}
                        element={<Application login={login} setLogin={setLogin} posts={posts} page={<Index posts={posts}/>}/>}
                    />

                    <Route
                        exact path={'/enter'}
                        element={<Application login={login} posts={posts} page={<Enter setLogin={setLogin}/>}/>}
                    />

                    <Route
                        exact path={'/users'}
                        element={
                            <RequireAuth login={login} isLoading={isLoading}>
                                <Application login={login} setLogin={setLogin} posts={posts} page={<Users users={users}/>} />
                            </RequireAuth>
                        }
                    />

                    <Route
                        exact path={'/register'}
                        element={<Application login={login}
                                              setLogin={setLogin}
                                              posts={posts}
                                              page={<Register users={users} setLogin={setLogin}/>}/>}
                    />

                    <Route
                        exact path={'/writePost'}
                        element={
                            <RequireAuth login={login} isLoading={isLoading}>
                                <Application login={login}
                                             setLogin={setLogin}
                                             posts={posts}
                                             page={<WritePost users={users} setPosts={setPosts} login={login}/>}/>
                            </RequireAuth>
                        }
                    />

                    <Route
                        path={'/posts/:id'}
                        element={<Application login={login} setLogin={setLogin} posts={posts} page={<PostPage/>}/>}
                    />

                    <Route
                        path="*"
                        element={<Application posts={posts} page={<NotFoundPage/>}/>}
                    />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;
