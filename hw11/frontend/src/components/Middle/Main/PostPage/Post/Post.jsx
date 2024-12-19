import React from "react";
import comment from "../../../../../assets/img/comments_16x16.png"
import {useNavigate} from "react-router-dom";

const Post = ({post, link}) => {
    const router = useNavigate();
    const formattedDate = new Date(post.creationTime).toLocaleString();
        //if (setPage) {
            return (
                <article key={post.id}>
                    { link ? (<a className="title" href="" onClick={(event) => {
                        event.preventDefault()
                        router(`posts/${post.id}`)
                    }}>{post.title}</a>) : (<div className="title">{post.title}</div>)}
                    <div className="information">On {formattedDate} by {post.user.login}</div>
                    <div className="body">{post.text}</div>
                    <div className="footer">
                        <div className="left">
                            <img src={comment} title="Comments" alt="Comments"/>
                            <span> {post.comments ? post.comments.length : 0}</span>
                        </div>
                    </div>
                </article>
            )
        // } else {
        //     return (
        //         <article key={post.id}>
        //         <div className="title">{post.title}</div>
        //             <div className="body">{post.text}</div>
        //             <div className="footer">
        //                 <div className="left">
        //                     <img src={comment} title="Comments" alt="Comments"/>
        //                     <span> {post.comments ? post.comments.length : 0}</span>
        //                 </div>
        //             </div>
        //         </article>
        //     )
        // }

}

export default Post;