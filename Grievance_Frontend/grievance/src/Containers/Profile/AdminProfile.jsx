import { Outlet, useNavigate } from 'react-router';
import classes from './Profile.module.css';
import { Link} from 'react-router-dom';
import { useEffect, useState } from 'react';
import { CHANGE_PASSWORD_PATH, PROFILE_EMPLOYEE_REGISTRATION_PATH, GMS_LIST_DEPARTMENTS_PATH, GMS_LIST_EMPLOYEES_PATH } from '../../API/PathConstant';

export default function AdminProfile(props) {

    const navigate = useNavigate()
    const [isAdmin, setIsAdmin] = useState()
    const currentPath = window.location.pathname

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

        let values = JSON.parse(localStorage.getItem('userDetails'));
        setIsAdmin(() => {
            return values.userType === 'ADMIN'
        })
    }, [])

    const menuItem = <>
        <Link to={GMS_LIST_DEPARTMENTS_PATH}>
            <div className={classes.menuItems + ' ' + (currentPath === GMS_LIST_DEPARTMENTS_PATH ? classes.active : '')}>
                Departments

            </div>
        </Link>
        <Link to={GMS_LIST_EMPLOYEES_PATH}>
            <div className={classes.menuItems + ' ' + (currentPath === GMS_LIST_EMPLOYEES_PATH ? classes.active : '')}>
                Employees
            </div>
        </Link>
        <Link to={PROFILE_EMPLOYEE_REGISTRATION_PATH}>
            <div className={classes.menuItems + ' ' + (currentPath === PROFILE_EMPLOYEE_REGISTRATION_PATH ? classes.active : '')}>
                Add New Employee
            </div>
        </Link>
    </>
    return (
        <>
            <div className={classes.outerDiv}>
                <div className={classes.sideBar}>

                    {isAdmin && menuItem}
                    <Link to={GMS_LIST_DEPARTMENTS_PATH}>
                        <div className={classes.menuItems + ' ' + (currentPath === GMS_LIST_DEPARTMENTS_PATH ? classes.active : '')}>
                            Change Password
                        </div>
                    </Link>
                </div>
                <div className={classes.mainContainer}>
                    <Outlet >
                    </Outlet>
                </div>
            </div>
        </>
    )
}