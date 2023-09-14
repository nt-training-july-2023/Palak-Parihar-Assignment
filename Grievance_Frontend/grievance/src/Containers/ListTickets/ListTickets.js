import { useEffect, useState } from "react"
import { FETCH_ALL_TICKETS } from "../../Service/TicketServices"
import Cell from "../../Components/Cell/Cell";

import classes from './ListTickets.module.css';
import HeadCell from "../../Components/Cell/HeadCell";
import Modal from "../../Components/UI/Modal/Modal";
import ViewTicket from "../ViewTicket/ViewTicket";


export default function ListTickets(props) {

    const [tickets, setTickets] = useState([]);
    const [modal, setModal] = useState()

    useEffect(() => {
        FETCH_ALL_TICKETS().
            then(res => {
                setTickets(res.data)
                return res.data
            }).catch(err => {
                return err.data
            })
    }, [])

    const viewSelectedTicket = (e) => {
        console.log(e)
        // const view = <ViewTicket ticket={e}/>
        setModal(() => <Modal component={ViewTicket(e)} />)
    }

    return (
        <>
            {/* <div className="modal-container"> */}
                {modal}
            {/* </div> */}
            <div className={classes.mainContainer}>

                <div className={classes.rowContent}>
                    <HeadCell value="Ticket Id" />
                    <HeadCell value="Ticket Type" />
                    <HeadCell value="Title" />
                    <HeadCell value="Department" />
                    <HeadCell value="Employee" email={true} />
                    <HeadCell value="Date Opened" />
                    <HeadCell value="Last Updated" />
                    <HeadCell value="Actions" />
                </div>

                {tickets.map(e => (

                    <div className={classes.rowContent}>
                        <Cell value={e.ticketId} />
                        <Cell value={e.ticketType} />
                        {console.log(e)}
                        <Cell value={e.title} />
                        <Cell value={e.department} />
                        <Cell value={e.employee} />
                        <Cell value={e.dateOpened.substring(0, 10)} />
                        <Cell value={e.lastUpdated.substring(0, 10)} />
                        <div className={classes.action}>
                            <div className={classes.icon}>
                                <i class='fas fa-edit' onClick={() => viewSelectedTicket(e)}></i>
                            </div>
                            <div className={classes.icon}>
                                <i class='fas fa-trash-alt'></i>
                            </div>
                        </div>
                    </div>

                ))}
            </div >
        </>
    )
}