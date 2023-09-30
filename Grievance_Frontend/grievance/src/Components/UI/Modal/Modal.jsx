import Button from '../Button/Button';
import classes from './Modal.module.css'

export default function Modal({ component, message, onClick }) {
    return (
        <>
            <div className={classes.outerDiv}>
                {onClick ?
                    <div className={classes.content}>
                        <h3 style={{margin:'3px', marginBottom:'7px'}}>{message}</h3>

                        <div className={classes.close}>
                            {onClick && <Button type='button' content='Close' onClick={onClick} enable={true} />}
                        </div>
                    </div> : component
                }

            </div>
        </>
    )
}