import { useEffect, useState } from "react"
import { FETCH_ALL_USERS } from "../../Service/EmployeeServices"
import './ListEmployees.css';
import { useNavigate } from "react-router";
import Table from "../../Components/Table/Table";


export default function ListEmployees(props) {

    const [employees, setEmployees] = useState([]);
    const actions = (
        <td>
            <i id="icon" class='fas fa-edit' />
            <i id="icon" class='fas fa-trash-alt'></i>
        </td>
    )

    const headings = ['Full Name', 'Email', 'Department', 'UserType', 'Actions']
    const navigate = useNavigate()
    useEffect(() => {

        if (sessionStorage.getItem('userDetails') === null) {
            navigate('/logout')
            return
        } else {
            let values = JSON.parse(sessionStorage.getItem('userDetails'))
            console.log(values.firstTimeUser)
            if (values.firstTimeUser) {
                navigate('/changePassword')
                return
            }
        }

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
            <div className="list_heading">
                Employees
            </div>
            <div className="list_main_container">
                <Table values={employees} headings={headings} view={actions} />
            </div>
        </>
    )
}