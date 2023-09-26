import classes from './Table.module.css';

export default function Table(props) {

    console.log(props)

    const generateRows = (value) => {
        const row = []
        console.log(value)
        for (let i in value) {
            if (i == 'firstTimeUser') {
                continue;
            }
            row.push(<td>{value[i]}</td>)
        }
        return row;
    }
    return (
        <>
            <div className={classes.table_container}>
                <table className={classes.table}>
                    {/* <tr>
                        {
                            props.headings.map(e => {
                                return <th>{e}</th>
                            })
                        }
                    </tr>
                    {
                        props.values.map((val, index) => (
                            <tr key={index}>
                                {generateRows(value)}
                                {props.viewSelectedTicket && <p onClick={() =>props.viewSelectedTicket(value.ticketId)}>{props.view}</p>}
                                {props.view} 

                            </tr>
                        ))
                    } */}
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
                                    {props.view && <td>
                                        <i id="icon" class='fas fa-edit' onClick={() => props.view(row[props.id])} />
                                    </td>}
                                    {props.delete && <td>
                                        <i id="icon" class='fas fa-trash-alt' onClick={() => props.delete(row[props.id])}></i>
                                    </td>}
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        </>
    )
}