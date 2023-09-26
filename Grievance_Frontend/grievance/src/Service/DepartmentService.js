import axios from "axios";
import { DELETE_DEPARTMENT_URL, FETCH_ALL_DEPARTMENTS_URL, GENERATE_NEW_DEPARTMENTS_URL } from "../API/Url";
import { deleteMapping, getMapping, postMapping } from "../API/url-order";
import { headers } from "../API/Headers";


export const FETCH_ALL_DEPARTMENTS = (pageNo) => {
    let userValues = headers()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        getMapping(
            FETCH_ALL_DEPARTMENTS_URL,
            {
                headers: headersRequired,
                params: {
                    page: pageNo
                }
            }
        ).then(res => {
            return resolve({ data: res.data })
        }).catch(err => {
            return reject({ data: err })
        })
    })
}

export const GENERATE_NEW_DEPARTMENT = (deptName) => {
    let userValues = headers()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        postMapping(GENERATE_NEW_DEPARTMENTS_URL,
            deptName,
            {
                headers: headersRequired,
            }).then(res => {
                resolve({ data: res.data })
            }).catch(err => {
                reject({ data: err })
            })
    });
}

export const DELETE_DEPARTMENT = (deptId) => {
    let userValues = headers()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        deleteMapping(DELETE_DEPARTMENT_URL,
            {
                headers: headersRequired,
                params: {
                    departmentId: deptId
                }
            }).then(res => {
                console.log(res)
                return resolve({ data: res.data })
            }).catch(err => {
                return reject({ data: err })
            })
    });
}