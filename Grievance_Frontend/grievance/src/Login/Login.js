import axios from "axios";
import "./Login.css";
import '../InputElement/InputElement';
import '../Dashboard/Dashboard'
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom'
import InputElement from "../InputElement/InputElement";
import Modal from "../Modal/Modal";
import Button from "../Button/Button";

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
            const pattern = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;
            isValid = pattern.test(value) && isValid
            const str = "@nucleusteq.com"
            if (isValid && value.indexOf("@nucleusteq.com", value.length - "@nucleusteq.com".length) !== -1) {
                //VALID
                isValid = true
            } else {
                isValid = false
            }
            setMessage("Invalid email domain")
        }

        if (rules.isNumeric) {
            const pattern = /^\d+$/;
            isValid = pattern.test(value) && isValid
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
        console.log("submit : ")
        console.log(controls)

        if (!(controls.email.valid && controls.password.valid)) {
            // setMessage("Credentials doesn't match the requirements")
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
            console.log("res " + res.data);
            navigate("/dashboard")
        }).catch(e => {
            console.log(e.response.data)
            var err = e.response.data;
            // alert(err)
            setModal(() => <Modal message={err} onClick = {closeModal}/>)
        })
    }

    const closeModal = (e) => {
        console.log('hello')
        setModal(() => <></>)
    }

    const navigate = useNavigate();

    let baseUrl = 'http://localhost:8080/';

    useEffect(() => {
        axios({
            url: (baseUrl + 'list'),
            method: 'GET',
            mode: 'CORS'
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
                        <Button type="submit" value="submit" />
                    </form>
                </div>
            </div>

        </>
    )
}