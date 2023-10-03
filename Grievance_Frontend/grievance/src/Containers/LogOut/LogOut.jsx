import { useEffect } from "react";
import Spinner from "../../Components/UI/Spinner/Spinner";
import { useNavigate } from "react-router";
import Modal from "../../Components/UI/Modal/Modal";


export default function LogOut() {

    const navigate = useNavigate();

    useEffect(() => {
        setTimeout(() => {
            localStorage.clear();
            navigate("/")
        }, 1000)
    })

    return (
        <>
            <Modal component={<Spinner />} />
        </>
    )
}