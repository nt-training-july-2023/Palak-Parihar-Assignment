import { useEffect, useState } from "react"
import InputElement from "../../Components/UI/InputElement/InputElement";
import { CHANGE_USER_PASSWORD } from "../../Service/EmployeeServices";
import Modal from "../../Components/UI/Modal/Modal";
import { useNavigate } from "react-router";
import Form from "../../Components/Form/Form";
import { inputValidity } from "../../Validation/Validation";
import { LIST_TICKETS_PATH } from "../../API/PathConstant";


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
                error: '',
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
                error: '',
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
    let containerCss = {
        display: 'flex',
        'justify-content': 'center',
        'align-items': 'center',
        width : '57%'
    }
    const [controls, setControls] = useState(cont.controls);
    const [message, setMessage] = useState('');
    const [modal, setModal] = useState();
    const [enableBtn, setEnableButton] = useState(false)
    const navigate = useNavigate()

    const formElementsArray = [];
    for (let key in controls) {
        formElementsArray.push({
            id: key,
            config: controls[key]
        })
    }

    useEffect(() => {
        if (localStorage.getItem('userDetails') === null) {
            navigate('/logout')
            return
        }
        setMessage('')

        let count = 0;
        for (let key in controls) {
            console.log(controls[key].valid)
            if (!controls[key].valid) {
                count += 1;
            }
        }
        console.log(count)
        if (count > 0) {
            setEnableButton(false)
        } else {
            setEnableButton(true)
        }
    }, [controls])


    const completeForm = formElementsArray.map(formElement => {
        return (<>
            <div className='form-container'>
                <div className='input-content'>
                    <InputElement
                        key={formElement.id}
                        elementType={formElement.config.elementType}
                        elementConfig={formElement.config.elementConfig}
                        options={formElement.config.options}
                        value={formElement.config.value}
                        invalid={!formElement.config.valid}
                        error={formElement.config.error}
                        shouldValidate={formElement.config.validation}
                        touched={formElement.config.touched}
                        label={formElement.config.label}
                        changed={(e) => inputChangeHandler(e, formElement.id)}
                    />
                </div>
            </div>
        </>)
    })

    const checkValidity = (value, rules) => inputValidity(value, rules)

    const inputChangeHandler = (e, controlName) => {

        const message = checkValidity(e.target.value, controls[controlName].validation)
        console.log(message)
        const updatedControls = {
            ...controls,
            [controlName]: {
                ...controls[controlName],
                value: e.target.value,
                error: message,
                valid: message === '',
                touched: true
            }
        }
        setControls(updatedControls)
    }

    const submithandler = (e) => {
        e.preventDefault();


        if (controls.confirmPassword.value !== controls.newPassword.value) {
            setMessage('New Password and Confirm Password values are mismatched')
            return;
        }

        let values = {
            oldPassword: btoa(controls.oldPassword.value),
            newPassword: btoa(controls.newPassword.value)
        }
        console.log(values)
        const response = CHANGE_USER_PASSWORD(values)
            .then(res => {
                setModal(() => <Modal message={res.data.message} onClick={closeModal} />)
                let values = JSON.parse(localStorage.getItem('userDetails'))
                const userDetails = {
                    ...values,
                    password : btoa(values.newPassword),
                    firstTimeUser: false,
                    isLoggedIn : true
                }
                localStorage.setItem('userDetails', JSON.stringify(userDetails));
                setTimeout(() => {
                    navigate(LIST_TICKETS_PATH)
                }, 1000);
                return res.data
            })
            .catch(err => {
                console.log(err)
                setModal(() => <Modal message={err.data.message} onClick={closeModal} />)
                return err.data
            })
        console.log(response)
    }

    const closeModal = () => {
        setModal(() => <></>)
    }



    return (
        <>
            {modal}
            <div className="reg-container">
                <div className="container">
                    <div style={containerCss} >
                        <Form
                            content={completeForm}
                            heading='Change Password'
                            onSubmit={submithandler}
                            enable={enableBtn}
                            message={message} />
                    </div>
                </div>
            </div>
        </>
    )
}