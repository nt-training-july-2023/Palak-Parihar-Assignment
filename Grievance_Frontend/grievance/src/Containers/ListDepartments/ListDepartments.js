import { useEffect, useState } from "react"
import { FETCH_ALL_DEPARTMENTS, GENERATE_NEW_DEPARTMENT } from "../../Service/DepartmentService"
import Modal from "../../Components/UI/Modal/Modal"
import NewDepartment from "../NewDepartment/NewDepartment"
import { useNavigate } from "react-router"


export default function ListDepartments(props) {

    const [departments, setDepartments] = useState([])
    const [modal, setModal] = useState()
    const navigate = useNavigate()

    useEffect(() => {

        if(sessionStorage.getItem('userDetails') === null){
            navigate('/logout')
            return
        }else {
            let values = JSON.parse(sessionStorage.getItem('userDetails'))
            console.log(values.firstTimeUser)
            if(values.firstTimeUser){
                navigate('/changePassword')
                return
            }
        }

        FETCH_ALL_DEPARTMENTS()
            .then(res => {
                setDepartments(res.data)
                console.log(res.data)
            })
            .catch(err => {
                setModal(() => <Modal message={err.data.response.data} onClick={closeModal} />)
            })
    }, [modal])

    const closeModal = () => {
        setModal(() => <></>)
    }

    const AddDepartment = () => {
        setModal(() => <Modal component={<NewDepartment closeModal={closeModal} />} />)
    }

    return (
        <>
            {modal}
            <div className="list_heading">
                Departments
            </div>
            <div className="list_main_container">
                <table id="list_content">
                    <tr>
                        <th>Department Id</th>
                        <th>Department Name</th>
                        <th>Actions</th>
                    </tr>
                    {
                        departments.map(d => {
                            return (
                                <>
                                    <tr>
                                        <td>{d.departmentId}</td>
                                        <td>{d.departmentName}</td>
                                        <td>
                                            <i id="icon" class='fas fa-trash-alt'></i>
                                        </td>

                                    </tr>

                                </>)
                        })
                    }

                </table>
            </div>
            <div className="add_entry">
                Add Department  <i id="icon" class='fas fa-plus-circle' onClick={() => AddDepartment()}></i>
            </div>
        </>
    )
}