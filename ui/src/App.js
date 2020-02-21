import React from 'react';
import logo from './assets/logo.png';
import './App.css';
import {Switch, Route, useHistory} from 'react-router';
import "swagger-ui-react/swagger-ui.css"
import Users from "./pages/Users";
import Docs from "./pages/Docs";
import { Link } from 'react-router-dom';
import Button from "@material-ui/core/Button";

const App = () => {

    const history = useHistory();
    const goToDocs = () => history.push("/docs");

    return (
        <div>
            <header className="app-header">
                <Link to="/">
                    <img src={logo} className="app-logo" alt="logo"/>
                </Link>
                <Button className="docs-button" variant="outlined" onClick={goToDocs}> DOCS </Button>
            </header>

            <Switch>
                <Route exact path="/">
                    <Users/>
                </Route>
                <Route exact path="/docs">
                    <Docs/>
                </Route>
            </Switch>
        </div>
    );
};

export default App;
