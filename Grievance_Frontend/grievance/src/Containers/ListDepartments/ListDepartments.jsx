import { useEffect, useState } from "react"
import { DELETE_DEPARTMENT, FETCH_ALL_DEPARTMENTS } from "../../Service/DepartmentService"
import Modal from "../../Components/UI/Modal/Modal"
import NewDepartment from "../NewDepartment/NewDepartment"
import { useNavigate } from "react-router"
import Table from "../../Components/Table/Table"
import classes from './ListDepartment.module.css';
import { CHANGE_PASSWORD_PATH } from "../../API/PathConstant"
import { headers } from "../../API/Headers"
import ConfirmationDialog from "../../Components/Confirmation/ConfirmationDialog"


export default function ListDepartments(props) {

    const [departments, setDepartments] = useState([])
    const [modal, setModal] = useState()
    const headings = ["Department Id", " Department Name", "Actions"]
    const columns = ["departmentId", "departmentName"]
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
                navigate(CHANGE_PASSWORD_PATH)
                return
            }
        }

        if(page === 0){
            setDisablePrevious(true)
        }

        FETCH_ALL_DEPARTMENTS(page)
            .then(res => {
                if (res.data.length < 10) {
                    setDisableNext(true)
                } else {
                    setDisableNext(false)
                }
                setDepartments(res.data)
                console.log(res.data)
            })
            .catch(err => {
                setModal(() => <Modal message={err.data.response.data} onClick={closeModal} />)
            })
    }, [modal, page, navigate])

    const closeModal = () => {
        setModal(() => <></>)
    }

    const AddDepartment = () => {
        setModal(() => <Modal component={<NewDepartment closeModal={closeModal} />} />)
    }

    const previousPage = () => {
        if(page > 0){
            setPage((page) => page-1)
        }
    }

    const nextPage = () => {
        setPage(page => page + 1)
        setDisablePrevious(false)
    }

    const deleteDepartmentHandler = (department) => {
        if (department.departmentName === headers().department) {
            setModal(<Modal message="You can not delete your department " onClick={closeModal} />)
            return
        }
        let params = {
            enable: true,
            content: 'Delete Department',
            delete: () => deleteDepartment(department.departmentId),
            close: () => closeModal()
        }
        setModal(<Modal component={ConfirmationDialog(params)} />)
    }

    const deleteDepartment = (deptId) => {
        console.log(deptId)
        DELETE_DEPARTMENT(deptId)
            .then(res => {
                console.log(res)
                setModal(<Modal message="Department deleted successfully" onClick={closeModal} />)
            }).catch(err => {
                setModal(<Modal message={err.data.response.data} onClick={closeModal} />)
            })
    }


    return (
        <>
            {modal}
            <div>
                <div className={classes.add_entry}>
                    Add Department  <i id="icon" class='fas fa-plus-circle' onClick={() => AddDepartment()}></i>
                </div>
                <div className="list_main_container">
                    <Table
                        headings={headings}
                        values={departments}
                        columns={columns}
                        delete={deleteDepartmentHandler}
                        id="departmentId"
                        previousPage={previousPage}
                        nextPage={nextPage}
                        disablePrevious={disablePrevious}
                        disableNext={disableNext} />
                </div>
            </div>
        </>
    )
}