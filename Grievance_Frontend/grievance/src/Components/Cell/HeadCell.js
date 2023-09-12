import classes from './Cell.module.css';


export default function HeadCell(props) {
    return (
        <>
            <div className={classes.headCellContent}>
                {props.value}
            </div>
        </>
    )
}