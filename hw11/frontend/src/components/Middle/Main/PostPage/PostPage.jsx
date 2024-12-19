import Post from "./Post/Post";
import React, {useCallback, useEffect, useState} from "react";
import Comment from "./Comment/Comment";
import WriteComment from "./WriteComment/WriteComment";
import axios from "axios";
import {useParams} from "react-router-dom";
import Loading from "../Loading/Loading";

const PostPage = ({}) => {

    const [post, setPost] = useState(null);
    const [loading, setLoading] = useState(true)

    const {id: postId} = useParams();

    useEffect(() => {
        const getPostById = () => {
            axios.get(`/api/posts/${postId}`)
                .then((response)=> {
                    console.log("Post fetched successfully:", response.data);
                    setPost(response.data);
                }).catch((error)=>{
                    //console.error("Error fetching posts:", error.response?.data || error.message);
                    console.log(error)
                }).finally(()=> {
                    setLoading(false)
                })
        };

        getPostById();

    }, [postId]);

    if (loading) {
        return <Loading/>
    }

    if (!post) {
        return <span>No such post</span>;
    }
    //const sortedComments = (post.comments || []).sort((a, b) => b.id - a.id)

    return (
        <div>
            <Post post={post} link={false}/>
            {/*<WriteComment createComment={createComment} postId={postId} user={user}/>*/}
            {/*{sortedComments.map((comment) => (*/}
            {/*    <Comment comment={comment}/>*/}
            {/*))}*/}
        </div>
    )

}

export default PostPage