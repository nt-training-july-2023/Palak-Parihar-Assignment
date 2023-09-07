import axios from "axios";
import "./Login.css";
import '../Dashboard/Dashboard'
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom'
import InputElement from "../UI/InputElement/InputElement";
import Modal from "../UI/Modal/Modal";
import Button from "../UI/Button/Button";

export default function Login() {
    let cont = {
        isValid: false,
        submit: false,
        controls: {
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
                touched: false
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
                touched: false
            }
        }
    }

    const [disableButton, setDisableButton] = useState(cont.isValid);
    const [controls, setControls] = useState(cont.controls);
    const [message, setMessage] = useState();
    const [modal, setModal] = useState();
    const navigate = useNavigate();

    let baseUrl = 'http://localhost:8080/';

    const formElementsArray = [];
    for (let key in controls) {
        formElementsArray.push({
            id: key,
            config: controls[key]
        })
    }

    const completeForm = formElementsArray.map(formElement => (
        <>
            {console.log(formElement)}
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
        </>
    ))


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
            const pattern = /^[A-Za-z0-9+_.-]+@+nucleusteq.com$/;
            console.log(value.endsWith("@nucleusteq.com") + " " + pattern.test(value))
            isValid = pattern.test(value) && isValid
            if (isValid) {
                setMessage("Invalid email domain")
            }

        }

        if (rules.isPassword) {
            const pattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
            isValid = pattern.test(value) && isValid
            setMessage("Password doesn't match the requirements")
        }
        if (isValid) {
            setMessage('')
            setDisableButton(false)
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
        console.log("submit : ")
        console.log(controls)

        if (!(controls.email.valid && controls.password.valid)) {
            setMessage("Credentials doesn't match the requirements")
            return
        }
        var values = {
            email: controls.email.value,
            password: controls.password.value
        }

        axios({
            url: (baseUrl + 'login'),
            method: 'POST',
            mode: 'CORS',
            data: values
        }).then(res => {
            console.log(res);
            navigate("/dashboard")
        }).catch(e => {
            console.log(e.response.data.message)
            var err = e.response.data.message
            // alert(err)
            setModal(() => <Modal message={err} onClick={closeModal} />)
        })
    }

    const closeModal = (e) => {
        console.log('hello')
        setModal(() => <></>)
    }



    useEffect(() => {

        let headersData = {
            email: "ayushi@nucleusteq.com",
            password: "Ayushi#124",
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': '*',
            'Access-Control-Allow-Credentials': true
        }
        axios({
            url: (baseUrl + 'listAllEmployees'),
            method: 'GET',
            headers:headersData,
            // withCredentials:true
        }).then(res => {
            console.log(res.data)
        }).catch(e => {
            console.log(e)
        })
    })

    return (
        <>
            <div className="modal-container">
                {modal}
            </div>
            <div className="container">
                <h1 className="login-heading"><p>Grievance Management System</p></h1>
                <div className="main_body">
                    {/* <h3 className="heading">Login!</h3> */}
                    <form onSubmit={e => submithandler(e)}>
                        {completeForm}
                        <p className="message">
                            {message}
                        </p>
                        <Button type="submit" value="submit" disabled={disableButton} />
                    </form>
                </div>
            </div>

        </>
    )
}