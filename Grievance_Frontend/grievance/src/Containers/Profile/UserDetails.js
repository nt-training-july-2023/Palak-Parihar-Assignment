import { useEffect } from "react"


export default function UserDetails() {

    useEffect(() => {
        console.log(localStorage.getItem('userDetails'))
    })

    return (
        <>
            UserDetails
        </>
    )
}