import classes from './Navigation.module.css';

export default function Navigation(props) {

    const NavContent = (
        <>
            <div className={classes.container}>
                <div>
                    <h1 className={classes.heading}>Nucleusteq</h1>
                </div>
                <div>
                    <ul className={classes.navItems}>
                        <li><a href="newTicket">Tickets</a></li>
                        <li><a href="#">My Profile</a></li>
                        <li><a href="/newDepartment">Department</a></li>
                    </ul>
                </div>
            </div>
        </>
    )


    return (
        <>
           {NavContent}
        </>
    )
}