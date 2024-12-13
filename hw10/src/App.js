import './App.css';
import Enter from "./components/Middle/Main/Enter/Enter";
import Register from "./components/Middle/Main/Register/Register";
import WritePost from "./components/Middle/Main/WritePost/WritePost";
import Index from "./components/Middle/Main/Index/Index";
import React, {useCallback, useState, useEffect} from "react";
import Middle from "./components/Middle/Middle";
import Footer from "./components/Footer/Footer";
import Header from "./components/Header/Header";
import Users from "./components/Middle/Main/Users/Users";
import PostPage from "./components/Middle/Main/PostPage/PostPage";
import NotFoundPage from "./components/Middle/Main/NotFoundPage/NotFoundPage";

function App({users, postsData}) {
    const [user, setUser] = useState(() => {
        const savedUser = localStorage.getItem('user');
        return savedUser ? JSON.parse(savedUser) : null;
    });

    const [page, setPage] = useState(() => {
        const savedPage = localStorage.getItem('page');
        return savedPage || 'index';
    });

    const [posts, setPosts] = useState(() => {
        const savedPosts = localStorage.getItem('posts');
        return savedPosts ? JSON.parse(savedPosts) : postsData;
    });

    const [usersState, setUsers] = useState(() => {
        const savedUsers = localStorage.getItem('usersState');
        return savedUsers ? JSON.parse(savedUsers) : users;
    });

    useEffect(() => {
        localStorage.setItem('user', JSON.stringify(user));
    }, [user]);

    useEffect(() => {
        localStorage.setItem('page', page);
    }, [page]);

    useEffect(() => {
        localStorage.setItem('posts', JSON.stringify(posts));
    }, [posts]);

    useEffect(() => {
        localStorage.setItem('usersState', JSON.stringify(usersState));
    }, [usersState]);


    const createPost = useCallback((post) => {
        const maxId = Math.max(...posts.map((post) => post.id)) + 1
        setPosts([...posts, {...post, id: maxId}])
    }, [posts])

    const registerUser = useCallback((newUser) => {
        const maxId = Math.max(...usersState.map((user) => user.id)) + 1
        setUsers([...usersState, {...newUser, id: maxId}])
    }, [usersState])

    const createComment = useCallback((postId, comment) => {
        setPosts((prevPosts) =>
            prevPosts.map((post) =>
                post.id === postId ? {...post,
                                        comments: [
                                            ...(post.comments || []),
                                            {
                                                id: Math.max(...(post.comments || []).map((c) => c.id), 0) + 1,
                                                ...comment,
                                            },
                                        ],
                                     } : post
            )
        );
    }, [posts, user]);


    const getPage = useCallback((page) => {
        switch (true) {
            case page === 'index':
                return (<Index posts={posts} setPage={setPage}/>)
            case page === 'enter':
                return (<Enter users={usersState} setUser={setUser} setPage={setPage}/>)
            case page === 'register':
                return (<Register setPage={setPage} registerUser={registerUser} users={usersState}/>)
            case page === 'writePost':
                return (<WritePost createPost={createPost} setPage={setPage}/>)
            case page === 'users':
                return (<Users usersState={usersState}/>)
            case /^post\/\d+$/.test(page):
                const postId = parseInt(page.match(/^post\/(\d+)$/)[1]);
                return (<PostPage posts={posts}
                                  postId={postId}
                                  createComment={createComment}
                                  user={user}
                                  setPage={setPage}/>)
            default:
                return (<NotFoundPage/>)

        }
    }, [usersState, posts, createPost, createComment])

    return (
        <div className="App">
            <Header user={user} setUser={setUser} setPage={setPage}/>
            <Middle
                posts={posts}
                page={getPage(page)}
                setPage={setPage}
            />
            <Footer usersCnt={usersState.length} postsCnt={posts.length}/>
        </div>
    );
}

export default App;
