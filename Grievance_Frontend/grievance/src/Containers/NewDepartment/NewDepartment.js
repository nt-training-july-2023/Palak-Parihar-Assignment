import { useEffect, useState } from 'react'
import Button from '../../Components/UI/Button/Button'
import classes from './NewDepartment.module.css'
import InputElement from '../../Components/UI/InputElement/InputElement';
import { FETCH_ALL_DEPARTMENTS, GENERATE_NEW_DEPARTMENT } from '../../Service/DepartmentService';
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

    useEffect(() => {
        const departments = FETCH_ALL_DEPARTMENTS()
            .then(response => {
                return response.data;
            }).catch(error => {
                return error.data
            })
        console.log(departments)
    })

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
                return response.data;
            }).catch(error => {
                setModal(() => <Modal message={error.data.response.data} onClick={closeModal} />)
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
                <h3 className={classes.heading}>Generate New Department</h3>
                <div className={classes.outerDiv}>
                    <form onSubmit={submithandler}>
                        {formElement}
                        <Button type='submit' content='submit' />
                    </form>
                </div>
            </div >
        </>
    )
}