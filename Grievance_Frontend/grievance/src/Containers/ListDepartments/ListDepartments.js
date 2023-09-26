import { useEffect, useState } from "react"
import { FETCH_ALL_DEPARTMENTS, GENERATE_NEW_DEPARTMENT } from "../../Service/DepartmentService"
import Modal from "../../Components/UI/Modal/Modal"
import NewDepartment from "../NewDepartment/NewDepartment"
import { useNavigate } from "react-router"
import Table from "../../Components/Table/Table"
import classes from './ListDepartment.module.css';


export default function ListDepartments(props) {

    const [departments, setDepartments] = useState([])
    const [modal, setModal] = useState()
    const headings = ["Department Id", " Department Name", "Actions"]
    const actions = (
        <td>
            <i id="icon" class='fas fa-trash-alt'></i>
        </td>
    )
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
            <div className={classes.add_entry}>
                Add Department  <i id="icon" class='fas fa-plus-circle' onClick={() => AddDepartment()}></i>
            </div>
            <div className="list_main_container">
                <Table headings={headings} values={departments} view={actions} />
            </div>
        </>
    )
}