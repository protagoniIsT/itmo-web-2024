import React, {useRef, useState} from "react";

const WriteComment = ({createComment, postId, user, setPage}) => {

    const textInputRef = useRef(null)
    const [error, setError] = useState('')

    const handleSubmit = (event) => {
        event.preventDefault()
        const text = textInputRef.current.value.trim()

        if (text.length === 0) {
            setError('Text can not be empty')
            return
        }

        createComment(postId, {
            author: user.login,
            text: text
        })
        textInputRef.current.value = ""
    }
    if (!user) {
        return (
            <div style={{marginTop: "1rem"}}>
                <a href="" onClick={(event) => {
                    event.preventDefault()
                    setPage(`enter`)
                }}>Sign in</a> or <a href="" onClick={(event) => {
                                    event.preventDefault()
                                    setPage(`register`)
                                    }}>register</a> to comment posts
            </div>
        )
    }
    return (
        <div className="form" style={{marginTop: "1rem"}}>
            <div className="body">
            <form method="post" action="" onSubmit={handleSubmit}>
                    <div className="field" style={{marginBottom: "0rem"}}>
                        <div className="name">
                            <label htmlFor="text">Write Comment</label>
                        </div>
                        <div className="value">
                            <textarea
                                id="text"
                                name="text"
                                ref={textInputRef}
                                onChange={() => setError(null)}
                                style={{minHeight: "3rem"}}></textarea>
                        </div>
                    </div>
                    <div className="button-field" style={{marginBottom: "1rem", marginTop: "0.3rem"}}>
                        <input type="submit" value="Send"/>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default WriteComment