import React from 'react';
import SwaggerUI from "swagger-ui-react";
import {API_URL} from "../config";

const Docs = () => {
    return <SwaggerUI url={`${API_URL}/docs`}/>
};

export default Docs;