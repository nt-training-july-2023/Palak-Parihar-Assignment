import Button from "../UI/Button/Button";
import classes from './ConfirmationDialog.module.css';

export default function ConfirmationDialog(props) {
    console.log(props)
    return (
        <>
            <div className={classes.outerDiv}>
                <div>
                    <h3 style={{textAlign:'center', marginTop:'10px', marginBottom:'0px'}}>{props.content}</h3>
                         <p style={{textAlign:'center'}}> Are you sure ?</p>
                    <div className={classes.btnContainer}>
                        <div style={{ width: '50%' }}>
                            <Button delete={true} enable={true} onClick={props.delete}/>
                        </div>
                        <div style={{ width: '50%' }}>
                            <Button enable={true} content='No' onClick={props.close}/>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}