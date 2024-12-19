import React, {useMemo} from 'react';
import Post from "../PostPage/Post/Post";

const Index = ({posts}) => {
    const sortedPosts = useMemo(() => {
        if (!posts)
            return []
        return posts.sort((a, b) => b.id - a.id)
    }, [posts])

    return (
        <div>
            {sortedPosts.map((post) =>
                <Post post={post} link={true}/>
            )}
        </div>
    );
};

export default Index;