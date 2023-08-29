import './Button.css';

export default function Button(props) {
    return (
        <>
            <input type={props.type} value={props.content} onClick={props.onClick}/>
        </>
    )
}