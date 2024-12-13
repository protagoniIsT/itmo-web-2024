import React from 'react';

const Section = ({post, setPage}) => {
    return (
        <section>
            <div className="header">
                {post.title}
            </div>
            <div className="body">
                {post.text.length > 350 ? post.text.slice(0, 350) + "..." : post.text}
            </div>
            <div className="footer">
                <a href="" onClick={(event)=>{
                    event.preventDefault()
                    setPage(`post/${post.id}`)
                }}>View all</a>
            </div>
        </section>
    );
};

export default Section;