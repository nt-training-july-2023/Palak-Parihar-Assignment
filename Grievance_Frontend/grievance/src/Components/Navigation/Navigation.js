import { useEffect, useState } from 'react';
import classes from './Navigation.module.css';
import { Outlet, useLocation } from 'react-router';
import { CHANGE_PASSWORD_PATH, LOGIN_PATH, LIST_TICKETS_PATH, NEW_TICKET_PATH, PROFILE_EMPLOYEE_REGISTRATION_PATH, GMS_LIST_DEPARTMENTS_PATH, GMS_LIST_EMPLOYEES_PATH, PROFILE_PATH, GMS_CHANGE_PASSWORD_PATH } from '../../API/PathConstant';
import { Link } from 'react-router-dom';

export default function Navigation(props) {

    const [show, setShow] = useState(false);
    const [showMenu, setshowMenu] = useState(false)
    const currentPath = useLocation()
    useEffect(() => {
        console.log(currentPath)
        if (localStorage.getItem('userDetails') !== null) {
            const userDetails = JSON.parse(localStorage.getItem('userDetails'));
            console.log(userDetails.userType)
            if (userDetails.userType === 'ADMIN') {
                setshowMenu(true)
            }
            setShow(true)
        }
    })

    console.log(currentPath)

    if (currentPath.pathname === LOGIN_PATH || currentPath.pathname === CHANGE_PASSWORD_PATH) {
        return <></>
    }

    const menuItem = <>
        <li className={classes.dropdown}>
            <Link to={GMS_LIST_DEPARTMENTS_PATH}>
                <div
                    className={classes.menuItems + ' '
                        + (currentPath.pathname.startsWith(GMS_LIST_DEPARTMENTS_PATH) ? classes.activeMenu : '')}>
                    Departments <i class="fa fa-caret-down"></i>
                </div>
            </Link>
            <ul className={classes.dropdownContent}>
                <li><Link to={GMS_LIST_DEPARTMENTS_PATH}>Departments</Link></li>
                <li><Link to={GMS_LIST_DEPARTMENTS_PATH}>Add Departments</Link></li>
            </ul>
        </li>
        <li className={classes.dropdown}>
            <Link to={GMS_LIST_EMPLOYEES_PATH}>
                <div
                    className={classes.menuItems + ' '
                        + (currentPath.pathname.startsWith(GMS_LIST_EMPLOYEES_PATH) ? classes.activeMenu : '')}>
                    Employees <i class="fa fa-caret-down"></i>
                </div>
            </Link>
            <ul className={classes.dropdownContent}>
                <li><Link to={GMS_LIST_EMPLOYEES_PATH}>All Employees</Link></li>
                <li><Link to={PROFILE_EMPLOYEE_REGISTRATION_PATH}>Add Employee</Link></li>
            </ul>
        </li>
    </>

    const NavContent = (
        <>
            <div className={classes.container}>
                <div>
                    <h1 className={classes.heading}>NucleusTeq</h1>
                </div>
                <div>
                    <ul className={classes.navItems}>
                        {showMenu ? menuItem : null}
                        <li className={classes.dropdown}>
                            <Link to={LIST_TICKETS_PATH}>
                                <div className={currentPath.pathname.startsWith(LIST_TICKETS_PATH) ? classes.activeMenu : ''}>
                                    Tickets <i class="fa fa-caret-down"></i>
                                </div></Link>
                            <ul className={classes.dropdownContent}>
                                <li><Link to={LIST_TICKETS_PATH}>Tickets</Link></li>
                                <li><Link to={NEW_TICKET_PATH}>Raise a Ticket</Link></li>
                            </ul>
                        </li>
                        <li className={classes.dropdown}>
                            <a href="#"><div className={currentPath.pathname.startsWith(GMS_CHANGE_PASSWORD_PATH) ? classes.active : ''}>
                                Profile <i class="fa fa-caret-down"></i></div></a>
                            <ul className={classes.dropdownContent}>
                                <li><Link to={GMS_CHANGE_PASSWORD_PATH}>Change Password</Link></li>
                                <li><Link to="/logout">Logout</Link></li>
                            </ul>
                        </li>
                    </ul>

                </div>
            </div>
        </>
    )

    return (
        <>
            {show ? NavContent : null}
            <Outlet></Outlet>
        </>
    )


}