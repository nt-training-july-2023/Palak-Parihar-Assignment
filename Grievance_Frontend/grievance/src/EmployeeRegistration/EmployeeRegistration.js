import { useState } from 'react';
import './EmployeeRegistration.css';
import InputElement from '../UI/InputElement/InputElement';
import Button from '../UI/Button/Button';


export default function EmployeeRegistration(props) {

    let userTypeOptions = ['Admin', 'Employee'];
    let departmentOptions = ['HR', 'Finance', 'Marketing', 'Engineering']
    const [message, setMessage] = useState();

    let cont = {
        isValid: false,
        submit: false,
        controls: {
            name: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Name'
                },
                value: '',
                validation: {
                    required: true,
                    isUserName: true
                },
                options: null,
                valid: false,
                touched: false,
                label: "Full Name"
            },
            email: {
                elementType: 'input',
                elementConfig: {
                    type: 'email',
                    placeholder: 'UserName'
                },
                value: '',
                validation: {
                    required: true,
                    isEmail: true
                },
                options: null,
                valid: false,
                touched: false,
                label: "Email"
            },
            password: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'Password'
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 6,
                    isPassword:true
                },
                options: null,
                valid: false,
                touched: false,
                label: "Password"
            },
            userType: {
                elementType: 'select',
                elementConfig: {
                    type: 'select',
                    placeholder: ''
                },
                value: '',
                validation: {
                    required: true,
                },
                options: userTypeOptions,
                valid: false,
                touched: false,
                label: 'User Type'
            },
            department: {
                elementType: 'select',
                elementConfig: {
                    type: 'select',
                    placeholder: ''
                },
                value: '',
                validation: {
                    required: true
                },
                options: departmentOptions,
                valid: false,
                label: 'Department'
            }
        }
    }

    const [controls, setControls] = useState(cont.controls);

    const formElementsArray = [];
    for (let key in controls) {
        formElementsArray.push({
            id: key,
            config: controls[key]
        })
    }

    const completeForm = formElementsArray.map(formElement => {
        return (<>
            {console.log(formElement.label)}
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
        console.log("checkvalidity")
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

        if (rules.isEmail) {
            const pattern = /^[A-Za-z0-9+_.-]+@nucleusteq.com(.+)$/;
            isValid = pattern.test(value) && isValid
            if (isValid && value.indexOf("@nucleusteq.com", value.length - "@nucleusteq.com".length) !== -1) {
                //VALID
                isValid = true
            } else {
                isValid = false
            }
            setMessage("Invalid email domain")
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
        // console.log(controls)
    }


    const submithandler = (e) => {
        e.preventDefault();
        console.log(controls)
    }

    return (
        <>
            <div className="reg-container">
                <h3 className='heading'>Employee Registration</h3>
                <div className="reg-content">
                    <form onSubmit={submithandler}>
                        {completeForm}
                        <Button type='submit' content='submit' />
                        {message}
                    </form>
                </div>
            </div>
        </>
    )
}