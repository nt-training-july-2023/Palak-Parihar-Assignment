import axios from "axios";
import { FETCH_ALL_DEPARTMENTS_URL, GENERATE_NEW_DEPARTMENTS_URL } from "../URL/Url";

const headersData = {
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Headers': '*',
    'Access-Control-Allow-Credentials': true,
};

const userDetails = () => {
    return JSON.parse(sessionStorage.getItem('userDetails'))
}

export const FETCH_ALL_DEPARTMENTS = () => {
    let userValues = userDetails()
    let headersRequired = {
        ...headersData,
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        axios({
            url: FETCH_ALL_DEPARTMENTS_URL,
            method: 'GET',
            headers: headersRequired,
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
    let userValues = userDetails()
    let headersRequired = {
        ...headersData,
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        axios({
            url: GENERATE_NEW_DEPARTMENTS_URL,
            method: 'POST',
            headers: headersRequired,
            data: departmentName
        }).then(res => {
            resolve({ data: res.data })
        }).catch(e => {
            reject({ data: e })
        })
    });
}