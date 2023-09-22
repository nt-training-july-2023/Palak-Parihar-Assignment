import Button from "../../Components/UI/Button/Button";
import InputElement from "../../Components/UI/InputElement/InputElement";
import Spinner from "../../Components/UI/Spinner/Spinner";
import classes from './ViewTicket.module.css';

export default function ViewTicket(props) {

    console.log(props)
    if (props.ticket === undefined) {
        return (
            <Spinner />
        )
    } else {

        console.log(props === undefined)
        return (
            <>
                <div className={classes.outerDiv}>
                    <div className={classes.heading}>
                        <h3>{props.ticket.title}</h3>
                        <div >
                            <i id={classes.icon_close} class="fa fa-window-close" onClick={props.closeModal}></i>
                        </div>
                    </div>
                    <div className={classes.mainContainer}>
                        <div className={classes.ticketContainer}>
                            <div>
                                <Button content={'Status : ' + props.ticket.status} />
                            </div>
                            <div className={classes.ticketContent}>
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
                                        options={['OPEN', 'BEING_ADDRESSED', 'RESOLVED']}
                                        value={props.ticket.status}
                                        changed={props.updateStatus}
                                    />
                                </div>
                                <div className={classes.ticketData}>
                                    <label>TicketType : </label>
                                    <p>{props.ticket.ticketType}</p>
                                </div>

                            </div>
                            <button onClick={() => props.updateTicket(props.ticket.ticketId)}>Submit</button>
                        </div>
                        <div className={classes.commentsContainer} id="commentBody">
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
                            <div className={classes.commentBox}>
                                <InputElement
                                    elementType='textarea'
                                    placeholder='Comment Box'
                                    changed={props.updateComment}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </>
        )

    }
}