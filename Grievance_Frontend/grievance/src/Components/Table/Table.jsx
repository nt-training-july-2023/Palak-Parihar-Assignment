import classes from './Table.module.css';

export default function Table(props) {

    const noDataFound = (
        <>
            <img className={classes.noData} src='/NotFound.png' alt='Not Found' />
            <h2 style={{ textAlign: 'center' }}>No Data Found, Please go back</h2>
        </>
    )

    const tableData = (
        <>
            <p className={classes.heading}><u>{props.heading}</u></p>

            <div className={classes.table_container}>
                <table className={classes.table}>
                    <thead>
                        {
                            props.headings.map((column, index) => {
                                return <th key={index}>{column}</th>
                            })
                        }
                    </thead>
                    <tbody>
                        {
                            props.values.map((row, rowIndex) => (
                                <tr key={rowIndex}>
                                    {

                                        props.columns.map((column, colIndex) => (
                                            <td key={colIndex}>{row[column]}</td>
                                        ))
                                    }
                                    {console.log(row[props.id])}
                                    {props.view && <td>
                                        <i id={classes.icon} class='fas fa-edit' onClick={() => props.view(row[props.id])} />
                                    </td>}
                                    {props.delete && <td>
                                        <i id={classes.icon} class='fas fa-trash-alt' onClick={() => props.delete(row)}></i>
                                    </td>}
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </>
    )
    return (
        <>
            <div>
                {props.values.length !== 0 ? tableData : noDataFound}
                <div id={classes.actions_arrow}>
                    {props.disablePrevious ?
                        <i id={classes.disable_action_icon} class='fas fa-arrow-alt-circle-left'></i>
                        : <i id={classes.action_icon} class='fas fa-arrow-alt-circle-left' onClick={props.previousPage}></i>}

                    {props.disableNext ? <i id={classes.disable_action_icon} class='fas fa-arrow-alt-circle-right'></i> :
                        <i id={classes.action_icon} class='fas fa-arrow-alt-circle-right' onClick={props.nextPage}></i>}
                </div>
            </div>
        </>
    )
}