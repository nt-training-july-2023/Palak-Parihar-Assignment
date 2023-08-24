import axios from "axios";
import "./Login.css";
import { useEffect } from "react";
import {useHistory, useNavigate} from 'react-router-dom'

export default function Login() {

    let values = {
        email: '',
        password: '',
    }

    let val = {
        name: {}
    }

    const navigate = useNavigate();

    let baseUrl = 'http://localhost:8080/';

    function handleSubmit(e) {
        e.preventDefault();
        console.log(e.target[0].value)
        console.log(e.target[1].value)

        values.email = e.target[0].value;
        values.password = e.target[1].value;

        console.log(values)


        axios({
            url: (baseUrl + 'login'),
            method: 'POST',
            mode: 'CORS',
            data: values
        }).then(res => {
            console.log(res.data);
            navigate("/dashboard")
            // sessionStorage.setItem('user', JSON.stringify(values));
            // <Navigate to = "/"/>
            // return res
        }).catch(e => {
            console.log(e)
        })
        // }

        // console.log("User" + )
        // var a = sessionStorage.getItem('user')
        // val.name = JSON.parse(a)
        // console.log(val)
    }

    useEffect(() => {


        axios({
            url: (baseUrl + 'list'),
            method: 'GET',
            mode: 'CORS'
        }).then(res => {
            console.log(res.data)
        }).catch(e => {
            console.log(e)
        })
    })

    return (
        <>
            <div className="main_body">
                <h3>Login!</h3>
                <form onSubmit={e => handleSubmit(e)}>
                    <label>User Name</label>
                    <input type="text" id="fname" name="username" placeholder="User name" />

                    <label>Last Name</label>
                    <input type="password" id="lname" name="password" placeholder="Password" />


                    <input type="submit" value="Submit" />
                </form>
            </div>

        </>
    )
}