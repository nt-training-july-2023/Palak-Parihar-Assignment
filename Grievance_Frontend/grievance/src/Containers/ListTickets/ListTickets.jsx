import { useEffect, useState } from "react"
import { FETCH_ALL_TICKETS, GET_TICKET_BY_ID, UPDATE_TICKET_BY_ID } from "../../Service/TicketServices"
import Modal from "../../Components/UI/Modal/Modal";
import ViewTicket from "../ViewTicket/ViewTicket";
import { useNavigate } from "react-router";
import Table from "../../Components/Table/Table";
import classes from "./ListTickets.module.css"
import InputElement from "../../Components/UI/InputElement/InputElement";


export default function ListTickets(props) {
    const statusOptions = [{
        value: '',
        option: 'All Tickets'
    }, {
        value: 'OPEN',
        option: 'Open'
    }, {
        value: 'BEING_ADDRESSED',
        option: 'Being Addressed'
    }, {
        value: 'RESOLVED',
        option: 'Resolved'
    }]
    const [tickets, setTickets] = useState([]);
    const [ticket, setTicket] = useState()
    const [showTicket, setShowTicket] = useState(false)
    const [disablePrevious, setDisablePrevious] = useState(true)
    const [disableNext, setDisableNext] = useState(true)
    const [canUpdateTicket, setCanUpdateTicket] = useState(true)
    const navigate = useNavigate()
    const [config, setConfig] = useState({
        page: 0,
        myTickets: null,
        status: null,
    })

    const headings = ["Ticket Id", "Title", "Ticket Type", "Department", "Raised By", "Status", "Last Updated", "Actions"]

    const columns = ["ticketId", "title", "ticketType", "department", "employee", "status", "lastUpdated"]

    const [ticketUpdate, setTicketUpdate] = useState({
        status: null,
        description: null
    })
    const [modal, setModal] = useState();


    useEffect(() => {
        console.log(config)
        if (localStorage.getItem('userDetails') === null) {
            navigate('/logout')
            return
        } else {
            console.log(localStorage)
        }

        FETCH_ALL_TICKETS(config)
            .then(res => {
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
    }, [config, navigate, ticketUpdate, ticket, showTicket])

    const viewSelectedTicket = (ticketId) => {
        console.log(ticketId)
        let values = JSON.parse(localStorage.getItem('userDetails'))

        const response = GET_TICKET_BY_ID(ticketId)
            .then(res => {
                const ticket = res.data
                console.log()
                if (values.department === ticket.department) {
                    setCanUpdateTicket(false)
                } else {
                    setCanUpdateTicket(true)
                }
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

    const closeTicketModal = () => {
        setShowTicket(false)
    }

    const closeModal = () => {
        setModal(<></>)
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
        console.log(ticketUpdate.description === null)

        if (ticketUpdate.description === null || ticketUpdate.description.trim().length === 0) {
            setModal(<Modal message="Please leave a comment to update ticket" onClick={closeModal} />)
            return
        }

        UPDATE_TICKET_BY_ID(Id, ticketUpdate)
            .then(res => {
                console.log(res.data)
                setTicket(res.data)
                let ticketUpdatedSuccessfully = {
                    status: null,
                    description: ''
                }
                setTicketUpdate(ticketUpdatedSuccessfully)
            }).catch(err => {
                console.log(err)
            })
    }

    const viewTicketparams = {
        ticket,
        closeTicketModal,
        updateStatus,
        updateTicket,
        updateComment,
        canUpdateTicket,
        'closeModal': closeTicketModal,
        ticketUpdate
    }
    return (
        <>
            {showTicket ? <Modal component={ViewTicket(viewTicketparams)} /> : null}
            {modal}
            <div className="list_main_container">
                <div>
                    <div className={classes.menu_bar}>
                        <div id={classes.menu}>
                            <button class={classes.menu_button} id={config.myTickets ? '' : classes.active} onClick={() => AllTickets()}>All Tickets</button>
                            <button class={classes.menu_button} id={config.myTickets ? classes.active : ''} onClick={() => MyTickets()}>MyTickets</button>
                        </div>
                        <h2 className={classes.heading}>All Tickets</h2>
                        <div className={classes.status_dropdown}>
                            <InputElement
                                label='Status'
                                elementType='select'
                                options={statusOptions}
                                selectValue='value'
                                selectOption='option'
                                default='ALL TICKETS'
                                changed={e => setStatus(e)}
                            />
                        </div>
                    </div>

                    <Table
                        values={tickets}
                        headings={headings}
                        view={viewSelectedTicket}
                        columns={columns}
                        previousPage={previousPage}
                        nextPage={nextPage}
                        disablePrevious={disablePrevious}
                        disableNext={disableNext}
                        id="ticketId" />
                </div>
            </div >
        </>
    )
}