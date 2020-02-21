import React, {useEffect, useRef, useState} from 'react';
import {TextField} from "@material-ui/core";
import ToggleButtonGroup from "@material-ui/lab/ToggleButtonGroup";
import ToggleButton from "@material-ui/lab/ToggleButton";
import {KeyboardArrowDown, KeyboardArrowUp} from '@material-ui/icons';
import './Search.css';

const orderDirection = {
    asc: "ASC",
    desc: "DESC"
};

const Search = (props) => {

    const [order, setOrder] = useState(orderDirection.desc);
    const [text, setText] = useState("");

    const isFirstRun = useRef(true);
    useEffect(() => {
        if (isFirstRun.current) {
            isFirstRun.current = false;
        } else {
            props.search(text, order);
        }
    }, [order, text]);

    return (
        <div className="search">
            <TextField value={text} onChange={e => setText(e.target.value)}
                       variant="outlined"
                       placeholder="Type User name here..."/>
            <ToggleButtonGroup onChange={(event, value) => (value && setOrder(value))}
                               exclusive
                               value={order}>
                <ToggleButton value={orderDirection.asc}>
                    <KeyboardArrowUp/>
                </ToggleButton>
                <ToggleButton value={orderDirection.desc}>
                    <KeyboardArrowDown/>
                </ToggleButton>
            </ToggleButtonGroup>
        </div>)
};

export default Search;