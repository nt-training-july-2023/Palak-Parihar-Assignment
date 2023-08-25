import './Input.css';

function InputElement(props){
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
                type = {props.elementConfig.type}
                {...props.elementConfig}
                value={props.value}
                onChange={props.changed} 
                required/>;
            break;

        case ('textarea'):
            inputElement = <textarea
                className={inputClasses.join(' ')}
                {...props.elementConfig}
                value={props.value}
                onChange={props.changed} 
                required/>;
            break;
    }

    return(
        <>
        <div className="Input">
            <label className="classes.Label">{props.label}</label>
            {inputElement}
        </div>
        </>
    )

}

export default InputElement;