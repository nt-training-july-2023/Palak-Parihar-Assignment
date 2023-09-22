import { Route, Routes } from "react-router";
import Login from "../Containers/Login/Login";
import EmployeeRegistration from "../Containers/EmployeeRegistration/EmployeeRegistration";
import NewTicket from "../Containers/NewTicket/NewTicket";
import NewDepartment from "../Containers/NewDepartment/NewDepartment";
import Navigation from "../Components/Navigation/Navigation";
import ListTickets from "../Containers/ListTickets/ListTickets";
import ListEmployees from "../Containers/ListEmployees/ListEmployees";
import ChangePassword from "../Containers/ChangePassword/ChangePassword";
import ViewTicket from "../Containers/ViewTicket/ViewTicket";
import ListDepartments from "../Containers/ListDepartments/ListDepartments";
import AdminProfile from "../Containers/Profile/AdminProfile";
import UserDetails from "../Containers/Profile/UserDetails";
import LogOut from "../Containers/LogOut/LogOut";

export default function Main(props) {
    
    return (
        <>
            <Routes>
                <Route path="/" element={<Login user={props.user} setUser={props.setUser} />} />
                <Route exact path='/newticket' element={<NewTicket />} />
                <Route exact path='/newdepartment' element={<NewDepartment />} />
                <Route exact path='/navigation' element={<Navigation />} />
                <Route exact path='/tickets' element={<ListTickets />} />
                <Route exact path='/viewTicket' element={<ViewTicket />} />
                <Route path='/changePassword' element={<ChangePassword />} />
                <Route exact path="/profile" element={<AdminProfile />} >
                    <Route index element={<UserDetails />} />
                    <Route path="/profile/departments" element={<ListDepartments />} />
                    <Route path='/profile/employees' element={<ListEmployees />} />
                    <Route path='/profile/employeeRegistration' element={<EmployeeRegistration />} />
                    <Route path='/profile/changePassword' element={<ChangePassword />} />
                </Route>
                <Route path="*" element={<LogOut />} />
            </Routes>
        </>
    )
}