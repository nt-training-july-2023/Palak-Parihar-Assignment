import axios from "axios"
import { FETCH_ALL_USERS_URL, LOGIN_USER_URL, SAVE_NEW_EMPLOYEE_URL } from "../URL/Url"


const userDetails = () => {
    return JSON.parse(sessionStorage.getItem('userDetails'))
}

export const LOGIN_USER = (userData) => {
    return new Promise((resolve, reject) => {
        axios({
            url: LOGIN_USER_URL,
            method: 'POST',
            // headers: headersData,
            data: userData,
            // withCredentials:true
        })
            .then((res) => {
                console.log(res.data);
                resolve({ data: res.data });
            })
            .catch((e) => {
                console.error(e);
                reject({ data: e });
            });
    });
}


export const FETCH_ALL_USERS = () => {
    let userValues = userDetails()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        axios({
            url: FETCH_ALL_USERS_URL,
            method: 'GET',
            headers: headersRequired,
        }).then((res) => {
            // const response = JSON.parse(res.data)
            console.log(res.data);
            resolve({ data: res.data});
        })
            .catch((e) => {
                console.error(e)
                reject({ data: e })
            });
    });
};


export const SAVE_NEW_EMPLOYEE = (employeeData) => {
    let userValues = userDetails()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        axios({
            url: SAVE_NEW_EMPLOYEE_URL,
            method: 'POST',
            headers: headersRequired,
            data: employeeData
        }).then((res) => {
            resolve({ data: res.data })
        }).catch((e) => {
            reject({ data: e })
        })
    })
}
