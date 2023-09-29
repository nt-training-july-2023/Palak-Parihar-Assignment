import { useEffect, useState } from "react"
import { DELETE_EMPLOYEE, FETCH_ALL_USERS } from "../../Service/EmployeeServices"
import './ListEmployees.css';
import { useNavigate } from "react-router";
import Table from "../../Components/Table/Table";
import { headers } from "../../API/Headers";
import Modal from "../../Components/UI/Modal/Modal";
import ConfirmationDialog from "../../Components/Confirmation/ConfirmationDialog";

export default function ListEmployees(props) {

    const [employees, setEmployees] = useState([]);

    const headings = ['Full Name', 'Email', 'Department', 'UserType', 'Actions']
    const columns = ["fullName", "email", "department", "userType"]
    const [modal, setModal] = useState()
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
                if (res.data.length < 10) {
                    setDisableNext(true)
                } else {
                    setDisableNext(false)
                }
                console.log(res.data)
                setEmployees(res.data)
            })
            .catch(err => {
                console.log(err.data)
            })
    }, [modal])

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

    const deleteEmployeeHandler = (employee) => {
        console.log(employee.email === headers().email)
        if (employee.email === headers().email) {
            setModal(<Modal message="User can not delete itself" onClick={closeModal} />)
            return
        }
        let params = {
            enable: true,
            content: 'Delete Employee',
            delete: () => deleteEmployee(employee.email),
            close: () => closeModal()
        }
        setModal(<Modal component={ConfirmationDialog(params)} />)
        // DELETE_EMPLOYEE(empId.email)
        //     .then(res => {
        //         console.log(res.status == 204)
        //         setModal(<Modal message="Employee Successfully deleted" onClick={closeModal}/>)
        //     }).catch(err => {
        //         console.log(err.data.message)
        //         setModal(<Modal message={err.data.message} onClick={closeModal}/>)
        //     })
    }

    const deleteEmployee = (empId) => {
        DELETE_EMPLOYEE(empId)
            .then(res => {
                setModal(<Modal message="Employee Successfully deleted" onClick={closeModal} />)
            }).catch(err => {
                console.log(err.data.message)
                setModal(<Modal message={err.data.message} onClick={closeModal} />)
            })
    }

    const closeModal = () => {
        setModal(<></>)
    }

    return (
        <>
            {modal}
            <div className="list_main_container">
                <Table values={employees}
                    headings={headings}
                    delete={deleteEmployeeHandler}
                    columns={columns}
                    heading=" Employees "
                    id="email"
                    previousPage={previousPage}
                    nextPage={nextPage}
                    disablePrevious={disablePrevious}
                    disableNext={disableNext} />
            </div>

        </>
    )
}