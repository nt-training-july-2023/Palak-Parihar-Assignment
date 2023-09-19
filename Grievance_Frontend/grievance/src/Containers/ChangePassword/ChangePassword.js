import { useState } from "react"
import Button from "../../Components/UI/Button/Button";
import InputElement from "../../Components/UI/InputElement/InputElement";
import { CHANGE_USER_PASSWORD } from "../../Service/EmployeeServices";
import Modal from "../../Components/UI/Modal/Modal";


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
                valid: false,
                touched: false,
                label: "Old Password"
            },
            newPassword: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'New Password'
                },
                value: '',
                validation: {
                    required: true,
                    minLength: 6,
                    isPassword: true
                },
                valid: false,
                touched: false,
                label: "New Password"
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
                valid: false,
                touched: false,
                label: "Confirm Password"
            }
        }
    }
    const [controls, setControls] = useState(cont.controls);
    const [message, setMessage] = useState();
    const [modal, setModal] = useState();

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

    const submithandler = (e) => {
        e.preventDefault();

        let values = {
            oldPassword: controls.oldPassword.value,
            newPassword: controls.newPassword.value
        }

        if (!controls.oldPassword.valid || !controls.newPassword.valid || !controls.confirmPassword.valid) {
            setMessage('Password must match requirements')
            return;
        }

        if (controls.confirmPassword.value !== controls.newPassword.value) {
            setMessage('New Password and Confirm Password values are mismatched')
            return;
        }

        console.log(values)
        const response = CHANGE_USER_PASSWORD(values)
            .then(res => {
                return res.data
            })
            .catch(err => {
                setModal(() => <Modal message={err.data.response.data} onClick={closeModal} />)
                return err.data
            })
        console.log(response)
    }

    const closeModal = () => {
        setModal(() => <></>)
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