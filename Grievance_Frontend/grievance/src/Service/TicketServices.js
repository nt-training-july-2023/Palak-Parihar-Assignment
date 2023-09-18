import axios from "axios"
import { FETCH_ALL_TICKETS_URL, GENERATE_NEW_TICKET_URL } from "../URL/Url"


const userDetails = () => {
    return JSON.parse(sessionStorage.getItem('userDetails'))
}

export const FETCH_ALL_TICKETS = () => {
    let userValues = userDetails()
    let headersRequired = {
        email: userValues.email,
        password: userValues.password
    }
    return new Promise((resolve, reject) => {
        axios({
            url: FETCH_ALL_TICKETS_URL,
            method: 'GET',
            headers : headersRequired
        }).then((res) => {
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
            headers:headersRequired
        }).then(res => {
            return resolve({ data: res.data })
        }).catch(err => {
            return reject({ data: err })
        })
    });
}