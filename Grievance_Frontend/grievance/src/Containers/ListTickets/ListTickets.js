import { useEffect, useState } from "react"
import { FETCH_ALL_TICKETS } from "../../Service/TicketServices"
import '../ListEmployees/ListEmployees';
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
            {modal}
            <div className="list_main_container">
                <table id="list_content">
                    <tr>
                        <th>Ticket Id</th>
                        <th>Ticket Type</th>
                        <th>Title</th>
                        <th>Department</th>
                        <th>Raised By</th>
                        <th>Last Updated</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>

                    {
                        tickets.map(t => {
                            return (
                                <>
                                    <tr>
                                        <td>{t.ticketId}</td>
                                        <td>{t.ticketType}</td>
                                        <td>{t.title}</td>
                                        <td>{t.department}</td>
                                        <td>{t.employee}</td>
                                        <td>{t.lastUpdated}</td>
                                        <td>{t.status}</td>
                                        <td>
                                            <i id="icon" class='fas fa-edit' onClick={() => viewSelectedTicket(t)}/>
                                            <i id="icon" class='fas fa-trash-alt'></i>
                                        </td>
                                    </tr>
                                </>
                            )
                        })
                    }
                </table>
            </div >
        </>
    )
}