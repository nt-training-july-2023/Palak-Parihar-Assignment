import { useEffect, useState } from "react"
import { DELETE_EMPLOYEE, FETCH_ALL_USERS } from "../../Service/EmployeeServices"
import './ListEmployees.css';
import { useNavigate } from "react-router";
import Table from "../../Components/Table/Table";
import { DELETE_DEPARTMENT } from "../../Service/DepartmentService";


export default function ListEmployees(props) {

    const [employees, setEmployees] = useState([]);
    const actions = (
        <td>
            <i id="icon" class='fas fa-edit' />
            <i id="icon" class='fas fa-trash-alt'></i>
        </td>
    )

    const headings = ['Full Name', 'Email', 'Department', 'UserType', 'Actions']
    const columns = ["fullName", "email", "department", "userType"]
    const [disablePrevious, setDisablePrevious] = useState(true)
    const [disableNext, setDisableNext] = useState(true)
    const [page, setPage] = useState(0);
    const navigate = useNavigate()
    useEffect(() => {

        if (localStorage.getItem('userDetails') === null) {
            navigate('/logout')
            return
        } else {
            let values = JSON.parse(localStorage.getItem('userDetails'))
            console.log(values.firstTimeUser)
            if (values.firstTimeUser) {
                navigate('/changePassword')
                return
            }
        }

        FETCH_ALL_USERS(page)
            .then(res => {
                console.log(res.data)
                setEmployees(res.data)
            })
            .catch(err => {
                console.log(err.data)
            })
    }, [])

    const previousPage = () => {
        if (page === 0) {
            setDisablePrevious(true)
            return
        }
        setPage((page) => page - 1)
    }

    const nextPage = () => {
        setPage(page => page + 1)
        setDisablePrevious(false)
    }

    const deleteEmployee = (empId) => {
        DELETE_EMPLOYEE(empId)
            .then(res => {
                console.log(res)
            }).catch(err => {
                console.log(err)
            })
    }

    return (
        <>
            <div className="list_heading">
                Employees
            </div>
            <div className="list_main_container">
                <Table values={employees}
                    headings={headings}
                    delete={deleteEmployee}
                    columns={columns}
                    id="email" />
            </div>
            <div id="actions_arrow">
                {disablePrevious ?
                    <i id="disable_action_icon" class='fas fa-arrow-alt-circle-left'></i>
                    : <i id="action_icon" class='fas fa-arrow-alt-circle-left' onClick={previousPage}></i>}

                {disableNext ? <i id="disable_action_icon" class='fas fa-arrow-alt-circle-right'></i> :
                    <i id="action_icon" class='fas fa-arrow-alt-circle-right' onClick={nextPage}></i>}
            </div>
        </>
    )
}