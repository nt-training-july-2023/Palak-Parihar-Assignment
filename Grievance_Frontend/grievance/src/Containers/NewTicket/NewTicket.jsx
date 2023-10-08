import { useEffect, useState } from "react";
import InputElement from "../../Components/UI/InputElement/InputElement";
import { useNavigate } from "react-router";
import { GENERATE_NEW_TICKET } from "../../Service/TicketServices";
import { FETCH_ALL_DEPARTMENTS } from "../../Service/DepartmentService";
import Modal from "../../Components/UI/Modal/Modal";
import { headers } from "../../API/Headers";
import { inputValidity } from "../../Validation/Validation";
import Form from "../../Components/Form/Form";
import { LIST_TICKETS_PATH } from "../../API/PathConstant";

export default function NewTicket(props) {

    let ticketTypeOptions = [
        {
            value: 'GRIEVANCE',
            option: 'Grievance'
        }, {
            value: 'FEEDBACK',
            option: 'Feedback'
        }
    ]
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
                selectValue: 'value',
                selectOption: 'option',
                value: '',
                error: '',
                valid: false,
                label: 'Ticket Type'
            },
            title: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Title'
                },
                options: null,
                value: '',
                validation: {
                    required: true,
                    isUserName: true,
                    minLength: 1,
                    maxLength: 50
                },
                error: '',
                valid: false,
                touched: false,
                label: "Title"
            },
            description: {
                elementType: 'textarea',
                elementConfig: {
                    type: 'email',
                    placeholder: 'Ticket description'
                },
                options: null,
                value: '',
                validation: {
                    required: true,
                    maxLength: 225,
                },
                error: '',
                valid: false,
                touched: false,
                label: "Description"
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
            },
            status: {
                elementType: 'input',
                elementConfig: {
                    type: 'input',
                    placeholder: 'Open'
                },
                value: 'OPEN',
                valid: true,
                disabled: true,
                label: "Status"
            },

        }
    }

    const [controls, setControls] = useState(cont.controls);
    const navigate = useNavigate()
    const [userValues, setUserValues] = useState()
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
                        selectOption={formElement.config.selectOption}
                        selectValue={formElement.config.selectValue}
                        disabled={formElement.config.disabled}
                        error={formElement.config.error}
                        label={formElement.config.label}
                    />
                </div>
            </div>
        </>)
    })

    const checkValidity = (value, rules) => inputValidity(value, rules)

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

        let count = 0;
        for (let key in controls) {
            if (!controls[key].valid) {
                console.log(controls[key].valid)
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
        let ticketValues = {
            title: controls.title.value,
            department: {
                departmentId: Number(controls.department.value),
                departmentName: ''
            },
            description: controls.description.value,
            status: controls.status.value.toUpperCase(),
            ticketType: controls.ticketType.value.toUpperCase(),
            employee: {
                employeeId: userValues.employeeId
            }
        }

        GENERATE_NEW_TICKET(ticketValues).then(res => {
            setModal(() => <Modal message={res.data.message} onClick={closeModal} />)
            console.log(res.data)
            setTimeout(() => {
                navigate(LIST_TICKETS_PATH)
            }, 2000)
        }).catch(err => {
            console.log(err)
            setModal(() => <Modal message={err.data.message} onClick={closeModal} />)
        })
    }


    const closeModal = () => {
        setModal(() => <></>)
    }

    useEffect(() => {
        console.log(userValues)
        if (headers() == null) {
            navigate('/logout')
            return
        } else {
            let values = headers()
            setUserValues(values)
            console.log(values.firstTimeUser)
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
    }, [])

    let containerCss = {
        display: 'flex',
        'justify-content': 'center',
        'align-items': 'center',
        width: '60%'
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
                            heading="Raise A Ticket" />
                    </div>
                </div>
            </div>
        </>
    )
}