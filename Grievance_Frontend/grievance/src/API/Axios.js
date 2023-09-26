import axios from "axios";
import { BASE_URL } from "./Url";

export const Axios = axios.create({
    baseURL: BASE_URL
})