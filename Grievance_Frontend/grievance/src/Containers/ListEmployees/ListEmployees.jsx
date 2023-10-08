import { useEffect, useState } from "react"
import classes from './ListEmployees.module.css';
import { DELETE_EMPLOYEE, FETCH_ALL_USERS } from "../../Service/EmployeeServices"
import { useNavigate } from "react-router";
import Table from "../../Components/Table/Table";
import Modal from "../../Components/UI/Modal/Modal";
import ConfirmationDialog from "../../Components/Confirmation/ConfirmationDialog";
import { CHANGE_PASSWORD_PATH } from "../../API/PathConstant";

export default function ListEmployees() {

    const [employees, setEmployees] = useState([]);
    const [flag, setFlag] = useState(false)
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
            if (values.firstTimeUser) {
                navigate(CHANGE_PASSWORD_PATH)
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
                setEmployees(res.data)
            })
            .catch(err => {
                console.log(err.data)
            })
    }, [page, navigate, flag])

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
        let params = {
            enable: true,
            content: 'Delete Employee',
            delete: () => deleteEmployee(employee.employeeId),
            close: () => closeModal()
        }
        setModal(<Modal component={ConfirmationDialog(params)} />)
    }

    const deleteEmployee = (empId) => {
        DELETE_EMPLOYEE(empId)
            .then(res => {
                setFlag(!flag)
                setModal(<Modal message={res.data.message} onClick={closeModal} />)
            }).catch(err => {
                setModal(<Modal message={err.data.message} onClick={closeModal} />)
            })
    }

    const closeModal = () => {
        setModal(<></>)
    }

    return (
        <>
            {modal}
            <div className={classes.outerDiv}>
                <div style={{ width: '70%' }}>
                    <Table values={employees}
                        headings={headings}
                        delete={deleteEmployeeHandler}
                        columns={columns}
                        heading=" Employees "
                        id="employeeId"
                        previousPage={previousPage}
                        nextPage={nextPage}
                        disablePrevious={disablePrevious}
                        disableNext={disableNext} />
                </div>
            </div>

        </>
    )
}