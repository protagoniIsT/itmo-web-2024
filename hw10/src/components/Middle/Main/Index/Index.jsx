import React, {useMemo} from 'react';
import Section from "../../Aside/Section/Section";
import Post from "../PostPage/Post/Post";

const Index = ({posts, setPage}) => {
    const sortedPosts = useMemo(() => {
        if (!posts)
            return []
        return posts.sort((a, b) => b.id - a.id)
    }, [posts])
    return (
        <div>
            {sortedPosts.map((post) =>
                <Post post={post} setPage={setPage}/>
            )}
        </div>
    );
};

export default Index;