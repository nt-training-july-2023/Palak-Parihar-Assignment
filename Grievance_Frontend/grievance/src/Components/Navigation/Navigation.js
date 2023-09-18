import { useEffect, useState } from 'react';
import classes from './Navigation.module.css';
import { useLocation } from 'react-router';

export default function Navigation(props) {

    const [show, setShow] = useState(false);

    const location = useLocation();
    const isLoginPage = location.pathname === '/';

    const NavContent = (
        <>
            <div className={classes.container}>
                <div>
                    <h1 className={classes.heading}>NucleusTeq</h1>
                </div>
                <div>
                    <ul className={classes.navItems}>
                        <li className={classes.dropdown}>
                            <a href="#">Tickets <i class="fa fa-caret-down"></i></a>
                            <ul className={classes.dropdownContent}>
                                <li><a href="#">Department <br /> Tickets</a></li>
                                <li><a href="#">My Tickets</a></li>
                                <li><a href="/newTicket">Raise a Ticket</a></li>
                            </ul>
                        </li>
                        <li><a href="/newDepartment">Department</a></li>
                        <li><a href="#">Profile <i class='fas fa-user-alt'></i></a></li>
                    </ul>
                </div>
            </div>
        </>
    )

    if (!isLoginPage) {
        return <>
            {NavContent}
        </>;
    }

    

    return null;


}