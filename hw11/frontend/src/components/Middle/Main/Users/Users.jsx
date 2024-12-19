import React, {useMemo} from "react";
import Loading from "../Loading/Loading";

const Users = ({users}) => {

    const sortedUsers = useMemo(() => {
        if (!users)
            return []
        return users.sort((a, b) => b.id - a.id)
    }, [users])

    return (
        <div className="datatable">
            <div className="caption">Users</div>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Login</th>
                        <th>Registered</th>
                    </tr>
                </thead>
                <tbody>
                    {sortedUsers.map((user) => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.login}</td>
                            <td>{new Date(user.creationTime).toLocaleString()}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default Users