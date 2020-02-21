import axios from "../axios";

export const findAllCars = () => axios.get("/cars");
export const findAllUsers = () => axios.get("/users");
export const searchUser = (find, fieldName, order) => axios.get(`/users?find=${find}&sort=${fieldName}:${order}`);