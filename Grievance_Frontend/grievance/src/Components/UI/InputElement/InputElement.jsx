import classes from './Input.module.css';

function InputElement(props) {
    let inputElement = null;
    const inputClasses = [classes.InputElement];

    if (props.invalid && props.shouldValidate && props.touched) {
        inputClasses.push(classes.Invalid);
    }

    switch (props.elementType) {
        case ('input'):
            inputElement = <>
                <div style={{ width: '100%' }}>
                    <input
                        className={inputClasses.join(' ')}
                        type={props.elementConfig.type}
                        {...props.elementConfig}
                        value={props.value}
                        onChange={props.changed}
                        disabled={props.disabled}
                    />
                    <p className={classes.errorMessage}>{props.error}</p>
                </div>
            </>;
            break;

        case ('textarea'):
            inputElement = <div style={{ width: '100%' }}>
                <textarea
                    className={inputClasses.join(' ')}
                    value={props.value}
                    onChange={props.changed}
                    disabled={props.disabled}
                    placeholder={props.placeholder}
                     />
                <p className={classes.errorMessage}>{props.error}</p>
            </div>
            break;

        case ('select'):
            inputElement = (<>
                <select className={classes.InputElement} onChange={props.changed} disabled={props.disabled}>
                    <option value='' selected disabled hidden>{props.default === undefined? 'Choose here' : props.default}</option>
                    {props.options.map(option => (
                        <option key={option[props.selectValue]} value={option[props.selectValue]}>
                            {option[props.selectOption]}
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
            <div className={classes.outerDiv}>
                <div className={props.label !== undefined ? classes.Input : ''}>
                    <p className={props.label !== undefined ? classes.Label : ''}>
                        {props.label}
                        {props.label && <p className={classes.mandatory}>*</p>}
                    </p>
                    {inputElement}
                </div>
            </div>
        </>
    )

}

export default InputElement;