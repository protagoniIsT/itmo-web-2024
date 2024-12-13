import React, {useMemo} from "react";

const Users = ({usersState}) => {

    const sortedUsers = useMemo(() => {
        if (!usersState)
            return []
        return usersState.sort((a, b) => b.id - a.id)
    }, [usersState])

    return (
        <div className="datatable">
            <div className="caption">Users</div>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Login</th>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    {sortedUsers.map((user) => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.login}</td>
                            <td>{user.name}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default Users