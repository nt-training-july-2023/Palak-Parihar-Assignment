import { useEffect, useState } from 'react';
import './EmployeeRegistration.css';
import InputElement from '../../Components/UI/InputElement/InputElement';
import Button from '../../Components/UI/Button/Button';
import { FETCH_ALL_DEPARTMENTS } from '../../Service/DepartmentService';
import { SAVE_NEW_EMPLOYEE } from '../../Service/EmployeeServices';
import Modal from '../../Components/UI/Modal/Modal';
import { useNavigate } from 'react-router';


export default function EmployeeRegistration(props) {
    let userTypeOptions = ['ADMIN', 'MEMBER']

    const [message, setMessage] = useState();

    const [modal, setModal] = useState();
    const navigate = useNavigate()

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
                    isPassword: true
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
                    placeholder: 'userType'
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
                elementType: 'department',
                elementConfig: {
                    type: 'select',
                    placeholder: 'department'
                },
                value: '',
                validation: {
                    required: true
                },
                options: [],
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

    useEffect(() => {
        
        if(sessionStorage.getItem('userDetails') === null){
            navigate('/logout')
            return
        }else{ 
            let values = JSON.parse(sessionStorage.getItem('userDetails'))
            console.log(values.firstTimeUser)
            if(values.firstTimeUser){
                navigate('/changePassword')
                return
            }
        }

        const departmentNames = async () => FETCH_ALL_DEPARTMENTS()
            .then(response => {
                let updatedControls = {
                    ...controls,
                    department: {
                        ...controls.department,
                        options: response.data
                    }
                }
                setControls(updatedControls);
                return response.data;
            }).catch(error => {
                return error.data
            })


        departmentNames();
    }, [])

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
            const pattern = /^[A-Za-z0-9._%+-]+@nucleusteq\.com$/;
            isValid = pattern.test(value) && isValid
            if (isValid) {
                //VALID
                isValid = true
            } else {
                isValid = false
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

        let data = {
            email: controls.email.value,
            fullName: controls.name.value,
            password: btoa(controls.password.value),
            userType: controls.userType.value,
            firstTimeUser: true,
            department: {
                departmentId: controls.department.value
            }
        }
        console.log(data)

        SAVE_NEW_EMPLOYEE(data).then(res => {
                console.log(res.data)
                setModal(() => <Modal message="Employee successfully created" onClick={closeModal} />)
                return res.data;
            }).catch(err => {
                console.log(err.data.response.data.message)
                setModal(() => <Modal message={err.data.response.data.message} onClick={closeModal} />)
                return err.data;
            })
    }

    const closeModal = () => {
        setModal(() => <></>)
    }

    return (
        <>
            <div className="modal-container">
                {modal}
            </div>
            <div className="reg-container">
                <h3 className='heading'>Employee Registration</h3>
                <div className="reg-content">
                    <form onSubmit={submithandler}>
                        {completeForm}
                        <p className="message">
                            {message}
                        </p>
                        <Button type='submit' content='submit' enable={true}/>
                    </form>
                </div>
            </div>
        </>
    )
}