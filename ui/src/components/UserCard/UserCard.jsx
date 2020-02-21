import React, {useState} from 'react';
import {
    Card,
    CardContent,
    CardHeader,
    IconButton,
    Dialog, DialogTitle, DialogContent, DialogActions
} from "@material-ui/core";
import {Visibility} from "@material-ui/icons";
import './UserCard.css';
import Button from "@material-ui/core/Button";
import CarsTable from "./CarsTable/CarsTable";

const UserCard = (props) => {
    const {name, cars} = props.user;
    const [openDetails, setOpenDetails] = useState(false);

    const openDetailsDialog = () => setOpenDetails(true);
    const closeDetailsDialog = () => setOpenDetails(false);

    return (
        <Card className="user-card">
            <CardHeader title={name} subheader={`${cars.length} cars`} action={
                <IconButton onClick={openDetailsDialog}>
                    <Visibility/>
                </IconButton>}/>
            <CardContent>
                <CarsTable cars={cars}/>
            </CardContent>
            <Dialog open={openDetails} onClose={closeDetailsDialog} maxWidth="sm" fullWidth>
                <DialogTitle>{name}</DialogTitle>
                <DialogContent dividers >
                    <CarsTable cars={cars}/>
                </DialogContent>
                <DialogActions>
                    <Button onClick={closeDetailsDialog}>Close</Button>
                </DialogActions>
            </Dialog>
        </Card>
    );
};

export default UserCard;
