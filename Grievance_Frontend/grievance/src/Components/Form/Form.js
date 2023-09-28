import classes from './Form.module.css';
import Button from '../UI/Button/Button';

export default function Form(props) {

    return (
        <>
            <div className={classes.outerDiv}>
                <div className={classes.heading}>{props.heading}</div>
                <form onSubmit={e => props.onSubmit(e)}>
                    {props.content}
                    <p className={classes.message}>{props.message}</p>
                    <Button content='SUBMIT' enable={props.enable} />
                </form>
            </div>
        </>
    )
}