import { Outlet, useNavigate } from 'react-router';
import classes from './Profile.module.css';
import { Link, NavLink } from 'react-router-dom';
import { useEffect, useState } from 'react';

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
                navigate('/changePassword')
                return
            }
        }

        let values = JSON.parse(localStorage.getItem('userDetails'));
        setIsAdmin(() => {
            return values.userType === 'ADMIN'
        })
    }, [])

    const menuItem = <>
        <Link to='/profile/departments'>
            <div className={classes.menuItems + ' ' + (currentPath === "/profile/departments" ? classes.active : '')}>
                Departments

            </div>
        </Link>
        <Link to='/profile/employees'>
            <div className={classes.menuItems + ' ' + (currentPath === "/profile/employees" ? classes.active : '')}>
                Employees
            </div>
        </Link>
        <Link to='/profile/employeeRegistration'>
            <div className={classes.menuItems + ' ' + (currentPath === "/profile/employeeRegistration" ? classes.active : '')}>
                Add New Employee
            </div>
        </Link>
    </>
    return (
        <>
            <div className={classes.outerDiv}>
                <div className={classes.sideBar}>

                    {isAdmin && menuItem}
                    <Link to='/profile/changePassword'>
                        <div className={classes.menuItems + ' ' + (currentPath === "/profile/changePassword" ? classes.active : '')}>
                            Change Password
                        </div>
                    </Link>
                    <Link to='/logout'>
                        <div className={classes.menuItems + ' ' + (currentPath === "/logout" ? classes.active : '')}>
                            Logout
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