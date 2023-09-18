import { useEffect, useState } from "react"
import { FETCH_ALL_USERS } from "../../Service/EmployeeServices"
import './ListEmployees.css';


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
        
            <div className="list_main_container">
                <table id="list_content">
                    <tr>
                        <th>FullName</th>
                        <th>email</th>
                        <th>department</th>
                        <th>userType</th>
                        <th>Actions</th>
                    </tr>
                    {
                        employees.map(e => {
                            return (
                                <>
                                    <tr>
                                        <td>{e.fullName}</td>
                                        <td>{e.email}</td>
                                        <td>{e.department}</td>
                                        <td>{e.userType}</td>
                                        <td>
                                            <i id="icon" class='fas fa-edit' />
                                            <i id="icon" class='fas fa-trash-alt'></i>
                                        </td>
                                    </tr>

                                </>)
                        })
                    }

                </table>

            </div>
        </>
    )
}