import React, {useMemo} from 'react';
import Section from "./Section/Section";
import cl from './Aside.module.css'

const Aside = ({posts, setPage}) => {

    const sortedPosts = useMemo(() => {
        if (!posts)
            return []
        return posts.sort((a, b) => b.id - a.id)
    }, [posts])

    return (
        <aside className={cl.sidePosts}>
            {sortedPosts.map((post) =>
                <Section post={post} setPage={setPage} key={post.title}/>
            )}
        </aside>
    );
};

export default Aside;