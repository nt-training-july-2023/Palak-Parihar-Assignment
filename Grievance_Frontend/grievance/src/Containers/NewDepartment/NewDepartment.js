import { useState } from 'react'
import Button from '../../Components/UI/Button/Button'
import classes from './NewDepartment.module.css'
import InputElement from '../../Components/UI/InputElement/InputElement';
import {GENERATE_NEW_DEPARTMENT } from '../../Service/DepartmentService';
import Modal from '../../Components/UI/Modal/Modal';

export default function NewDepartment(props) {

    const departmentName = {
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
        label: "Department Name"
    }


    const [controls, setControls] = useState(departmentName);
    const [modal, setModal] = useState();

    const inputChangeHandler = (e) => {
        const updatedControls = {
            ...controls,
            value: e.target.value
        }
        setControls(updatedControls)
    }

    const closeModal = (e) => {
        setModal(() => <></>)
    }

    const submithandler = (e) => {
        e.preventDefault();
        let departmentData = {
            departmentName: controls.value
        }
        const savedDepartment = GENERATE_NEW_DEPARTMENT(departmentData)
            .then(response => {
                console.log(response)
                setModal(() => <Modal message="Department successfully created" onClick={closeModal} />)
                return response.data;
            }).catch(error => {
                setModal(() => <Modal message={error.data.response.data.message} onClick={closeModal} />)
                console.log(error.data.response.data)
            })
        console.log(savedDepartment)
    }

    const formElement = (<InputElement
        elementType={controls.elementType}
        elementConfig={controls.elementConfig}
        value={controls.value}
        invalid={!controls.valid}
        shouldValidate={controls.validation}
        touched={controls.touched}
        changed={(e) => inputChangeHandler(e)}
    />);

    return (
        <>
            <div className="modal-container">
                {modal}
            </div>
            <div className="reg-container">
                <div className={classes.heading}>
                    Generate New Department
                    <div id={classes.icon_close}>
                        <i class="fa fa-window-close" onClick={props.closeModal}></i>
                    </div>
                </div>
                <div className={classes.outerDiv}>
                    <form onSubmit={submithandler}>
                        {formElement}
                        <Button type='submit' content='submit' enable={true}/>
                    </form>
                </div>
            </div >
        </>
    )
}