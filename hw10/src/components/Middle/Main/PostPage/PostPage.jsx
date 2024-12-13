import Post from "./Post/Post";
import React from "react";
import Comment from "./Comment/Comment";
import WriteComment from "./WriteComment/WriteComment";

const PostPage = ({posts, postId, createComment, user, setPage}) => {
    const post = posts.find((p) => p.id === postId);

    if (!post) {
        return <span>No such post</span>;
    }

    const sortedComments = (post.comments || []).sort((a, b) => b.id - a.id)

    return (
        <div>
            <Post post={post}/>
            <WriteComment createComment={createComment} postId={postId} user={user} setPage={setPage}/>
            {sortedComments.map((comment) => (
                <Comment comment={comment}/>
            ))}
        </div>
    )

}

export default PostPage