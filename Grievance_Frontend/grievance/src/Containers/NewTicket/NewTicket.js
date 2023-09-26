import { useEffect, useState } from "react";
import InputElement from "../../Components/UI/InputElement/InputElement";
import Button from "../../Components/UI/Button/Button";
import { useNavigate } from "react-router";
import { GENERATE_NEW_TICKET } from "../../Service/TicketServices";
import { FETCH_ALL_DEPARTMENTS } from "../../Service/DepartmentService";
import Modal from "../../Components/UI/Modal/Modal";
import { headers } from "../../API/Headers";

export default function NewTicket(props) {

    let ticketTypeOptions = ['GRIEVANCE', 'FEEDBACK']
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
                value: '',
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
                    isUserName: true
                },
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
                    isEmail: true
                },
                valid: false,
                touched: false,
                label: "Description"
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
            },
            status: {
                elementType: 'input',
                elementConfig: {
                    type: 'input',
                    placeholder: 'Open'
                },
                options: null,
                value: 'OPEN',
                validation: {
                    required: true,
                    minLength: 6,
                    disabled: 'disabled'
                },
                valid: false,
                touched: false,
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
                        disabled={formElement.config.disabled}
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
                touched: true
            }
        }
        setControls(updatedControls)
    }


    const submithandler = (e) => {
        e.preventDefault();
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
                email: userValues.email
            }
        }
        console.log(ticketValues)

        GENERATE_NEW_TICKET(ticketValues).then(res => {
            setModal(() => <Modal message="Ticket successfully created" onClick={closeModal} />)
            console.log(res.data)
            return res.data;
        }).catch(err => {
            console.log(err.data)
            // setModal(() => <Modal message={err.data.response.data} onClick={closeModal} />)
            return err.data
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

    return (
        <>
            <div className="modal-container">
                {modal}
            </div>
            <div className="reg-container">
                <h3 className='heading'>Generate New Ticket</h3>
                <div className="reg-content">
                    <form onSubmit={submithandler}>
                        {completeForm}
                        <Button type='submit' content='submit' enable={true}/>
                    </form>
                </div>
            </div>
        </>
    )
}