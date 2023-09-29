import classes from './Button.module.css';

export default function Button(props) {
    let inputClasses = '';

    if (props.enable) {
        inputClasses = classes.btnContainer
    }

    if(props.delete){
        inputClasses = classes.deleteBtn
    }

    if(props.delete){
        return(
            <div className={classes.btnDiv}>
            <button
                className={inputClasses}
                onClick={props.onClick}
                disabled={!props.enable} >
                Delete
            </button>
        </div>
        )
    }

    return (
        <div className={classes.btnDiv}>
            <button
                className={inputClasses}
                onClick={props.onClick}
                disabled={!props.enable} >
                {props.content}
            </button>
        </div>
    )
}