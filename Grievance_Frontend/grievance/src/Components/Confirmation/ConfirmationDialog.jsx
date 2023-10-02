import Button from "../UI/Button/Button";
import classes from './ConfirmationDialog.module.css';

export default function ConfirmationDialog(props) {
    return (
        <>
            <div className={classes.outerDiv}>
                <div>
                    <h3 className={classes.content}>{props.content}</h3>
                    <p style={{ textAlign: 'center' }}> Are you sure ?</p>
                    <div className={classes.btnContainer}>
                        <div className={classes.btn}>
                            <Button delete={true} content="Yes" enable={true} onClick={props.delete} />
                        </div>
                        <div className={classes.btn}>
                            <Button enable={true} content='No' onClick={props.close} />
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}