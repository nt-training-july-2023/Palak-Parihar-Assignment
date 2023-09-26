import classes from './Button.module.css';

export default function Button(props) {
    let inputClasses = '';

    if (props.enable) {
        inputClasses = classes.btnContainer
    }

    return (
        <div className={classes.btnDiv}>
            <button
                className={inputClasses}
                type={props.type}
                onClick={props.onClick}
                disabled={!props.enable} >
                {props.content}
            </button>
        </div>
    )
}