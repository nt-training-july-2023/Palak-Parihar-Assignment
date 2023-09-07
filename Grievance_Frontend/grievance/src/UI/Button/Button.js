import classes from './Button.module.css';

export default function Button(props) {
    const inputClasses = [classes.btnContainer]

    if (props.disabled) {
        inputClasses.join(classes.disabledBtn)
    }
    return (
        <>
            <div >
                <input
                    className={inputClasses}
                    type={props.type}
                    value={props.content}
                    onClick={props.onClick}
                    disabled={props.disableButton} />
            </div>
        </>
    )
}