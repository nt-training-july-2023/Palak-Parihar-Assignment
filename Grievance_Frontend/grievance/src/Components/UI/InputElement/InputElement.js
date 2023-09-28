import classes from './Input.module.css';

function InputElement(props) {
    let inputElement = null;
    const inputClasses = [classes.InputElement];

    if (props.invalid && props.shouldValidate && props.touched) {
        inputClasses.push(classes.Invalid);
    }
    const fetchOptions = (options) => {
        const selectOptions = [];
        Object.keys(options).forEach(key => {
            let id = options[key].departmentId;
            selectOptions.push(
                <option className={inputClasses} key={options[key].departmentId} value={id}>
                    {options[key].departmentName}
                </option>
            )
        })
        return selectOptions
    }

    switch (props.elementType) {
        case ('input'):
            inputElement = <>
                <div style={{ width: '100%' }}>
                    <h3 style={{ 'text-align': 'left' }}>{props.headLabel}</h3>
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
                    {...props.elementConfig}
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
                <select className={classes.InputElement} onChange={props.changed} >
                    <option value='' selected disabled hidden>{props.default === undefined? 'Choose here' : props.default}</option>
                    {props.options.map(option => (
                        <option key={option} value={option === 'ALL TICKETS' ? '' : option}>
                            {option}
                        </option>
                    ))}
                </select>
            </>)
            break;

        case ('department'):
            inputElement = (<>
                <select className={inputClasses.join(' ')} onChange={props.changed}>
                    <option className={classes.OptionElement} value="" selected disabled hidden>Choose here</option>
                    {fetchOptions(props.options)}
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
                {console.log(props.label)}
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