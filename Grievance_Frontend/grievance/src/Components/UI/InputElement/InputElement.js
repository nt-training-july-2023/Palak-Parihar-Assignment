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
                placeholder={props.placeholder}
                required />;
            break;

        case ('select'):
            inputElement = (<>
                <select className={classes.InputElement} onChange={props.changed} {...props.shouldValidate} >
                    <option value='' selected disabled hidden>Choose here</option>
                    {props.options.map(option => (
                        <option key={option} value={option}>
                            {option}
                        </option>
                    ))}
                </select>
            </>)
            break;

        case ('department'):
            inputElement = (<>
                <select className={inputClasses.join(' ')} onChange={props.changed} {...props.shouldValidate} >
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
            <div className="Input">
                <p className={classes.Label}>{props.label}</p>
                {inputElement}
            </div>
        </>
    )

}

export default InputElement;