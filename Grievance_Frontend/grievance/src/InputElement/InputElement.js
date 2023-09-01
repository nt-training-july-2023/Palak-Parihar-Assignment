import './Input.css';

function InputElement(props) {
    let inputElement = null;
    const inputClasses = ['InputElement'];

    if (props.invalid && props.shouldValidate && props.touched) {
        inputClasses.push('Invalid');
    }

    console.log(props)
    switch (props.elementType) {
        case ('input'):
            inputElement = <input
                className={inputClasses.join(' ')}
                type={props.elementConfig.type}
                {...props.elementConfig}
                value={props.value}
                onChange={props.changed}
                required />;
            break;

        case ('textarea'):
            inputElement = <textarea
                className={inputClasses.join(' ')}
                {...props.elementConfig}
                value={props.value}
                onChange={props.changed}
                required />;
            break;

        case ('select'):
            inputElement = (<>
                <select className='InputElement' onChange={props.changed} {...props.shouldValidate} >
                    <option value="" selected disabled hidden>Choose here</option>
                    {props.options.map(option => (
                        <option className='InputElement' key={option} value={option}>
                            {option}
                        </option>
                    ))}
                </select>
            </>)
            break;
        case ('disabled'):
            inputElement = <input
                className={inputClasses.join(' ')}
                type={props.elementConfig.type}
                {...props.elementConfig}
                value={props.value}
                onChange={props.changed}
                required disabled />;
            break;
    }

    return (
        <>
            <div className="Input">
                <p className='label-container'>{props.label}</p>
                {inputElement}
            </div>
        </>
    )

}

export default InputElement;