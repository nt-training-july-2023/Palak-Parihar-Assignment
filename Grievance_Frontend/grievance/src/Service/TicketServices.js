import axios from "axios"
import { FETCH_ALL_TICKETS_URL, GENERATE_NEW_TICKET_URL, GET_TICKET_BY_ID_URL, UPDATE_TICKET_BY_ID_URL } from "../API/Url"
import { getMapping, postMapping, putMapping } from "../API/url-order";
import { headers } from "../API/Headers";

export const FETCH_ALL_TICKETS = (parameters) => {
    let userValues = headers()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        getMapping(FETCH_ALL_TICKETS_URL, {
            headers: headersRequired,
            params: parameters
        }).then((res) => {
            return resolve({ data: res.data })
        }).catch((err) => {
            return reject({ data: err })
        })
    })
}

export const GENERATE_NEW_TICKET = (ticketData) => {
    let userValues = headers()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    console.log(headersRequired)
    return new Promise((resolve, reject) => {
        postMapping(GENERATE_NEW_TICKET_URL,
            ticketData,
            {
                headers: headersRequired
            }).then(res => {
                resolve({ data: res.data })
            }).catch(err => {
                reject({ data: err })
            })
    });
}

export const GET_TICKET_BY_ID = (Id) => {
    let userValues = headers()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        getMapping(GET_TICKET_BY_ID_URL, {
            headers: headersRequired,
            params: {
                ticketId: Id
            }
        }).then((res) => {
            return resolve({ data: res.data })
        }).catch((err) => {
            console.log(err)
            return reject({ data: err })
        })
    })
}

export const UPDATE_TICKET_BY_ID = (Id, ticketUpdate) => {
    let userValues = headers()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    console.log(ticketUpdate)

    return new Promise((resolve, reject) => {
        // axios({
        //     url: UPDATE_TICKET_BY_ID_URL,
        //     params: {
        //         ticketId: Id
        //     },
        //     data: ticketUpdate,
        //     method: 'PUT',
        //     headers: headersRequired
        // }).then(res => {
        //     return resolve({ data: res.data })
        // }).catch(err => {
        //     return reject({ data: err })
        // })
        putMapping(UPDATE_TICKET_BY_ID_URL,
            ticketUpdate, {
            headers: headersRequired,
            params: {
                ticketId : Id
            }
        })
            .then(res => {
                return resolve({ data: res.data })
            }).catch(err => {
                return reject({ data: err })
            })
    })
}