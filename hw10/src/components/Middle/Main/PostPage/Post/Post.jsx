import React from "react";
import comment from "../../../../../assets/img/comments_16x16.png"

const Post = ({post, setPage}) => {
        if (setPage) {
            return (
                <article key={post.id}>
                    <a className="title" href="" onClick={(event) => {
                        event.preventDefault()
                        setPage(`post/${post.id}`)
                    }}>{post.title}</a>
                    <div className="body">{post.text}</div>
                    <div className="footer">
                        <div className="left">
                            <img src={comment} title="Comments" alt="Comments"/>
                            <span> {post.comments ? post.comments.length : 0}</span>
                        </div>
                    </div>
                </article>
            )
        } else {
            return (
                <article key={post.id}>
                <div className="title">{post.title}</div>
                    <div className="body">{post.text}</div>
                    <div className="footer">
                        <div className="left">
                            <img src={comment} title="Comments" alt="Comments"/>
                            <span> {post.comments ? post.comments.length : 0}</span>
                        </div>
                    </div>
                </article>
            )
        }

}

export default Post;