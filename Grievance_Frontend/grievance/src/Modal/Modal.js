import Button from '../Button/Button';
import './Modal.css';

export default function Modal(props) {
    return (
        <>
            <div className='outer-div'>
                <div className='content'>
                    <p>{props.message}</p>
                    <div className='close'>
                        <Button type='button' content = 'close' onClick={props.onClick}/>
                    </div>
                </div>
            </div>
        </>
    )
}