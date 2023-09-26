import axios from "axios"
import { FETCH_ALL_TICKETS_URL, GENERATE_NEW_TICKET_URL, GET_TICKET_BY_ID_URL, UPDATE_TICKET_BY_ID_URL } from "../URL/Url"


const userDetails = () => {
    const userDetailsData = JSON.parse(sessionStorage.getItem('userDetails'))
    return userDetailsData;
}

export const FETCH_ALL_TICKETS = (parameters) => {
    let userValues = userDetails()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        axios({
            url: FETCH_ALL_TICKETS_URL,
            method: 'GET',
            headers: headersRequired,
            params: parameters
        }).then((res) => {
            console.log(res.data)
            return resolve({ data: res.data })
        }).catch((err) => {
            return reject({ data: err })
        })
    })
}

export const GENERATE_NEW_TICKET = (ticketData) => {
    let userValues = userDetails()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        axios({
            url: GENERATE_NEW_TICKET_URL,
            method: 'POST',
            data: ticketData,
            headers: headersRequired
        }).then(res => {
            return resolve({ data: res.data })
        }).catch(err => {
            return reject({ data: err })
        })
    });
}

export const GET_TICKET_BY_ID = (Id) => {
    let userValues = userDetails()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        axios({
            url: GET_TICKET_BY_ID_URL,
            params: {
                ticketId: Id
            },
            headers: headersRequired
        }).then(res => {
            return resolve({ data: res.data })
        }).catch(err => {
            return reject({ data: err })
        })
    })
}

export const UPDATE_TICKET_BY_ID = (Id, ticketUpdate) => {
    let userValues = userDetails()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    console.log(ticketUpdate)

    return new Promise((resolve, reject) => {
        axios({
            url: UPDATE_TICKET_BY_ID_URL,
            params: {
                ticketId: Id
            },
            data: ticketUpdate,
            method: 'PUT',
            headers: headersRequired
        }).then(res => {
            return resolve({ data: res.data })
        }).catch(err => {
            return reject({ data: err })
        })
    })
}