import { useState } from 'react'
import Button from '../UI/Button/Button'
import classes from './NewDepartment.module.css'
import InputElement from '../UI/InputElement/InputElement';

export default function NewDepartment(props) {

    // const[departmentName, setDepartmentName] = useState();
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

    const inputChangeHandler = (e) => {
        const updatedControls = {
            ...controls,
            value: e.target.value
        }
        setControls(updatedControls)
    }

    const submithandler = (e) => {
        e.preventDefault();
        console.log(controls.value)
    }

    return (
        <>
            <div className="reg-container">
                <h3 className={classes.heading}>Generate New Department</h3>
                <div className={classes.outerDiv}>
                        <form onSubmit={submithandler}>

                            <InputElement
                                elementType={controls.elementType}
                                elementConfig={controls.elementConfig}
                                value={controls.value}
                                invalid={!controls.valid}
                                shouldValidate={controls.validation}
                                touched={controls.touched}
                                changed={(e) => inputChangeHandler(e)}
                            />
                            <Button type='submit' content='submit' />
                        </form>
                </div>
            </div >
        </>
    )
}