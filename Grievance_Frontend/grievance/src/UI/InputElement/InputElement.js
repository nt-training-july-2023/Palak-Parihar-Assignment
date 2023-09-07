import classes from './Input.module.css';

function InputElement(props) {
    let inputElement = null;
    const inputClasses = [classes.InputElement];

    if (props.invalid && props.shouldValidate && props.touched) {
        inputClasses.push(classes.Invalid);
    }

    // console.log(props)
    switch (props.elementType) {
        case ('input'):
            inputElement = <input
                className={inputClasses.join(' ')}
                type={props.elementConfig.type}
                {...props.elementConfig}
                value={props.value}
                onChange={props.changed}
                disabled={props.disabled}
                required />;
            break;

        case ('textarea'):
            inputElement = <textarea
                className={inputClasses.join(' ')}
                {...props.elementConfig}
                value={props.value}
                onChange={props.changed}
                disabled={props.disabled}
                required />;
            break;

        case ('select'):
            inputElement = (<>
                <select className={inputClasses.join(' ')} onChange={props.changed} {...props.shouldValidate} >
                    <option value="" selected disabled hidden>Choose here</option>
                    {props.options.map(option => (
                        <option className={inputClasses} key={option} value={option}>
                            {option}
                        </option>
                    ))}
                </select>
            </>)
            break;
        default:
            inputElement = (
                <></>
            )
    }

    return (
        <>
            <div className="Input">
                <p className={classes.Label}>{props.label}</p>
                {inputElement}
            </div>
        </>
    )

}

export default InputElement;