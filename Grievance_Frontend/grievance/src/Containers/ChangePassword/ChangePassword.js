import { useState } from "react"
import Button from "../../Components/UI/Button/Button";
import InputElement from "../../Components/UI/InputElement/InputElement";


export default function ChangePassword(props) {

    let cont = {
        isValid: false,
        submit: false,
        controls: {
            oldPassword: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'Old Password'
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 6,
                    isPassword: true
                },
                options: null,
                valid: false,
                touched: false,
                label: "Old Password"
            },
            changePassword: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'Change Password'
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 6,
                    isPassword: true
                },
                options: null,
                valid: false,
                touched: false,
                label: "Change Password"
            },
            confirmPassword: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'Confirm Password'
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 6,
                    isPassword: true
                },
                options: null,
                valid: false,
                touched: false,
                label: "Confirm Password"
            }
        }
    }
    const [controls, setControls] = useState(cont.controls);
    const [message, setMessage] = useState();

    const formElementsArray = [];
    for (let key in controls) {
        formElementsArray.push({
            id: key,
            config: controls[key]
        })
    }

    const completeForm = formElementsArray.map(formElement => {
        return (<>
            <div className='form-container'>
                <p className='input-label'>{formElement.config.label}</p>
                <div className='input-content'>
                    <InputElement
                        key={formElement.id}
                        elementType={formElement.config.elementType}
                        elementConfig={formElement.config.elementConfig}
                        options={formElement.config.options}
                        value={formElement.config.value}
                        invalid={!formElement.config.valid}
                        shouldValidate={formElement.config.validation}
                        touched={formElement.config.touched}
                        changed={(e) => inputChangeHandler(e, formElement.id)}
                    />
                </div>
            </div>
        </>)
    })

    const checkValidity = (value, rules) => {
        let isValid = true;
        if (!rules) {
            return true;
        }

        if (rules.required) {
            isValid = value.trim() !== '' && isValid;
        }

        if (rules.minLength) {
            isValid = value.length >= rules.minLength && isValid;
            setMessage("Password doesn't match the min length of 6")
        }

        if (rules.maxLength) {
            isValid = value.length >= rules.minLength && isValid;
            setMessage("Password doesn't match the max length of 15")
        }

        if (rules.isPassword) {
            const pattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
            isValid = pattern.test(value) && isValid
            setMessage("Password doesn't match the requirements")
        }
        if (isValid) {
            setMessage('')
        }
        return isValid;
    }

    const inputChangeHandler = (e, controlName) => {
        const updatedControls = {
            ...controls,
            [controlName]: {
                ...controls[controlName],
                value: e.target.value,
                valid: checkValidity(e.target.value, controls[controlName].validation),
                touched: true
            }
        }
        setControls(updatedControls)
    }

    const submithandler = (e) =>{
        e.preventDefault();
        console.log(controls);
    }

    return (
        <>
            <div className="reg-container">
                <h3 className='heading'>Change Password</h3>
                <div className="reg-content">
                    <form onSubmit={submithandler}>
                        {completeForm}
                        <p className="message">
                            {message}
                        </p>
                        <Button type='submit' content='submit' />
                    </form>
                </div>
            </div>
        </>
    )
}