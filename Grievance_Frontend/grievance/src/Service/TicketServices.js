import axios from "axios"
import { FETCH_ALL_TICKETS_URL, GENERATE_NEW_TICKET_URL } from "../URL/Url"

export const FETCH_ALL_TICKETS = () => {
    return new Promise((resolve, reject) => {
        axios({
            url: FETCH_ALL_TICKETS_URL,
            method: 'GET'
        }).then((res) => {
            return resolve({ data: res.data })
        }).catch((err) => {
            return reject({ data: err })
        })
    })
}

export const GENERATE_NEW_TICKET = (ticketData) => {
    return new Promise((resolve, reject) => {
        axios({
            url: GENERATE_NEW_TICKET_URL,
            method: 'POST',
            data: ticketData
        }).then(res => {
            return resolve({ data: res.data })
        }).catch(err => {
            return reject({ data: err })
        })
    });
}