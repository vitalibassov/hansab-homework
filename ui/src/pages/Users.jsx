import React, {useEffect, useState} from 'react';
import Search from "../components/Search/Search";
import UserCard from "../components/UserCard/UserCard";
import {searchUser} from "../utils/requests";

const Users = () => {
    const [users, setUsers] = useState();

    const findUsers = (text, order) => searchUser(text, "name", order)
        .then(response => {
            setUsers(response.data)
        });

    useEffect(() => {
        findUsers("", "desc")
    }, []);

    return <><Search search={(text, order) => {
        findUsers(text, order);
    }}/>
        <div className="user-cards">
            {users && users.map(user => {
                return <UserCard key={user.name} user={user}/>
            })}
        </div>
    </>
};

export default Users;