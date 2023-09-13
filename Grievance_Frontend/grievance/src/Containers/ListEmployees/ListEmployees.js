import { useEffect, useState } from "react"
import { FETCH_ALL_USERS } from "../../Service/EmployeeServices"
import classes from '../ListTickets/ListTickets.module.css'
import HeadCell from "../../Components/Cell/HeadCell"
import Cell from "../../Components/Cell/Cell"
import Modal from "../../Components/UI/Modal/Modal"
import Button from "../../Components/UI/Button/Button"


export default function ListEmployees(props) {

    const [employees, setEmployees] = useState([]);
    useEffect(() => {
        FETCH_ALL_USERS()
            .then(res => {
                console.log(res.data)
                setEmployees(res.data)
            })
            .catch(err => {
                console.log(err.data)
            })
    }, [])

    return (
        <>
            <div className={classes.mainContainer}>

                <div className={classes.rowContent}>
                    <HeadCell value="Email" />
                    <HeadCell value="Full Name" />
                    <HeadCell value="Department" />
                    <HeadCell value="Role" />
                    <HeadCell value="Action" />
                </div>

                {employees.map(e => (

                    <div className={classes.rowContent}>
                        <Cell email="emailContent" value={e.email} />
                        <Cell value={e.fullName} />
                        {console.log(e)}
                        <Cell value={e.department} />
                        <Cell value={e.userType} />
                        <div className={classes.action}>
                            {/* <i class="fa-duotone fa-pen-to-square fa-lg"></i> */}
                            {/* <i class="fa fa-angle-down"></i> */}
                            {/* <i class="fa fa-car fa-lg"></i> */}
                            {/* <i class="fa fa-caret-up"></i> */}
                            {/* <i class="fa fa-caret-down"></i> */}
                            {/* <i class="fa fa-pen"></i> */}
                            {/* <i class="fa-duotone fa-pen-to-square"></i> */}
                            <div className={classes.icon}>
                                <i class='fas fa-edit' ></i>
                            </div>
                            <div className={classes.icon}>
                                <i class='fas fa-trash-alt'></i>
                            </div>
                            
                        </div>

                    </div>

                ))}
            </div >
        </>
    )
}