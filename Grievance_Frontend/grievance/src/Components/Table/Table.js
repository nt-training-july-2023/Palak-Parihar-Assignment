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
                    <tr>
                        {
                            props.headings.map(e => {
                                return <th>{e}</th>
                            })
                        }
                    </tr>
                    {
                        props.values.map(value => (
                            <tr>
                                {generateRows(value)}
                                {props.viewSelectedTicket && <p onClick={() =>props.viewSelectedTicket(value.ticketId)}>{props.view}</p>}
                                {props.view}
                            </tr>
                        ))
                    }
                </table>
            </div>
        </>
    )
}