import { useEffect } from "react"


export default function UserDetails() {

    useEffect(() => {
        console.log(sessionStorage.getItem('userDetails'))
    })

    return (
        <>
            UserDetails
        </>
    )
}