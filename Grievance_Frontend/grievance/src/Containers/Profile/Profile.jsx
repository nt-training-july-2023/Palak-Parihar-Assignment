import { headers } from "../../API/Headers";
import classes from './Profile.module.css';

export default function Profile() {

    const userDetails = headers();

    const detailsArray = [];
    for (let key in userDetails) {
        if (key === 'firstTimeUser' || key === 'isLoggedIn' || key === 'password') continue;
        detailsArray.push({
            key: key,
            label: key.toUpperCase(),
            value: userDetails[key]
        })
    }
    return (
        <>
            <div className={classes.container}>
                <div className={classes.content}>
                    <div className={classes.profile}>
                        <img src='/profile.jpg' alt="profile" />
                    </div>
                    <div className={classes.details}>
                        {detailsArray.map(val => {
                            return (
                                <div className={classes.row} key={val.key}>
                                    <label>{val.label}</label>
                                    <p>{val.value}</p>
                                </div>)
                        })}
                    </div>
                </div>
            </div>
        </>
    )
}