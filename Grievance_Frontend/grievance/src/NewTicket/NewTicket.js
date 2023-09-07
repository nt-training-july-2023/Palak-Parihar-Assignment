import { useState } from "react";
import InputElement from "../UI/InputElement/InputElement";
import Button from "../UI/Button/Button";

export default function NewTicket(props) {

    let departmentOptions = ['HR', 'Finance', 'Marketing', 'Engineering']
    let ticketTypeOptions = ['Grievance', 'Feedback']
    let cont = {
        isValid: false,
        submit: false,
        controls: {
            ticketType: {
                elementType: 'select',
                elementConfig: {
                    type: 'select',
                    placeholder: ''
                },
                options: ticketTypeOptions,
                validation: {
                    required: true
                },
                value:'',
                valid: false,
                label: 'Ticket Type'
            },
            title: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Title'
                },
                options:null,
                value: '',
                validation: {
                    required: true,
                    isUserName: true
                },
                valid: false,
                touched: false,
                label: "Full Name"
            },
            description: {
                elementType: 'textarea',
                elementConfig: {
                    type: 'email',
                    placeholder: 'Ticket description'
                },
                options:null,
                value: '',
                validation: {
                    required: true,
                    isEmail: true
                },
                valid: false,
                touched: false,
                label: "Description"
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
            },
            status: {
                elementType: 'input',
                elementConfig: {
                    type: 'input',
                    placeholder: 'Open'
                },
                options:null,
                value: 'Open',
                validation: {
                    required: true,
                    minLength: 6,
                    disabled:'disabled'
                },
                valid: false,
                touched: false,
                disabled : true,
                label: "Status"
            },
            
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
                        options={formElement.config.options}
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
            <h3 className='heading'>Generate New Ticket</h3>
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