import React from 'react';
import {Card, CardContent, CardHeader, Table, TableBody, TableCell, TableHead, TableRow} from "@material-ui/core";
import './UserCard.css';

const renderTableBody = (cars) => <TableBody>
    {cars && cars.map(car => (
        <TableRow key={car.numberplate}>
            <TableCell>{car.make}</TableCell>
            <TableCell>{car.model}</TableCell>
            <TableCell>{car.numberplate}</TableCell>
        </TableRow>
    ))}
</TableBody>;

const UserCard = (props) => {
    const {name, cars} = props.user;

    return (
        <Card className="user-card">
            <CardHeader title={name}/>
            <CardContent>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Make</TableCell>
                            <TableCell>Model</TableCell>
                            <TableCell>Number Plate</TableCell>
                        </TableRow>
                    </TableHead>
                    {renderTableBody(cars)}
                </Table>
            </CardContent>
        </Card>
    );
};

export default UserCard;
