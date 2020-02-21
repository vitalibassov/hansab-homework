import React, {useEffect, useState} from 'react';
import logo from './assets/logo.png';
import './App.css';
import {findAllUsers, searchUser} from "./utils/requests";
import UserCard from "./components/UserCard/UserCard";
import Search from "./components/Search/Search";

const App = () => {
    const [users, setUsers] = useState();

    const findUsers = (text, order) => searchUser(text, "name", order)
        .then(response => {
            setUsers(response.data)
        });

    useEffect(() => {
            findAllUsers()
                .then(response => {
                    setUsers(response.data)
                });
        }, []);

    return (
        <div className="App">
            <header className="app-header">
                <img src={logo} className="app-logo" alt="logo"/>
            </header>

            <Search search={(text, order) => {
                findUsers(text, order);
            }}/>
            <div className="user-cards">
                {users && users.map(user => {
                    return <UserCard key={user.name} user={user}/>
                })}
            </div>
        </div>
    );
};

export default App;
