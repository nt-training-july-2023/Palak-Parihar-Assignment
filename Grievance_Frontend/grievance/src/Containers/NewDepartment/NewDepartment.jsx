import { useEffect, useState } from 'react'
import Form from '../../Components/Form/Form';
import classes from './NewDepartment.module.css'
import InputElement from '../../Components/UI/InputElement/InputElement';
import { GENERATE_NEW_DEPARTMENT } from '../../Service/DepartmentService';
import Modal from '../../Components/UI/Modal/Modal';
import { inputValidity } from '../../Validation/Validation';

export default function NewDepartment(props) {

    const departmentName = {
        elementType: 'input',
        elementConfig: {
            type: 'text',
            placeholder: 'Department Name'
        },
        value: '',
        validation: {
            required: true,
            textOnly : true
        },
        error: '',
        valid: false,
        touched: false,
        label: "Department Name"
    }


    const [controls, setControls] = useState(departmentName);
    const [enableBtn, setEnableBtn] = useState(false)
    const heading = (
        <>Add Department
            <div id={classes.icon_close}>
                <i class="fa fa-window-close" onClick={props.closeModal}></i>
            </div>
        </>)
    const [modal, setModal] = useState();

    const inputChangeHandler = (e) => {
        let message = inputValidity(e.target.value, controls.validation)
        console.log(message)
        const updatedControls = {
            ...controls,
            value: e.target.value,
            error: message,
            valid: message === ''
        }
        setControls(updatedControls)
    }

    useEffect(()=>{
        if(controls.valid){
            setEnableBtn(true)
        }else{
            setEnableBtn(false)
        }
        console.log(controls.value)
    }, [controls])

    const closeModal = (e) => {
        setModal(() => <></>)
    }

    const submithandler = (e) => {
        e.preventDefault();
        let departmentData = {
            departmentName: controls.value
        }
      GENERATE_NEW_DEPARTMENT(departmentData)
            .then(response => {
                console.log(response)
                setModal(() => <Modal message={response.data.message} onClick={props.closeModal} />)
                return response.data;
            }).catch(error => {
                setModal(() => <Modal message={error.data.message} onClick={closeModal} />)
                console.log(error.data)
            })
    }

    const formElement = (<InputElement
        elementType={controls.elementType}
        elementConfig={controls.elementConfig}
        value={controls.value}
        invalid={!controls.valid}
        shouldValidate={controls.validation}
        touched={controls.touched}
        changed={(e) => inputChangeHandler(e)}
        headLabel={controls.label}
        error={controls.error}
    />);

    return (
        <>
            <div className="modal-container">
                {modal}
            </div>
            <div className="reg-container">
                <div style={{ width: '70%' }}>
                    <Form
                        content={formElement}
                        onSubmit={submithandler}
                        enable={enableBtn}
                        heading={heading}
                    />
                </div>
            </div >
        </>
    )
}