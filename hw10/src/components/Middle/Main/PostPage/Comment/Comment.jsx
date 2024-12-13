import React from "react";

const Comment = ({comment}) => {
    return (
        <comment>
            <div className="header">{comment.author}</div>
            <div className="body">{comment.text}</div>
        </comment>
    );
}

export default Comment