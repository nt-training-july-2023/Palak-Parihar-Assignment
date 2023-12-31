import classes from './Table.module.css';

export default function Table(props) {

    const noDataFound = (
        <>
            <div className={classes.noDataDiv}>
                <img className={classes.noData} src='/NotFound.png' alt='Not Found' />
            </div>
            <h2 style={{ textAlign: 'center' }}>No Data Found, Please go back</h2>
        </>
    )

    const tableData = (
        <div>
            <p className={classes.heading}><u>{props.heading}</u></p>

            <div className={classes.table_container}>
                <table className={classes.table}>
                    <thead>
                        <tr>
                            {
                                props.headings.map((column, index) => {
                                    return <th key={index}>{column}</th>
                                })
                            }
                        </tr>
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
                                    {props.view && <td>
                                        <i id={classes.icon} className='fas fa-edit' onClick={() => props.view(row[props.id])} />
                                    </td>}
                                    {props.delete && <td>
                                        <i id={classes.icon} className='fas fa-trash-alt' onClick={() => props.delete(row)}></i>
                                    </td>}
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
    return (
        <>
            <div>
                {props.values.length !== 0 ? tableData : noDataFound}
                <div id={classes.actions_arrow}>
                    {props.disablePrevious ?
                        <i id={classes.disable_action_icon} className='fas fa-arrow-alt-circle-left'></i>
                        : <i id={classes.action_icon} className='fas fa-arrow-alt-circle-left' onClick={props.previousPage}></i>}

                    {props.disableNext ? <i id={classes.disable_action_icon} className='fas fa-arrow-alt-circle-right'></i> :
                        <i id={classes.action_icon} className='fas fa-arrow-alt-circle-right' onClick={props.nextPage}></i>}
                </div>
            </div>
        </>
    )
}