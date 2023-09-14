import Button from '../Button/Button';
import classes from './Modal.module.css'

export default function Modal({ component, message, onClick }) {
    console.log(component)
    return (
        <>
            <div className={classes.outerDiv}>
                {onClick ?
                    <div className={classes.content}>
                        <p>{message}</p>

                        <div className={classes.close}>
                            {onClick && <Button type='button' content='close' onClick={onClick} />}
                        </div>
                    </div> : component
                }

            </div>
        </>
    )
}