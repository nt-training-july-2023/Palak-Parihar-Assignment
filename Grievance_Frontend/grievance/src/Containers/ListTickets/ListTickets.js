import { useEffect, useState } from "react"
import { FETCH_ALL_TICKETS, GET_TICKET_BY_ID, UPDATE_TICKET_BY_ID } from "../../Service/TicketServices"
import Modal from "../../Components/UI/Modal/Modal";
import ViewTicket from "../ViewTicket/ViewTicket";
import './ListTickets.css';
import { useNavigate } from "react-router";
import Table from "../../Components/Table/Table";


export default function ListTickets(props) {
    const [tickets, setTickets] = useState([]);
    const [ticket, setTicket] = useState()
    const [showTicket, setShowTicket] = useState(false)
    const [disablePrevious, setDisablePrevious] = useState(true)
    const [disableNext, setDisableNext] = useState(true)
    const navigate = useNavigate()
    const [config, setConfig] = useState({
        page: 0,
        myTickets: null,
        status: null,
        // departmentTickets: null
    })

    const headings= ["Ticket Id", "Title", "Ticket Type", "Department", "Raised By", "Status", "Last Updated", "Actions"]
    
    const columns = ["ticketId", "title","ticketType", "department", "employee", "status", "lastUpdated"]

    const [ticketUpdate, setTicketUpdate] = useState({
        status: null,
        description: null
    })


    useEffect(() => {
        console.log(config)
        if (localStorage.getItem('userDetails') === null) {
            navigate('/logout')
            return
        } else {
            let values = JSON.parse(localStorage.getItem('userDetails'))
            console.log(values.firstTimeUser)
            if (values.firstTimeUser) {
                navigate('/changePassword')
                return
            }
        }

        FETCH_ALL_TICKETS(config).
            then(res => {
                if (res.data.length < 10) {
                    setDisableNext(true)
                } else {
                    setDisableNext(false)
                }
                setTickets(res.data)
                return res.data
            }).catch(err => {
                return err.data
            })
    }, [config, ticketUpdate])

    const viewSelectedTicket = (ticketId) => {
        console.log(ticketId)
        const response = GET_TICKET_BY_ID(ticketId)
            .then(res => {
                const ticket = res.data
                setTicket(ticket)
                setShowTicket(true)
                return res.data;
            })
            .catch(err => {
                console.error(err)
                return err.data
            })
        console.log(response)
    }

    const closeModal = () => {
        setShowTicket(false)
    }

    const MyTickets = () => {
        console.log(config)
        let updateConfig = {
            ...config,
            myTickets: true,
            departmentTickets: null,
            page: 0,
        }
        setConfig(updateConfig)
        console.log(config)
        setDisablePrevious(true)
    }

    const nextPage = () => {
        let updateConfig = {
            ...config,
            page: config.page + 1
        }
        setConfig(updateConfig)
        setDisablePrevious(false)
    }

    const AllTickets = () => {
        let updateConfig = {
            ...config,
            page: 0,
            myTickets: null,
            departmentTickets: null
        }
        console.log(updateConfig)
        setConfig(updateConfig)
        setDisablePrevious(true)
    }

    const setStatus = (e) => {
        console.log(e.target.value)
        let updateConfig = {
            ...config,
            status: e.target.value
        }
        console.log(updateConfig)
        setConfig(updateConfig)
    }

    const previousPage = () => {
        let updateConfig = {
            ...config,
            page: config.page - 1
        }
        setConfig(updateConfig)
        if (updateConfig.page === 0) {
            setDisablePrevious(true)
            return;
        }
    }

    const updateStatus = (e) => {
        console.log(e.target.value)
        let updateTicketConfig = {
            ...ticketUpdate,
            status: e.target.value

        }
        setTicketUpdate(updateTicketConfig)
    }

    const updateComment = (e) => {
        console.log(e.target.value)
        let updateTicketConfig = {
            ...ticketUpdate,
            description: e.target.value

        }
        setTicketUpdate(updateTicketConfig)
    }

    const updateTicket = (Id) => {
        console.log(ticketUpdate)

        UPDATE_TICKET_BY_ID(Id, ticketUpdate)
            .then(res => {
                console.log(res.data)
                setTicket(res.data)
            }).catch(err => {
                console.log(err)
            })
    }

    return (
        <>
            {showTicket ? <Modal component={ViewTicket({ ticket, closeModal, updateStatus, updateComment, updateTicket })} /> : null}
            <div className="list_main_container">
                <div className="menu_bar">
                    <div id="menu">
                        <button class="menu_button" id={config.myTickets ? '' : 'active'} onClick={() => AllTickets()}>All Tickets</button>
                        <button class="menu_button" id={config.myTickets ? 'active' : ''} onClick={() => MyTickets()}>MyTickets</button>
                    </div>
                    <div className="status_dropdown">
                        <p>Status :</p>
                        <select onChange={(e) => setStatus(e)}>
                            <option value=''>ALL TICKETS</option>
                            <option value='OPEN'> OPEN </option>
                            <option value='BEING_ADDRESSED'> BEING ADDRESSED </option>
                            <option value='RESOLVED'> RESOLVED </option>
                        </select>
                    </div>
                </div>
                <Table
                    values={tickets}
                    headings={headings}
                    view={viewSelectedTicket}
                    columns={columns}
                    id="ticketId"/>
            </div >
            <div id="actions_arrow">
                {disablePrevious ?
                    <i id="disable_action_icon" class='fas fa-arrow-alt-circle-left'></i>
                    : <i id="action_icon" class='fas fa-arrow-alt-circle-left' onClick={previousPage}></i>}

                {disableNext ? <i id="disable_action_icon" class='fas fa-arrow-alt-circle-right'></i> :
                    <i id="action_icon" class='fas fa-arrow-alt-circle-right' onClick={nextPage}></i>}
            </div>
        </>
    )
}