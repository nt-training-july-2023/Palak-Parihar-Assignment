import "./Login.css";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom'
import InputElement from "../../Components/UI/InputElement/InputElement";
import Modal from "../../Components/UI/Modal/Modal";
import { LOGIN_USER } from "../../Service/EmployeeServices";
import Spinner from "../../Components/UI/Spinner/Spinner";
import { inputValidity } from "../../Validation/Validation";
import Form from "../../Components/Form/Form";
import {CHANGE_PASSWORD_PATH, LIST_TICKETS_PATH } from "../../API/PathConstant";

export default function Login() {
    let cont = {
        isValid: false,
        submit: false,
        controls: {
            email: {
                elementType: 'input',
                elementConfig: {
                    type: 'email',
                    placeholder: 'Your Email'
                },
                value: '',
                label: 'Email',
                validation: {
                    required: true,
                    isEmail: true
                },
                error: '',
                valid: false,
                touched: false
            },
            password: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'Password'
                },
                value: '',
                label: 'Password',
                validation: {
                    required: true,
                    minLength: 6,
                    isPassword: true
                },
                error: '',
                valid: false,
                touched: false
            }
        }
    }

    const [enableButton, setEnableButton] = useState(false);
    const [controls, setControls] = useState(cont.controls);
    const [modal, setModal] = useState();
    const navigate = useNavigate();

    const formElementsArray = [];
    for (let key in controls) {
        formElementsArray.push({
            id: key,
            config: controls[key]
        })
    }

    const completeForm = formElementsArray.map(formElement => (
        <>
            <InputElement
                key={formElement.id}
                elementType={formElement.config.elementType}
                elementConfig={formElement.config.elementConfig}
                value={formElement.config.value}
                label={formElement.config.label}
                invalid={formElement.config.valid}
                error={formElement.config.error}
                shouldValidate={formElement.config.validation}
                touched={formElement.config.touched}
                changed={(e) => inputChangeHandler(e, formElement.id)}
            />
        </>
    ))

    useEffect(() => {

        const userDetails = JSON.parse(localStorage.getItem('userDetails'))
        console.log(userDetails)

        if (userDetails && userDetails.isLoggedIn) {
            setModal(<Modal component={<Spinner />} />)
            setTimeout(() => {
                navigate(LIST_TICKETS_PATH)
            }, 1000)
        }

        if (controls.email.valid && controls.password.valid) {
            setEnableButton(true)
        }else{
            setEnableButton(false)
        }
    }, [controls])

    const checkValidity = (value, rules) => inputValidity(value, rules);

    const inputChangeHandler = (e, controlName) => {

        const message = checkValidity(e.target.value, controls[controlName].validation)
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

        var values = {
            email: controls.email.value,
            password: btoa(controls.password.value)
        }


        LOGIN_USER(values)
            .then(res => {
                setModal(() => <Modal component={<Spinner />} />)
                let userValues = {
                    email: values.email,
                    password: values.password,
                    fullName: res.data.fullName,
                    userType: res.data.userType,
                    firstTimeUser: res.data.firstTimeUser,
                    department: res.data.department,
                    isLoggedIn : !res.data.firstTimeUser,
                }
                
                let userDetails = JSON.stringify(userValues);
                localStorage.setItem('userDetails', userDetails);
                console.log(JSON.parse(localStorage.getItem('userDetails')))
                if (userValues.firstTimeUser) {
                    setTimeout(() => {
                        navigate(CHANGE_PASSWORD_PATH)
                    }, 1000);
                    return res.data;
                }
                setTimeout(() => {
                    navigate(LIST_TICKETS_PATH)
                }, 1000);
                return res.data;
            }).catch(err => {
                console.log(err)
                setModal(() => <Modal message={err?.data?.message} onClick={closeModal} />)
            })


    }

    const closeModal = (e) => {
        setModal(() => <></>)
    }

    let containerCss = {
        display: 'flex',
        'justify-content': 'center',
        'align-items': 'center',
        width: '50%'
    }

    return (
        <>
            {modal}
            <h1 className="login-heading"><p>Grievance Management System</p></h1>
            <div className="container">
                <div style={containerCss}>
                    <Form
                        content={completeForm}
                        onSubmit={submithandler}
                        enable={enableButton}
                        heading="Login" />
                </div>
            </div>
        </>
    )
}