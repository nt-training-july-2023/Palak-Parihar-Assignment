import { Route, Routes } from "react-router";
import Login from "../Containers/Login/Login";
import Dashboard from "../Dashboard/Dashboard";
import EmployeeRegistration from "../Containers/EmployeeRegistration/EmployeeRegistration";
import NewTicket from "../Containers/NewTicket/NewTicket";
import NewDepartment from "../Containers/NewDepartment/NewDepartment";
import Navigation from "../Components/Navigation/Navigation";
import ListTickets from "../Containers/ListTickets/ListTickets";
import ListEmployees from "../Containers/ListEmployees/ListEmployees";
import ChangePassword from "../Containers/ChangePassword/ChangePassword";
import ViewTicket from "../Containers/ViewTicket/ViewTicket";

export default function Main(props) {

    return (
        <>
            <Routes>
                <Route exact path="/" element={<Login />} />
                <Route exact path='/dashboard' element={<Dashboard />} />
                <Route exact path='/registration' element={<EmployeeRegistration />} />
                <Route exact path='/newticket' element={<NewTicket />} />
                <Route exact path='/newdepartment' element={<NewDepartment />} />
                <Route exact path='/navigation' element={<Navigation />} />
                <Route exact path='/listAllTickets' element={<ListTickets />} />
                <Route exact path='/listAllEmployees' element={<ListEmployees />} />
                <Route exact path='/changePassword' element={<ChangePassword />} />
                <Route exact path='/viewTicket' element={<ViewTicket />} />
            </Routes>
        </>
    )
}