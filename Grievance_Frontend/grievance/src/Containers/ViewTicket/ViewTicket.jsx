import Button from "../../Components/UI/Button/Button";
import InputElement from "../../Components/UI/InputElement/InputElement";
import classes from './ViewTicket.module.css';

export default function ViewTicket(props) {

    console.log(props)

    const commentBox = (
        <div className={classes.commentBox}>
            <InputElement
                elementType='textarea'
                placeholder='Comment Box'
                changed={props.updateComment}
            />
        </div>
    )

    const updateBtn = (
        <div style={{ width: '70%', marginLeft: '50px' }}>
            <Button content='UPDATE' enable={true} onClick={() => props.updateTicket(props.ticket.ticketId)} />
        </div>
    )

    return (
        <>
            <div className={classes.outerDiv}>
                <div className={classes.heading}>
                    <h3 style={{ marginLeft: '50px' }}>Title : {props.ticket.title}</h3>

                    <div >
                        <i id={classes.icon_close} class="fa fa-window-close" onClick={props.closeModal}></i>
                    </div>
                </div>
                <div className={classes.mainContainer}>
                    <div className={classes.ticketContainer}>
                        <div className={classes.ticketContent}>
                            <div className={classes.ticketData}>
                                <label>TicketType : </label>
                                <p>{props.ticket.ticketType}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Ticket Title : </label>
                                <p>{props.ticket.title}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>TicketId : </label>
                                <p>{props.ticket.ticketId}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Created By : </label>
                                <p>{props.ticket.employee}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Last Updated : </label>
                                <p>{props.ticket.lastUpdated}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Assigned to : </label>
                                <p>{props.ticket.department}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Description : </label>
                                <p>{props.ticket.description}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Status : </label>
                                <InputElement
                                    elementType='select'
                                    default={props.ticket.status}
                                    options={['OPEN', 'BEING_ADDRESSED', 'RESOLVED']}
                                    value={props.ticket.status}
                                    changed={props.updateStatus}
                                    disabled={!props.canUpdateTicket}
                                />
                            </div>
                        </div>
                        {props.canUpdateTicket ? updateBtn : ''}

                    </div>
                    <div className={classes.commentsContainer} id="commentBody" >
                        {
                            props.ticket.comments.map(e => {
                                return <>
                                    <div className={classes.commentContent}>
                                        <p className={classes.userName}>{e.userName}</p>
                                        <p className={classes.description}>{e.description}</p>
                                        <p className={classes.date}>{e.createdOn}</p>
                                    </div>
                                </>
                            })
                        }

                        {props.canUpdateTicket ? commentBox : ''}
                    </div>
                </div>
            </div>
        </>
    )

}
