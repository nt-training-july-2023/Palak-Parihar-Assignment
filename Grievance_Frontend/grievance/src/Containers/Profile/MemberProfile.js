import { Outlet, Route, Routes } from 'react-router';
import classes from './Profile.module.css';
import ListDepartments from '../ListDepartments/ListDepartments';
import { Link } from 'react-router-dom';

export default function MemberProfile(props) {

    return (
        <>
            <div className={classes.outerDiv}>
                <div className={classes.sideBar}>
                    <Link to='/admin/departments'>
                        <div className={classes.menuItems}>
                            Departments
                            <i id={classes.icon} class='fas fa-caret-right'></i>
                        </div>
                    </Link>
                    <Link to='/admin/employees'>
                        <div className={classes.menuItems}>
                            Employees <i id={classes.icon} class='fas fa-caret-right'></i>
                        </div>
                    </Link>
                    <Link to='/admin/employeeRegistration'>
                        <div className={classes.menuItems}>
                            Add New Employee <i id={classes.icon} class='fas fa-caret-right'></i>
                        </div>
                    </Link>
                    <Link to='/logout'>
                        <div className={classes.menuItems}>
                            Logout <i id={classes.icon} class='fas fa-caret-right'></i>
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