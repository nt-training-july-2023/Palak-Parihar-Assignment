import axios from "axios";
import { FETCH_ALL_DEPARTMENTS_URL, GENERATE_NEW_DEPARTMENTS_URL } from "../URL/Url";

let headersData = {
    email: "ayushi@nucleusteq.com",
    password: "Ayushi#124",
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': '*',
    'Access-Control-Allow-Credentials': true
}

export const FETCH_ALL_DEPARTMENTS = () => {
    return new Promise((resolve, reject) => {
        axios({
            url: FETCH_ALL_DEPARTMENTS_URL,
            method: 'GET',
            headers: headersData,
        }).then((res) => {
            // console.log(res.data);
            resolve({ data: res.data });
        }).catch((e) => {
            console.error(e);
            reject({ data: e });
        });
    })
}

export const GENERATE_NEW_DEPARTMENT = (departmentName) => {
    return new Promise((resolve, reject) => {
        axios({
            url: GENERATE_NEW_DEPARTMENTS_URL,
            method: 'POST',
            headers: headersData,
            data: departmentName
        }).then(res => {
            resolve({ data: res.data })
        }).catch(e => {
            reject({ data: e })
        })
    });
}