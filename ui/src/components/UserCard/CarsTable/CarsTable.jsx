import React from 'react';
import {
    Table,
    TableBody,
    TableCell,
    TableHead,
    TableRow,
} from "@material-ui/core";


const renderTableBody = (cars) => <TableBody>
    {cars && cars.map(car => (
        <TableRow key={car.numberplate}>
            <TableCell>{car.make}</TableCell>
            <TableCell>{car.model}</TableCell>
            <TableCell>{car.numberplate}</TableCell>
        </TableRow>
    ))}
</TableBody>;

const CarsTable = (props) =>
    <Table>
        <TableHead>
            <TableRow>
                <TableCell>Make</TableCell>
                <TableCell>Model</TableCell>
                <TableCell>Number Plate</TableCell>
            </TableRow>
        </TableHead>
        {renderTableBody(props.cars)}
    </Table>;


export default CarsTable;
