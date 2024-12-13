import React from 'react';
import {data} from "../../data";

const Footer = ({postsCnt, usersCnt}) => {
    return (
        <footer>
            <a href="#">Codehorses</a> 2099 by Mike Mirzayanov
            <div>Users: {usersCnt}</div>
            <div>Posts: {postsCnt}</div>
        </footer>
    );
};

export default Footer;