import "./Login.css";
import '../../Dashboard/Dashboard'
import { useState } from "react";
import { useNavigate } from 'react-router-dom'
import InputElement from "../../Components/UI/InputElement/InputElement";
import Modal from "../../Components/UI/Modal/Modal";
import Button from "../../Components/UI/Button/Button";
import { LOGIN_USER } from "../../Service/EmployeeServices";
import Spinner from "../../Components/UI/Spinner/Spinner";

export default function Login({ callback }) {
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
                    minLength: 6,
                    isPassword:true
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
            if (!isValid) {
                setMessage("Invalid email domain")
            }

        }

        if (rules.isPassword) {
            const pattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,20}$/;
            isValid = pattern.test(value) && isValid
            if(!isValid){
                setMessage("Password doesn't match the requirements")
            }
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

        if (!(controls.email.valid && controls.password.valid)) {
            setMessage("Credentials doesn't match the requirements")
            return
        }

        var values = {
            email: controls.email.value,
            password: btoa(controls.password.value)
        }


        const response = LOGIN_USER(values)
            .then(res => {
                setModal(() => <Modal component={Spinner} />)
                console.log(values)
                let userValues = {
                    email: values.email,
                    password: values.password,
                    fullName: res.data.fullName,
                    userType: res.data.userType,
                    firstTimeUser: res.data.firstTimeUser,
                    department: res.data.department,
                    isAuthenticated: true
                }
                sessionStorage.setItem('userDetails', JSON.stringify(userValues));
                if(userValues.firstTimeUser){
                    setTimeout(() => {
                        navigate("/changePassword")
                    }, 1000);
                    return res.data;
                }
                setTimeout(() => {
                    navigate("/listAllTickets")
                }, 1000);
                return res.data;
            }).catch(err => {
                console.log(err)
                setModal(() => <Modal message={err.data.response.data} onClick={closeModal} />)
                return err.data.response.data;
            })


    }

    const closeModal = (e) => {
        setModal(() => <></>)
    }

    return (
        <>
            <div className="modal-container">
                {modal}
            </div>
            <h1 className="login-heading"><p>Grievance Management System</p></h1>
            <div className="container">
                <div className="main_body">
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