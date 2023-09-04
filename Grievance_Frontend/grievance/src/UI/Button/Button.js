import classes from './Button.module.css';

export default function Button(props) {
    console.log(props)
    const inputClasses = [classes.btnContainer]

    if(props.disabled){
        inputClasses.join(classes.disabledBtn)
    }
    return (
        <>
            <div >
                <input className={inputClasses} type={props.type} value={props.content} onClick={props.onClick}  />
            </div>
        </>
    )
}