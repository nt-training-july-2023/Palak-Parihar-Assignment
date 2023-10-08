import Button from "../../Components/UI/Button/Button";
import InputElement from "../../Components/UI/InputElement/InputElement";
import classes from './ViewTicket.module.css';

export default function ViewTicket(props) {
    const statusOptions = [
        {
            option: 'Open',
            value: 'OPEN'
        }, {
            option: 'Being Addressed',
            value: 'BEING_ADDRESSED'
        }, {
            option: 'Resolved',
            value: 'RESOLVED'
        }
    ]

    const scrollToBottom = () => {
        const commentBody = document.getElementById("commentBody")
        commentBody.scrollTop = commentBody.scrollHeight;
    }

    const commentBox = (
        <div className={classes.commentBox}>
            <div className={classes.scrollBtn} onClick={scrollToBottom}>
                Scroll to bottom <i style={{fontSize:'20px', color:'#266183'}} class='fas fa-arrow-alt-circle-down'></i>
            </div>
            <InputElement
                elementType='textarea'
                placeholder='Comment Box'
                changed={props.updateComment}
                value={props.ticketUpdate.description}
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
                        <i id={classes.icon_close} className="fa fa-window-close" onClick={props.closeModal}></i>
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
                                    options={statusOptions}
                                    selectValue='value'
                                    selectOption='option'
                                    value={props.ticket.status}
                                    changed={props.updateStatus}
                                    disabled={!props.canUpdateTicket}
                                />
                            </div>
                        </div>
                        <div style={{ display: 'flex' }}>
                            {props.canUpdateTicket ? updateBtn : ''}
                        </div>
                    </div>
                    <div className={classes.commentsContainer} id="commentBody" >
                        {props.ticket.comments.length === 0 ?
                            (<h2 style={{ opacity: '0.3', textAlign: 'center' }}>No comments yet</h2>)
                            : null}
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
