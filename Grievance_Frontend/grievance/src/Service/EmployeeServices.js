import { CHANGE_PASSWORD_URL, DELETE_EMPLOYEE_URL, FETCH_ALL_USERS_URL, LOGIN_USER_URL, SAVE_NEW_EMPLOYEE_URL } from "../API/Url"
import { deleteMapping, getMapping, postMapping, putMapping } from "../API/url-order"
import { headers } from "../API/Headers"


const userDetails = () => {
    return JSON.parse(localStorage.getItem('userDetails'))
}

export const LOGIN_USER = (userData) => {
    return new Promise((resolve, reject) => {
        postMapping(LOGIN_USER_URL,
            userData,
        ).then(res => {
            resolve({ data: res.data?.data })
        }).catch(err => {
            if (err.response === undefined) {
                reject({ data: err })
            } else {
                reject({ data: err?.response?.data })
            }
        })
    });
}

export const CHANGE_USER_PASSWORD = (values) => {
    let userValues = headers()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password,
    }
    return new Promise((resolve, reject) => {
        putMapping(CHANGE_PASSWORD_URL,
            values,
            { headers: headersRequired })
            .then(res => {
                resolve({ data: res.data })
            }).catch(err => {
                if (err.response === undefined) {
                    reject({ data: err })
                } else {
                    reject({ data: err?.response?.data })
                }
            })
    })
}


export const FETCH_ALL_USERS = (pageNo) => {
    let userValues = userDetails()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        getMapping(FETCH_ALL_USERS_URL,
            {
                headers: headersRequired,
                params: {
                    page: pageNo
                }
            }).then(res => {
                resolve({ data: res.data.data })
            }).catch(err => {
                if (err.response === undefined) {
                    reject({ data: err })
                } else {
                    reject({ data: err?.response?.data })
                }
            })
    });
};


export const SAVE_NEW_EMPLOYEE = (employeeData) => {
    let userValues = userDetails()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        postMapping(SAVE_NEW_EMPLOYEE_URL,
            employeeData,
            {
                headers: headersRequired,
            }).then(res => {
                console.log(res)
                resolve({ data: res.data })
            }).catch(err => {
                if (err.response === undefined) {
                    reject({ data: err })
                } else {
                    reject({ data: err?.response?.data })
                }
            })
    })
}

export const DELETE_EMPLOYEE = (empId) => {
    let userValues = headers()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        deleteMapping(DELETE_EMPLOYEE_URL, {
            headers: headersRequired,
            params: {
                deleteEmployee: empId
            }
        }).then(res => {
            console.log(res)
            resolve({ data: res.data })
        }).catch(err => {
            if (err.response === undefined) {
                reject({ data: err })
            } else {
                reject({ data: err?.response?.data })
            }
        })
    })
}
