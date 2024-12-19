import React from 'react';
import {useNavigate} from "react-router-dom";

const Section = ({post}) => {

    const route = useNavigate()

    return (
        <section>
            <div className="header">
                {post.title}
            </div>
            <div className="body">
                {post.text.length > 350 ? post.text.slice(0, 350) + "..." : post.text}
            </div>
            <div className="footer">
                <a href="" onClick={(event) => {
                    event.preventDefault()
                    route(`/posts/${post.id}`)
                }}>View all</a>
            </div>
        </section>
    );
};

export default Section;