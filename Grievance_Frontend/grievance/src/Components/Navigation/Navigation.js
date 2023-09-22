import { useEffect, useState } from 'react';
import classes from './Navigation.module.css';
import { useLocation } from 'react-router';

export default function Navigation(props) {

    const [show, setShow] = useState(false);

    const location = useLocation();

    useEffect(() => {
        if (sessionStorage.getItem('userDetails') !== null) {
            setShow(true)
        }
    })

    if(location.pathname === '/' || location.pathname === '/changePassword'){
        return <></>
    }

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
                                <li><a href="/tickets">Tickets</a></li>
                                <li><a href="/newTicket">Raise a Ticket</a></li>
                            </ul>
                        </li>
                        <li className={classes.dropdown}>
                            <a href="#">Profile <i class='fas fa-user-alt'></i></a>
                            <ul className={classes.dropdownContent}>
                                <li><a href="/profile">My Profile</a></li>
                                <li><a href="/logout">Log out</a></li>
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
        </>
    )


}