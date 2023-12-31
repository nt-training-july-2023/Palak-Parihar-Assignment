import { useEffect, useState } from 'react';
import InputElement from '../../Components/UI/InputElement/InputElement';
import { FETCH_ALL_DEPARTMENTS } from '../../Service/DepartmentService';
import { SAVE_NEW_EMPLOYEE } from '../../Service/EmployeeServices';
import Modal from '../../Components/UI/Modal/Modal';
import { useNavigate } from 'react-router';
import Form from '../../Components/Form/Form';
import { inputValidity } from '../../Validation/Validation';


export default function NewEmployee(props) {
    let userTypeOptions = [
        {
            value: 'ADMIN',
            option: 'Admin'
        }, {
            value: 'MEMBER',
            option: 'Member'
        }
    ]

    const [modal, setModal] = useState();
    const [flag, setFlag] = useState(false);
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
                    isUserName: true,
                    textOnly: true,
                    minLength: 3,
                    maxLength: 100
                },
                error: '',
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
                    isEmail: true,
                    maxLength: 100
                },
                error: '',
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
                    maxLength: 20,
                    isPassword: true
                },
                error: '',
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
                error: '',
                selectValue: 'value',
                selectOption: 'option',
                valid: false,
                touched: false,
                label: 'User Type'
            },
            department: {
                elementType: 'select',
                elementConfig: {
                    type: 'select',
                    placeholder: 'department'
                },
                value: '',
                validation: {
                    required: true
                },
                error: '',
                options: [],
                selectValue: 'departmentId',
                selectOption: 'departmentName',
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

        if (localStorage.getItem('userDetails') === null) {
            navigate('/logout')
            return
        } else {
            let values = JSON.parse(localStorage.getItem('userDetails'))
            if (values.firstTimeUser) {
                navigate('/changePassword')
                return
            }
        }

        FETCH_ALL_DEPARTMENTS()
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
    }, [flag])

    const completeForm = formElementsArray.map(formElement => {
        return (
            <InputElement
                key={formElement.id}
                elementType={formElement.config.elementType}
                elementConfig={formElement.config.elementConfig}
                options={formElement.config.options}
                value={formElement.config.value}
                invalid={!formElement.config.valid}
                shouldValidate={formElement.config.validation}
                touched={formElement.config.touched}
                error={formElement.config.error}
                label={formElement.config.label}
                selectOption={formElement.config.selectOption}
                selectValue={formElement.config.selectValue}
                changed={(e) => inputChangeHandler(e, formElement.id)}
            />
        )
    })

    const checkValidity = (value, rules) => inputValidity(value, rules)

    const inputChangeHandler = (e, controlName) => {
        const message = checkValidity(e.target.value, controls[controlName].validation);
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

        let count = 0;
        for (let key in controls) {
            if (!controls[key].valid) {
                count += 1
            }
        }
        if (count > 0) {
            setModal(() => <Modal message="Mandatory fields are either invalid or empty" onClick={closeModal} />)
            setTimeout(() => {
                setModal(<></>)
            }, 2000)
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

        SAVE_NEW_EMPLOYEE(data).then(res => {
            setModal(() => <Modal message={res.data.message} onClick={clearForm} />)
            return res.data;
        }).catch(err => {
            setModal(() => <Modal message={err.data.message} onClick={closeModal} />)
            return err.data;
        })
    }

    const clearForm = () => {
        const initialControls = { ...cont.controls };
    
        for (let key in initialControls) {
            initialControls[key].value = '';
            initialControls[key].error = '';
            initialControls[key].valid = false;
            initialControls[key].touched = false;
        }
    
        setControls(initialControls);
        setFlag(!flag);
        setModal(<></>);
    }

    const closeModal = () => {
        setModal(() => <></>)
    }
    let containerCss = {
        display: 'flex',
        'justifyContent': 'center',
        'alignItems': 'center',
        width: '55%'
    }

    let outerContainer = {
        width: '100%',
        display: 'flex',
        justifyContent: 'center'
    }

    return (
        <>
            {modal}
            <div className="container">
                <div style={outerContainer}>
                    <div style={containerCss}>
                        <Form
                            content={completeForm}
                            onSubmit={submithandler}
                            enable={true}
                            heading="Register Employee" />
                    </div>
                </div>
            </div>
        </>
    )
}