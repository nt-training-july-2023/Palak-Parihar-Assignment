import { Route, Routes } from "react-router";
import Login from "../Containers/Login/Login";
import EmployeeRegistration from "../Containers/EmployeeRegistration/EmployeeRegistration";
import NewTicket from "../Containers/NewTicket/NewTicket";
import Navigation from "../Containers/Navigation/Navigation";
import ListTickets from "../Containers/ListTickets/ListTickets";
import ListEmployees from "../Containers/ListEmployees/ListEmployees";
import ChangePassword from "../Containers/ChangePassword/ChangePassword";
import ListDepartments from "../Containers/ListDepartments/ListDepartments";
import LogOut from "../Containers/LogOut/LogOut";
import { CHANGE_PASSWORD_PATH, LOGIN_PATH, LIST_TICKETS_PATH, NEW_TICKET_PATH, PROFILE_EMPLOYEE_REGISTRATION_PATH, GMS_LIST_DEPARTMENTS_PATH, GMS_LIST_EMPLOYEES_PATH, HOME_PATH, GMS_CHANGE_PASSWORD_PATH } from "../API/PathConstant";

export default function Main(props) {

    return (
        <>
            <Routes>
                <Route exact path={LOGIN_PATH} element={<Login />} />
                <Route exact path={HOME_PATH} element={<Navigation />}>
                    <Route exact path={NEW_TICKET_PATH} element={<NewTicket />} />
                    <Route exact path={LIST_TICKETS_PATH} element={<ListTickets />} />
                    <Route path={GMS_CHANGE_PASSWORD_PATH} element={<ChangePassword />} />
                    <Route path={GMS_LIST_DEPARTMENTS_PATH} element={<ListDepartments />} />
                    <Route path={GMS_LIST_EMPLOYEES_PATH} element={<ListEmployees />} />
                    <Route path={PROFILE_EMPLOYEE_REGISTRATION_PATH} element={<EmployeeRegistration />} />
                </Route>
                <Route path={CHANGE_PASSWORD_PATH} element={<ChangePassword />} />
                <Route path="*" element={<LogOut />} />
            </Routes>
        </>
    )
}