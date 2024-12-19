import React from 'react';
import { Navigate } from 'react-router-dom';
import Loading from "../Middle/Main/Loading/Loading";

const RequireAuth = ({login, children, isLoading}) => {
    if (isLoading) {
        return <Loading/>;
    }

    return login ? children : <Navigate to="/enter" />;
};

export default RequireAuth;
