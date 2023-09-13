import Button from "../../Components/UI/Button/Button";
import InputElement from "../../Components/UI/InputElement/InputElement";
import classes from './ViewTicket.module.css';


export default function ViewTicket(props) {

    const dummy = [
        {
            commentId: '152',
            commentContent: "Lorem Ipsum is simply dummy text of the .",
            employee: 'lorem@nucleusteq.com'
        }, {
            commentId: '153',
            commentContent: "Lorem Ipsum is simply  to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            employee: 'lorem@nucleusteq.com'
        }, {
            commentId: '154',
            commentContent: "Lorem Ipsum is standard Lorem Ipsum.",
            employee: 'lorem@nucleusteq.com'
        }, {
            commentId: '155',
            commentContent: "Lorem Ipsum is  of Lorem Ipsum.",
            employee: 'lorem@nucleusteq.com'
        }
    ]

    console.log(props)
    const { ticket } = props;
    console.log(ticket)
    return (
        <>
            <div className={classes.outerDiv}>
                <div className={classes.heading}>Hello</div>
                <div className={classes.mainContainer}>
                    <div className={classes.ticketContainer}>
                        <Button status="" content={props.status} />
                        <div className={classes.ticketContent}>
                            <div className={classes.ticketData}>
                                <label>Ticket Title : </label>
                                <p>{props.title}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>TicketId : </label>
                                <p>{props.ticketId}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Created By : </label>
                                <p>{props.employee}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Assigned to : </label>
                                <p>{props.department}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Description : </label>
                                <p>{props.description}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>Status : </label>
                                <p>{props.status}</p>
                            </div>
                            <div className={classes.ticketData}>
                                <label>TicketType : </label>
                                <p>{props.ticketType}</p>
                            </div>

                        </div>
                    </div>
                    <div className={classes.commentsContainer}>
                        {
                            dummy.map(e => {
                                return <>
                                    <div className={classes.commentContent}>
                                        <p>{e.commentContent}</p>
                                        <div className={classes.ticketData}>
                                            <label>commented By: </label>
                                            <p>{e.employee}</p>
                                        </div>
                                    </div>
                                </>
                            })
                        }
                        <div className={classes.commentBox}>
                            <InputElement
                                elementType='textarea'
                            />
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}