import React, {useEffect, useRef, useState} from 'react';
import {TextField, Divider} from "@material-ui/core";
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

    const setSearchText = (e) => setText(e.target.value);
    const setOrderDirection = (event, value) => (value && setOrder(value));

    return (
        <>
            <div className="search">
                <TextField value={text}
                           onChange={setSearchText}
                           variant="outlined"
                           label="Search"
                           placeholder="Type User name here..."/>
                <ToggleButtonGroup onChange={setOrderDirection}
                                   exclusive
                                   value={order}>
                    <ToggleButton value={orderDirection.asc}>
                        <KeyboardArrowUp/>
                    </ToggleButton>
                    <ToggleButton value={orderDirection.desc}>
                        <KeyboardArrowDown/>
                    </ToggleButton>
                </ToggleButtonGroup>
            </div>
            <Divider/>
        </>)
};

export default Search;