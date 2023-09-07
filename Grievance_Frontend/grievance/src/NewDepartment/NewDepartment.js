import { useEffect, useState } from 'react'
import Button from '../UI/Button/Button'
import classes from './NewDepartment.module.css'
import InputElement from '../UI/InputElement/InputElement';
import axios from 'axios';
import { DEPARTMENT_BASE_URL } from '../URL/Url';

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

    useEffect(() => {
        let headersData = {
            email: "ayushi@nucleusteq.com",
            password: "Ayushi#124",
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': '*',
            'Access-Control-Allow-Credentials': true
        }
        axios({
            url: (DEPARTMENT_BASE_URL + 'listDepartments'),
            method: 'GET',
            headers: headersData,
            // withCredentials:true
        }).then(res => {
            console.log(res.data)
        }).catch(e => {
            console.log(e)
        })
    })

    const inputChangeHandler = (e) => {
        const updatedControls = {
            ...controls,
            value: e.target.value
        }
        setControls(updatedControls)
    }

    const submithandler = (e) => {
        e.preventDefault();
        // console.log(controls.value)
        let departmentData = {
            departmentName: controls.value
        }

        let headersData = {
            email: "ayushi@nucleusteq.com",
            password: "Ayushi#124",
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': '*',
            'Access-Control-Allow-Credentials': true
        }
        axios({
            url: DEPARTMENT_BASE_URL + "save",
            method: 'POST',
            data: departmentData,
            headers: headersData
        }).then(res => {
            console.log(res.data);
        })
            .catch(err => {
                console.log(err.response.data);
            });
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