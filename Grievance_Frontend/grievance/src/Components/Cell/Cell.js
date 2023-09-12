import Button from '../UI/Button/Button';
import classes from './Cell.module.css';


export default function Cell(props) {
    return (
        <>
            <div className={classes.cellContent+' '+props.email}>
                {!props.btn && props.value}
                {props.btn && <Button content={props.value}/>}
            </div>
        </>
    )
}