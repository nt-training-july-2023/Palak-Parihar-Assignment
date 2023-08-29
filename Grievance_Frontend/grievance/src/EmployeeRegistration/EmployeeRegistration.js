import { useState } from 'react';
import './EmployeeRegistration.css';
import InputElement from '../InputElement/InputElement';
import Button from '../Button/Button';


export default function EmployeeRegistration(props) {

    let userTypeOptions = ['Admin', 'Employee'];
    let departmentOptions = ['HR', 'Finance', 'Marketing', 'Engineering']
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
                    minLength: 6
                },
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
                value: userTypeOptions,
                validation: {
                    required: true,
                },
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
                value: departmentOptions,
                validation: {
                    required: true
                },
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

    const inputChangeHandler = (e, controlName) => {
        const updatedControls = {
            ...controls,
            [controlName]: {
                ...controls[controlName],
                value: e.target.value,
                // valid: checkValidity(e.target.value, controls[controlName].validation),
                touched: true
            }
        }
        setControls(updatedControls)
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
                    </form>
                </div>
            </div>
        </>
    )
}